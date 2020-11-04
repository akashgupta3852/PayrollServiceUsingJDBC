package com.bridgelabz.employeepayrollserviceusingjdbc;

import java.util.ArrayList;
import java.util.List;

public class EmployeePayrollService {
	private List<EmployeePayrollData> employeePayrollList;
	private EmployeePayrollDBService employeePayrollDBService;

	public enum IOService {
		DB_IO
	}

	public EmployeePayrollService() {
		employeePayrollDBService = EmployeePayrollDBService.getInstance();
		employeePayrollList = new ArrayList<>();
	}

	public EmployeePayrollService(List<EmployeePayrollData> employeePayrollList) {
		this();
		this.employeePayrollList = employeePayrollList;
	}

	public List<EmployeePayrollData> readEmployeePayrollData(IOService ioService) throws CustomException {
		if (ioService.equals(EmployeePayrollService.IOService.DB_IO))
			this.employeePayrollList = employeePayrollDBService.readData();
		return this.employeePayrollList;
	}

	public void updateEmployeeSalary(String name, double salary) throws CustomException {
		int result = employeePayrollDBService.updateEmployeeData(name, salary);
		if (result == 0)
			return;
		EmployeePayrollData employeePayrollData = this.getEmployeePayrollData(name);
		if (employeePayrollData != null) {
			employeePayrollData.salary = salary;
		}
	}

	public void updateEmployeeSalaryUsingPreparedStatement(String name, double salary) throws CustomException {
		int result = employeePayrollDBService.updateEmployeeData(salary, name);
		if (result == 0)
			return;
		EmployeePayrollData employeePayrollData = this.getEmployeePayrollData(name);
		if (employeePayrollData != null) {
			employeePayrollData.salary = salary;
		}
	}

	private EmployeePayrollData getEmployeePayrollData(String name) {
		return this.employeePayrollList.stream()
				.filter(employeePayrollListItem -> employeePayrollListItem.name.equals(name)).findFirst().orElse(null);
	}

	public boolean checkEmployeePayrollInSyncWithDB(String name) throws CustomException {
		List<EmployeePayrollData> employeePayrollList = employeePayrollDBService.getEmployeePayrollData(name);
		return employeePayrollList.get(0).equals(getEmployeePayrollData(name));
	}

	public List<EmployeePayrollData> findEmployeeByDateRange(String fromDate, String toDate) throws CustomException {
		return employeePayrollDBService.findEmployeeByDateRange(fromDate, toDate);
	}

	public double findMaxSalaryByGender(String gender) throws CustomException {
		return employeePayrollDBService.findMaxSalaryByGender(gender);
	}

	public double findMinSalaryByGender(String gender) throws CustomException {
		return employeePayrollDBService.findMinSalaryByGender(gender);
	}

	public double calculateTotalSalaryByGender(String gender) throws CustomException {
		return employeePayrollDBService.calculateTotalSalaryByGender(gender);
	}

	public int countByGender(String gender) throws CustomException {
		return employeePayrollDBService.countByGender(gender);
	}

	public double findAvgSalaryByGender(String gender) throws CustomException {
		return employeePayrollDBService.findAvgSalaryByGender(gender);
	}

	public void addEmployeeToPayrollData(String name, String gender, Double salary, String startDate)
			throws CustomException {
		employeePayrollList.add(employeePayrollDBService.addEmployeeToPayrollData(name, gender, salary, startDate));
	}
}
