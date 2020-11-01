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
		employeePayrollDBService = new EmployeePayrollDBService();
		employeePayrollList = new ArrayList<>();
	}
	
	public List<EmployeePayrollData> readEmployeePayrollData(IOService ioService) throws CustomException {
		if (ioService.equals(EmployeePayrollService.IOService.DB_IO))
			this.employeePayrollList = employeePayrollDBService.readData();
		return this.employeePayrollList;
	}

	public void updateEmployeeSalary(String name, double salary) throws CustomException {
		int result = employeePayrollDBService.updateEmployeeData(name,salary);
		if(result == 0) return;
		EmployeePayrollData employeePayrollData = this.getEmployeePayrollData(name);
		if(employeePayrollData != null) {
			employeePayrollData.salary = salary;
		}
	}

	private EmployeePayrollData getEmployeePayrollData(String name) {
		return this.employeePayrollList.stream()
				.filter(employeePayrollListItem -> employeePayrollListItem.name.equals(name))
				.findFirst()
				.orElse(null);
	}

	public boolean checkEmployeePayrollInSyncWithDB(String name) throws CustomException {
		List<EmployeePayrollData> employeePayrollList = employeePayrollDBService.getEmployeePayrollData(name);
		return employeePayrollList.get(0).equals(getEmployeePayrollData(name));
	}
}
