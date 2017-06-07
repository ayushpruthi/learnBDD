/**
 * 
 */
package com.learn.design.patterns.app.header;

import java.awt.Menu;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

/**
 * @author sku202
 *
 */
public class CategoryHeader extends AppHeader {

	public CategoryHeader(AppiumDriver<MobileElement> driver) {
		super(driver);
	}

	public Menu getMenu() {
		return null;
	}

}
