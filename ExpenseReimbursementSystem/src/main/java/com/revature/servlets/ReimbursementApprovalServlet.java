package com.revature.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.service.ReimbursementApprovalService;

@WebServlet("/approval")
public class ReimbursementApprovalServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BufferedReader br = request.getReader();
		
		boolean approvalStatus = false;
		
		int reimbursementId = 0;
		int managerId = 0;
		String type = "";
		
		String line = "";
		
		while((line = br.readLine()) != null) {
			reimbursementId = Integer.parseInt(line.substring(0, line.indexOf(",")));
			line = line.substring(line.indexOf(",") + 1, line.length());
			managerId = Integer.parseInt(line.substring(0, line.indexOf(",")));
			type = line.substring(line.indexOf(",") + 1, line.length());
		}
		
		if(type.equals("approve")) {
			ReimbursementApprovalService reimbursementApprovalService = new ReimbursementApprovalService();
			approvalStatus = reimbursementApprovalService.approveReimbursementRequest(reimbursementId, managerId);
		} else if(type.equals("deny")) {
			ReimbursementApprovalService reimbursementApprovalService = new ReimbursementApprovalService();
			approvalStatus = reimbursementApprovalService.denyReimbursementRequest(reimbursementId);
		}
		
		PrintWriter output = response.getWriter();
		output.write(String.valueOf(approvalStatus));
	}
	
	
	
}
