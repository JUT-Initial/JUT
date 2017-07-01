package com.jutapp.matcher.receipt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.junit.Test;

import com.jutapp.receipt.Merchant;
import com.jutapp.receipt.Receipt;
import com.jutapp.receipt.ReceiptTax;
import com.jutapp.receipt.matcher.ReceiptMatcher;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReceiptMatcherTest extends AbstractTest {
	private static final String MOCKED_RECEIPT_DIR = "mocked.receipt.dir";
	
	@Test
	public void testMatchMerchant() {
		Map<String, ReceiptOCR> map = loadReceiptData();
		
		ReceiptMatcher matcher = new ReceiptMatcher();
		
		Iterator<String> loadedReceiptIterator = map.keySet().iterator();
		while (loadedReceiptIterator.hasNext()) {
			String merchantKey = loadedReceiptIterator.next();
			log.info("Matching for merchant in receipt file[{}]", merchantKey);

			
			Receipt receipt = matcher.recognizeReceipt(map.get(merchantKey).content);
			Merchant merchant = receipt.getMerchant();
			
			assertNotNull("Merchant object null", merchant);
			assertEquals("Brand not match", getProperty(merchantKey + ".brand"), merchant.getBrand());
			assertEquals("Name not match", getProperty(merchantKey + ".name"), merchant.getName());
			assertEquals("Company registration name not match", getProperty(merchantKey + ".companyRegName"), merchant.getCompanyRegName());
			
			ReceiptTax tax = new ReceiptTax();
			tax.setTaxType(ReceiptTax.TaxType.GST);
		}
	}

	@Test
	public void testCompanyNo() {
		// Not implemented
	}

	private Map<String, ReceiptOCR> loadReceiptData() {
		File dir = new File(getProperty(MOCKED_RECEIPT_DIR));
		File[] files = dir.listFiles();

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
			r.content = FileUtils.readFileToString(file, Charset.defaultCharset());
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
