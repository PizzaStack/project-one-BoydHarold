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
import com.revature.service.DownloadReimbursementService;

@WebServlet("/downloadReimbursementServlet")
public class DownloadReimbursementServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BufferedReader br = request.getReader();

		String line = "";
		int employeeId = 0;
		String type = "";
		int count = 0;

		while ((line = br.readLine()) != null) {
			employeeId = Integer.parseInt(line.substring(0, line.indexOf(",")));
			type = line.substring(line.indexOf(",") + 1, line.length());
		}

		if (type.equals("pending")) {
			DownloadReimbursementService downloadReimbursementService = new DownloadReimbursementService();
			List<Reimbursement> reimbursementsById = downloadReimbursementService
					.getPendingReimbursementsByEmployeeId(employeeId);
			PrintWriter output = response.getWriter();
			JSONObject json;
			JSONArray jsonArray = new JSONArray();
			for (Reimbursement newReimbursement : reimbursementsById) {
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

			output.print(jsonArray);

		} else if (type.equals("resolved")) {
			DownloadReimbursementService downloadReimbursementService = new DownloadReimbursementService();
			List<Reimbursement> reimbursementsById = downloadReimbursementService
					.getResolvedReimbursementsByEmployeeId(employeeId);
			PrintWriter output = response.getWriter();
			JSONObject json;
			JSONArray jsonArray = new JSONArray();
			for (Reimbursement newReimbursement : reimbursementsById) {
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

			output.print(jsonArray);
		}

	}
}
