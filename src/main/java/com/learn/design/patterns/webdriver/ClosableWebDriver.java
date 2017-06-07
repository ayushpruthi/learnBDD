package com.learn.design.patterns.webdriver;

import org.openqa.selenium.WebDriver;

/**
 * Implementing class will have the functionality of adding listeners and
 * closing driver for reusable mode.
 * 
 * @author Sachin Kumar 12 [skumar213@sapient.com]
 */
public interface ClosableWebDriver extends WebDriver {
	/**
	 * @return State of the driver: true means alive, false means dead.
	 */
	boolean isAlive();

	/**
	 * Registers new listener
	 *
	 * @param listener
	 *            Listener to register
	 */
	void addListener(WebDriverClosedListener listener);

	/**
	 * Shuts down browser instance even if the webdriver is in reusable mode
	 */
	void forceShutdown();
}
