package org.ontoware.rdfreactor.runtime;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.ontoware.aifbcommons.collection.ClosableIterator;
import org.ontoware.rdf2go.exception.ModelRuntimeException;
import org.ontoware.rdf2go.model.Model;
import org.ontoware.rdf2go.model.QueryRow;
import org.ontoware.rdf2go.model.Statement;
import org.ontoware.rdf2go.model.node.Node;
import org.ontoware.rdf2go.model.node.Resource;
import org.ontoware.rdf2go.model.node.URI;
import org.ontoware.rdf2go.model.node.Variable;
import org.ontoware.rdf2go.vocabulary.RDF;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <b>ReactorBaseImpl</b> is the base class for instances of classes from the
 * RDFS/OWL schema generated by the code generator. <br>
 * 
 * If a class inheriting from ReactorBaseImpl is instantiated, it represents an
 * instance of a class from an RDFS/OWL schema. The properties of this class
 * instance are stored in an RDF model using RDF2Go (via the static Bridge
 * object) as triples of the form: (this.instanceIdentifier, key, value). <br>
 * 
 * Besides methods for putting, getting and removing properties, the Map<URI,
 * Object> Interface is also supported for handling of all properties of this
 * instance in one Map, backed by the actual RDF2Go model.
 * 
 * <br>
 * RDF Reactor uses the following naming:
 * 
 * <b>resource</b> - instance of an RDF schema class, identified by the
 * resource ID (an URI or BlankNode), almost all statements about the resource
 * use the resource ID as the object
 * 
 * <b>property</b> - a property belongs to a resource, represented by the
 * predicate of a statement about a resource
 * 
 * <b>value</b> - value of a property of a resource, represented by the object
 * of the statement with the property as predicate and the resource ID as the
 * subject
 * 
 * @author $Author: xamde $
 * @version $Id: ReactorBaseImpl.java,v 1.24 2006/12/05 19:47:28 xamde Exp $
 * 
 * 
 */
public class ReactorBaseImpl implements ReactorBase {

	private static Logger log = LoggerFactory.getLogger(ReactorBaseImpl.class);

	/**
	 * the underlying RDF2Go model in which the triples representing the
	 * properties of this object are saved
	 */
	protected Model model;

	/**
	 * the URI of the RDFS class from which this object is an instance
	 */
	protected URI classURI;

	/**
	 * the identifier of this instance is a URI or a BlankNode. It is used as
	 * the Subject of all triples representing this instance in the RDF model.
	 */
	private Resource instanceIdentifier;

