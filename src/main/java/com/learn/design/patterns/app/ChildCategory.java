/**
 * 
 */
package com.learn.design.patterns.app;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

/**
 * @author sku202
 *
 */
public class ChildCategory {
	private final String name;
	private AppiumDriver<MobileElement> driver;

	public ChildCategory(AppiumDriver<MobileElement> driver, String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Page getPage() {
		return null;
	}
}
