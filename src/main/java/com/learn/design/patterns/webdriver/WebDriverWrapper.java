
package com.learn.design.patterns.webdriver;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.ContextAware;
import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.Rotatable;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.html5.Location;
import org.openqa.selenium.html5.LocationContext;
import org.openqa.selenium.internal.FindsByClassName;
import org.openqa.selenium.internal.FindsByCssSelector;
import org.openqa.selenium.internal.FindsById;
import org.openqa.selenium.internal.FindsByLinkText;
import org.openqa.selenium.internal.FindsByName;
import org.openqa.selenium.internal.FindsByTagName;
import org.openqa.selenium.internal.FindsByXPath;
import org.openqa.selenium.internal.HasIdentity;
import org.openqa.selenium.remote.Response;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import io.appium.java_client.ExecutesMethod;
import io.appium.java_client.FindsByAccessibilityId;
import io.appium.java_client.FindsByAndroidUIAutomator;
import io.appium.java_client.FindsByFluentSelector;
import io.appium.java_client.FindsByIosUIAutomation;
import io.appium.java_client.FindsByWindowsAutomation;
import io.appium.java_client.HasAppStrings;
import io.appium.java_client.HasDeviceTime;
import io.appium.java_client.HasSessionDetails;
import io.appium.java_client.HidesKeyboard;
import io.appium.java_client.HidesKeyboardWithKeyName;
import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.InteractsWithFiles;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.PerformsActions;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.PressesKeyCode;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.Connection;
import io.appium.java_client.android.HasNetworkConnection;
import io.appium.java_client.android.PushesFiles;
import io.appium.java_client.android.StartsActivity;
import io.appium.java_client.ios.ShakesDevice;
import io.appium.java_client.windows.WindowsKeyCode;

/**
 * This class is a "simple" extension of Selenium's EventFiringWebDriver that
 * additionally implements several useful interfaces.
 * 
 * @author Sachin Kumar 12 [skumar213@sapient.com]
 */
