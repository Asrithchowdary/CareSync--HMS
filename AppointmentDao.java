package com.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class AppointmentDao {
	public void bookAppointment(int patientId,int doctorId,String date) throws Exception{
		Connection con = DBconnection.getConnection();
		PreparedStatement ps = con.prepareStatement("select status,specialization from doctors where doctor_id=?");
		
		ps.setInt(1, doctorId);
		ResultSet rs = ps.executeQuery();
		
		if(!rs.next()) {
			System.out.println("doctor not found..");
			return;
		}
		
		String status = rs.getString("status");
		String specialization = rs.getString("specialization");
	
	if(!status.equals("Available")) {
		System.out.println("Doctor is "+status+". Assigning alternate Doctor...");
		
		PreparedStatement ps1 = con.prepareStatement("select doctor_id from doctors where specialization=? and status='Available' Limit 1");
		
		ps1.setString(1, specialization);
		
		ResultSet rs1 = ps1.executeQuery();
		
		if(rs1.next()) {
			doctorId = rs1.getInt(1);
			System.out.println("Assigned Doctor ID: "+doctorId);
		}
		else {
			System.out.println("No available doctor found");
			return;
			}
		}
	
	PreparedStatement ps2 = con.prepareStatement("INSERT INTO appointments(patient_id,doctor_id,appointment_date,valid_till) VALUES (?,?,?,DATE_ADD(?,INTERVAL 30 DAY))");
	
	ps2.setInt(1, patientId);
	ps2.setInt(2, doctorId);
	ps2.setString(3, date);
	ps2.setString(4, date);
	
	ps2.executeUpdate();
	System.out.println("Appointment Booked..");
	
	con.close();
	}
	public void ViewAppointment() throws Exception{
		Connection con = DBconnection.getConnection();
		
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("SELECT a.appointment_id, p.name,d.name,a.appointment_date,a.valid_till "+"FROM appointments a "+"JOIN patients p ON a.patient_id = p.patient_id "+ "JOIN doctors d ON a.doctor_id = d.doctor_id");
		
		
		System.out.println("Appointments: ");
		while(rs.next()) {
			System.out.println(
					rs.getInt(1)+" |"+
					rs.getString(2)+" |"+
					rs.getString(3)+" |"+
					rs.getDate(4)+" | Valid till: "+
					rs.getDate(5)
					);
		}
		con.close();
		}
	
	public void renewAppointment(int appointmentId,String newDate) throws Exception{
		Connection con = DBconnection.getConnection();
		PreparedStatement ps= con.prepareStatement("update apppointments set valid_till=? where appointment_id=?");
		
		ps.setString(1, newDate);
		ps.setInt(2, appointmentId);
		
		ps.executeUpdate();
		System.out.println("Appointment renewd SuccessFully..");
		
		con.close();
	}
}

