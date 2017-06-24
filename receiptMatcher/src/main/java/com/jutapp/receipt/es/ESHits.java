package com.jutapp.receipt.es;

import java.util.List;

public class ESHits {
	private int total;
	private double max_score;
	private List<ESIndex> hits;
	
	public int getTotal() {
		return total;
	}
	public double getMax_score() {
		return max_score;
	}
	public List<ESIndex> getHits() {
		return hits;
	}
	
	
}
