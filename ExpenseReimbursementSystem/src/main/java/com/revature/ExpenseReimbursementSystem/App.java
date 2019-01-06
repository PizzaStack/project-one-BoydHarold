package com.revature.ExpenseReimbursementSystem;

import com.revature.jdbc.ConnectionHelper;

public class App 
{
    public static void main( String[] args )
    {
    	ConnectionHelper ch = new ConnectionHelper();
    	ch.establishConnection();
    	
    	ch.closeConnection();
    }
}
