package com.revature.ExpenseReimbursementSystem;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.revature.jdbc.ConnectionHelper;
import com.revature.service.UploadReimbursementService;

public class UploadReimbursementTest {
	@Test
	public void anEmployeeCanUploadAReimbursement() {
		ConnectionHelper connectionHelper = new ConnectionHelper();
		connectionHelper.establishConnection();
		UploadReimbursementService uploadReimbursementService = new UploadReimbursementService();
		Reimbursement reimbursement = new Reimbursement(1,"OCA Reimbursement","Took to further my professional credentials",245.00,"C:\\Users\\boydt\\Desktop\\Project One\\project-one-BoydHarold\\Reimbursements\\EmployeeId1Receipts\\Fairy Ring.PNG");
		boolean addStatus = uploadReimbursementService.addReimbursement(reimbursement);
		assertTrue(addStatus);
		connectionHelper.closeConnection();

	}
}


