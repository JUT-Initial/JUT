package com.jutapp.receipt;

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
	
	public TaxType getTaxType() {
		return taxType;
	}
	public void setTaxType(TaxType taxType) {
		this.taxType = taxType;
	}
	public Calculation getCalculation() {
		return calculation;
	}
	public void setCalculation(Calculation calculation) {
		this.calculation = calculation;
	}
	public double getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}
	
	
}
