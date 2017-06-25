package com.jutapp.receipt;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getReceiptNo() {
		return receiptNo;
	}

	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}

	public String getRegisterNo() {
		return registerNo;
	}

	public void setRegisterNo(String registerNo) {
		this.registerNo = registerNo;
	}

	public String getCashier() {
		return cashier;
	}

	public void setCashier(String cashier) {
		this.cashier = cashier;
	}

	public Outlet getOutlet() {
		return outlet;
	}

	public void setOutlet(Outlet outlet) {
		this.outlet = outlet;
	}

	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getRounding() {
		return rounding;
	}

	public void setRounding(double rounding) {
		this.rounding = rounding;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public double getCash() {
		return cash;
	}

	public void setCash(double cash) {
		this.cash = cash;
	}

	public double getChange() {
		return change;
	}

	public void setChange(double change) {
		this.change = change;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public String getWalletNo() {
		return walletNo;
	}

	public void setWalletNo(String walletNo) {
		this.walletNo = walletNo;
	}

	public double getWalletBalance() {
		return walletBalance;
	}

	public void setWalletBalance(double walletBalance) {
		this.walletBalance = walletBalance;
	}

	public List<ReceiptTax> getTaxes() {
		return taxes;
	}

	public void setTaxes(List<ReceiptTax> taxes) {
		this.taxes = taxes;
	}

	public List<ReceiptItem> getItems() {
		return items;
	}

	public void setItems(List<ReceiptItem> items) {
		this.items = items;
	}
	
}
