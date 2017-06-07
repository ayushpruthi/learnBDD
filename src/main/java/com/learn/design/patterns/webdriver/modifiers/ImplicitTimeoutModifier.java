
package com.learn.design.patterns.webdriver.modifiers;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import com.learn.design.patterns.webdriver.WebDriverConfig;
/**
 * Describes a modifier for WebDriver instance - it will be triggered
 * <b>before</b> WebDriver creation. Modification can be controlled via
 * {@code shouldModify()} method. 
 * 
 *  @author Sachin Kumar 12 [skumar213@sapient.com]
 */
public class ImplicitTimeoutModifier implements WebDriverModifier {

    private int defaultTimeout;

    public ImplicitTimeoutModifier(WebDriverConfig webDriverConfig) {
	defaultTimeout = webDriverConfig.getDefaultTimeOut();
    }

    @Override
    public boolean shouldModify() {
	return true;
    }

    @Override
    public WebDriver modify(WebDriver webDriver) {
	webDriver.manage().timeouts().implicitlyWait(defaultTimeout, TimeUnit.SECONDS);
	return webDriver;
    }
}
