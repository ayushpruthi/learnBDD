
package com.learn.design.patterns.webdriver.modifiers;

import org.openqa.selenium.WebDriver;

import com.learn.design.patterns.webdriver.WebDriverConfig;
/**
 * Describes a modifier for WebDriver instance - it will be triggered
 * <b>before</b> WebDriver creation. Modification can be controlled via
 * {@code shouldModify()} method. 
 * 
 *  @author Sachin Kumar 12 [skumar213@sapient.com]
 */
public class MaximizeModifier implements WebDriverModifier {

    private boolean maximize;

    public MaximizeModifier(WebDriverConfig webDriverConfig) {
	maximize = webDriverConfig.isMaximize();
    }

    @Override
    public boolean shouldModify() {
	return maximize;
    }

    @Override
    public WebDriver modify(WebDriver webDriver) {
	webDriver.manage().window().maximize();
	return webDriver;
    }
}
