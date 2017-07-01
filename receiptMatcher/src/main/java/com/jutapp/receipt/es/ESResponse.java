package com.jutapp.receipt.es;

import lombok.Getter;

@Getter 
public class ESResponse {
	private int took;
	private boolean timed_out;

	private ESShards _shards;
	private ESHits hits;

}
