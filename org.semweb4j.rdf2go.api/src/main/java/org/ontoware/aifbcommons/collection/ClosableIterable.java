/**
 * LICENSE INFORMATION
 * 
 * Copyright 2005-2008 by FZI (http://www.fzi.de). Licensed under a BSD license
 * (http://www.opensource.org/licenses/bsd-license.php) <OWNER> = Max Völkel
 * <ORGANIZATION> = FZI Forschungszentrum Informatik Karlsruhe, Karlsruhe,
 * Germany <YEAR> = 2010
 * 
 * Further project information at http://semanticweb.org/wiki/RDF2Go
 */

package org.ontoware.aifbcommons.collection;

import java.io.Serializable;


// IMPROVE: USe Jakarta Commons Collections instead?

/**
 * Implementing this interface allows an object to be the target of the
 * "for-each" statement.
 */
public interface ClosableIterable<T> extends Iterable<T>, Serializable {
	
	/**
	 * Returns an iterator over a set of elements of type T.
	 * 
	 * @return an Iterator.
	 */
	@Override
    ClosableIterator<T> iterator();
}
