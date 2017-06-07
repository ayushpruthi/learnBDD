
package com.learn.design.patterns.webdriver.proxy.analyzer;

import io.netty.handler.codec.http.HttpRequest;

/**
 * Interface representing a condition that accepts some requests and rejects
 * others.
 * 
 * @author Sachin Kumar 12 [skumar213@sapient.com]
 */
public interface RequestPredicate {
    /**
     * Method checks if given resource fulfills conditions.
     *
     * @param request
     *            Tested resource
     * @return true if the request fulfills conditions, false otherwise
     */
    boolean accepts(HttpRequest request);

    /**
     * String representing given predicate, used in the reports.
     */
    @Override
    String toString();
}
