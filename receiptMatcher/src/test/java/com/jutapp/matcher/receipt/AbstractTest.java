package com.jutapp.matcher.receipt;

import java.io.IOException;
import java.util.Properties;

public abstract class AbstractTest {
	private Properties props = new Properties();
	
	public AbstractTest() {
		try {
			String className = getClass().getSimpleName();
			props.load(getClass().getResourceAsStream("/" + className + ".properties"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	public final String getProperty(String key) {
		return props.getProperty(key);
	}
}
