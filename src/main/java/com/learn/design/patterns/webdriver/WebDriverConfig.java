package com.learn.design.patterns.webdriver;

import java.util.Properties;

/**
 * Gathers all Capabilities and WebDriver modifiers from web driver properties
 * file.
 * 
 * @author Sachin Kumar 12 [skumar213@sapient.com]
 */
public class WebDriverConfig {
    private final String appiumURL;
    private final String firefoxBin;
    private final String capablilitiesPlatform;
    private final int defaultTimeOut;
    private final boolean maximize;
    private final boolean mobile;
    private final String proxyCookie;
    private final String proxyCookieName;
    private final String proxyCookieValue;
    private final String proxyCookieDomain;
    private final boolean reusable;
    private final String type;
    private final String xvfbID;
    private final String remoteURL;
    private final boolean proxyEnable;
    private final String proxyIP;
    private final String baseURL;
    private final String username;
    private final String password;
    private final Properties properties;

    public WebDriverConfig(Properties properties,Properties globalProperties) {
	this.properties = globalProperties;
	appiumURL = properties.getProperty("webdriver.appium.url", "");
	firefoxBin = properties.getProperty("webdriver.firefox.bin", "");
	capablilitiesPlatform = properties.getProperty("webdriver.cap.platformName", "");
	defaultTimeOut = Integer.parseInt(properties.getProperty("webdriver.defaultTimeout", "10"));
	maximize = Boolean.parseBoolean(properties.getProperty("webdriver.maximize", "true"));
	mobile = Boolean.parseBoolean(properties.getProperty("webdriver.mobile", "false"));
	proxyCookie = properties.getProperty("webdriver.secure.proxy.cookie", "");
	proxyCookieName = properties.getProperty("webdriver.secure.proxy.cookie_name", "");
	proxyCookieValue = properties.getProperty("webdriver.secure.proxy.cookie_value", "");
	proxyCookieDomain = properties.getProperty("webdriver.secure.proxy.cookie_domain", "");
	reusable = Boolean.parseBoolean(properties.getProperty("webdriver.reusable", "true"));
	type = properties.getProperty("webdriver.type", "");
	xvfbID = properties.getProperty("webdriver.xvfb.id", "");
	remoteURL = properties.getProperty("webdriver.url", "");
	proxyEnable = Boolean.parseBoolean(globalProperties.getProperty("proxy.enabled", "false"));
	proxyIP = globalProperties.getProperty("proxy.ip", "127.0.0.1");
	baseURL = globalProperties.getProperty("base.url", "");
	username = globalProperties.getProperty("proxy.username", "");
	password = globalProperties.getProperty("proxy.password", "");
    }

    public Properties getProperties() {
	return properties;
    }

    public String getAppiumURL() {
	return appiumURL;
    }

    public String getFirefoxBin() {
	return firefoxBin;
    }

    public String getCapablilitiesPlatform() {
	return capablilitiesPlatform;
    }

    public int getDefaultTimeOut() {
	return defaultTimeOut;
    }

    public boolean isMaximize() {
	return maximize;
    }

    public boolean isMobile() {
	return mobile;
    }

    public String getProxyCookie() {
	return proxyCookie;
    }

    public String getProxyCookieName() {
	return proxyCookieName;
    }

    public String getProxyCookieValue() {
	return proxyCookieValue;
    }

    public String getProxyCookieDomain() {
	return proxyCookieDomain;
    }

    public boolean isReusable() {
	return reusable;
    }

    public String getType() {
	return type;
    }

    public String getXvfbID() {
	return xvfbID;
    }

    public String getRemoteURL() {
	return remoteURL;
    }

    public boolean getProxyEnable() {
	return proxyEnable;
    }

    public String getProxyIP() {
	return proxyIP;
    }

    public String getBaseURL() {
	return baseURL;
    }

    public String getUsername() {
	return username;
    }

    public String getPassword() {
	return password;
    }

}