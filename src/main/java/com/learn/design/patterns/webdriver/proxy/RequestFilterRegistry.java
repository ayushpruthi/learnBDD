
package com.learn.design.patterns.webdriver.proxy;

import java.util.LinkedHashSet;
import java.util.Set;

import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import net.lightbody.bmp.filters.RequestFilter;
import net.lightbody.bmp.util.HttpMessageContents;
import net.lightbody.bmp.util.HttpMessageInfo;

/**
 * This class allows to dynamically register and unregister RequestFilter
 * objects in the current proxy.
 * 
 * @author Sachin Kumar 12 [skumar213@sapient.com]
 */
public class RequestFilterRegistry implements RequestFilter {

    private final Set<RequestFilter> filters = new LinkedHashSet<>();

    /**
     * Register new filter
     *
     * @param filter
     *            filter to be added to the Set of filters.
     */
    public void add(RequestFilter filter) {
	filters.add(filter);
    }

    /**
     * Unregister filter (it won't get anymore events)
     *
     * @param filter
     *            filter to be removed to the Set of filters.
     */
    public void remove(RequestFilter filter) {
	filters.remove(filter);
    }

    @Override
    public HttpResponse filterRequest(HttpRequest httpRequest, HttpMessageContents httpMessageContents,
	    HttpMessageInfo httpMessageInfo) {
	for (RequestFilter requestFilter : filters) {
	    HttpResponse response = requestFilter.filterRequest(httpRequest, httpMessageContents, httpMessageInfo);
	    if (response != null) {
		return response;
	    }
	}
	return null;
    }
}
