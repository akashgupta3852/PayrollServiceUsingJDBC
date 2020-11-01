package com.bridgelabz.employeepayrollserviceusingjdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class EmployeePayrollDBService {

	public void printWelcome() {
		System.out.println("Welcome to Payroll Services Problem");
	}

	private Connection getConnection() throws CustomException {
		String jdbcURL = "jdbc:mysql://localhost:3306/payroll_services?useSSL=false";
		String username = "root";
		String password = "473852";
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, username, password);
	        listDrivers();
		} catch (SQLException e) {
			throw new CustomException("Data is insufficient");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}

	private void listDrivers() {
		Enumeration<Driver> driverList = DriverManager.getDrivers();
		while(driverList.hasMoreElements()) {
			Driver driverClass = (Driver) driverList.nextElement();
		}
	}

	public List<EmployeePayrollData> readData() throws CustomException {
		String sql = "SELECT * FROM employee_payroll;";
		try {
			Statement statement = this.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			return this.getEmployeePayrollData(resultSet);
		} catch (SQLException e) {
			throw new CustomException("Data is insufficient");
		}
	}

	public List<EmployeePayrollData> getEmployeePayrollData(String name) throws CustomException {
		String sql = "SELECT * FROM employee_payroll where name = '" + name + "';";
		try {
			Statement statement = this.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			return this.getEmployeePayrollData(resultSet);
		} catch (SQLException e) {
			throw new CustomException("Data is insufficient");
		}
	}

	private List<EmployeePayrollData> getEmployeePayrollData(ResultSet resultSet) throws CustomException {
		List<EmployeePayrollData> employeePayrollList = new ArrayList<>();
		try {
			while (resultSet.next()) {
				int empId = resultSet.getInt("id");
				String empName = resultSet.getString("name");
				double salary = resultSet.getDouble("salary");
				LocalDate startDate = resultSet.getDate("start").toLocalDate();
				employeePayrollList.add(new EmployeePayrollData(empId, empName, salary, startDate));
			}
			return employeePayrollList;
		} catch (SQLException e) {
			throw new CustomException("Data is insufficient");
		}
	}

	public int updateEmployeeData(String name, double salary) throws CustomException {
		return this.updateEmployeeDataUsingStatement(name, salary);
	}

	private int updateEmployeeDataUsingStatement(String name, double salary) throws CustomException {
		String sql = "update employee_payroll set salary = " + salary + " where name = '" + name + "';";
		try {
			Statement statement = this.getConnection().createStatement();
			return statement.executeUpdate(sql);
		} catch (SQLException e) {
			throw new CustomException("Data is insufficient");
		}
	}
}
