package hr_management;

import java.util.InputMismatchException;

import hr_management.EnumClass.Role;



	//search another profile in basic emp options - check in last
	//time delay for some methods
	//valid - doj
	//display only the other teams if team change
	
	//why should i use!!
	//email regex
	//sorted map
	//change chooseOptionFromList for senior emp.
	//change chooseOptionFromList for hr

//option for leave - when ID opened
//delete employee option - HR(terminate emp) - last

public class Main 
{
	
	private static final int LOGIN = 1;
	private static final int EXIT = 2;
	
	public static void main(String[] args) 
	{
		
									//ID, Name, role, team name, Reporter Name, reporting ID, Location, DOJ, Gender
		
		Employee ceo = new Employee(1, "Jack-CEO", Role.CEO, "HEAD", "DEFAULT", 0, "CHENNAI", "1-11-2022", "ceo@zoho.in", "MALE");
		Resource.employees.add(ceo);
		
		Employee hr = new Employee(2, "HR-Team", Role.HR, "HR", "JACK", 1, "Chennai", "2-11-2022", "hr@zoho.in", "MALE");
		Resource.employees.add(hr);

		
		//adding CEO and HR, employees MAIL ID's
		Resource.officialMail.add("ceo@zoho.in");
		Resource.officialMail.add("hr@zoho.in");
		Resource.officialMail.add("arun@zoho.in");
		Resource.officialMail.add("guhan@zoho.in");
		Resource.officialMail.add("ajay@zoho.in");
		
		
		Employee emp = new Employee(3, "Ajay", Role.MANAGER, "APPX", "JACK", 1, "Chennai", "2-11-2022","arun@zoho.in", "MALE");
		Resource.employees.add(emp);
		Resource.teamMap.put(101, "APPX");
		Resource.teamMap.put(102, "MAIL");
		
		
		Employee emp1 = new Employee(4, "Guhan", Role.LEAD, "APPX", "Ajay", 3,  "Chennai", "2-11-2022", "guhan@zoho.in", "MALE");
		Resource.employees.add(emp1);
		
		Employee emp2 = new Employee(5, "Arun", Role.MTS, "APPX", "Guhan", 4,  "Chennai", "2-11-2022", "ajay@zoho.in", "MALE");
		Resource.employees.add(emp2);
		
		Employee emp3 = new Employee(6, "Deepak", Role.PT, "APPX", "Arun", 5,  "Chennai", "2-11-2022", "deepak@zoho.in", "MALE");
		Resource.employees.add(emp3);
	
		Employee emp4 = new Employee(7, "Aravind", Role.LEAD, "MAIL", "null", 0,  "Chennai", "2-11-2022", "aravind@zoho.in", "MALE");
		Resource.employees.add(emp4);
		
		Employee emp5 = new Employee(8, "Munesh", Role.PT, "MAIL", "Aravind", 7,  "Chennai", "2-11-2022", "munesh@zoho.in", "MALE");
		Resource.employees.add(emp5);
	
		
		Boolean condition = true;
		
		while(condition)
		{
			
			 System.out.println(" Enter an Option : ");
			 Utils.printLine();
			 System.out.println(" * HR login ID - 2 ");
			 Utils.printSpace();
			 System.out.println(" 1. Login.");
			 System.out.println(" 2. Exit. ");
			 Utils.printSpace();
			 
			 try
			 {
				 int userInput = Utils.getIntInput();
				 Utils.printSpace();
			 
				 switch(userInput)
				 	{
				 
					 	case LOGIN :
					 		Utils.printSpace();
				 			EmployeeManager.employeeLogin();
				 			break;
				 			
				 		case EXIT :
				 			System.out.println("  ~ Thanks for your Patience ~ ");
				 			return;
				 			
				 		default :
				 			Utils.printInvalidInputMessage();
				 	}
			 }
			 
			 catch(InputMismatchException e)
			 {
				 System.out.println(" Plaese, enter a Integer value.");
				 Utils.printSpace();
				 Utils.scanner.nextLine();
			 }
				
		} //while loop end
		
	} //mail method ends

}







				 			
			 			
				 
			

