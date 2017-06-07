/**
 * 
 */
package com.learn.design.patterns.app.header;

import com.learn.design.patterns.app.Page;
import com.learn.design.patterns.app.SearchPane;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

/**
 * @author sku202
 *
 */
public class PageHeader extends AppHeader {

	public PageHeader(AppiumDriver<MobileElement> driver) {
		super(driver);
	}

	public SearchPane search() {
		return null;
	}

	public Page back() {
		return null;
	}
}
