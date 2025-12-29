package com.JDBC;

import java.util.Scanner;

public class HMSMainMenu {

	public static void main(String[] args)throws Exception {
		Scanner sc = new Scanner(System.in);
		PatientDao pdo = new PatientDao();
		DoctorDao ddo = new DoctorDao();
		AppointmentDao ado = new AppointmentDao();
		
		while(true) {
			System.out.println("----Hospital Management System---");
			System.out.println("1. Add Patient");
			System.out.println("2. View Patient");
			System.out.println("3. Update Patient");
			System.out.println("4. Discharge Patient");
			System.out.println("5. Add Doctor");
			System.out.println("6. View Doctors");
			System.out.println("7. Update Doctor");
			System.out.println("8. Book Appointment");
			System.out.println("9. View Appointment");
			System.out.println("10. Renew Appointment");
			System.out.println("11. Exit");
			System.out.println("Enter choice: ");
			
			int ch = sc.nextInt();
			
			switch(ch) {
			case 1:
				System.out.println("name: ");
				String n = sc.next();
				System.out.println("Age: ");
				int a =sc.nextInt();
				System.out.println("Gender: ");
				String g = sc.next();
				System.out.println("phone: ");
				String p = sc.next();
				pdo.addPatient(n,a,g,p);
				break;
				
			case 2:
				pdo.viewPatients();
				break;
				
			case 3:
				System.out.println("Patient Id: ");
				int pid = sc.nextInt();
				System.out.println("New Name: ");
				String nn = sc.next();
				System.out.println("New Age: ");
				int na = sc.nextInt();
				System.out.println("New Gender: ");
				String ng = sc.next();
				System.out.println("New Phone: ");
				String np = sc.next();
				pdo.updatePatient(pid, nn, na, ng, np);
				break;
				
			case 4:
				System.out.println("Patient ID: ");
				int  dpid = sc.nextInt();
				pdo.dischargePatient(dpid);
				break;
				
			case 5:
				System.out.println("Doctor name: ");
				String dn = sc.next();
				System.out.println("Specialization: ");
				String sp = sc.next();
				ddo.addDoctor(dn, sp);
				break;
				
			case 6:
				ddo.viewDoctors();
				break;
			
			case 7:
				System.out.println("Doctor ID: ");
				int did = sc.nextInt();
				System.out.println("New Name: ");
				String dname = sc.next();
				System.out.println("New Specialization: ");
				String spec = sc.next();
				ddo.updateDoctor(did, dname, spec);
				break;
				
			case 8:
				System.out.println("Patient ID: ");
				int ap = sc.nextInt();
				System.out.println("Doctor ID: ");
				int ad = sc.nextInt();
				System.out.println("Date (YYYY-MM-DD): ");
				String dt = sc.next();
				ado.bookAppointment(ap, ad, dt);
				break;
				
			case 9:
				ado.ViewAppointment();
				break;
				
			case 10:
				System.out.println("Appointment ID: ");
				int apid = sc.nextInt();
				System.out.println("New Valid Till(YYYY-MM-DD): ");
				String nd = sc.next();
				ado.renewAppointment(apid, nd);
				break;
				
			case 11:
				System.out.println("--- Thank you ---");
				System.out.println("EXITING....");
				System.exit(0);
				
			default:
				System.out.println("Invalid Choice!!");
			}
		}

	}

}
