package com.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class PatientDao {
	
	public  void addPatient(String name,int age,String gender,String phone) throws Exception{
		Connection con = DBconnection.getConnection();
		PreparedStatement ps = con.prepareStatement(
			"insert into patients(name,age,gender,phone,status) values(?,?,?,?,'Admitted')"	
				);
		ps.setString(1, name);
		ps.setInt(2, age);
		ps.setString(3, gender);
		ps.setString(4, phone);
		
		ps.executeUpdate();
		System.out.println("patient added successfully..");
	}
	public void viewPatients() throws Exception{
		Connection con = DBconnection.getConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("Select * from patients");
		
		
		System.out.println("Patient List: ");
		while(rs.next()) {
			System.out.println(rs.getInt("patient_id")+" |"+
					rs.getString("name")+" |"+
					rs.getInt("age")+" |"+
					rs.getString("gender")+" |"+
					rs.getString("phone")+" |"+
					rs.getString("status")
					);
		}
		con.close();
	}
	
	public void updatePatient(int id,String name,int age,String gender,String phone) throws Exception{
		Connection con = DBconnection.getConnection();
		PreparedStatement ps = con.prepareStatement(
				"update patients set name=?, age=?,gender=?,phone=? where patient_id=?"
				);
		
		ps.setString(1, name);
		ps.setInt(2, age);
		ps.setString(3, gender);
		ps.setString(4, phone);
		ps.setInt(5, id);
		
		int result = ps.executeUpdate();
		
		if(result >0) {
			System.out.println("patient updated sucessfully..");
		}
		else {
			System.out.println("patient id not found..");
		}
		con.close();
	}
	
	public void dischargePatient(int patientId) throws Exception{
		Connection con =  DBconnection.getConnection();
		PreparedStatement ps = con.prepareStatement(
				"Update patients set status='Discharged' where patient_id=?"
				);
		
		ps.setInt(1, patientId);
		ps.executeUpdate();
		
		
		System.out.println("patient discharged SuccessFully..");
		con.close();
	}

}
