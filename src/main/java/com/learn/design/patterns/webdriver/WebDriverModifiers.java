
package com.learn.design.patterns.webdriver;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import com.learn.design.patterns.webdriver.modifiers.CapabilitiesModifier;
import com.learn.design.patterns.webdriver.modifiers.WebDriverModifier;
import com.learn.design.patterns.webdriver.modifiers.WebDriverModifyingCollector;

/**
 * Gathers all Capabilities and WebDriver modifiers, sorts them and filters
 * enabled ones only.
 * 
 * @author Sachin Kumar 12 [skumar213@sapient.com]
 */
public class WebDriverModifiers {

    private List<CapabilitiesModifier> capabilitiesModifiers;
    private List<WebDriverModifier> driverModifiers;

    public WebDriverModifiers() {
	capabilitiesModifiers = new ArrayList<>();
	driverModifiers = new ArrayList<>();
    }

    public void registerCapabilitiesModifier(CapabilitiesModifier modifier) {
	if (modifier.shouldModify())
	    capabilitiesModifiers.add(modifier);
    }

    public void registerCapabilitiesModifier(List<CapabilitiesModifier> modifier) {
	capabilitiesModifiers.addAll(modifier);
	this.capabilitiesModifiers = capabilitiesModifiers.stream().filter(CapabilitiesModifier::shouldModify)
		.sorted(Comparator.comparing(CapabilitiesModifier::getOrder)).collect(Collectors.toList());
    }

    public void registerWebDriverModifier(WebDriverModifier modifier) {
	if (modifier.shouldModify())
	    driverModifiers.add(modifier);
    }

    public void registerWebDriverModifier(List<WebDriverModifier> modifier) {
	driverModifiers.addAll(modifier);
	this.driverModifiers = driverModifiers.stream().filter(WebDriverModifier::shouldModify)
		.sorted(Comparator.comparing(WebDriverModifier::getOrder)).collect(Collectors.toList());
    }

    public void removeCapabilitiesModifier(CapabilitiesModifier modifier) {
	capabilitiesModifiers.remove(modifier);
	this.capabilitiesModifiers = capabilitiesModifiers.stream().filter(CapabilitiesModifier::shouldModify)
		.sorted(Comparator.comparing(CapabilitiesModifier::getOrder)).collect(Collectors.toList());
    }

    public void removeWebDriverModifier(WebDriverModifier modifier) {
	driverModifiers.remove(modifier);
	this.driverModifiers = driverModifiers.stream().filter(WebDriverModifier::shouldModify)
		.sorted(Comparator.comparing(WebDriverModifier::getOrder)).collect(Collectors.toList());
    }

    /**
     * Modifies provided Capabilities with registered set of modifiers.
     *
     * @param capabilities
     *            raw Capabilities instance
     * @return modified Capabilities instance
     */
    public Capabilities modifyCapabilities(Capabilities capabilities) {
	Capabilities modifiedCapabilities = capabilities;
	for (CapabilitiesModifier modifier : capabilitiesModifiers) {
	    modifiedCapabilities = modifier.modify(capabilities);
	}
	return modifiedCapabilities;
    }

    /**
     * Modifies provided WebDriver with registered set of modifiers.
     *
     * @param webDriver
     *            raw WebDriver instance
     * @return modified WebDriver instance
     */
    public WebDriver modifyWebDriver(WebDriver webDriver) {
	return driverModifiers.stream().collect(modifyDrivers(webDriver));
    }

    private Collector<? super WebDriverModifier, WebDriver, WebDriver> modifyDrivers(final WebDriver webdriver) {
	return new WebDriverModifyingCollector(webdriver);
    }

}
