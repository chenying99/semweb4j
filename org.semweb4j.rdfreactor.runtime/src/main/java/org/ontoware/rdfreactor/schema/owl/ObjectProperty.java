/**
 * generated by http://RDFReactor.semweb4j.org ($Id: CodeGenerator.java 870 2007-11-07 17:30:59Z max.at.xam.de $) on 26.01.08 12:47
 */
package org.ontoware.rdfreactor.schema.owl;

import org.ontoware.aifbcommons.collection.ClosableIterator;
import org.ontoware.rdf2go.exception.ModelRuntimeException;
import org.ontoware.rdf2go.model.Model;
import org.ontoware.rdf2go.model.node.BlankNode;
import org.ontoware.rdf2go.model.node.URI;
import org.ontoware.rdf2go.model.node.impl.URIImpl;
import org.ontoware.rdfreactor.runtime.Base;
import org.ontoware.rdfreactor.runtime.ReactorResult;





/**
 * This class manages access to these properties:
 * <ul>
 *   <li> InverseOf </li>
 * </ul>
 *
 * This class was generated by <a href="http://RDFReactor.semweb4j.org">RDFReactor</a> on 26.01.08 12:47
 */
public class ObjectProperty extends OwlProperty {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

	/** http://www.w3.org/2002/07/owl#ObjectProperty */
	@SuppressWarnings("hiding")
	public static final URI RDFS_CLASS = new URIImpl("http://www.w3.org/2002/07/owl#ObjectProperty", false);

    /** http://www.w3.org/2002/07/owl#inverseOf */
	public static final URI INVERSEOF = new URIImpl("http://www.w3.org/2002/07/owl#inverseOf",false);

    /** all property-URIs with this class as domain */
    @SuppressWarnings("hiding")
	public static final URI[] MANAGED_URIS = {
      new URIImpl("http://www.w3.org/2002/07/owl#inverseOf",false) 
    };


	// protected constructors needed for inheritance
	
	/**
	 * Returns a Java wrapper over an RDF object, identified by URI.
	 * Creating two wrappers for the same instanceURI is legal.
	 * @param model RDF2GO Model implementation, see http://rdf2go.semweb4j.org
	 * @param classURI URI of RDFS class
	 * @param instanceIdentifier Resource that identifies this instance
	 * @param write if true, the statement (this, rdf:type, TYPE) is written to the model
	 *
	 * [Generated from RDFReactor template rule #c1] 
	 */
	protected ObjectProperty ( Model model, URI classURI, org.ontoware.rdf2go.model.node.Resource instanceIdentifier, boolean write ) {
		super(model, classURI, instanceIdentifier, write);
	}

	// public constructors

	/**
	 * Returns a Java wrapper over an RDF object, identified by URI.
	 * Creating two wrappers for the same instanceURI is legal.
	 * @param model RDF2GO Model implementation, see http://rdf2go.ontoware.org
	 * @param instanceIdentifier an RDF2Go Resource identifying this instance
	 * @param write if true, the statement (this, rdf:type, TYPE) is written to the model
	 *
	 * [Generated from RDFReactor template rule #c2] 
	 */
	public ObjectProperty ( Model model, org.ontoware.rdf2go.model.node.Resource instanceIdentifier, boolean write ) {
		super(model, RDFS_CLASS, instanceIdentifier, write);
	}


	/**
	 * Returns a Java wrapper over an RDF object, identified by a URI, given as a String.
	 * Creating two wrappers for the same URI is legal.
	 * @param model RDF2GO Model implementation, see http://rdf2go.ontoware.org
	 * @param uriString a URI given as a String
	 * @param write if true, the statement (this, rdf:type, TYPE) is written to the model
	 * @throws ModelRuntimeException if URI syntax is wrong
	 *
	 * [Generated from RDFReactor template rule #c7] 
	 */
	public ObjectProperty ( Model model, String uriString, boolean write) throws ModelRuntimeException {
		super(model, RDFS_CLASS, new URIImpl(uriString,false), write);
	}

	/**
	 * Returns a Java wrapper over an RDF object, identified by a blank node.
	 * Creating two wrappers for the same blank node is legal.
	 * @param model RDF2GO Model implementation, see http://rdf2go.ontoware.org
	 * @param bnode BlankNode of this instance
	 * @param write if true, the statement (this, rdf:type, TYPE) is written to the model
	 *
	 * [Generated from RDFReactor template rule #c8] 
	 */
	public ObjectProperty ( Model model, BlankNode bnode, boolean write ) {
		super(model, RDFS_CLASS, bnode, write);
	}

