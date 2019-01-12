package com.revature.ExpenseReimbursementSystem;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.revature.jdbc.ConnectionHelper;
import com.revature.service.ReimbursementApprovalService;

public class ReimbursementApprovalTest {
	
	@Test
	public void aManagerCanApproveAReimbursementRequest() {
		ConnectionHelper connectionHelper = new ConnectionHelper();
		connectionHelper.establishConnection();
		ReimbursementApprovalService reimbursementApprovalService = new ReimbursementApprovalService();
		boolean approvalStatus = reimbursementApprovalService.approveReimbursementRequest(2, 1);
		assertTrue(approvalStatus);
		connectionHelper.closeConnection();
	}
	
	@Test
	public void aManagerCanDenyAReimbursementRequest() {
		ConnectionHelper connectionHelper = new ConnectionHelper();
		connectionHelper.establishConnection();
		ReimbursementApprovalService reimbursementApprovalService = new ReimbursementApprovalService();
		boolean approvalStatus = reimbursementApprovalService.denyReimbursementRequest(4);
		assertTrue(approvalStatus);
		connectionHelper.closeConnection();
	}
	
}
