package com.revature.ExpenseReimbursementSystem;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.revature.jdbc.ConnectionHelper;
import com.revature.service.DownloadReimbursementService;

public class DownloadReimbursementTest {
	@Test
	public void aListOfPendingReimbursementRequestsCanBeProvidedPerEmployee() {
		ConnectionHelper connectionHelper = new ConnectionHelper();
		connectionHelper.establishConnection();
		DownloadReimbursementService downloadReimbursementService = new DownloadReimbursementService();
		List<Reimbursement> reimbursementsById = downloadReimbursementService.getPendingReimbursementsByEmployeeId(2);
		assertEquals(1,reimbursementsById.size());
		connectionHelper.closeConnection();
	}
	
	@Test
	public void aListOfResolvedReimbursementRequestsCanBeProvidedPerEmployee() {
		ConnectionHelper connectionHelper = new ConnectionHelper();
		connectionHelper.establishConnection();
		DownloadReimbursementService downloadReimbursementService = new DownloadReimbursementService();
		List<Reimbursement> reimbursementsById = downloadReimbursementService.getResolvedReimbursementsByEmployeeId(2);
		assertEquals(1,reimbursementsById.size());
		connectionHelper.closeConnection();
	}
	
	@Test
	public void aListOfAllReimbursementRequestsCanBeProvidedPerEmployee() {
		ConnectionHelper connectionHelper = new ConnectionHelper();
		connectionHelper.establishConnection();
		DownloadReimbursementService downloadReimbursementService = new DownloadReimbursementService();
		List<Reimbursement> reimbursementsById = downloadReimbursementService.getAllReimbursementsByEmployeeId(2);
		assertEquals(2,reimbursementsById.size());
		connectionHelper.closeConnection();
	}
}
