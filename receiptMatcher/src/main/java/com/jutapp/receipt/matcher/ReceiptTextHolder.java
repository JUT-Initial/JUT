package com.jutapp.receipt.matcher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import com.jutapp.es.ESUtils;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReceiptTextHolder {

	private List<String> lines = new ArrayList<String>();
	private @Getter String text;
	
	public ReceiptTextHolder(String text) {
		this.text = text;
		BufferedReader reader = new BufferedReader(new StringReader(text));
		String s = null;
		try {
			while ( (s = reader.readLine()) != null) {
				put(s);
			}
		} catch (IOException e) {
			// Shoudn't throw error
			log.error(e.getMessage(), e);
		}
	}
	public void put(String line) {
		lines.add(line);
	}
	
	public void put(String text, int line) {
		lines.add(line, text);
	}
	
	public String getLine(int line) {
		return lines.get(line);
	}
	
	public String asJSON() {
		return ESUtils.escapeForJson(text);
	}

	public String asJSON(int line) {
		return ESUtils.escapeForJson(lines.get(line));
	}
}