	/**
	 * Constructor: create a ReactorBaseImpl for the RDFS/OWL schema class
	 * identified by classURI, with instanceIdentifier as the identifing URL or
	 * BlankNode.
	 * 
	 * @param model,
	 *            the underlying RDF2Go model
	 * @param classURI,
	 *            URI of the RDFS/OWL class from which this object is an
	 *            instance
	 * @param instanceIdentifier,
	 *            has to be an URI or URL or BlankNode
	 * @param write
	 *            if true, the triple (this, rdf:type, classURI) is written to
	 *            the model (in addition to any other triples denoting
	 *            properties of the instance)
	 */
	public ReactorBaseImpl(Model model, URI classURI,
			Resource instanceIdentifier, boolean write) {
		this.model = model;
		this.classURI = classURI;
		this.instanceIdentifier = instanceIdentifier;

		// this can lead to concurrenty exceptions when used in
		// iterators on the model
		if (write) {
			try {
				// add type information only if not present
				if (!model
						.contains(this.instanceIdentifier, RDF.type, classURI)) {
					log.debug("adding type information: "
							+ this.instanceIdentifier + " a " + classURI);
					add(RDF.type, classURI);

				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * Constructor: create a ReactorBaseImpl for the RDFS/OWL schema class
	 * identified by classURI, with instanceIdentifier as the identifing URL or
	 * BlankNode. Dont write (this, rdf:type, classURI) for this instance to the
	 * model.
	 * 
	 * @param model,
	 *            the underlying RDF2Go model
	 * @param classURI,
	 *            URI of the RDFS/OWL class from which this object is an
	 *            instance
	 * @param instanceIdentifier,
	 *            has to be an URI or URL or BlankNode
	 */
	public ReactorBaseImpl(Model model, URI classURI,
			Resource instanceIdentifier) {
		// FIXME: default true or false?
		this(model, classURI, instanceIdentifier, false);
	}

	/**
	 * implements
	 * 
	 * @see ReactorEntity
	 */
	public Resource getResource() {
		return this.instanceIdentifier;
	}

	/**
	 * @return the URI of the RDFS schema class of which this object is an
	 *         instance
	 */
	public URI getRDFSClassURI() {
		return this.classURI;
	}

	/**
	 * Find the URI of the RDFS schema class through inspection, this is usefull
	 * if the model contains no (this, rdf:type, classURI) triple
	 * 
	 * @param javaClass
	 *            for which the RDFS schema class should be found
	 * @return the RDFs_CLASS URI of an instance by using reflection
	 */
	private static URI getClassURI(Class<?> javaClass) {
		// TODO experimental
		try {
			URI classURI;
			classURI = (URI) javaClass.getDeclaredField("RDFS_CLASS").get(null);
			return classURI;
		} catch (Exception e) {
			throw new IllegalArgumentException(
					javaClass
							+ " seems not to have an RDFS_CLASS. Is it really a subclass of ReactorBaseImpl ?",
					e);
		}
	}

	// //////////////////////////////////////
	// override some java.lang.Object methods

	/**
	 * implement
	 * 
	 * @see Object methods
	 */
	@Override
	public boolean equals(Object other) {

		if (other instanceof ReactorBase) {
			return ((ReactorBase) other).getResource().equals(
					this.getResource());
		} else if (other instanceof URI) {
			return this.getResource().equals(other);
		} else
			return false;
	}

	/**
	 * implement
	 * 
	 * @see Object methods
	 */
	@Override
	public int hashCode() {
		return this.instanceIdentifier.hashCode();
	}

	/**
	 * implement
	 * 
	 * @see Object methods
	 * @return a string representation of the instance identifier (URI or blank
	 *         node). Representations are dependant on the used RDF2Go adaptor.
	 */
	@Override
	public String toString() {
		return this.instanceIdentifier.toString();
	}

	// ////////////////////////
	// queries

	public static boolean hasInstance(Model model, URI uri, URI classURI) {
		try {
			return (model.contains(uri, RDF.type, classURI));
		} catch (ModelRuntimeException e) {
			throw new RuntimeException(e);
		}
	}

	public static Object getInstance(Model model, URI uri,
			Class<?> javaClass) throws Exception {
		if (model.contains(uri, RDF.type, getClassURI(javaClass))) {
			return RDFReactorRuntime.resource2reactorbase(model, uri, javaClass);
		} else
			return null;
	}

	/**
	 * Return all instances of the given class.
	 * 
	 * @param model -
	 *            underlying RDF2Go model
	 * @param javaClass -
	 *            the java class representing the class the instances which
	 *            should be returned
	 * @param classURI -
	 *            URI of the (RDFS/OWL) class. currently not used
	 * @return array of all instances of the given java class in the model
	 */
	private static Object[] getAllInstances(Model model, Class<?> javaClass,
			URI classURI) {

		// FIXME
		if (!model.isOpen())
			model.open();

		// FIXME: classURI is not used ?
		List<Object> result = new ArrayList<Object>();
		try {

			ClosableIterator<? extends Statement> it = model.findStatements(
					Variable.ANY, RDF.type, getClassURI(javaClass));
			while (it.hasNext()) {
				Resource o = it.next().getSubject();
				// log.debug("found instance " + o + " of class "
				// + javaClass.getName());
				result.add(RDFReactorRuntime.node2javatype(model, o, javaClass));
			}
			it.close();

			Object[] typedResult = (Object[]) Array.newInstance(javaClass,
					result.size());
			for (int i = 0; i < typedResult.length; i++)
				typedResult[i] = javaClass.cast(result.get(i));

			log.debug("returning " + typedResult.length + " typed " + javaClass
					+ " instances");
			return typedResult;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Return all instances of the given class as a SubjectResultIterator
	 * object.
	 * 
	 * @param model -
	 *            underlying RDF2Go model
	 * @param javaClass -
	 *            the java class representing the class the instances which
	 *            should be returned
	 * @param classURI -
	 *            URI of the (RDFS/OWL) class. currently not used
	 * @return SubjectResultIterator over the resulting instances
	 */
	@SuppressWarnings("unchecked")
	public static Iterator<?> getAllInstancesAsIterator(Model model,
			Class javaClass, URI classURI) {
		ClosableIterator<QueryRow> it;
		try {
			// it = model.findStatements(Variable.ANY, RDF.type,
			// getClassURI(javaClass)).iterator();
			it = model.sparqlSelect(
					"SELECT ?x WHERE { ?x <" + RDF.type + "> <" + classURI
							+ ">}").iterator();
			return new ObjectResultIterator(model, new ExtractingIterator(
					model, it, "x"), javaClass);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Return all instances of the given class.
	 * 
	 * @param model -
	 *            underlying RDF2Go model
	 * @param javaClass -
	 *            the java class representing the class the instances which
	 *            should be returned
	 * @return array of all instances of the given java class in the model
	 */
	public static Object[] getAllInstances(Model model, Class<?> javaClass) {
		URI classURI = getClassURI(javaClass);
		return getAllInstances(model, javaClass, classURI);
	}

	/**
	 * Check if .this object is an instance of the given RDFS/OWL class URI.
	 * 
	 * @param classURI -
	 *            URI of the RDFS/OWL class
	 * @return true if .this is an instance of the given classURI
	 */
	public boolean isInstanceof(URI classURI) throws ModelRuntimeException {
		return this.model.contains(this.getResource(), RDF.type, classURI);
	}

	/**
	 * Check if .this object is an instance of the given Java class.
	 * 
	 * @param javaClass -
	 *            given Java class
	 * @return true if .this is an instance of the given Java Class in the model
	 * @deprecated
	 */
	@SuppressWarnings("deprecation")
	@Deprecated
	public boolean isInstanceof(Class<?> javaClass) throws ModelRuntimeException {
		return (isInstanceof(getClassURI(javaClass)));
	}

	/**
	 * Cast .this object to the given target Java type.
	 * 
	 * @param targetType -
	 *            Java type to which to cast this object
	 * @return converted object
	 */
	public Object castTo(Class<?> targetType) {
		return RDFReactorRuntime.node2javatype(this.model, this.getResource(), targetType);
	}

	// FIXME new

	/**
	 * Returns the first x in (this, prop, x) if such a statement is in the
	 * model. Null otherwise. If there are several x the first is returned
	 * (depending on the underlying RDF store). This method is useful for
	 * functional properties.
	 * 
	 * @param prop -
	 *            URI of the property
	 * @param returnType -
	 *            desired Java return type
	 * @return null or object typed as returnType
	 * @throws RDFDataException
	 */
	public Object get(URI prop, Class<?> returnType) throws RDFDataException {
		return BridgeBase.getValue(this.model, this.instanceIdentifier, prop,
				returnType);
	}

	/**
	 * Returns an array of x with (this, prop, x) if such statements are in the
	 * model. Null otherwise.
	 * 
	 * @param prop -
	 *            URI of the property
	 * @param returnType -
	 *            desired Java return type
	 * @return all values, array can be empty, never null
	 */
	public Object[] getAll(URI prop, Class<?> returnType) {

		try {
			return BridgeBase.getAllValues(this.model, this.instanceIdentifier,
					prop, returnType);
		} catch (Exception e) {
			throw new RuntimeException("return type " + returnType.getName()
					+ ". " + e, e);
		}
	}

	/**
	 * Get all resources having the given property and value. Return x matching
	 * (x, property, o).
	 * 
	 * @param property -
	 *            URI of the property
	 * @param o -
	 *            value of the property
	 * @param returnType -
	 *            desired Java return type
	 * @return array of desired returnType representing the found resources
	 */
	public Object[] getAll_Inverse(URI property, Node o, Class<?> returnType) {
		try {
			return BridgeBase.getAllValues_Inverse(this.model, property, o,
					returnType);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Get all values for the given property of this resource instance as a Set.
	 * 
	 * @param prop -
	 *            URI of the property
	 * @param returnType -
	 *            desired Java return type
	 * @return Set of all values for the given property
	 */
	public Set<Object> getAll_AsSet(URI prop, Class<?> returnType) {

		try {
			return BridgeBase.getAllValues_asSet(this.model,
					this.instanceIdentifier, prop, returnType);
		} catch (Exception e) {
			throw new RuntimeException("type " + returnType.getName(), e);
		}
	}

	/**
	 * Removes all statements (this, prop, x) and set one anew: (this, prop, o).
	 * 
	 * @param prop -
	 *            URI of the property
	 * @param o -
	 *            new value for the property
	 */
	public void set(URI prop, Object o) {
		try {
			Bridge.setValue(this.model, this.instanceIdentifier, prop, o);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Removes all statements (this, prop, x) and sets anew: (this, prop, o[0]),
	 * (this, prop, o[1]), ...
	 * 
	 * @param prop -
	 *            URI of the property
	 * @param o -
	 *            array of new values for the property
	 */
	public void setAll(URI prop, Object[] o) throws ModelRuntimeException {
		Bridge.setAllValue(this.model, this.instanceIdentifier, prop, o);
	}

	/**
	 * Removes all statements (this, prop, x) and sets anew: (this, prop, o[0]),
	 * (this, prop, o[1]), ... But only if the number of objects in o[] is less
	 * than or equal to maxCard.
	 * 
	 * @param prop -
	 *            URI of the property
	 * @param o -
	 *            array of new values of the property
	 * @param maxCard -
	 *            the maximum number of triples allowed to match (this, prop, x)
	 * @throws CardinalityException
	 * @throws CardinalityException
	 *             if the size of o[] is larger then maxCard
	 */
	public void setAll(URI prop, Object[] o, int maxCard)
			throws ModelRuntimeException, CardinalityException {
		if (o.length <= maxCard)
			setAll(prop, o);
		else
			throw new CardinalityException("Only " + maxCard
					+ " values allowed for property " + prop + " in class "
					+ this.classURI + ". You tried to add " + o.length);
	}

	/**
	 * Looks for a statement (this, prop, oldValue) and replaces it by a new
	 * statement (this, prop, newValue). If the first cannot be found, false is
	 * returned, true otherwise.
	 * 
	 * @param prop -
	 *            URI of the property
	 * @param oldValue -
	 *            old value of the property
	 * @param newValue -
	 *            new value of the property
	 * @return true if old value was found
	 */
	public boolean update(URI prop, Object oldValue, Object newValue) {
		try {
			return Bridge.updateValue(this.model, this.instanceIdentifier,
					prop, oldValue, newValue);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * 
	 * @param prop
	 * @param value
	 * @return true if the model contains the statement (this, prop, value)
	 */
	public boolean hasValue(URI prop, Object value) {
		try {
			return BridgeBase.containsGivenValue(this.model, this.instanceIdentifier, prop,
					value);
		} catch (ModelRuntimeException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @param prop
	 * @return true if the model contains any statement (this, prop, *)
	 */
	public boolean hasValue(URI prop) {
		try {
			return ResourceUtils.containsAnyValue(this.model, this.instanceIdentifier,
					prop);
		} catch (ModelRuntimeException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Adds a statement (this, prop, o). Returns false if this statement has
	 * already been in model. True otherwise.
	 * 
	 * @param property -
	 *            URI of the property
	 * @param object -
	 *            value of the property
	 * @return true if value was already in the model
	 */
	public boolean add(URI property, Object object) {
		try {
			return Bridge.addValue(this.model, this.instanceIdentifier,
					property, object);
		} catch (Exception e) {
			log.error("", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Adds a statement (this, prop, o) if the number of statements matching
	 * (this, prop, x) is less then maxCard
	 * 
	 * @param property -
	 *            URI of the property
	 * @param object -
	 *            value of the property
	 * @param maxCard -
	 *            number of occurences of (this, prop, x) allowed in the model
	 * @return true if value was already preset
	 * @throws CardinalityException
	 *             if the resource already has more then maxCard values for the
	 *             property
	 */
	public boolean add(URI property, Object object, int maxCard)
			throws CardinalityException {
		if (getAll(property, Resource.class).length < maxCard)
			return add(property, object);
		else
			throw new CardinalityException("Only " + maxCard
					+ " values allowed for property " + property + " in class "
					+ this.classURI);
	}

	/**
	 * Tries to remove a statement (this, prop, o).
	 * 
	 * @param prop -
	 *            URI of the property
	 * @param o -
	 *            value of the property
	 * @return true, if value was present
	 */
	public boolean remove(URI prop, Object o) {
		try {
			return BridgeBase.removeValue(this.model, this.instanceIdentifier,
					prop, o);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Tries to remove a statement (this, prop, o) if the number of statements
	 * matching (this, prop, x) in the model is less then minCard
	 * 
	 * @param prop -
	 *            URI of the property
	 * @param o -
	 *            value of the property
	 * @param minCard -
	 *            number of occurences of (this, prop, x) needed in the model
	 * @return true if value was found
	 * @throws CardinalityException
	 *             if resource has less then minCard values for the property
	 */
	public boolean remove(URI prop, Object o, int minCard)
			throws CardinalityException {
		if (getAll(prop, Object.class).length > minCard)
			return remove(prop, o);
		else
			throw new CardinalityException("Must have at least " + minCard
					+ " values for property " + prop + " in class " + this.classURI);
	}

	/**
	 * remove all (this, rdf:type, prop) statements
	 * 
	 * @param prop,
	 *            Object of a Triple
	 */
	public boolean removeAll(URI prop) {
		try {
			return BridgeBase.removeAllValues(this.model, this.instanceIdentifier,
					prop);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public Model getModel() {
		return this.model;
	}

	/**
	 * Delete all statements of the form (this, *,*)
	 */
	public void delete() {
		try {
			this.model.removeStatements(this.model.createTriplePattern(
					this.instanceIdentifier, Variable.ANY, Variable.ANY));
		} catch (ModelRuntimeException e) {
			throw new RuntimeException(e);
		}

	}

	// TODO implement boolean in(Model)
	public boolean in(@SuppressWarnings("unused")
	Model model) {
		throw new UnsupportedOperationException("not yet implemented");

	}

	// TODO re-enable after cleanup is complete
	//	
	// public Map<URI, Object> map() {
	// return new ReactorMap(model, instanceIdentifier);
	// }

}
