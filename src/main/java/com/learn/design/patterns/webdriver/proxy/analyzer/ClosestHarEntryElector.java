
package com.learn.design.patterns.webdriver.proxy.analyzer;

import java.util.List;

import net.lightbody.bmp.core.har.HarEntry;

/**
 * Interface that defines object which can elect one HarEntry from collection
 * regarding to predicate.
 * 
 * @author Sachin Kumar 12 [skumar213@sapient.com]
 */
public interface ClosestHarEntryElector {
    /**
     * Finds entry that matches predicate most
     *
     * @param harEntries
     *            collection of entries that would be searched
     * @return elected entry or null if nothing is found
     */
    HarEntry findSimilarEntry(List<HarEntry> harEntries);
}
