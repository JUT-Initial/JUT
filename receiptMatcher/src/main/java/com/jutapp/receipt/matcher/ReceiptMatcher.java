package com.jutapp.receipt.matcher;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.StatusLine;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;

import com.google.gson.Gson;
import com.jutapp.es.ESUtils;
import com.jutapp.receipt.Receipt;
import com.jutapp.receipt.es.ESHits;
import com.jutapp.receipt.es.ESIndex;
import com.jutapp.receipt.es.ESResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReceiptMatcher {
	private static HttpHost esHost;
	
	static {
		esHost = new HttpHost(ReceiptMatcherProperties.esHost(), ReceiptMatcherProperties.esPort(),
				ReceiptMatcherProperties.esProtocol());
		
	}


	public Receipt recognizeReceipt(String text) {
		ReceiptTextHolder textHolder = new ReceiptTextHolder(text);
		
		String esBody = String.format(loadMultiSearchTemplate(), textHolder.asJSON());
		
		RestClient restClient = RestClient.builder(esHost).build();
		Response resp;
		int statusCode = -1;
		Receipt receipt = new Receipt();
		
		try {
			HttpEntity reqEntity = new NStringEntity(esBody, ContentType.APPLICATION_JSON);
			resp = restClient.performRequest("POST", "merchant/retail/_search?pretty=true", Collections.<String, String>emptyMap(), reqEntity);

			StatusLine status = resp.getStatusLine();
			statusCode = status.getStatusCode();
			
			if (200 != statusCode) {
				RuntimeException e = new RuntimeException(String.format("Elastic search error, status[%s], reason[%s]", statusCode, status.getReasonPhrase()));
				log.error(e.getMessage(), e);
				throw e;
			}
			
			HttpEntity entity = resp.getEntity();
			byte[] b = IOUtils.readFully(entity.getContent(), (int) entity.getContentLength());
			String responseText = new String(b, "UTF-8");
			
			Gson gson = ESUtils.newGson();
			ESResponse esResp = gson.fromJson(responseText, ESResponse.class);
			
			
			ESHits hits = esResp.getHits();
			if (hits.getTotal() == 1) {
				receipt.setMerchant(hits.getHits().iterator().next().get_source());
			} else {
				for (ESIndex index : hits.getHits()) {
					receipt.getSuspectedMerchants().add(index.get_source());
				}
			}
			
		} catch (IOException e) {
			log.error("Error in ElasticSearch.", e);
			throw new RuntimeException(e);
		} finally {
			try {
				restClient.close();
			} catch (IOException e) {
				log.error("Error closing elastic search client.", e);
			}
		}
		
		
		
		
		return receipt;
	}
	
	private String loadMultiSearchTemplate() {
		InputStream in = getClass().getResourceAsStream("multi-search.es");
		byte[] b = new byte[1024];
		int c = 0;
		StringBuilder sb = new StringBuilder();
		try {
			while ( (c = in.read(b)) > 0) {
				sb.append(new String(b, 0, c));
			}
		} catch (IOException e) {
			//Shouldn't throw errors
			throw new RuntimeException(e);
		}
		return sb.toString();
	}
	
}
