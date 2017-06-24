package com.jutapp.matcher.receipt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jutapp.receipt.Receipt;
import com.jutapp.receipt.matcher.ReceiptMatcher;

public class ReceiptMatcherTest extends AbstractTest {
	private static final String MOCKED_RECEIPT_DIR = "mocked.receipt.dir";
	private static final Logger logger = LoggerFactory.getLogger(ReceiptMatcherTest.class);
	
	@Test
	public void testBrandName() {
		Map<String, ReceiptOCR> map = loadReceiptData();
		
		ReceiptMatcher matcher = new ReceiptMatcher();
		
		//Test File_003
		Receipt File_003 = matcher.recognizeReceipt(map.get("File_003").content);
		assertNotNull("Merchant object null", File_003.getMerchant());
		assertEquals("Brand name not match", "Sangkaya", File_003.getMerchant().getBrand());
	}
	
	@Test
	public void testJSON() {
//		ESRequestBody req = new ESRequestBody();
//		ObjectMapper mapper = new ObjectMapper();
//		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
//		try {
//			System.out.println(mapper.writeValueAsString(req));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

	@Test
	public void testMerchantName() {
		//Not implemented
	}

	@Test
	public void testCompanyNo() {
		//Not implemented
	}
	
	private Map<String, ReceiptOCR> loadReceiptData() {
		File dir = new File(getProperty(MOCKED_RECEIPT_DIR));
		File[] files  = dir.listFiles();

		assertNotNull("Receipt files not found", files);
		
		Map<String, ReceiptOCR> receiptMap = new HashMap<String, ReceiptOCR>();
		for (int i = 0; i < files.length; i++) {
			ReceiptOCR r = readReceipt(files[i]);
			receiptMap.put(r.name, r);
		}
		return receiptMap;
	}
	
	private ReceiptOCR readReceipt(File file) {
		ReceiptOCR r = new ReceiptOCR();
		r.name = FilenameUtils.removeExtension(file.getName());
		
		try {
			r.content = FileUtils.readFileToString(file,Charset.defaultCharset());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return r;
	}
	
	private static class ReceiptOCR {
		String name;
		
		String content;
	}
}
