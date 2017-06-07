/**
 * 
 */
package com.learn.design.patterns.app;

import org.openqa.selenium.support.ui.LoadableComponent;

import io.appium.java_client.AppiumDriver;

/**
 * @author sku202
 *
 */
public class Page extends LoadableComponent<Page> {
	protected boolean loaded;

	public Page(AppiumDriver driver) {
		while (!loaded) {

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openqa.selenium.support.ui.LoadableComponent#load()
	 */
	@Override
	protected void load() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openqa.selenium.support.ui.LoadableComponent#isLoaded()
	 */
	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub

	}

}
