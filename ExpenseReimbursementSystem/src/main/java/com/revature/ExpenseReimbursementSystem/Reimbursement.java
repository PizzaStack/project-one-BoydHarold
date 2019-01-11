package com.revature.ExpenseReimbursementSystem;

public class Reimbursement {
	private int reimbursementId;
	private int employeeId;
	private int managerId;
	private int status;
	private String title;
	private String description;
	private double amount;
	private String reimbursementDate;
	private String receiptLocation;
	
	public Reimbursement(int employeeId, String title, String description, double amount, String receiptLocation) {
		this.employeeId = employeeId;
		this.title = title;
		this.description = description;
		this.amount = amount;
		this.receiptLocation = receiptLocation;
	}
	
	public Reimbursement() {
		
	}
	
	public int getReimbursementId() {
		return reimbursementId;
	}
	public void setReimbursementId(int reimbursementId) {
		this.reimbursementId = reimbursementId;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public int getManagerId() {
		return managerId;
	}
	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getReimbursementDate() {
		return reimbursementDate;
	}
	public void setReimbursementDate(String reimbursementDate) {
		this.reimbursementDate = reimbursementDate;
	}
	public String getReceiptLocation() {
		return receiptLocation;
	}
	public void setReceiptLocation(String receiptLocation) {
		this.receiptLocation = receiptLocation;
	}
	
	
}
