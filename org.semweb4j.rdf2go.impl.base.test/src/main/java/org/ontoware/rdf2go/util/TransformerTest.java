package org.ontoware.rdf2go.util;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.ontoware.rdf2go.RDF2Go;
import org.ontoware.rdf2go.model.Model;
import org.ontoware.rdf2go.model.node.URI;
import org.ontoware.rdf2go.model.node.Variable;
import org.ontoware.rdf2go.model.node.impl.URIImpl;
import org.ontoware.rdf2go.util.transform.NamespaceSearchReplaceRule;
import org.ontoware.rdf2go.util.transform.SearchRemoveAddRule;
import org.ontoware.rdf2go.util.transform.Transformer;
import org.ontoware.rdf2go.util.transform.URISearchReplaceRule;
import org.ontoware.rdf2go.vocabulary.RDF;
import org.ontoware.rdf2go.vocabulary.RDFS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TransformerTest {

	private static Logger log = LoggerFactory.getLogger(TransformerTest.class);

	@Test
	public void testToSparqlConstruct() {
		Map<String, URI> nsMap = new HashMap<String, URI>();
		nsMap.put("skos", new URIImpl("http://www.w3.org/2004/02/skos/core#"));
		nsMap.put("", new URIImpl("http://purl.org/net/schemarama#"));
		String construct = "[] a :Error;  :message 'Resource [1] has more than one preferred lexical label [2][3] in a given language.';  :implicated ( ?x ?l ?m );  .";
		String where = "?x skos:prefLabel ?l; skos:prefLabel ?m.  FILTER ( str(?l) != str(?m) && lang(?l) = lang(?m) )";
		String query = Transformer.toSparqlConstruct(nsMap, construct, where);
		log.debug("query = " + query);
	}

	@Test
	public void testApplyRule() {
		Model m = RDF2Go.getModelFactory().createModel();
		m.open();
		Map<String, URI> nsMap = new HashMap<String, URI>();
		nsMap.put("rdf", new URIImpl(
				"http://www.w3.org/1999/02/22-rdf-syntax-ns#"));
		String LOCAL = "http://example.com#";
		nsMap.put("", new URIImpl(LOCAL));
		URI a = new URIImpl(LOCAL + "a");
		URI b = new URIImpl(LOCAL + "b");
		URI c = new URIImpl(LOCAL + "c");

		m.addStatement(a, b, c);
		m.addStatement(a, RDF.type, c);
		log.debug("Before");
		m.dump();

		String constructRemove = "?x a " + c.toSPARQL();
		String constructAdd = "?x a " + b.toSPARQL();
		String where = constructRemove;

		SearchRemoveAddRule.searchAndReplace(m, nsMap, where, constructRemove,
				constructAdd);
		log.debug("After");
		m.dump();
	}

	@Test
	public void testUriRename() {
		Model m = RDF2Go.getModelFactory().createModel();
		m.open();

		URI a = new URIImpl("urn:test:a");
		URI b = new URIImpl("urn:test:b");
		URI c = new URIImpl("urn:test:c");

		URI superRel = new URIImpl(
				"http://www.semanticdesktop.org/ontologies/2007/09/cds/hasSuperRelation");
		m.addStatement(superRel, b, c);
		m.addStatement(a, superRel, c);
		m.addStatement(a, b, superRel);
		Map<String, URI> nsMap = new HashMap<String, URI>();
		URISearchReplaceRule.searchAndReplace(m, nsMap, superRel,
				RDFS.subPropertyOf);

		m.dump();
		Assert.assertFalse(m.contains(superRel, Variable.ANY, Variable.ANY));
	}

	@Test
	public void testUriPrefixRename() {
		Model m = RDF2Go.getModelFactory().createModel();
		m.open();

		URI a = new URIImpl("urn:test:a");
		URI b = new URIImpl("urn:test:b");
		URI c = new URIImpl("urn:test:c");

		URI superRel = new URIImpl(
				"http://www.semanticdesktop.org/ontologies/2007/09/cds/hasSuperRelation");
		m.addStatement(superRel, b, c);
		m.addStatement(a, superRel, c);
		m.addStatement(a, b, superRel);
		NamespaceSearchReplaceRule.searchAndReplace(m, "urn:test:", "http://example.com#");
		
		m.dump();
		Assert.assertFalse(m.contains(a, Variable.ANY, Variable.ANY));
	}
}
