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
	
	public List<EmployeePayrollData> readEmployeePayrollData(IOService ioService) {
		if (ioService.equals(EmployeePayrollService.IOService.DB_IO))
			this.employeePayrollList = employeePayrollDBService.readData();
		return this.employeePayrollList;
	}
}
