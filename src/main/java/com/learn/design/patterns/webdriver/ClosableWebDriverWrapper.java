
package com.learn.design.patterns.webdriver;

import java.util.HashSet;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * WebDriver wrapper implementing ClosingAwareWebDriver interface.
 * 
 * @author Sachin Kumar 12 [skumar213@sapient.com]
 */
public class ClosableWebDriverWrapper extends WebDriverWrapper implements ClosableWebDriver {

	private static final Logger LOG = LoggerFactory.getLogger(ClosableWebDriverWrapper.class);

	private static final String BLANK_PAGE = "about:blank";

	private final Set<WebDriverClosedListener> closedListeners;

	private final boolean maximize;

	private final boolean reusable;

	private final boolean mobile;

	private boolean alive = true;

	/**
	 * Constructs ClosingAwareWebDriver.
	 *
	 * @param driver
	 *            WebDriver to be wrapped by ClosingAwareWebDriver
	 * @param frameSwitcher
	 *            FrameSwitcher instance
	 * @param maximize
	 *            should the browser be maximized during reset (affects reusable
	 *            mode)
	 * @param reusable
	 *            overrides behavior of close() and quit() methods. If the
	 *            browser is reusable the cookies for current domain will be
	 *            deleted and browser will navigate to about:blank page instead
	 *            of terminating the process.
	 * @param mobile
	 *            denotes mobile web driver. Mobile web drivers do not support
	 *            alert checking.
	 */
	public ClosableWebDriverWrapper(WebDriver driver, boolean maximize, boolean reusable, boolean mobile) {
		super(driver);
		this.closedListeners = new HashSet<>();
		this.maximize = maximize;
		this.reusable = reusable;
		this.mobile = mobile;
	}

	/**
	 * Closes the driver and informs the listeners about driver's death.
	 */
	@Override
	public void close() {
		if (reusable) {
			cleanDriver();
			sendEvent(false);
		} else {
			super.close();
			alive = false;
			sendEvent(true);
		}
	}

	/**
	 * Quits the driver and informs the listeners about driver's death.
	 */
	@Override
	public void quit() {
		if (reusable) {
			cleanDriver();
			sendEvent(false);
		} else {
			super.quit();
			alive = false;
			sendEvent(true);
		}
	}

	@Override
	public boolean isAlive() {
		return alive;
	}

	@Override
	public void addListener(WebDriverClosedListener listener) {
		closedListeners.add(listener);
	}

	@Override
	public void forceShutdown() {
		super.quit();
		alive = false;
		sendEvent(true);
	}

	private void sendEvent(boolean terminated) {
		closedListeners.stream().forEach(listener -> listener.onWebDriverClosed(terminated));
	}

	private void cleanDriver() {
		manage().deleteAllCookies();
		get(BLANK_PAGE);
		// if (!mobile) {
		// try {
		// switchTo().alert().accept();
		// } catch (NoAlertPresentException e) {
		// LOG.debug("No alert was present when returnDriver was executed: {}",
		// e);
		// }
		// }
		if (maximize) {
			manage().window().maximize();
		}
	}
}
