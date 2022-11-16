package hr_management;

import java.util.ArrayList;

public interface EmployerFeatures
{
	
	//interface implements by Human Resource and Senior Employee Classes.

	
	void listEmployeeMenu(Employee employee, ArrayList<Employee> employees);
	
	void inboxMessages(Employee employee);
	
}
