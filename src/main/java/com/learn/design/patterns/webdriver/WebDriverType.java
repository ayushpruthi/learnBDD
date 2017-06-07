
package com.learn.design.patterns.webdriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobilePlatform;

/**
 * Enum represent available web driver types.
 * 
 * @author Sachin Kumar 12 [skumar213@sapient.com]
 */
public enum WebDriverType {
	FIREFOX {
		@Override
		public WebDriver create(Capabilities capabilities, WebDriverConfig webDriverConfig) {
			return getWebDriverWithProxyCookieSupport(webDriverConfig, new FirefoxDriver(capabilities));
		}
	},
	MARIONETTE {
		@Override
		public WebDriver create(Capabilities capabilities, WebDriverConfig webDriverConfig) {
			DesiredCapabilities caps = DesiredCapabilities.firefox();
			caps.setCapability("marionette", true);
			return getWebDriverWithProxyCookieSupport(webDriverConfig, new FirefoxDriver(capabilities));
		}
	},
	CHROME {
		@Override
		public WebDriver create(Capabilities capabilities, WebDriverConfig webDriverConfig) {
			return getWebDriverWithProxyCookieSupport(webDriverConfig, new ChromeDriver(capabilities));
		}
	},
	IE {
		@Override
		public WebDriver create(Capabilities capabilities, WebDriverConfig webDriverConfig) {
			return getWebDriverWithProxyCookieSupport(webDriverConfig, new InternetExplorerDriver(capabilities));
		}
	},
	SAFARI {
		@Override
		public WebDriver create(Capabilities capabilities, WebDriverConfig webDriverConfig) {
			return getWebDriverWithProxyCookieSupport(webDriverConfig, new SafariDriver(capabilities));
		}
	},
	HTML {
		@Override
		public WebDriver create(Capabilities capabilities, WebDriverConfig webDriverConfig) {
			return getWebDriverWithProxyCookieSupport(webDriverConfig, new HtmlUnitDriver(capabilities));
		}
	},
	GHOST {
		@Override
		public WebDriver create(Capabilities capabilities, WebDriverConfig webDriverConfig) {
			return getWebDriverWithProxyCookieSupport(webDriverConfig, new PhantomJSDriver());
		}
	},
	APPIUM {
		@Override
		public WebDriver create(Capabilities capabilities, WebDriverConfig webDriverConfig) {
			return getWebDriverWithProxyCookieSupport(webDriverConfig,
					createMobileDriver(capabilities, webDriverConfig));
		}

		private WebDriver createMobileDriver(Capabilities capabilities, WebDriverConfig webDriverConfig) {
			final URL url;
			try {
				url = new URL(webDriverConfig.getAppiumURL());
			} catch (MalformedURLException e) {
				throw new IllegalArgumentException(e);
			}

			final String platform = webDriverConfig.getCapablilitiesPlatform();
			switch (platform) {
			case MobilePlatform.ANDROID:
				return new AndroidDriver(url, capabilities);

			case MobilePlatform.IOS:
				return new IOSDriver(url, capabilities);

			default:
				throw new IllegalArgumentException(
						String.format("webdriver.cap.platformName not configured correctly. Set it either to %s or %s",
								MobilePlatform.ANDROID, MobilePlatform.IOS));
			}
		}
	},
	REMOTE {
		@Override
		public WebDriver create(Capabilities capabilities, WebDriverConfig webDriverConfig) {
			final URL url;
			try {
				url = new URL(webDriverConfig.getRemoteURL());
			} catch (MalformedURLException e) {
				throw new IllegalArgumentException(e);
			}
			WebDriver driver = new RemoteWebDriver(url, capabilities);
			return getWebDriverWithProxyCookieSupport(webDriverConfig, driver);
		}
	},
	XVFB {
		@Override
		public WebDriver create(Capabilities capabilities, WebDriverConfig webDriverConfig) {
			final File firefoxPath = new File(webDriverConfig.getFirefoxBin());
			FirefoxBinary firefoxBinary = new FirefoxBinary(firefoxPath);
			firefoxBinary.setEnvironmentProperty("DISPLAY", webDriverConfig.getXvfbID());
			FirefoxOptions options = new FirefoxOptions();
			options.setBinary(firefoxBinary);
			options.addDesiredCapabilities(capabilities);
			return getWebDriverWithProxyCookieSupport(webDriverConfig, new FirefoxDriver(options));
		}
	};

	private static WebDriver getWebDriverWithProxyCookieSupport(WebDriverConfig webDriverConfig, WebDriver driver) {
		if (Boolean.valueOf(webDriverConfig.getProxyCookie())) {
			driver.get(webDriverConfig.getBaseURL());
			Cookie cookie = new Cookie(webDriverConfig.getProxyCookieName(), webDriverConfig.getProxyCookieValue(),
					"." + webDriverConfig.getProxyCookieDomain(), "/", DateUtils.addMonths(new Date(), 1));
			driver.manage().addCookie(cookie);
		}
		return driver;
	}

	private static final Logger LOG = LoggerFactory.getLogger(WebDriverType.class);

	public abstract WebDriver create(Capabilities capabilities, WebDriverConfig webDriverConfig);

	/**
	 * Returns WebDriverType for name
	 *
	 * @param webDriverConfig
	 *            WebDriverConfig
	 * @return WebDriverType
	 */
	public static WebDriverType get(WebDriverConfig webDriverConfig) {
		WebDriverType webDriverType = WebDriverType.HTML;
		if (StringUtils.isNotBlank(webDriverConfig.getType())) {
			try {
				webDriverType = WebDriverType.valueOf(webDriverConfig.getType().toUpperCase());
			} catch (IllegalArgumentException e) {
				LOG.error("Illegal WebDriver type: " + webDriverConfig.getType(), e);
			}
		}
		return webDriverType;
	}
}
