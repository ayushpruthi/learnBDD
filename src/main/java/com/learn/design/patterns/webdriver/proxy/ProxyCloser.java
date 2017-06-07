
package com.learn.design.patterns.webdriver.proxy;

import com.learn.design.patterns.webdriver.WebDriverClosedListener;

/**
 * This class stops {@link net.lightbody.bmp.BrowserMobProxy} after closing
 * webdriver
 * 
 * @author Sachin Kumar 12 [skumar213@sapient.com]
 */
public class ProxyCloser implements WebDriverClosedListener {

    private ProxyController controller;

    public ProxyCloser(ProxyController controller) {
	this.controller = controller;
    }

    @Override
    public void onWebDriverClosed(boolean terminated) {
	if (terminated) {
	    controller.stopProxyServer();
	}
    }

}
