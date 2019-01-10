package com.revature.servlets;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig
public class UploadReimbursementServlet extends HttpServlet {
		
		
	
	
	    protected void doPost(HttpServletRequest request,
	            HttpServletResponse response) throws ServletException, IOException {
	    	
	        String title = request.getParameter("titleBox"); // Retrieves <input type="text" name="description">
	        Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
	        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
	        InputStream fileContent = filePart.getInputStream();
	        
	        
	        System.out.println(title);
	        System.out.println(fileName);
	    	
	    	
	    	
	    	

	    		
	    		FileOutputStream fos = new FileOutputStream("C:\\Users\\boydt\\Desktop\\Project One\\project-one-BoydHarold\\" + fileName);
	    		
	    		byte[] buf = new byte[512];
	    		int num;
	    		
	    		while ((num = fileContent.read(buf)) != -1) {
	    			fos.write(buf, 0, num);
	    		}
	    		
	    	
	    
	    }
	    
}
