package com.jutapp.receipt.es;

public class ESResponse {
	private int took;
	private boolean timed_out;

	private ESShards _shards;
	private ESHits hits;

	public int getTook() {
		return took;
	}

	public boolean isTimed_out() {
		return timed_out;
	}

	public ESShards get_shards() {
		return _shards;
	}

	public ESHits getHits() {
		return hits;
	}

}
