package com.bridgelabz.employeepayrollserviceusingjdbc;

import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EmployeePayrollServiceTest {
	private EmployeePayrollService employeePayrollService;

	@Before
	public void initialize() {
		employeePayrollService = new EmployeePayrollService();
	}

	@Test
	public void givenEmployeePayrolInDB_WhenRetrieved_ShouldMatchEmployeeCount() throws CustomException {
		List<EmployeePayrollData> employeePayrollList = employeePayrollService
				.readEmployeePayrollData(EmployeePayrollService.IOService.DB_IO);
		Assert.assertEquals(3, employeePayrollList.size());
	}

	@Test
	public void givenNewSalaryForEmployee_WhenUpdated_ShouldSyncWithDB() throws CustomException {
		employeePayrollService.readEmployeePayrollData(EmployeePayrollService.IOService.DB_IO);
		employeePayrollService.updateEmployeeSalary("Terisa", 3000000.00);
		boolean result = employeePayrollService.checkEmployeePayrollInSyncWithDB("Terisa");
		Assert.assertTrue(result);
	}

	@Test
	public void givenNewSalaryForEmployee_WhenUpdatedUsingPreparedStatement_ShouldSyncWithDB() throws CustomException {
		employeePayrollService.readEmployeePayrollData(EmployeePayrollService.IOService.DB_IO);
		employeePayrollService.updateEmployeeSalaryUsingPreparedStatement("Terisa", 3000000.00);
		boolean result = employeePayrollService.checkEmployeePayrollInSyncWithDB("Terisa");
		Assert.assertTrue(result);
	}

	@Test
	public void givenEmployeePayrolInDB_WhenRetrievedAllEmployeesWhoJoinedInAParticularDateRange_ShouldMatchEmployeeCount()
			throws CustomException {
		List<EmployeePayrollData> employeePayrollList = employeePayrollService.findEmployeeByDateRange("2019-01-01",
				"2020-12-31");
		Assert.assertEquals(2, employeePayrollList.size());
	}

	@Test
	public void givenEmployeePayrolInDB_WhenRetrievedForMaxSalaryByGender_ShouldMatch() throws CustomException {
		double maxSalaryofMale = employeePayrollService.findMaxSalaryByGender("M");
		double maxSalaryofFemale = employeePayrollService.findMaxSalaryByGender("F");
		boolean result = (maxSalaryofMale == 3000000) && (maxSalaryofFemale == 3000000);
		Assert.assertTrue(result);
	}

	@Test
	public void givenEmployeePayrolInDB_WhenRetrievedForMinSalaryByGender_ShouldMatch() throws CustomException {
		double minSalaryofMale = employeePayrollService.findMinSalaryByGender("M");
		double minSalaryofFemale = employeePayrollService.findMinSalaryByGender("F");
		boolean result = (minSalaryofMale == 1000000) && (minSalaryofFemale == 3000000);
		Assert.assertTrue(result);
	}

	@Test
	public void givenEmployeePayrolInDB_WhenRetrievedForSumOfSalaryByGender_ShouldMatch() throws CustomException {
		double salaryofMale = employeePayrollService.calculateTotalSalaryByGender("M");
		double salaryofFemale = employeePayrollService.calculateTotalSalaryByGender("F");
		boolean result = (salaryofMale == 4000000) && (salaryofFemale == 3000000);
		Assert.assertTrue(result);
	}

	@Test
	public void givenEmployeePayrolInDB_WhenRetrievedForCountByGender_ShouldMatch() throws CustomException {
		int noOfMale = employeePayrollService.countByGender("M");
		int noOfFemale = employeePayrollService.countByGender("F");
		boolean result = (noOfMale == 2) && (noOfFemale == 1);
		Assert.assertTrue(result);
	}

	@Test
	public void givenEmployeePayrolInDB_WhenRetrievedForAvgSalaryByGender_ShouldMatch() throws CustomException {
		double avgSalaryofMale = employeePayrollService.findAvgSalaryByGender("M");
		double avgSalaryofFemale = employeePayrollService.findAvgSalaryByGender("F");
		boolean result = (avgSalaryofMale == 2000000) && (avgSalaryofFemale == 3000000);
		Assert.assertTrue(result);
	}
}