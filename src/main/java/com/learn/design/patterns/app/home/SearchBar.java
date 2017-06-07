/**
 * 
 */
package com.learn.design.patterns.app.home;

import java.io.IOException;
import java.util.List;

import com.learn.design.patterns.app.Page;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

/**
 * @author sku202
 *
 */
public class SearchBar {

	private MobileElement searchField;
	private MobileElement barCode;
	private AppiumDriver<MobileElement> driver;

	public SearchBar(AppiumDriver<MobileElement> driver) {
		super();
		this.driver = driver;
	}

	public List<String> getSuggetion(String text) {
		return null;
	}

	public Page search(String text) {
		return null;
	}

	/**
	 * open bar code scanner to scan the bar code of the products
	 * 
	 * @throws IOException:
	 *             When unable to communicate with camera
	 * @throws UnsupportedOperationException:
	 *             Scanner is opened but this operation is not supported for now
	 *             :)
	 */
	public void tapBarcode() throws IOException {
		if (driver.findElementsById("").isEmpty()) {
			throw new IOException("Unable to open barcode scanner");
		}
		throw new UnsupportedOperationException("Unable to simulate camera operations");
	}
}
