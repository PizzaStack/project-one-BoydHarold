package com.revature.servlets;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.revature.ExpenseReimbursementSystem.Reimbursement;
import com.revature.service.UploadReimbursementService;

@MultipartConfig
public class UploadReimbursementServlet extends HttpServlet {
	private boolean addStatus;
		
		protected void doGet(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			PrintWriter output = response.getWriter();
			output.write(String.valueOf(addStatus));
		}
	
	
	    protected void doPost(HttpServletRequest request,
	            HttpServletResponse response) throws ServletException, IOException {

	    	
	        String title = request.getParameter("titleBox"); // Retrieves <input type="text" name="description">
	        String description = request.getParameter("description");
	        Double amount = Double.parseDouble(request.getParameter("amount"));
	        int employeeId = Integer.parseInt(request.getParameter("employeeid"));
	        Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
	        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
	        InputStream fileContent = filePart.getInputStream();

	        	new File("C:\\Users\\boydt\\Desktop\\Project One\\project-one-BoydHarold\\Reimbursements\\EmployeeId" + employeeId + "Receipts").mkdir();
	    		FileOutputStream fos = null;
	    		
	        	try {
	    		fos = new FileOutputStream("C:\\Users\\boydt\\Desktop\\Project One\\project-one-BoydHarold\\Reimbursements\\EmployeeId" + employeeId + "Receipts\\" + fileName);
	        	} catch(FileNotFoundException e) {
	        	
	        	}
	    		byte[] buf = new byte[512];
	    		int num;
	    		
	    		while ((num = fileContent.read(buf)) != -1) {
	    			fos.write(buf, 0, num);
	    		}
	    		
	    		UploadReimbursementService uploadReimbursementService = new UploadReimbursementService();
	    		Reimbursement reimbursement = new Reimbursement(employeeId,title,description,amount,"C:\\Users\\boydt\\Desktop\\Project One\\project-one-BoydHarold\\Reimbursements\\EmployeeId" + employeeId + "Receipts\\" + fileName);
	    		addStatus = uploadReimbursementService.addReimbursement(reimbursement);	    	
	    
	    }
	    
}
