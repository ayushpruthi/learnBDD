
package com.learn.design.patterns.webdriver.proxy;

import com.learn.design.patterns.webdriver.proxy.analyzer.ClosestHarEntryElector;
import com.learn.design.patterns.webdriver.proxy.analyzer.RequestPredicate;

import net.lightbody.bmp.core.har.Har;

/**
 * Classes implementing this interface are informed
 * about events related to traffic analysis.
 * 
 * @author Sachin Kumar 12 [skumar213@sapient.com]
 */
public interface ProxyEventListener {
    /**
     * Method will be invoked when analysis is started.
     */
    void listeningStarted();

    /**
     * Method will be invoked when analysis is finished.
     *
     * @param har
     *            Logged requests
     */
    void listeningStopped(Har har);

    /**
     * Method will be invoked after listeningStarted(), when the analyzer is
     * waiting for desired request.
     *
     * @param predicate
     *            RequestPredicate instance.
     * @param closestHarEntryElector
     *            object that can find closest object regarding predicate might
     *            be null value if predicate doesn't allow so
     */
    void waitingForRequest(RequestPredicate predicate, ClosestHarEntryElector closestHarEntryElector);

    /**
     * This method will be invoked if the analyzer finds request.
     */
    void requestFound();

    /**
     * This method will be invoked if the analyzer doesn't find the request.
     */
    void timeout();

    /**
     * Method called after listeningStopped by the original thread starting
     * analysis.
     */
    void dispatch();
}
