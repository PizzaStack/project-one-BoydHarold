package com.revature.servlets;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UploadReimbursementServlet extends HttpServlet {
		
	    protected void doPost(HttpServletRequest request,
	            HttpServletResponse response) throws ServletException, IOException {
	    		InputStream is = request.getInputStream();
	    		
	    		FileOutputStream fos = new FileOutputStream("C:\\Users\\boydt\\Desktop\\Project One\\project-one-BoydHarold\\image.png");
	    		
	    		byte[] buf = new byte[512];
	    		int num;
	    		
	    		while ((num = is.read(buf)) != -1) {
	    			fos.write(buf, 0, num);
	    		}
	    
	    }
}
