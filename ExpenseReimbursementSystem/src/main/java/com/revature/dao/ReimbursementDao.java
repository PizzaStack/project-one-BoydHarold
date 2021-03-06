package com.revature.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.revature.ExpenseReimbursementSystem.Reimbursement;
import com.revature.jdbc.ConnectionHelper;

public class ReimbursementDao {
	private Reimbursement reimbursement;
	private List<Reimbursement> reimbursements;
	
	public ReimbursementDao() {
		reimbursement = new Reimbursement();
		reimbursements = new ArrayList<>();
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
				reimbursement.setStatus(rs.getInt(4));
				reimbursement.setReimbursementDate(rs.getString(8));
			}
			
			
		} catch(SQLException e) {
			
		}
		
		return reimbursement;
	}

	public List<Reimbursement> getAllReimbursements() {
		try {
			PreparedStatement ps = ConnectionHelper.connection.prepareStatement("SELECT * FROM Reimbursement ORDER BY Status, ReimbursementId;");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				reimbursement = new Reimbursement(rs.getInt(2),
						rs.getString(5),
						rs.getString(6),
						rs.getDouble(7),
						rs.getString(9));
				reimbursement.setReimbursementId(rs.getInt(1));
				reimbursement.setManagerId(rs.getInt(3));
				reimbursement.setStatus(rs.getInt(4));
				reimbursement.setReimbursementDate(rs.getString(8));
				reimbursements.add(reimbursement);
			}
			
		} catch(SQLException e) {
			
		}
		
		return reimbursements;
	}

	public void updateReimbursement(Reimbursement reimbursement) {
		try {
			PreparedStatement ps = ConnectionHelper.connection.prepareStatement("UPDATE Reimbursement SET ManagerId = ?, Status = ? WHERE ReimbursementId = ?;");
			ps.setInt(1, reimbursement.getManagerId());
			ps.setInt(2, reimbursement.getStatus());
			ps.setInt(3, reimbursement.getReimbursementId());
			ps.executeUpdate();
		} catch (SQLException e) {
			
		}
		
	}

	public void deleteReimbursement(Reimbursement reimbursement) {
		try {
			PreparedStatement ps = ConnectionHelper.connection.prepareStatement("DELETE FROM Reimbursement WHERE ReimbursementId = ?;");
			ps.setInt(1, reimbursement.getReimbursementId());
			ps.executeUpdate();
		} catch (SQLException e) {
			
		}
	}

}
