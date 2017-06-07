package com.learn.design.patterns.webdriver;

import java.util.HashSet;
import java.util.Set;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;

/**
 * Class providing WebDriver instance based on capabilities, modifiers and
 * listeners etc.
 * 
 * 
 * @author Sachin Kumar 12 [skumar213@sapient.com]
 */

public class WebDriverProvider {
    private ClosableWebDriver cachedWebDriver;
    private Capabilities capabilities;
    private WebDriverModifiers webDriverModifiers;
    private Set<WebDriverClosedListener> closedListeners;
    private Set<WebDriverEventListener> listeners;
    private WebDriverConfig webDriverConfig;

    public WebDriverProvider(WebDriverConfig webDriverConfig, WebDriverModifiers webDriverModifiers) {
	this.webDriverConfig = webDriverConfig;
	this.capabilities = new DesiredCapabilitiesProvider(webDriverConfig.getProperties()).get();
	this.webDriverModifiers = webDriverModifiers;
	this.closedListeners = new HashSet<>();
	this.listeners = new HashSet<>();
    }

    public void registerEventListeners(WebDriverEventListener listener) {
	listeners.add(listener);
    }

    public void registerClosedListener(WebDriverClosedListener listener) {
	closedListeners.add(listener);
    }

    public void removeEventListeners(WebDriverEventListener listener) {
	listeners.remove(listener);
    }

    public void removeClosedListener(WebDriverClosedListener listener) {
	closedListeners.remove(listener);
    }

    public WebDriver get() {
	if (cachedWebDriver == null || !cachedWebDriver.isAlive()) {
	    cachedWebDriver = create();
	}
	return cachedWebDriver;
    }

    private ClosableWebDriver create() {
	final Capabilities modifiedCapabilities = webDriverModifiers.modifyCapabilities(capabilities);
	final WebDriverType webDriverType = WebDriverType.get(webDriverConfig);
	final WebDriver raw = webDriverType.create(modifiedCapabilities, webDriverConfig);
	final WebDriver modified = webDriverModifiers.modifyWebDriver(raw);
	final ClosableWebDriverWrapper closableWebDriver = wrapInClosableWebDriver(modified);
	registerEventListeners(closableWebDriver);
	WebDriverRegistry.getWebDriverRegistry().add(closableWebDriver);
	return closableWebDriver;
    }

    private void registerEventListeners(final EventFiringWebDriver closableWebDriver) {
	listeners.stream().forEach(closableWebDriver::register);
    }

    private ClosableWebDriverWrapper wrapInClosableWebDriver(WebDriver webDriver) {
	final ClosableWebDriverWrapper closingWebDriver = new ClosableWebDriverWrapper(webDriver,
		webDriverConfig.isMaximize(), webDriverConfig.isReusable(), webDriverConfig.isMobile());
	closedListeners.stream().forEach(closingWebDriver::addListener);
	return closingWebDriver;
    }
}
