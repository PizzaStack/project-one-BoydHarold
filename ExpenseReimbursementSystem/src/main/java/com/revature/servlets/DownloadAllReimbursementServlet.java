package com.revature.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.revature.ExpenseReimbursementSystem.Reimbursement;
import com.revature.service.DownloadAllReimbursementService;

@WebServlet("/allReimbursement")
public class DownloadAllReimbursementServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BufferedReader br = request.getReader();
		
		String type = "";
		
		String line = "";
		
		while((line = br.readLine()) != null) {
			type = line;
		}
		
		JSONArray jsonArray = new JSONArray();
		JSONObject json = new JSONObject();
		
		if(type.equals("pending")) {
			DownloadAllReimbursementService downloadAllReimbursementService = new DownloadAllReimbursementService();
			List<Reimbursement> pendingReimbursements = downloadAllReimbursementService.getAllPendingReimbursements();
			for(Reimbursement newReimbursement : pendingReimbursements) {
				json = new JSONObject();
				json.put("reimbursementid", newReimbursement.getReimbursementId());
				json.put("employeeid", newReimbursement.getEmployeeId());
				json.put("managerid", newReimbursement.getManagerId());
				json.put("status", newReimbursement.getStatus());
				json.put("title", newReimbursement.getTitle());
				json.put("description", newReimbursement.getDescription());
				json.put("amount", newReimbursement.getAmount());
				json.put("date", newReimbursement.getReimbursementDate());
				json.put("location", newReimbursement.getReceiptLocation());

				jsonArray.put(json);
			}
		} else if(type.equals("resolved")) {
			DownloadAllReimbursementService downloadAllReimbursementService = new DownloadAllReimbursementService();
			List<Reimbursement> resolvedReimbursements = downloadAllReimbursementService.getAllResolvedReimbursements();
			for(Reimbursement newReimbursement : resolvedReimbursements) {
				json = new JSONObject();
				json.put("reimbursementid", newReimbursement.getReimbursementId());
				json.put("employeeid", newReimbursement.getEmployeeId());
				json.put("managerid", newReimbursement.getManagerId());
				json.put("status", newReimbursement.getStatus());
				json.put("title", newReimbursement.getTitle());
				json.put("description", newReimbursement.getDescription());
				json.put("amount", newReimbursement.getAmount());
				json.put("date", newReimbursement.getReimbursementDate());
				json.put("location", newReimbursement.getReceiptLocation());

				jsonArray.put(json);
			}
		}
		
		PrintWriter output = response.getWriter();
		output.print(jsonArray);
		
		
		
	}
}
