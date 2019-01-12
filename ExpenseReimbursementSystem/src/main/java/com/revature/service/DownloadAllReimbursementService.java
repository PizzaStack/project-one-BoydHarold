package com.revature.service;

import java.util.ArrayList;
import java.util.List;

import com.revature.ExpenseReimbursementSystem.Reimbursement;
import com.revature.dao.ReimbursementDao;

public class DownloadAllReimbursementService {
	private Reimbursement reimbursement;
	private ReimbursementDao reimbursementDao;
	private List<Reimbursement> reimbursements;
	
	public DownloadAllReimbursementService() {
		reimbursement = new Reimbursement();
		reimbursementDao = new ReimbursementDao();
		reimbursements = new ArrayList<>();
	}

	public List<Reimbursement> getAllPendingReimbursements() {
		List<Reimbursement> pendingReimbursements = new ArrayList<>();
		reimbursements = reimbursementDao.getAllReimbursements();
		
		for(Reimbursement reimbursement : reimbursements) {
			if(reimbursement.getStatus() == 0) {
				pendingReimbursements.add(reimbursement);
			}
		}
		
		return pendingReimbursements;
	}

	public List<Reimbursement> getAllResolvedReimbursements() {
		List<Reimbursement> resolvedReimbursements = new ArrayList<>();
		reimbursements = reimbursementDao.getAllReimbursements();
		
		for(Reimbursement reimbursement : reimbursements) {
			if(reimbursement.getStatus() == 1) {
				resolvedReimbursements.add(reimbursement);
			}
		}
		
		return resolvedReimbursements;
	}
	
	
	
}
