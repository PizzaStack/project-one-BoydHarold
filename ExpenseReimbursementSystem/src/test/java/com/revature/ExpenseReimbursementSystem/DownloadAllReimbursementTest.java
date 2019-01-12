package com.revature.ExpenseReimbursementSystem;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.revature.jdbc.ConnectionHelper;
import com.revature.service.DownloadAllReimbursementService;

public class DownloadAllReimbursementTest {

		@Test
		public void aManagerCanGetAllPendingReimbursementsForAllEmployees() {
			ConnectionHelper connectionHelper = new ConnectionHelper();
			connectionHelper.establishConnection();
			DownloadAllReimbursementService downloadAllReimbursementService = new DownloadAllReimbursementService();
			List<Reimbursement> pendingReimbursements = downloadAllReimbursementService.getAllPendingReimbursements();
			assertEquals(2, pendingReimbursements.size());
			connectionHelper.closeConnection();
		}
		
		@Test
		public void aManagerCanGetAllResolvedReimbursementsForAllEmployees() {
			ConnectionHelper connectionHelper = new ConnectionHelper();
			connectionHelper.establishConnection();
			DownloadAllReimbursementService downloadAllReimbursementService = new DownloadAllReimbursementService();
			List<Reimbursement> resolvedReimbursements = downloadAllReimbursementService.getAllResolvedReimbursements();
			assertEquals(1, resolvedReimbursements.size());
			connectionHelper.closeConnection();
		}
}
