package com.jutapp.receipt.es;

import java.util.List;

import lombok.Getter;

@Getter 
public class ESHits {
	private int total;
	private double max_score;
	private List<ESIndex> hits;
	
}