	/**
	 * Returns a Java wrapper over an RDF object, identified by 
	 * a randomly generated URI.
	 * Creating two wrappers results in different URIs.
	 * @param model RDF2GO Model implementation, see http://rdf2go.ontoware.org
	 * @param write if true, the statement (this, rdf:type, TYPE) is written to the model
	 *
	 * [Generated from RDFReactor template rule #c9] 
	 */
	public ObjectProperty ( Model model, boolean write ) {
		super(model, RDFS_CLASS, model.newRandomUniqueURI(), write);
	}

    ///////////////////////////////////////////////////////////////////
    // typing

	/**
	 * Create a new instance of this class in the model. 
	 * That is, create the statement (instanceResource, RDF.type, http://www.w3.org/2002/07/owl#ObjectProperty).
	 * @param model an RDF2Go model
	 * @param instanceResource an RDF2Go resource
	 *
	 * [Generated from RDFReactor template rule #class1] 
	 */
	public static void createInstance(Model model, org.ontoware.rdf2go.model.node.Resource instanceResource) {
		Base.createInstance(model, RDFS_CLASS, instanceResource);
	}

	/**
	 * @param model an RDF2Go model
	 * @param instanceResource an RDF2Go resource
	 * @return true if instanceResource is an instance of this class in the model
	 *
	 * [Generated from RDFReactor template rule #class2] 
	 */
	public static boolean hasInstance(Model model, org.ontoware.rdf2go.model.node.Resource instanceResource) {
		return Base.hasInstance(model, RDFS_CLASS, instanceResource);
	}

	/**
	 * @param model an RDF2Go model
	 * @return all instances of this class in Model 'model' as RDF resources
	 *
	 * [Generated from RDFReactor template rule #class3] 
	 */
	public static ClosableIterator<org.ontoware.rdf2go.model.node.Resource> getAllInstances(Model model) {
		return Base.getAllInstances(model, RDFS_CLASS, org.ontoware.rdf2go.model.node.Resource.class);
	}

	/**
	 * @param model an RDF2Go model
	 * @return all instances of this class in Model 'model' as a ReactorResult,
	 * which can conveniently be converted to iterator, list or array.
	 *
	 * [Generated from RDFReactor template rule #class3-as] 
	 */
	public static ReactorResult<? extends ObjectProperty> getAllInstance_as(Model model) {
		return Base.getAllInstances_as(model, RDFS_CLASS, ObjectProperty.class );
	}

    /**
	 * Delete all rdf:type from this instance. Other triples are not affected.
	 * @param model an RDF2Go model
	 * @param instanceResource an RDF2Go resource
	 *
	 * [Generated from RDFReactor template rule #class4] 
	 */
	public static void deleteInstance(Model model, org.ontoware.rdf2go.model.node.Resource instanceResource) {
		Base.deleteInstance(model, RDFS_CLASS, instanceResource);
	}

    ///////////////////////////////////////////////////////////////////
    // property access methods

	/**
	 * @param model an RDF2Go model
	 * @param objectValue
	 * @return all A's as RDF resources, that have a relation 'InverseOf' to this ObjectProperty instance
	 *
	 * [Generated from RDFReactor template rule #getallinverse1static] 
	 */
	public static ClosableIterator<org.ontoware.rdf2go.model.node.Resource> getAllInverseOf_Inverse( Model model, Object objectValue) {
		return Base.getAll_Inverse(model, ObjectProperty.INVERSEOF, objectValue);
	}



     /**
     * Get all values of property InverseOf as an Iterator over RDF2Go nodes 
     * @param model an RDF2Go model
     * @param resource an RDF2Go resource
     * @return a ClosableIterator of RDF2Go Nodes
	 *
	 * [Generated from RDFReactor template rule #get7static] 
     */
	public static ClosableIterator<org.ontoware.rdf2go.model.node.Node> getAllInverseOf_asNode(Model model, org.ontoware.rdf2go.model.node.Resource instanceResource) {
		return Base.getAll_asNode(model, instanceResource, INVERSEOF);
	}
	
    /**
     * Get all values of property InverseOf as a ReactorResult of RDF2Go nodes 
     * @param model an RDF2Go model
     * @param resource an RDF2Go resource
     * @return a List of RDF2Go Nodes
	 *
	 * [Generated from RDFReactor template rule #get7static-reactor-result] 
     */
	public static ReactorResult<org.ontoware.rdf2go.model.node.Node> getAllInverseOf_asNode_(Model model, org.ontoware.rdf2go.model.node.Resource instanceResource) {
		return Base.getAll_as(model, instanceResource, INVERSEOF, org.ontoware.rdf2go.model.node.Node.class);
	}