public class WebDriverWrapper extends EventFiringWebDriver implements FindsById, FindsByClassName, FindsByLinkText,
		FindsByName, FindsByCssSelector, FindsByTagName, FindsByXPath, HasCapabilities, MobileDriver, Rotatable,
		LocationContext, ContextAware, InteractsWithApps, HasAppStrings, HasNetworkConnection, PushesFiles,
		StartsActivity, FindsByAndroidUIAutomator, HasDeviceTime, HidesKeyboard, HidesKeyboardWithKeyName,
		PressesKeyCode, ShakesDevice, HasSessionDetails, FindsByIosUIAutomation, HasIdentity, PerformsTouchActions,
		FindsByFluentSelector, FindsByAccessibilityId, PerformsActions, FindsByWindowsAutomation, WindowsKeyCode {

	/**
	 * Constructs WebDriverWrapper.
	 *
	 * @param driver
	 *            instance of WebDriver.
	 * @param frameSwitcher
	 *            instance of FrameSwitcher.
	 */
	public WebDriverWrapper(WebDriver driver) {
		super(driver);
	}

	/**
	 * @return The capabilities of the current driver.
	 */
	@Override
	public Capabilities getCapabilities() {
		return ((HasCapabilities) super.getWrappedDriver()).getCapabilities();
	}

	/**
	 * Finds element by xpath.
	 */
	@Override
	public WebElement findElementByXPath(String xPath) {
		return ((FindsByXPath) super.getWrappedDriver()).findElementByXPath(xPath);
	}

	/**
	 * Finds elements by xpath.
	 */
	@Override
	public List<WebElement> findElementsByXPath(String xPath) {
		return ((FindsByXPath) super.getWrappedDriver()).findElementsByXPath(xPath);
	}

	/**
	 * Finds element by tag name.
	 */
	@Override
	public WebElement findElementByTagName(String tagName) {
		return ((FindsByTagName) super.getWrappedDriver()).findElementByTagName(tagName);
	}

	/**
	 * Finds elements by tag name.
	 */
	@Override
	public List<WebElement> findElementsByTagName(String tagName) {
		return ((FindsByTagName) super.getWrappedDriver()).findElementsByTagName(tagName);
	}

	/**
	 * Finds element by css selector.
	 */
	@Override
	public WebElement findElementByCssSelector(String cssSelector) {
		return ((FindsByCssSelector) super.getWrappedDriver()).findElementByCssSelector(cssSelector);
	}

	/**
	 * Finds elements by css selector.
	 */
	@Override
	public List<WebElement> findElementsByCssSelector(String cssSelector) {
		return ((FindsByCssSelector) super.getWrappedDriver()).findElementsByCssSelector(cssSelector);
	}

	/**
	 * Finds element by name.
	 */
	@Override
	public WebElement findElementByName(String name) {
		return ((FindsByName) super.getWrappedDriver()).findElementByName(name);
	}

	/**
	 * Finds elements by name.
	 */
	@Override
	public List<WebElement> findElementsByName(String name) {
		return ((FindsByName) super.getWrappedDriver()).findElementsByName(name);
	}

	/**
	 * Finds element by link text.
	 */
	@Override
	public WebElement findElementByLinkText(String linkText) {
		return ((FindsByLinkText) super.getWrappedDriver()).findElementByLinkText(linkText);
	}

	/**
	 * Finds elements by link text.
	 */
	@Override
	public List<WebElement> findElementsByLinkText(String linkText) {
		return ((FindsByLinkText) super.getWrappedDriver()).findElementsByLinkText(linkText);
	}

	/**
	 * Finds element by partial link text.
	 */
	@Override
	public WebElement findElementByPartialLinkText(String partialLinkText) {
		return ((FindsByLinkText) super.getWrappedDriver()).findElementByPartialLinkText(partialLinkText);
	}

	/**
	 * Finds elements by partial link text.
	 */
	@Override
	public List<WebElement> findElementsByPartialLinkText(String partialLinkText) {
		return ((FindsByLinkText) super.getWrappedDriver()).findElementsByPartialLinkText(partialLinkText);
	}

	/**
	 * Finds element by class name.
	 */
	@Override
	public WebElement findElementByClassName(String className) {
		return ((FindsByClassName) super.getWrappedDriver()).findElementByClassName(className);
	}

	/**
	 * Finds elements by class name.
	 */
	@Override
	public List<WebElement> findElementsByClassName(String className) {
		return ((FindsByClassName) super.getWrappedDriver()).findElementsByClassName(className);
	}

	/**
	 * Finds element by id.
	 */
	@Override
	public WebElement findElementById(String id) {
		return ((FindsById) super.getWrappedDriver()).findElementById(id);
	}

	/**
	 * Finds elements by id.
	 */
	@Override
	public List<WebElement> findElementsById(String id) {
		return ((FindsById) super.getWrappedDriver()).findElementsById(id);
	}

	@Override
	public TouchAction performTouchAction(TouchAction touchAction) {
		return ((PerformsTouchActions) super.getWrappedDriver()).performTouchAction(touchAction);
	}

	@Override
	public void performMultiTouchAction(MultiTouchAction multiAction) {
		((PerformsTouchActions) super.getWrappedDriver()).performMultiTouchAction(multiAction);
	}

	@Override
	public void launchApp() {
		((InteractsWithApps) super.getWrappedDriver()).launchApp();
	}

	@Override
	public void installApp(String appPath) {
		((InteractsWithApps) super.getWrappedDriver()).installApp(appPath);
	}

	@Override
	public boolean isAppInstalled(String bundleId) {
		return ((InteractsWithApps) super.getWrappedDriver()).isAppInstalled(bundleId);
	}

	@Override
	public void resetApp() {
		((InteractsWithApps) super.getWrappedDriver()).resetApp();
	}

	@Override
	public void runAppInBackground(int seconds) {
		((InteractsWithApps) super.getWrappedDriver()).runAppInBackground(seconds);
	}

	@Override
	public void removeApp(String bundleId) {
		((InteractsWithApps) super.getWrappedDriver()).removeApp(bundleId);
	}

	@Override
	public void closeApp() {
		((InteractsWithApps) super.getWrappedDriver()).closeApp();
	}

	@Override
	public byte[] pullFile(String remotePath) {
		return ((InteractsWithFiles) super.getWrappedDriver()).pullFile(remotePath);
	}

	@Override
	public byte[] pullFolder(String remotePath) {
		return ((InteractsWithFiles) super.getWrappedDriver()).pullFolder(remotePath);
	}

	@Override
	public Location location() {
		return ((LocationContext) super.getWrappedDriver()).location();
	}

	@Override
	public void setLocation(Location arg0) {
		((LocationContext) super.getWrappedDriver()).setLocation(arg0);
	}

	@Override
	public WebElement findElementByAccessibilityId(String using) {
		return ((FindsByAccessibilityId) super.getWrappedDriver()).findElementByAccessibilityId(using);
	}

	@Override
	public List<WebElement> findElementsByAccessibilityId(String using) {
		return ((FindsByAccessibilityId) super.getWrappedDriver()).findElementsByAccessibilityId(using);
	}

	@Override
	public ScreenOrientation getOrientation() {
		return ((Rotatable) super.getWrappedDriver()).getOrientation();
	}

	@Override
	public void rotate(DeviceRotation deviceRotation) {
		((Rotatable) super.getWrappedDriver()).rotate(deviceRotation);
	}

	@Override
	public DeviceRotation rotation() {
		return ((Rotatable) super.getWrappedDriver()).rotation();
	}

	@Override
	public void rotate(ScreenOrientation screenOrientation) {
		((Rotatable) super.getWrappedDriver()).rotate(screenOrientation);
	}

	@Override
	public WebDriver context(String name) {
		return ((ContextAware) super.getWrappedDriver()).context(name);
	}

	@Override
	public String getContext() {
		return ((ContextAware) super.getWrappedDriver()).getContext();
	}

	@Override
	public Set<String> getContextHandles() {
		return ((ContextAware) super.getWrappedDriver()).getContextHandles();
	}

	@Override
	public Response execute(String driverCommand, Map parameters) {
		return ((MobileDriver) super.getWrappedDriver()).execute(driverCommand, parameters);
	}

	@Override
	public WebElement findElementByIosUIAutomation(String using) {
		return ((FindsByIosUIAutomation) super.getWrappedDriver()).findElementByIosUIAutomation(using);
	}

	@Override
	public List<WebElement> findElementsByIosUIAutomation(String using) {
		return ((FindsByIosUIAutomation) super.getWrappedDriver()).findElementsByIosUIAutomation(using);
	}

	@Override
	public WebElement findElementByAndroidUIAutomator(String using) {
		return ((FindsByAndroidUIAutomator) super.getWrappedDriver()).findElementByAndroidUIAutomator(using);
	}

	@Override
	public List<WebElement> findElementsByAndroidUIAutomator(String using) {
		return ((FindsByAndroidUIAutomator) super.getWrappedDriver()).findElementsByAndroidUIAutomator(using);
	}

	@Override
	public void startActivity(String appPackage, String appActivity, String appWaitPackage, String appWaitActivity,
			boolean stopApp) throws IllegalArgumentException {
		((StartsActivity) super.getWrappedDriver()).startActivity(appPackage, appActivity, appWaitPackage,
				appWaitActivity, stopApp);
	}

	@Override
	public void startActivity(String appPackage, String appActivity, String appWaitPackage, String appWaitActivity) {
		((StartsActivity) super.getWrappedDriver()).startActivity(appPackage, appActivity, appWaitPackage,
				appWaitActivity);
	}

	@Override
	public void startActivity(String appPackage, String appActivity) {
		((StartsActivity) super.getWrappedDriver()).startActivity(appPackage, appActivity);
	}

	@Override
	public void startActivity(String appPackage, String appActivity, String appWaitPackage, String appWaitActivity,
			String intentAction, String intentCategory, String intentFlags, String intentOptionalArgs)
			throws IllegalArgumentException {
		((StartsActivity) super.getWrappedDriver()).startActivity(appPackage, appActivity, appWaitPackage,
				appWaitActivity, intentAction, intentCategory, intentFlags, intentOptionalArgs);

	}

	@Override
	public void startActivity(String appPackage, String appActivity, String appWaitPackage, String appWaitActivity,
			String intentAction, String intentCategory, String intentFlags, String intentOptionalArgs, boolean stopApp)
			throws IllegalArgumentException {
		((StartsActivity) super.getWrappedDriver()).startActivity(appPackage, appActivity, appWaitPackage,
				appWaitActivity, intentAction, intentCategory, intentFlags, intentOptionalArgs, stopApp);
	}

	@Override
	public void pushFile(String remotePath, byte[] base64Data) {
		((PushesFiles) super.getWrappedDriver()).pushFile(remotePath, base64Data);
	}

	@Override
	public void pushFile(String remotePath, File file) throws IOException {
		((PushesFiles) super.getWrappedDriver()).pushFile(remotePath, file);
	}

	@Override
	public TargetLocator switchTo() {
		return super.getWrappedDriver().switchTo();
	}

	@Override
	public Map<String, String> getAppStringMap() {
		return ((HasAppStrings) super.getWrappedDriver()).getAppStringMap();
	}

	@Override
	public Map<String, String> getAppStringMap(String language) {
		return ((HasAppStrings) super.getWrappedDriver()).getAppStringMap(language);
	}

	@Override
	public Map<String, String> getAppStringMap(String language, String stringFile) {
		return ((HasAppStrings) super.getWrappedDriver()).getAppStringMap(language, stringFile);
	}

	@Override
	public void pressKeyCode(int key) {
		((AndroidDriver) super.getWrappedDriver()).longPressKeyCode(key);
	}

	@Override
	public void pressKeyCode(int key, Integer metastate) {
		((AndroidDriver) super.getWrappedDriver()).pressKeyCode(key, metastate);
	}

	@Override
	public void longPressKeyCode(int key) {
		((AndroidDriver) super.getWrappedDriver()).longPressKeyCode(key);
	}

	@Override
	public void longPressKeyCode(int key, Integer metastate) {
		((AndroidDriver) super.getWrappedDriver()).longPressKeyCode(key, metastate);
	}

	@Override
	public void setConnection(Connection connection) {
		((HasNetworkConnection) super.getWrappedDriver()).setConnection(connection);
	}

	@Override
	public Connection getConnection() {
		return ((HasNetworkConnection) super.getWrappedDriver()).getConnection();
	}

	@Override
	public String getId() {
		return UUID.randomUUID().toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.appium.java_client.ExecutesMethod#execute(java.lang.String)
	 */
	@Override
	public Response execute(String arg0) {
		return ((ExecutesMethod) super.getWrappedDriver()).execute(arg0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * io.appium.java_client.FindsByFluentSelector#findElement(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public WebElement findElement(String arg0, String arg1) {
		return ((FindsByFluentSelector) super.getWrappedDriver()).findElement(arg0, arg1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.appium.java_client.FindsByFluentSelector#findElements(java.lang.
	 * String, java.lang.String)
	 */
	@Override
	public List findElements(String arg0, String arg1) {
		((FindsByFluentSelector) super.getWrappedDriver()).findElements(arg0, arg1);
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.appium.java_client.HasSessionDetails#isBrowser()
	 */
	@Override
	public boolean isBrowser() {
		return ((HasSessionDetails) super.getWrappedDriver()).isBrowser();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.appium.java_client.PerformsActions#perform()
	 */
	@Override
	public PerformsActions perform() {
		return ((PerformsActions) super.getWrappedDriver()).perform();
	}

}
