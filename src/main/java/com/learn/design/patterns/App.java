package com.learn.design.patterns;

import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.WebDriver;

import com.learn.design.patterns.utils.PropertyUtils;
import com.learn.design.patterns.webdriver.WebDriverConfig;
import com.learn.design.patterns.webdriver.WebDriverModifiers;
import com.learn.design.patterns.webdriver.WebDriverProvider;
import com.learn.design.patterns.webdriver.WebDriverRegistry;
import com.learn.design.patterns.webdriver.modifiers.EnableProxy;
import com.learn.design.patterns.webdriver.modifiers.ImplicitTimeoutModifier;
import com.learn.design.patterns.webdriver.modifiers.MaximizeModifier;
import com.learn.design.patterns.webdriver.proxy.BasicAuthenticator;
import com.learn.design.patterns.webdriver.proxy.ProxyCloser;
import com.learn.design.patterns.webdriver.proxy.ProxyController;
import com.learn.design.patterns.webdriver.proxy.RequestFilterRegistry;

public class App {

	public static void main(String[] args) {
		if (System.getProperty("configuration.paths") == null) {
			System.setProperty("configuration.paths", "src/main/resources/config/common");
		}

		Set<Properties> properties2 = PropertyUtils.getWebDriverProperties();
		for (Properties p : properties2) {
			WebDriverConfig config = new WebDriverConfig(p, PropertyUtils.gatherProperties());
			RequestFilterRegistry reg = new RequestFilterRegistry();
			reg.add(new BasicAuthenticator(config));
			ProxyController proxyController = new ProxyController(reg);
			EnableProxy proxy = new EnableProxy(config, proxyController);
			WebDriverModifiers webDriverModifiers = new WebDriverModifiers();
			webDriverModifiers.registerWebDriverModifier(new MaximizeModifier(config));
			webDriverModifiers.registerWebDriverModifier(new ImplicitTimeoutModifier(config));
			webDriverModifiers.registerCapabilitiesModifier(proxy);
			WebDriverProvider provider = new WebDriverProvider(config, webDriverModifiers);
			provider.registerClosedListener(new ProxyCloser(proxyController));
			WebDriver driver = provider.get();
			driver.get("http://google.com");
			driver.quit();
		}
		// System.out.println(properties);
		WebDriverRegistry.getWebDriverRegistry().shutdown();
	}

}
