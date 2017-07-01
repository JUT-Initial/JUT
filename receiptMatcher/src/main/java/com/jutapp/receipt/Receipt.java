package com.jutapp.receipt;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
public class Receipt {
	public enum PaymentType {
		CASH, CREDIT, VOUCHER
	}
	
	private Date date;
	private String orderNo;
	private String receiptNo;
	private String registerNo;
	private String cashier;
	
	private Outlet outlet;
	
	private double subTotal;
	private double discount;
	private double rounding;
	private double total;
	
	private PaymentType paymentType;
	private double cash;
	private double change;
	
	private int points;
	private String walletNo;
	private double walletBalance;
	
	private List<ReceiptTax> taxes = new ArrayList<ReceiptTax>();
	private Merchant merchant = new Merchant();
	private List<ReceiptItem> items = new ArrayList<ReceiptItem>();
	
	private List<Merchant> suspectedMerchants = new ArrayList<Merchant>();


}
