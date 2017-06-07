package com.learn.design.patterns.constants;

/**
 * Class responsible providing timeout constants
 * 
 * @author Sachin Kumar 12 [skumar213@sapient.com]
 */
public final class Timeouts {

    public static int BIG = 30;

    public static int MEDIUM = 15;

    public static int SMALL = 5;

    public static int MINIMAL = 1;

    private static Timeouts instance;

    private Timeouts() {
    }

    public Timeouts(int big, int medium, int small, int minimal) {
	synchronized (this) {
	    if (instance == null) {
		BIG = big;
		SMALL = small;
		MEDIUM = medium;
		MINIMAL = minimal;
		instance = this;
	    }
	}
    }

}
