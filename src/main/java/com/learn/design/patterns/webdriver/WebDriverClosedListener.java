
package com.learn.design.patterns.webdriver;

/**
 * Subscribers of the webdriver's closing event must implement this interface.
 * 
 *  
 * @author Sachin Kumar 12 [skumar213@sapient.com]
 */

public interface WebDriverClosedListener {
    /**
     * This method will be called by the webDriver after it is closed.
     *
     * @param terminated
     *            indicates if the browser was actually terminated.
     */
    void onWebDriverClosed(boolean terminated);
}
