/**
 * 
 */
package com.learn.design.patterns.app.header;

import com.learn.design.patterns.app.cart.Cart;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

/**
 * @author sku202
 *
 */
public class AppHeader {
	private String text;
	protected AppiumDriver<MobileElement> driver;

	public AppHeader(AppiumDriver<MobileElement> driver) {
		super();
		this.driver = driver;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Cart tapCart() {
		return new Cart();
	}
}
