
package com.learn.design.patterns.webdriver.proxy;

import java.net.InetAddress;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.proxy.CaptureType;

/**
 * This class can be used to start and stop traffic interception.
 * 
 * @author Sachin Kumar 12 [skumar213@sapient.com]
 */
public class ProxyController {

    private static final Logger LOG = LoggerFactory.getLogger(ProxyController.class);

    private static final EnumSet<CaptureType> CAPTURE_TYPES = EnumSet.of(CaptureType.REQUEST_HEADERS,
	    CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_HEADERS, CaptureType.RESPONSE_CONTENT);

    private BrowserMobProxy browserMobProxy;
    private final Set<ProxyEventListener> eventListeners;
    private final RequestFilterRegistry filterRegistry;

    public Set<ProxyEventListener> getEventListeners() {
	return eventListeners;
    }

    public RequestFilterRegistry getFilterRegistry() {
	return filterRegistry;
    }

    public ProxyController(RequestFilterRegistry filterRegistry) {
	this.browserMobProxy = new BrowserMobProxyServer();
	this.eventListeners = new HashSet<>();
	this.filterRegistry = filterRegistry;
    }

    public void registerProxyEventListener(ProxyEventListener listener) {
	eventListeners.add(listener);
    }

    public void removeProxyEventListener(ProxyEventListener listener) {
	eventListeners.remove(listener);
    }

    public BrowserMobProxy startProxyServer(InetAddress proxyAddress) {
	if (!browserMobProxy.isStarted()) {
	    try {
		browserMobProxy.start(0, proxyAddress);
		browserMobProxy.addRequestFilter(filterRegistry);

	    } catch (Exception e) {
		LOG.error("Can't start proxy", e);
	    }
	}
	return browserMobProxy;
    }

    public BrowserMobProxy getBrowserMobProxy() {
	return browserMobProxy;
    }

    public int getPort() throws Exception {
	if (browserMobProxy.isStarted()) {
	    return browserMobProxy.getPort();
	}
	throw new Exception("Proxy is not started");
    }

    public void stopProxyServer() {
	if (browserMobProxy.isStarted()) {
	    try {
		browserMobProxy.stop();
		// proxy cannot be started again thus new instance should be
		// created
		// for more info see
		// https://github.com/lightbody/browsermob-proxy/issues/264
		browserMobProxy = new BrowserMobProxyServer();
	    } catch (Exception e) {
		LOG.error("Can't stop proxy", e);
	    }
	}
    }

    public void startAnalysis() {
	browserMobProxy.setHarCaptureTypes(CAPTURE_TYPES);
	browserMobProxy.newHar("page");
	for (ProxyEventListener l : eventListeners) {
	    l.listeningStarted();
	}
    }

    public void stopAnalysis() {
	browserMobProxy.disableHarCaptureTypes(CAPTURE_TYPES);
	for (ProxyEventListener l : eventListeners) {
	    l.listeningStopped(browserMobProxy.getHar());
	}
    }
}
