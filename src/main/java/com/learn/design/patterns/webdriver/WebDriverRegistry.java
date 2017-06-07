
package com.learn.design.patterns.webdriver;

import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible for terminating browser processes after suite execution
 * when webdriver is using reusable mode.
 * 
 *  @author Sachin Kumar 12 [skumar213@sapient.com]
 */
public class WebDriverRegistry {

    private final List<ClosableWebDriver> drivers;
    private static WebDriverRegistry INSTANCE;

    private WebDriverRegistry() {
	drivers = new ArrayList<>();
    }

    public synchronized static WebDriverRegistry getWebDriverRegistry() {
	if (INSTANCE == null) {
	    INSTANCE = new WebDriverRegistry();
	}
	return INSTANCE;
    }

    /**
     * Register WebDriver instance that should be terminated at the end of the
     * suite
     *
     * @param driver
     *            instance of ClosingAwareWebDriver.
     */
    public void add(ClosableWebDriver driver) {
	drivers.add(driver);
    }

    /**
     * Shutdown all registered drivers
     */
    public void shutdown() {
	drivers.stream().filter(ClosableWebDriver::isAlive).forEach(ClosableWebDriver::forceShutdown);
    }

}
