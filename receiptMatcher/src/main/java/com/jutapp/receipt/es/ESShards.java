package com.jutapp.receipt.es;

public class ESShards {
	private int total;
	private int successful;
	private int failed;

	public int getTotal() {
		return total;
	}

	public int getSuccessful() {
		return successful;
	}

	public int getFailed() {
		return failed;
	}
}
