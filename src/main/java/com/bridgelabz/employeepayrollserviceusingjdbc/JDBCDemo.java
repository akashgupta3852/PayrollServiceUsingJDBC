package com.bridgelabz.employeepayrollserviceusingjdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;

public class JDBCDemo {
	public static void main(String[] args) {
		System.out.println("Welcome to Payroll Services Problem");
		String jdbcURL = "jdbc:mysql://localhost:3306/payroll_services?useSSL=false";
		String userName = "root";
		String password = "473852";
		Connection connection;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver Loaded");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("Cannot find the driver in the classpath!", e);
		}
		listDrivers();
		try {
			System.out.println("Connecting to database: " + jdbcURL);
			connection = DriverManager.getConnection(jdbcURL, userName, password);
			System.out.println("Connecting is successful!!!!! " + connection);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void listDrivers() {
		Enumeration<Driver> driverList = DriverManager.getDrivers();
		while (driverList.hasMoreElements()) {
			Driver driverClass = (Driver) driverList.nextElement();
			System.out.println(" " + driverClass.getClass().getName());
		}
	}
}