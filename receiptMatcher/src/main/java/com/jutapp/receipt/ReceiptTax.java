package com.jutapp.receipt;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
public class ReceiptTax {
	public enum TaxType {
		GST, SERVICE
	}
	
	public enum Calculation {
		BY_FIX_AMOUNT, BY_TOTAL_PERCENTAGE
	}
	
	private TaxType taxType;
	private Calculation calculation;
	private double subTotal;
		
}
