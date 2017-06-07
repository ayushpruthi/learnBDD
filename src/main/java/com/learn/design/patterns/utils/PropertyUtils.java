
package com.learn.design.patterns.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.learn.design.patterns.constants.ConfigKeys;
import com.learn.design.patterns.constants.Timeouts;

/**
 * This class contains utility methods for loading properties from classpath and
 * system.
 * 
 *
 * @author Sachin Kumar 12 [skumar213@sapient.com]
 */
public class PropertyUtils {

	private static final Logger LOG = LoggerFactory.getLogger(PropertyUtils.class);

	private static final String[] PREFIXES = new String[] { "webdriver.", "phantomjs." };

	private static Properties properties = null;

	private PropertyUtils() {
		// util class...
	}

	/**
	 * This util method gather system and class path properties, and returns
	 * them as <code>Properties</code> object.
	 *
	 * @return Properties - object with all properties.
	 */
	public static Properties gatherProperties() {
		if (properties == null) {
			try {
				String parents = System.getProperty("configuration.paths", "src/main/resources/config/common");
				String[] split = StringUtils.split(parents, ";");
				properties = loadDefaultProperties();
				for (String name : split) {
					File configParent = new File(StringUtils.trim(name));
					loadProperties(configParent, properties);
				}
				overrideFromSystemProperties(properties);
				setSystemProperties(properties);
				overrideTimeouts(properties);
			} catch (IOException e) {
				LOG.error("Can't bind properties", e);
			}
		}
		return properties;
	}

	public static Set<Properties> getWebDriverProperties() {

		Set<Properties> set = new HashSet<>();
		try {
			String parents = System.getProperty(ConfigKeys.WEBDRIVER_PROPERTIES_PATH,
					"src/main/resources/config/drivers");
			String[] split = StringUtils.split(parents, ";");
			for (String name : split) {
				File configParent = new File(StringUtils.trim(name));
				getProperties(configParent, set);
			}
		} catch (IOException e) {
			LOG.error("Can't bind properties", e);
		}
		return set;
	}

	private static void getProperties(File file, Set<Properties> set) throws IOException {
		if (!file.exists()) {
			LOG.warn("{} file doesn't exists.", file.getPath());
		} else {
			if (file.isDirectory()) {
				for (File child : file.listFiles()) {
					getProperties(child, set);
				}
			} else {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
				try {
					Properties pro = new Properties();
					pro.load(reader);
					String add = pro.getProperty("webdriver.enable");
					if (add != null && Boolean.parseBoolean(add)) {
						set.add(pro);
						LOG.debug("loaded properties from: {} (ie. {})", file, file.getAbsolutePath());
					}
				} finally {
					reader.close();
				}
			}
		}

	}

	private static void overrideTimeouts(Properties properties) {
		int big = Integer.parseInt(properties.getProperty(ConfigKeys.TIMEOUTS_BIG));
		int medium = Integer.parseInt(properties.getProperty(ConfigKeys.TIMEOUTS_MEDIUM));
		int small = Integer.parseInt(properties.getProperty(ConfigKeys.TIMEOUTS_SMALL));
		int minimal = Integer.parseInt(properties.getProperty(ConfigKeys.TIMEOUTS_MINIMAL));
		new Timeouts(big, medium, small, minimal);
	}

	private static Properties loadDefaultProperties() throws IOException {
		Properties properties = new Properties();
		try (InputStream is = PropertyUtils.class.getClassLoader()
				.getResourceAsStream(ConfigKeys.DEFAULT_PROPERTIES_NAME)) {
			properties.load(is);
			LOG.debug("loaded properties from default properties ");
		} catch (IOException e) {
			LOG.error("Can't bind default properties", e);
		}
		return properties;
	}

	private static void loadProperties(File file, Properties properties) throws IOException {
		if (!file.exists()) {
			LOG.warn("{} file doesn't exists.", file.getPath());
		} else {
			if (file.isDirectory()) {
				for (File child : file.listFiles()) {
					loadProperties(child, properties);
				}
			} else {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));

				LOG.debug("loading properties from: {} (ie. {})", file, file.getAbsolutePath());
				try {
					properties.load(reader);
				} finally {
					reader.close();
				}
			}
		}
	}

	private static void overrideFromSystemProperties(Properties properties) {
		properties.stringPropertyNames().stream().forEach((key) -> {
			String systemProperty = System.getProperty(key);
			if (StringUtils.isNotBlank(systemProperty)) {
				properties.setProperty(key, systemProperty);
			}
		});
	}

	private static void setSystemProperties(Properties properties) {
		for (String key : properties.stringPropertyNames()) {
			String systemProperty = System.getProperty(key);
			if (StringUtils.isNotBlank(systemProperty)) {
				continue;
			}
			for (String prefix : PREFIXES) {
				if (key.startsWith(prefix)) {
					System.setProperty(key, properties.getProperty(key));
					break;
				}
			}
		}
	}
}
