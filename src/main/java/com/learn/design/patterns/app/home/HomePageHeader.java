/**
 * 
 */
package com.learn.design.patterns.app.home;

import com.learn.design.patterns.app.header.AppHeader;
import com.learn.design.patterns.app.notification.NotificationPage;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

/**
 * @author sku202
 *
 */
public class HomePageHeader extends AppHeader {

	public HomePageHeader(AppiumDriver<MobileElement> driver) {
		super(driver);
	}

	public NotificationPage tapNotification() {
		// To DO
		return null;
	}

	public AppMenu getMenu() {
		return null;
	}
}
