package com.revature.service;

import java.util.ArrayList;
import java.util.List;

import com.revature.ExpenseReimbursementSystem.Reimbursement;
import com.revature.dao.ReimbursementDao;

public class DownloadReimbursementService {
	private Reimbursement reimbursement;
	private ReimbursementDao reimbursementDao;
	private List<Reimbursement> reimbursements;
	
	public DownloadReimbursementService() {
		reimbursement = new Reimbursement();
		reimbursementDao = new ReimbursementDao();
		reimbursements = new ArrayList<>();
	}
	
	public List<Reimbursement> getPendingReimbursementsByEmployeeId(int id) {
		reimbursements = reimbursementDao.getAllReimbursements();
		List<Reimbursement> reimbursementsById = new ArrayList<>();
		for(Reimbursement reimbursement : reimbursements) {
			if(reimbursement.getEmployeeId() == id && reimbursement.getStatus() == 0) {
				reimbursementsById.add(reimbursement);
			}
		}
		
		return reimbursementsById;
	}

	public List<Reimbursement> getResolvedReimbursementsByEmployeeId(int id) {
		reimbursements = reimbursementDao.getAllReimbursements();
		List<Reimbursement> reimbursementsById = new ArrayList<>();
		for(Reimbursement reimbursement : reimbursements) {
			if(reimbursement.getEmployeeId() == id && reimbursement.getStatus() == 1) {
				reimbursementsById.add(reimbursement);
			}
		}
	
		return reimbursementsById;
	}
	
}
