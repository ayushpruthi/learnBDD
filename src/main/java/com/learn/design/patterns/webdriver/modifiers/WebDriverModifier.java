
package com.learn.design.patterns.webdriver.modifiers;

import org.openqa.selenium.WebDriver;

/**
 * Describes a modifier for WebDriver instance - 
 * 
 * @author Sachin Kumar 12 [skumar213@sapient.com]
 */
public interface WebDriverModifier {

    /**
     * This method can be used to control if the modification should be
     * triggered, e.g. when a property is set to some value.
     *
     * @return true if the modification should be triggered
     */
    boolean shouldModify();

    /**
     * Modifies given instance of WebDriver and passes it further (potentially
     * for additional modifications).
     *
     * @param webDriver
     *            instance of the WebDriver that will be modified
     * @return modified WebDriver instance
     */
    WebDriver modify(WebDriver webDriver);

    /**
     * Optional method, used to control order of modifications - modificators
     * will be sorted based on the value returned by this method.
     *
     * @return order of the modification, default is set to 0
     */
    default int getOrder() {
	return 0;
    }
}