    /**
     * Get all values of property InverseOf as an Iterator over RDF2Go nodes 
     * @return a ClosableIterator of RDF2Go Nodes
	 *
	 * [Generated from RDFReactor template rule #get8dynamic] 
     */
	public ClosableIterator<org.ontoware.rdf2go.model.node.Node> getAllInverseOf_asNode() {
		return Base.getAll_asNode(this.model, this.getResource(), INVERSEOF);
	}

    /**
     * Get all values of property InverseOf as a ReactorResult of RDF2Go nodes 
     * @return a List of RDF2Go Nodes
	 *
	 * [Generated from RDFReactor template rule #get8dynamic-reactor-result] 
     */
	public ReactorResult<org.ontoware.rdf2go.model.node.Node> getAllInverseOf_asNode_() {
		return Base.getAll_as(this.model, this.getResource(), INVERSEOF, org.ontoware.rdf2go.model.node.Node.class);
	}
     /**
     * Get all values of property InverseOf     * @param model an RDF2Go model
     * @param resource an RDF2Go resource
     * @return a ClosableIterator of $type
	 *
	 * [Generated from RDFReactor template rule #get11static] 
     */
	public static ClosableIterator<ObjectProperty> getAllInverseOf(Model model, org.ontoware.rdf2go.model.node.Resource instanceResource) {
		return Base.getAll(model, instanceResource, INVERSEOF, ObjectProperty.class);
	}
	
    /**
     * Get all values of property InverseOf as a ReactorResult of ObjectProperty 
     * @param model an RDF2Go model
     * @param resource an RDF2Go resource
     * @return a ReactorResult of $type which can conveniently be converted to iterator, list or array
	 *
	 * [Generated from RDFReactor template rule #get11static-reactorresult] 
     */
	public static ReactorResult<ObjectProperty> getAllInverseOf_as(Model model, org.ontoware.rdf2go.model.node.Resource instanceResource) {
		return Base.getAll_as(model, instanceResource, INVERSEOF, ObjectProperty.class);
	}

    /**
     * Get all values of property InverseOf     * @return a ClosableIterator of $type
	 *
	 * [Generated from RDFReactor template rule #get12dynamic] 
     */
	public ClosableIterator<ObjectProperty> getAllInverseOf() {
		return Base.getAll(this.model, this.getResource(), INVERSEOF, ObjectProperty.class);
	}

    /**
     * Get all values of property InverseOf as a ReactorResult of ObjectProperty 
     * @return a ReactorResult of $type which can conveniently be converted to iterator, list or array
	 *
	 * [Generated from RDFReactor template rule #get12dynamic-reactorresult] 
     */
	public ReactorResult<ObjectProperty> getAllInverseOf_as() {
		return Base.getAll_as(this.model, this.getResource(), INVERSEOF, ObjectProperty.class);
	}
 
    /**
     * Adds a value to property InverseOf as an RDF2Go node 
     * @param model an RDF2Go model
     * @param resource an RDF2Go resource
	 * @param value the value to be added
	 *
	 * [Generated from RDFReactor template rule #add1static] 
     */
	public static void addInverseOf( Model model, org.ontoware.rdf2go.model.node.Resource instanceResource, org.ontoware.rdf2go.model.node.Node value) {
		Base.add(model, instanceResource, INVERSEOF, value);
	}
	
    /**
     * Adds a value to property InverseOf as an RDF2Go node 
	 * @param value the value to be added
	 *
	 * [Generated from RDFReactor template rule #add1dynamic] 
     */
	public void addInverseOf( org.ontoware.rdf2go.model.node.Node value) {
		Base.add(this.model, this.getResource(), INVERSEOF, value);
	}
    /**
     * Adds a value to property InverseOf from an instance of ObjectProperty 
     * @param model an RDF2Go model
     * @param resource an RDF2Go resource
	 *
	 * [Generated from RDFReactor template rule #add3static] 
     */
	public static void addInverseOf(Model model, org.ontoware.rdf2go.model.node.Resource instanceResource, ObjectProperty value) {
		Base.add(model, instanceResource, INVERSEOF, value);
	}
	
    /**
     * Adds a value to property InverseOf from an instance of ObjectProperty 
	 *
	 * [Generated from RDFReactor template rule #add4dynamic] 
     */
	public void addInverseOf(ObjectProperty value) {
		Base.add(this.model, this.getResource(), INVERSEOF, value);
	}
  

