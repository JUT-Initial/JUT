package com.jutapp.receipt.es;

import com.jutapp.receipt.Merchant;

import lombok.Getter;

@Getter 
public class ESIndex {
	private String _index;
	private String _type;
	private String _id;
	private double _score;
	private Merchant _source;

}
