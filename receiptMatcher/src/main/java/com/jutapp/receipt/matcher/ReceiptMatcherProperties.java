package com.jutapp.receipt.matcher;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ReceiptMatcherProperties {
	
	private static final Logger logger = LoggerFactory.getLogger(ReceiptMatcherProperties.class);
	
	private static final String PROP_PATH = "/receiptMatcher.properties";
	private final static Properties props = new Properties();
	
	private static final String MOCK_RECEIPT_DIR = "mock.receipt.ocr.directory";
	
	private static final String ES_HOST = "es.host";
	private static final String ES_PROTOCOL = "es.protocol";
	private static final String ES_PORT = "es.port";
	
	static {
		try {
			if (logger.isTraceEnabled()) logger.trace("Trying to load properties from classpath[{}]", PROP_PATH);
			props.load(ReceiptMatcherProperties.class.getResourceAsStream(PROP_PATH));
			if (logger.isTraceEnabled()) {
				logger.trace("Properties loaded.");
			}
		} catch (IOException e) {
			//Load default properties
		}
	}
	
	public static String mockReceiptDirectory() {
		return props.getProperty(MOCK_RECEIPT_DIR);
	}
	
	public static String esHost() {
		return props.getProperty(ES_HOST);
	}
	
	public static String esProtocol() {
		return props.getProperty(ES_PROTOCOL);
	}
	
	public static int esPort() {
		return Integer.parseInt(props.getProperty(ES_PORT));
	}
	
}
