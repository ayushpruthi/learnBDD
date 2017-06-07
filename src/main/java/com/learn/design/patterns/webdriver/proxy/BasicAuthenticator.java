package com.learn.design.patterns.webdriver.proxy;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import com.learn.design.patterns.webdriver.WebDriverConfig;

import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import net.lightbody.bmp.filters.RequestFilter;
import net.lightbody.bmp.util.HttpMessageContents;
import net.lightbody.bmp.util.HttpMessageInfo;

/**
 * Class to login in LDAP site
 * 
 * @author Sachin Kumar 12 [skumar213@sapient.com]
 */
public class BasicAuthenticator implements RequestFilter {
    private WebDriverConfig config;

    public BasicAuthenticator(WebDriverConfig config) {
	this.config = config;
    }

    @Override
    public HttpResponse filterRequest(HttpRequest request, HttpMessageContents contents, HttpMessageInfo messageInfo) {
	if (!StringUtils.isBlank(config.getUsername())) {
	    final String login = config.getUsername() + ":" + config.getPassword();
	    final String base64login = new String(Base64.encodeBase64(login.getBytes()));
	    request.headers().add("Authorization", "Basic " + base64login);
	}
	return null;
    }

}
