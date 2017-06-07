
package com.learn.design.patterns.webdriver.modifiers;

import org.openqa.selenium.Capabilities;

/**
 * Describes a modifier for Capabilities instance - it will be triggered
 * <b>before</b> WebDriver creation. Modification can be controlled via
 * {@code shouldModify()} method. Order of the execution can be controlled via
 * {@code getOrder()} method.
 * 
 *  @author Sachin Kumar 12 [skumar213@sapient.com]
 */
public interface CapabilitiesModifier {

    /**
     * This method can be used to control if the modification should be
     * triggered, e.g. when a property is set to some value.
     *
     * @return true if the modification should be triggered
     */
    boolean shouldModify();

    /**
     * Modifies given instance of Capabilities and passes it further
     * (potentially for additional modifications).
     *
     * @param capabilities
     *            instance of the Capabilities that will be modified
     * @return modified Capabilities instance
     */
    Capabilities modify(Capabilities capabilities);

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
