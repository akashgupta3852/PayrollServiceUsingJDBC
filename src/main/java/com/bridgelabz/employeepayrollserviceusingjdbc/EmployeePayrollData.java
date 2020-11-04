package com.bridgelabz.employeepayrollserviceusingjdbc;

import java.time.LocalDate;
import java.util.List;

public class EmployeePayrollData {
	public int id;
	public String name;
	public String gender;
	public int companyID;
	public String compName;
	public Object[] depName;
	public double salary;
	public LocalDate startDate;

	public EmployeePayrollData(int id, String name, String gender, int companyID, String compName, Object[] depName,
			Double salary, LocalDate startDate) {
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.companyID = companyID;
		this.compName = compName;
		this.depName = depName;
		this.salary = salary;
		this.startDate = startDate;
	}

	public EmployeePayrollData(int id, String name, String gender, int companyID, double salary, LocalDate startDate) {
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.companyID = companyID;
		this.salary = salary;
		this.startDate = startDate;
	}

	@Override
	public String toString() {
		return "id= " + id + ", name= " + name + ", gender= " + gender + ", companyID= " + companyID + ", compName= "
				+ compName + ", depName= " + depName + ", salary= " + salary + ", startDate=" + startDate;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		EmployeePayrollData that = (EmployeePayrollData) obj;
		return (id == that.id && Double.compare(that.salary, salary) == 0 && name.equals(that.name)
				&& gender.equals(that.gender));
	}
}