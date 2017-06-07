
package com.learn.design.patterns.webdriver.modifiers;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.learn.design.patterns.webdriver.WebDriverConfig;
import com.learn.design.patterns.webdriver.proxy.ProxyController;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.client.ClientUtil;

/**
 * Describes a modifier for Capabilities instance - it will be triggered
 * <b>before</b> WebDriver creation. Modification can be controlled via
 * {@code shouldModify()} method. Order of the execution can be controlled via
 * {@code getOrder()} method.
 * 
 * @author Sachin Kumar 12 [skumar213@sapient.com]
 */
public class EnableProxy implements CapabilitiesModifier {

	private boolean proxyEnabled;
	private String proxyIp;
	private ProxyController controller;

	public EnableProxy(WebDriverConfig webDriverConfig, ProxyController controller) {
		this.controller = controller;
		proxyEnabled = webDriverConfig.getProxyEnable();
		proxyIp = webDriverConfig.getProxyIP();
	}

	@Override
	public boolean shouldModify() {
		return proxyEnabled;
	}

	@Override
	public Capabilities modify(Capabilities capabilities) {
		return enableProxy(capabilities);
	}

	private DesiredCapabilities enableProxy(Capabilities capabilities) {
		DesiredCapabilities caps = new DesiredCapabilities(capabilities);
		try {
			InetAddress proxyInetAddress = InetAddress.getByName(proxyIp);
			BrowserMobProxy browserMobProxy = controller.startProxyServer(proxyInetAddress);
			Proxy seleniumProxy = ClientUtil.createSeleniumProxy(browserMobProxy, proxyInetAddress);
			caps.setCapability(CapabilityType.PROXY, seleniumProxy);
		} catch (UnknownHostException e) {
			throw new IllegalStateException(e);
		}
		return caps;
	}
}
