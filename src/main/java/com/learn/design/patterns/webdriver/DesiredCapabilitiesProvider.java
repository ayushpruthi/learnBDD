
package com.learn.design.patterns.webdriver;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * This is a provider for the Capabilities instances.
 * @author Sachin Kumar 12 [skumar213@sapient.com]
 */
public class DesiredCapabilitiesProvider {

    private static final String CAPABILITIES_PREFIX = "webdriver.cap.";

    private static final String CAPABILITIES_MAP_PREFIX = "webdriver.map.cap.";

    private Properties properties;

    /**
     * Returns new default DesiredCapabilities object.
     */
    DesiredCapabilitiesProvider(Properties properties) {
	this.properties = properties;
    }

    public Capabilities get() {
	DesiredCapabilities capabilities = new DesiredCapabilities();
	Map<String, String> mapProperties = new HashMap<>();

	for (String name : properties.stringPropertyNames()) {
	    if (name.startsWith(CAPABILITIES_PREFIX)) {
		String capName = StringUtils.removeStart(name, CAPABILITIES_PREFIX);
		capabilities.setCapability(capName, properties.getProperty(name));
	    } else if (name.startsWith(CAPABILITIES_MAP_PREFIX)) {
		mapProperties.put(StringUtils.removeStart(name, CAPABILITIES_MAP_PREFIX), properties.getProperty(name));
	    }
	}
	processMapProperties(capabilities, mapProperties);
	return capabilities;
    }

    private void processMapProperties(DesiredCapabilities capabilities, Map<String, String> mapProperties) {
	Map<String, Object> maps = new HashMap<>();
	for (Map.Entry<String, String> entry : mapProperties.entrySet()) {
	    String[] parts = entry.getKey().split("\\.");
	    Map<String, Object> map = maps;
	    for (int i = 0; i < parts.length - 1; i++) {
		map = getOrCreate(map, parts[i]);
	    }
	    map.put(parts[parts.length - 1], entry.getValue());
	}
	for (Map.Entry<String, Object> entry : maps.entrySet()) {
	    capabilities.setCapability(entry.getKey(), entry.getValue());
	}
    }

    private Map<String, Object> getOrCreate(Map<String, Object> map, String key) {
	Map<String, Object> result;
	if (map.get(key) instanceof Map) {
	    result = (Map<String, Object>) map.get(key);
	} else {
	    result = new HashMap<>();
	    map.put(key, result);
	}
	return result;
    }

}
