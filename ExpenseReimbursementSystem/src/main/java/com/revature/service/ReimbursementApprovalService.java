package com.revature.service;

import com.revature.ExpenseReimbursementSystem.Reimbursement;
import com.revature.dao.ReimbursementDao;

public class ReimbursementApprovalService {
	ReimbursementDao reimbursementDao;
	Reimbursement reimbursement;
	
	public ReimbursementApprovalService() {
		reimbursementDao = new ReimbursementDao();
		reimbursement = new Reimbursement();
	}

	public boolean approveReimbursementRequest(int reimbursementId, int managerId) {
		boolean approvalStatus = false;
		reimbursement = reimbursementDao.getReimbursementById(reimbursementId);
		reimbursement.setStatus(1);
		reimbursement.setManagerId(managerId);
		reimbursementDao.updateReimbursement(reimbursement);
		Reimbursement changedReimbursement = reimbursementDao.getReimbursementById(reimbursementId);
		if(reimbursement.getStatus() == changedReimbursement.getStatus() && reimbursement.getManagerId() == changedReimbursement.getManagerId()) {
			approvalStatus = true;
		}
		return approvalStatus;
	}

	public boolean denyReimbursementRequest(int reimbursementId) {
		boolean approvalStatus = false;
		reimbursement = reimbursementDao.getReimbursementById(reimbursementId);
		reimbursementDao.deleteReimbursement(reimbursement);
		Reimbursement changedReimbursement = reimbursementDao.getReimbursementById(reimbursementId);
		if(changedReimbursement == null) {
			approvalStatus = true;
		}
		return approvalStatus;
	}

}
