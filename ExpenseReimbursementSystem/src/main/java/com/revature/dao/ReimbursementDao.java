package com.revature.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

import com.revature.ExpenseReimbursementSystem.Reimbursement;
import com.revature.jdbc.ConnectionHelper;

public class ReimbursementDao {
	private Reimbursement reimbursement;
	public ReimbursementDao() {
		reimbursement = new Reimbursement();
	}

	public int addReimbursement(Reimbursement reimbursement) {
		int reimbursementId = 0;
		
		Date date = new Date();
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		
		try {
			PreparedStatement ps = ConnectionHelper.connection.prepareStatement("INSERT INTO Reimbursement(EmployeeId, Status, Title, Description, Amount, ReimbursementDate, ReceiptLocation) VALUES(?,0,?,?,?,?,?);");
			ps.setInt(1, reimbursement.getEmployeeId());
			ps.setString(2, reimbursement.getTitle());
			ps.setString(3, reimbursement.getDescription());
			ps.setDouble(4, reimbursement.getAmount());
			ps.setDate(5, sqlDate);
			ps.setString(6, reimbursement.getReceiptLocation());
			ps.executeUpdate();
			
			ps = ConnectionHelper.connection.prepareStatement("SELECT ReimbursementId FROM Reimbursement WHERE EmployeeId = ? AND Status = ? AND Title = ? AND Description = ? AND Amount = ? AND ReimbursementDate = ? AND ReceiptLocation = ?;");
			ps.setInt(1, reimbursement.getEmployeeId());
			ps.setInt(2, 0);
			ps.setString(3, reimbursement.getTitle());
			ps.setString(4, reimbursement.getDescription());
			ps.setDouble(5, reimbursement.getAmount());
			ps.setDate(6, sqlDate);
			ps.setString(7, reimbursement.getReceiptLocation());
			ResultSet rs = ps.executeQuery();
		
			while(rs.next()) {
				reimbursementId = rs.getInt(1);
			}
		} catch(SQLException e) {
			System.out.println(e);
		}
		
		return reimbursementId;
	}

	public Reimbursement getReimbursementById(int reimbursementId) {
		reimbursement = null;
		
		try {
			PreparedStatement ps = ConnectionHelper.connection.prepareStatement("SELECT * FROM Reimbursement WHERE ReimbursementId = ?;");
			ps.setInt(1, reimbursementId);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				reimbursement = new Reimbursement(
						rs.getInt(2), 
						rs.getString(5), 
						rs.getString(6), 
						rs.getDouble(7), 
						rs.getString(9));
				reimbursement.setReimbursementId(rs.getInt(1));
				if(rs.getString(3) != null) {
					reimbursement.setManagerId(Integer.parseInt(rs.getString(3)));
				}
				reimbursement.setStatus(rs.getInt(5));
				reimbursement.setReimbursementDate(rs.getString(8));
			}
			
			
		} catch(SQLException e) {
			
		}
		
		return reimbursement;
	}

}
