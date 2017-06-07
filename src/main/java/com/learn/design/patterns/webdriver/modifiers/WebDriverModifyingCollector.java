
package com.learn.design.patterns.webdriver.modifiers;

import java.util.Collections;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import org.openqa.selenium.WebDriver;

/**
 * Collector that applies WebDriverModifiers to WebDriver.
 * 
 * @author Sachin Kumar 12 [skumar213@sapient.com]
 */
public class WebDriverModifyingCollector implements Collector<WebDriverModifier, WebDriver, WebDriver> {

    private final WebDriver webdriver;

    public WebDriverModifyingCollector(WebDriver webdriver) {
	this.webdriver = webdriver;
    }

    @Override
    public Supplier<WebDriver> supplier() {
	return () -> webdriver;
    }

    @Override
    public BiConsumer<WebDriver, WebDriverModifier> accumulator() {
	return (wd, wdMod) -> wd = wdMod.modify(wd);
    }

    @Override
    public BinaryOperator<WebDriver> combiner() {
	return (webDriver, webDriver2) -> {
	    throw new UnsupportedOperationException();
	};
    }

    @Override
    public Function<WebDriver, WebDriver> finisher() {
	return webDriver -> webDriver;
    }

    @Override
    public Set<Characteristics> characteristics() {
	return Collections.emptySet();
    }
}
