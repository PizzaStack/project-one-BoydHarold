package com.revature.service;

import com.revature.ExpenseReimbursementSystem.Reimbursement;
import com.revature.dao.ReimbursementDao;
import com.revature.jdbc.ConnectionHelper;

public class UploadReimbursementService {
	private ReimbursementDao reimbursementDao;
	
	public UploadReimbursementService() {
		reimbursementDao = new ReimbursementDao();
	}

	public boolean addReimbursement(Reimbursement reimbursement) {
		boolean addStatus = false;
		int reimbursementId = reimbursementDao.addReimbursement(reimbursement);
		if(reimbursementId > 0) {
			addStatus = true;
		}
		return addStatus;
	}

}
