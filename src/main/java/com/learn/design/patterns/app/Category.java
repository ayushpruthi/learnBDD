/**
 * 
 */
package com.learn.design.patterns.app;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

/**
 * @author sku202
 *
 */
public class Category {
	private final String name;
	private List<Category> categories;
	private List<ChildCategory> childCategories;

	public Category(AppiumDriver<MobileElement> driver, String name) {
		super();
		this.name = name;
		categories = new ArrayList<>();
		childCategories = new ArrayList<>();
		for (MobileElement e : driver.findElementsById("com.flipkart.android:id/header_container")) {
			String text = e.findElement(By.id("com.flipkart.android:id/title")).getText();
			if (!e.findElements(By.id("com.flipkart.android:id/expandable_icon")).isEmpty()) {
				categories.add(new Category(driver, text));
			} else {
				childCategories.add(new ChildCategory(driver, text));
			}
		}
	}

	public Category getCategoryByName(String name) {
		for (Category category : categories) {
			if (category.getName().equals(name))
				return category;
		}
		return null;
	}

	public ChildCategory getChildCategoryByName(String name) {
		for (ChildCategory category : childCategories) {
			if (category.getName().equals(name))
				return category;
		}
		return null;
	}

	public String getName() {
		return name;
	}
}
