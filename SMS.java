package projects;

import java.sql.*;
import java.util.Scanner;

public class SMS {

	static int id;
	static int mobile;
	static String firstName;
	static String lastName;
	static String city;
	static String college_name;
	static Scanner scan = new Scanner(System.in);
	static Connection con;

	SMS() {

		System.out.println("Enter Student Id : ");
		id = scan.nextInt();
		System.out.println("Enter Student's First Name : ");
		firstName = scan.next();
		System.out.println("Enter Student's Last Name : ");
		lastName = scan.next();
		System.out.println("Enter Mobile Number : ");
		mobile = scan.nextInt();
		System.out.println("Enter City name :");
		city = scan.nextLine()+" "+scan.nextLine();
		System.out.println("Enter College name :");
		college_name = scan.nextLine()+" ";
		
	}

	public static void main(String[] args) {

		int option;
		do {
			System.out.println("Welcome To Student Management System\n");
			System.out.println(
					"1. Add Data   2. Update Data   3. Delete Data   4. Fetch All Students Data   5. Fetch Particular Student Data   6. Exit  ");
			System.out.println("Enter any option : ");
			option = scan.nextInt();

			if (option < 6) {
				if (option == 1) {
					SMS sp = new SMS();
					addData();
					System.exit(0);
				} else if (option == 2) {
					updateData();
					System.exit(0);
				} else if (option == 3) {
					deleteData();
					System.exit(0);
				} else if (option == 4) {
					fetchAllStudentsData();
					System.exit(0);
				} else if (option == 5) {
					fetchParticularStudentData();
					System.exit(0);
				}
			} else if (option == 6) {
				System.out.println("\nThank you for using the Student Management System application");
				System.exit(0);
			}

			else {
				System.out.println("Invalid Option, Enter options ranging from 1 - 6.");
			}
		} while (option < 6);
	}

	public static void connectivity() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Aadhityaa", "root", "Aadhi@745");

		} catch (Exception e) {
			System.out.println("Couldn't connect to the database");
		}
	}

	public static void addData() {

		try {
			connectivity();

			PreparedStatement ps = con.prepareStatement("insert into student1 values (?,?,?,?,?,?);");
			ps.setInt(1, id);
			ps.setString(2, firstName);
			ps.setString(3, lastName);
			ps.setInt(4, mobile);
			ps.setString(5, city);
			ps.setString(6, college_name);
			ps.execute();
			System.out.println("\nYour Data has been added successfully!\n");
			System.out.println("Thank you for using the Student Management System application");

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void updateData() {
		try {
			int size = 0;
			connectivity();
			PreparedStatement ps;

			System.out.println("Enter Id : ");
			id = scan.nextInt();

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from student1 where id =" + id + ";");

			while (rs.next()) {
				size++;
			}
			if (size == 0) {
				System.out.println("Please enter correct student id to update.");
				updateData();
				System.exit(0);
			}

			System.out.println("\nSelect the Data to be Updated");
			System.out.println("1. First Name  2. Last Name  3. City  4. College name  5. Mobile");
			int option = scan.nextInt();

			switch (option) {
			case 1:
				System.out.println("Enter New FirstName : ");
				firstName = scan.next();
				ps = con.prepareStatement("update student1 set FirstName = ? where id = ?");
				ps.setString(1, firstName);
				ps.setInt(2, id);
				ps.execute();
				break;

			case 2:
				System.out.println("Enter New LastName : ");
				lastName = scan.next();
				ps = con.prepareStatement("update student1 set LastName = ? where id = ?");
				ps.setString(1, lastName);
				ps.setInt(2, id);
				ps.execute();
				break;

			case 3:
				System.out.println("Enter New City name : ");
				city = scan.nextLine()+scan.nextLine();
				ps = con.prepareStatement("update student1 set city = ? where id = ?");
				ps.setString(1, city);
				ps.setInt(2, id);
				ps.execute();
				break;

			case 4:
				System.out.println("Enter New College name : ");
				college_name =scan.nextLine()+" "+scan.nextLine();
				ps = con.prepareStatement("update student1 set college_name = ? where id = ?");
				ps.setString(1, college_name);
				ps.setInt(2, id);
				ps.execute();
				break;

			case 5:
				System.out.println("Enter New Mobile Number : ");
				mobile = scan.nextInt();
				ps = con.prepareStatement("update student1 set Mobile = ? where id = ?");
				ps.setInt(1, mobile);
				ps.setInt(2, id);
				ps.execute();
				break;

			default:
				System.out.println("Invalid Option");
				break;
			}

			System.out.println("\nYour Data has been updated successfully!\n");
			System.out.println("Thank you for using the Student Management System application");

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void deleteData() {
		try {
			int size = 0;
			System.out.println("Enter id :");
			int id = scan.nextInt();
			connectivity();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from student1 where id =" + id + ";");

			while (rs.next()) {
				size++;
			}
			if (size == 0) {
				System.out.println("Please enter correct student ID to delete.");
				deleteData();
				System.exit(0);
			}
			PreparedStatement ps = con.prepareStatement("delete from student1 where id =?;");
			ps.setInt(1, id);
			ps.execute();
			System.out.println("The data has been deleted successfully\n");
			System.out.println("Thank you for using the Student Management System application");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void fetchParticularStudentData() {
		try {
			System.out.println("Enter id :");
			int id = scan.nextInt();
			connectivity();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from student1 where id =" + id + ";");

			while (rs.next()) {
				System.out.println("\nID                        : " + rs.getInt(1));
				System.out.println("First Name         : " + rs.getString(2));
				System.out.println("Last Name          : " + rs.getString(3));
				System.out.println("Mobile Number                     : " + rs.getInt(4));
				System.out.println("City     : " + rs.getString(5));
				System.out.println("College Name : " + rs.getString(6) + "\n");
			}
			System.out.println("Thank you for using the Student Management System application");
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void fetchAllStudentsData() {
		try {
			connectivity();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from student1 ;");

			while (rs.next()) {
				System.out.println("\nID                           : " + rs.getInt(1));
				System.out.println("First Name            : " + rs.getString(2));
				System.out.println("Last Name            : " + rs.getString(3));
				System.out.println("Mobile Number                       : " + rs.getInt(4));
				System.out.println("City       : " + rs.getString(5));
				System.out.println("College Name   : " + rs.getString(6) + "\n");
			}
			System.out.println("Thank you for using the Student Management System application");
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}