    /**
     * Sets a value of property InverseOf from an RDF2Go node.
     * First, all existing values are removed, then this value is added.
     * Cardinality constraints are not checked, but this method exists only for properties with
     * no minCardinality or minCardinality == 1.
     * @param model an RDF2Go model
     * @param resource an RDF2Go resource
	 * @param value the value to be set
	 *
	 * [Generated from RDFReactor template rule #set1static] 
     */
	public static void setInverseOf( Model model, org.ontoware.rdf2go.model.node.Resource instanceResource, org.ontoware.rdf2go.model.node.Node value) {
		Base.set(model, instanceResource, INVERSEOF, value);
	}
	
    /**
     * Sets a value of property InverseOf from an RDF2Go node.
     * First, all existing values are removed, then this value is added.
     * Cardinality constraints are not checked, but this method exists only for properties with
     * no minCardinality or minCardinality == 1.
	 * @param value the value to be added
	 *
	 * [Generated from RDFReactor template rule #set1dynamic] 
     */
	public void setInverseOf( org.ontoware.rdf2go.model.node.Node value) {
		Base.set(this.model, this.getResource(), INVERSEOF, value);
	}
    /**
     * Sets a value of property InverseOf from an instance of ObjectProperty 
     * First, all existing values are removed, then this value is added.
     * Cardinality constraints are not checked, but this method exists only for properties with
     * no minCardinality or minCardinality == 1.
     * @param model an RDF2Go model
     * @param resource an RDF2Go resource
	 * @param value the value to be added
	 *
	 * [Generated from RDFReactor template rule #set3static] 
     */
	public static void setInverseOf(Model model, org.ontoware.rdf2go.model.node.Resource instanceResource, ObjectProperty value) {
		Base.set(model, instanceResource, INVERSEOF, value);
	}
	
    /**
     * Sets a value of property InverseOf from an instance of ObjectProperty 
     * First, all existing values are removed, then this value is added.
     * Cardinality constraints are not checked, but this method exists only for properties with
     * no minCardinality or minCardinality == 1.
	 * @param value the value to be added
	 *
	 * [Generated from RDFReactor template rule #set4dynamic] 
     */
	public void setInverseOf(ObjectProperty value) {
		Base.set(this.model, this.getResource(), INVERSEOF, value);
	}
  


    /**
     * Removes a value of property InverseOf as an RDF2Go node 
     * @param model an RDF2Go model
     * @param resource an RDF2Go resource
	 * @param value the value to be removed
	 *
	 * [Generated from RDFReactor template rule #remove1static] 
     */
	public static void removeInverseOf( Model model, org.ontoware.rdf2go.model.node.Resource instanceResource, org.ontoware.rdf2go.model.node.Node value) {
		Base.remove(model, instanceResource, INVERSEOF, value);
	}
	
    /**
     * Removes a value of property InverseOf as an RDF2Go node
	 * @param value the value to be removed
	 *
	 * [Generated from RDFReactor template rule #remove1dynamic] 
     */
	public void removeInverseOf( org.ontoware.rdf2go.model.node.Node value) {
		Base.remove(this.model, this.getResource(), INVERSEOF, value);
	}
    /**
     * Removes a value of property InverseOf given as an instance of ObjectProperty 
     * @param model an RDF2Go model
     * @param resource an RDF2Go resource
	 * @param value the value to be removed
	 *
	 * [Generated from RDFReactor template rule #remove3static] 
     */
	public static void removeInverseOf(Model model, org.ontoware.rdf2go.model.node.Resource instanceResource, ObjectProperty value) {
		Base.remove(model, instanceResource, INVERSEOF, value);
	}
	
    /**
     * Removes a value of property InverseOf given as an instance of ObjectProperty 
	 * @param value the value to be removed
	 *
	 * [Generated from RDFReactor template rule #remove4dynamic] 
     */
	public void removeInverseOf(ObjectProperty value) {
		Base.remove(this.model, this.getResource(), INVERSEOF, value);
	}
  
    /**
     * Removes all values of property InverseOf     * @param model an RDF2Go model
     * @param resource an RDF2Go resource
	 *
	 * [Generated from RDFReactor template rule #removeall1static] 
     */
	public static void removeAllInverseOf( Model model, org.ontoware.rdf2go.model.node.Resource instanceResource) {
		Base.removeAll(model, instanceResource, INVERSEOF);
	}
	
    /**
     * Removes all values of property InverseOf	 *
	 * [Generated from RDFReactor template rule #removeall1dynamic] 
     */
	public void addInverseOf() {
		Base.removeAll(this.model, this.getResource(), INVERSEOF);
	}
 }