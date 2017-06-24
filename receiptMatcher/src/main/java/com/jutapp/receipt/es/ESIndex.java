package com.jutapp.receipt.es;

import com.jutapp.receipt.Merchant;

public class ESIndex {
	private String _index;
	private String _type;
	private String _id;
	private double _score;
	private Merchant _source;

	public String get_index() {
		return _index;
	}

	public String get_type() {
		return _type;
	}

	public String get_id() {
		return _id;
	}

	public double get_score() {
		return _score;
	}

	public Merchant get_source() {
		return _source;
	}

}
