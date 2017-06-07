/**
 * 
 */
package com.learn.design.patterns.app;

import com.learn.design.patterns.app.cart.Cart;

/**
 * @author sku202
 *
 */
public class AppHeader {
	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Cart tapCart() {
		return new Cart();
	}
}
