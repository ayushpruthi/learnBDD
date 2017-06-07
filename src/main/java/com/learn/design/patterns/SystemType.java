
package com.learn.design.patterns;

import org.apache.commons.lang3.StringUtils;

/**
 * SystemType is an enum that represents type of operating system. It provides a
 * {@link #current() current} method to retrieve an instance of SystemType that
 * represents currently used operating system.
 */
public enum SystemType {
    MAC {
	@Override
	protected boolean accepts(String osName) {
	    return StringUtils.containsIgnoreCase(osName, "mac");
	}
    },
    WINDOWS {
	@Override
	protected boolean accepts(String osName) {
	    return StringUtils.containsIgnoreCase(osName, "windows");
	}
    },
    LINUX {
	@Override
	protected boolean accepts(String osName) {
	    return StringUtils.containsIgnoreCase(osName, "linux");
	}
    },
    OTHER {
	@Override
	protected boolean accepts(String osName) {
	    return true;
	}
    };

    /**
     * Checks the type of operating system by reading os.name system property.
     *
     * @return SystemType instance indicating current operating system.
     */
    public static SystemType current() {
	String osName = System.getProperty("os.name");
	for (SystemType t : values()) {
	    if (t.accepts(osName)) {
		return t;
	    }
	}
	return OTHER;
    }

    protected abstract boolean accepts(String osName);
}
