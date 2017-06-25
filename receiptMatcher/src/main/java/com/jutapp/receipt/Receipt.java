package com.jutapp.receipt;

import java.util.ArrayList;
import java.util.List;

public class Receipt {
	private Merchant merchant = new Merchant();
	
	private List<Merchant> suspectedMerchants = new ArrayList<Merchant>();

	public List<Merchant> getSuspectedMerchants() {
		return suspectedMerchants;
	}

	public void setSuspectedMerchants(List<Merchant> suspectedMerchants) {
		this.suspectedMerchants = suspectedMerchants;
	}

	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}
	
}
