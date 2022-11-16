package hr_management;

import java.util.ArrayList;

import java.util.InputMismatchException;

import hr_management.EnumClass.PreferedLocation;
import hr_management.EnumClass.Role;


public class EmployeeManager 
{
	
	private static final int VIEW_PROFILE = 1;
	private static final int EDIT_PERSONAL_INFO = 2;
	private static final int APPLY_LEAVE = 3;
	private static final int VIEW_LEAVE_REQUEST = 4;
	private static final int LOG_OUT = 6;
	
	private static final int EDIT_MOBILE_NUM = 1;
	private static final int EDIT_EMAIL = 2;
	private static final int EDIT_ADDRESS = 3;
	private static final int EDIT_WORK = 4;
	private static final int EDIT_STUDIES = 5;
	private static final int BACK_MENU = 6;
	
	
	static int teamID;


	public static  void addEmployee(ArrayList<Employee> employees)
	{
		
			int teamID = 0;
			try
			{
					Boolean isTeamListAvailable = listTeamName();
					
					if (isTeamListAvailable == true)
					{
						System.out.println(" Enter Team ID from the List : ");
						teamID = Utils.getIntInput();
						Utils.printSpace();
						boolean isTeamPresent = EmployeeValidation.isTeamIDAlreadyExists(teamID);
						
							if (isTeamPresent == true)
							{
								String teamName = EmployeeValidation.getTeamName(teamID);
								getEmployeeDetails(employees,teamName);
							}
							else 
							{
								Utils.printSpace();
								System.out.println(" Sorry! No such team is Available.");
								Utils.printSpace();
								addEmployee(employees);
								Utils.printSpace();
								return;
							}	
				
			    	}
					else
					{
						System.out.println(" Team List is Empty.");
						Utils.printLine();
						Utils.printSpace();
					}
			}
			catch(InputMismatchException e)
			{
				Utils.printSpace();
				System.out.println(" Enter Team ID only.");
				Utils.printSpace();
				Utils.scanner.nextLine();
				addEmployee(employees);
				return;
			}
		
	}
		

	private static void getEmployeeDetails(ArrayList<Employee> employees, String teamName) 
	{
		
		Utils.printSpace();
		Role role = displayEmployeeRoles();
		
		String name = Utils.getNameInput();
		Utils.printSpace();
		
		String gender = Utils.getGenderInput();
		Utils.printSpace();
		
		String reportingToName = displayReportingToEmployees(teamName, role);
		
		int reportingToID = displayReportingToID(teamName, reportingToName);
		Utils.printSpace();
		
		System.out.println(" Enter User Date of Joining :  [ dd-mm-yyyy ] ");
		String doj = Utils.getStringInput();
		Utils.printSpace();
		
		String location = displayPreferedLocation();
		Utils.printSpace();
		
		Employee getlastIndex = Resource.employees.get(Resource.employees.size()-1);
		int employeeID = getlastIndex.employeeID;
		
		boolean isPresent = EmployeeValidation.isEmployeePresent(employees, ++employeeID);
		
		String newMail = name+"@zoho.in";
		boolean isMailExist = EmployeeValidation.isOfficialMailExists(newMail);
		Utils.printSpace();
		
		if(isMailExist == true)
		{
			newMail = "";
			System.out.println("  Mail Id Already Exists  -->  " +name+"@zoho.in");
			Utils.printSpace();

			newMail = Utils.generateMail();
		}
		
			if (isPresent == true)
			{
				System.out.println(" User ID " + employeeID + " Already Exists.");
				Utils.printSpace();
			} 
			else 
			{
				Employee employee = new Employee(employeeID, name, role, teamName, reportingToName, reportingToID, location, doj, newMail, gender);
				Resource.employees.add(employee);
				Resource.officialMail.add(newMail);
				Utils.printSpace();
				System.out.println("   ~ Employee added Successful ~ ");
				Utils.printSpace();
				displayProfile(employee);
			} 
		
	}



	public static int displayReportingToID(String teamName, String reportingTo)
	{
		
		if(reportingTo.equalsIgnoreCase(Resource.employees.get(0).employeeName))
		{
			return Resource.employees.get(0).employeeID;
		}
		
		for(Employee employee : Resource.employees)
		{
			if(employee.employeeName.equalsIgnoreCase(reportingTo) && employee.employeeTeamName.equalsIgnoreCase(teamName))
			{
				return employee.employeeID;
			}
		}
		return 0;
	}
	

	
	public void listEmployeeMenu(Employee employee)
	{
		
		System.out.println(" Employee Features :");
		Utils.printLine();
		System.out.println(" 1. My Profile");
		System.out.println(" 2. Edit Personal Info");
		System.out.println(" 3. Apply Leave");
		System.out.println(" 4. View Leave Request Report");
		Utils.printSpace();
		
	}
	

	
	public static void addTeam() 
	{
		
		//default teamID, if no team is present
		teamID = 110;
		Utils.printSpace();
		System.out.println(" Enter team name : ");
		String teamName = Utils.getStringInput();
		Utils.printSpace();
		boolean isTeamExists = EmployeeValidation.isTeamAlreadyExists(teamName);
		
		if( isTeamExists == true )
		{
			Utils.printSpace();
			System.out.println(" Team Name Already Exists.");
			System.out.println(" Choose a Unique Name.");
			addTeam();
		}
		
		if(isTeamExists == false )
		{
			if(Resource.teamMap.isEmpty())
			{
				
				Resource.teamMap.put(++teamID, teamName.toUpperCase());
				Utils.printTeamAddedSuccessful();
			}
			else
			{
				teamID = Resource.teamMap.lastKey();
				Resource.teamMap.put(++teamID, teamName.toUpperCase());
				Utils.printTeamAddedSuccessful();
			}
		}
	}
	

	public static  void viewTeamList()
	{
		
		if(Resource.teamMap.isEmpty())
		{
			Utils.printSpace();
			System.out.println("  No Team is Available. ");
			Utils.printSpace();
			return;
		}
		
		try
		{
			
			System.out.println(" Enter Team ID : ");
			listTeamName();
			int teamID = Utils.getIntInput();
			Utils.printSpace();
			boolean isTeamPresent = EmployeeValidation.isTeamIDAlreadyExists(teamID);
			
			if(isTeamPresent == true)
			{
				String teamName = EmployeeValidation.getTeamName(teamID);
				
				System.out.println("  "+teamName+"  Team Info :");
				Utils.printLine();
				System.out.println("   Employee ID       Name         Role");
				Utils.printLine();
				
				for (Employee employee : Resource.employees) 
				{
					if (employee.employeeTeamName.equals(teamName))
					{
						System.out.println("         "+employee.employeeID + "            " + employee.employeeName + "       "
								+ employee.role);
					}
				}
				
				Utils.printSpace();
				Utils.printSpace();
				
			}
			else
			{
				Utils.printSpace();
				System.out.println("   Please, enter  a valid Team ID only..!");
				Utils.printSpace();
				viewTeamList();
				return;
			}
		
		}
		catch(InputMismatchException e)
		{
			Utils.printInvalidInputMessage();
			Utils.scanner.nextLine();
			viewTeamList();
			return;
		}
	}
	
	
	
	private static void displayPersonalInfo(Employee employeee)
	{
		
		System.out.println(" PERSONAL INFORMATION :");
		Utils.printLine();
		System.out.println(" Mobile		  : "+employeee.mobileNumber);
		System.out.println(" Email ID	  : "+employeee.emailID);
		System.out.println(" Address	  : "+employeee.address);
		System.out.println(" Work Experience  : "+employeee.workExperience);
		System.out.println(" Education	  : "+employeee.education);
		Utils.printLine();
		Utils.printSpace();
		
	}
	
	
	
	public static void displayProfile(Employee employeee) 
	{
		
		Utils.printSpace();
		System.out.println(" EMPLOYEE DETAILS");
		Utils.printLine();
		System.out.println("  Team Name	  : " + employeee.employeeTeamName);
		System.out.println("  Employee ID     : " + employeee.employeeID);
		System.out.println("  Name		  : " + employeee.employeeName);
		System.out.println("  Role		  : " + employeee.role);
		System.out.println("  Reporting to	  : " + employeee.employeeReportingTo);
		System.out.println("  Official Mail   : " + employeee.companyMailId);
		System.out.println("  Date of Joining : " + employeee.dateOfJoining);
		System.out.println("  Work Location	  : " + employeee.employeeWorkLocation);
		Utils.printLine();
		Utils.printSpace();
		
	}
	
	
	
	public static Role displayEmployeeRoles() 
	{
		
		Role[] roles = Role.values();
		
		System.out.println(" Select User Role : ");
		Utils.printLine();
		int toCheck = 0;

		for (Role role : roles)
		{
			if(Role.CEO != role && Role.HR != role)
			{
				System.out.println(role.getValue() + " - " + role);
				
			}
		}

		Utils.printSpace();
		System.out.println(" Choose a Role :");
		
		try 
		{
			int userInput = 0;
			userInput = Utils.getIntInput();
			Utils.printSpace();
			
			for (Role role : roles)
			{
				if (userInput == role.getValue()) 
				{
					toCheck = 1;
					return role;
				}
			} 
		} 
		catch (InputMismatchException e)
		{
			Utils.printInvalidInputMessage();
			Utils.scanner.nextLine();
			return displayEmployeeRoles();
		}
		
		if (toCheck == 0) 
		{
			System.out.println(" Choose in this Options.");
			Utils.printSpace();
			return displayEmployeeRoles();
		}
		
		return null;

	}
	
	
	
	public static Role displayEmployeeRoles(Role r) 
	{
		
		Role[] roles = Role.values();
		System.out.println(" Select role : ");
		Utils.printLine();
		int toCheck = 0, noAboveRole = 0;

		for (Role role : roles)
		{
			if(Role.CEO != role && Role.HR != role && r.getValue() > role.getValue())
			{
				noAboveRole = 1;
				System.out.println(role.getValue() + " - " + role);
			}
		}

		Utils.printSpace();
		Utils.printSpace();
		
		if(noAboveRole == 0)
		{
			System.out.println("   ~ Can't change \"MANAGER\" role ");
			Utils.printSpace();
			return r;
		}
		
		System.out.println(" Choose the Roleee :");
		
		try 
		{
			int userInput = 0;
			userInput = Utils.getIntInput();
			Utils.printSpace();
			
			for (Role role : roles)
			{
				if (userInput == role.getValue() && r.getValue() > role.getValue())   //&& r.getValue() > role.getValue()
				{
					toCheck = 1;
					return role;
				}
			} //end of loop
		} 
		catch (InputMismatchException e)
		{
			Utils.printInvalidInputMessage();
			Utils.scanner.nextLine();
			return displayEmployeeRoles(r);
		}
		
		if (toCheck == 0) 
		{
			System.out.println(" Choose in this Options.");
			Utils.printSpace();
			return displayEmployeeRoles(r);
		}
		return null;

	}
	

	
	public static  void employeeLogin() 
	{
		
		try
		{
			System.out.println(" Enter User ID :");
			int userID = Utils.getIntInput();
			Utils.printSpace();
			
			Boolean isEmployeePresent = EmployeeValidation.isEmployeePresent(Resource.employees, userID);
	
			if (isEmployeePresent == true)
			{
				loginAsUser(userID);
			}
			else 
			{
				System.out.println(" User ID is Not Available.");
				Utils.printSpace();
			}
		}
		catch(InputMismatchException e)
		{
			Utils.printInvalidInputMessage();
			Utils.scanner.nextLine();
			employeeLogin();
		}
		
	}
	
	
	
	private static void loginAsUser(int userID) 
	{

			for (Employee employeee : Resource.employees) 
			{

				if (employeee.employeeID == userID) 
				{ 
					Role userRole = employeee.role;
					
					if (userRole.equals(Role.HR)) 
					{

						HumanResource hr = new HumanResource();
						Utils.printWelcomeMessage(employeee.employeeName);
						hr.listEmployeeMenu(employeee, Resource.employees);
						break;
						
					}

					else if (userRole.getValue() < Role.PT.getValue() && userRole.getValue() > Role.HR.getValue())
					{
						
						SeniorEmployee seniorEmployee = new SeniorEmployee();
						Utils.printWelcomeMessage(employeee.employeeName);
						seniorEmployee.listEmployeeMenu(employeee, Resource.employees);
						break;
						
					}

					else if (userRole.equals(Role.PT))
					{
						
						EmployeeManager pt = new EmployeeManager();
						Utils.printWelcomeMessage(employeee.employeeName);
						pt.ptMenu(employeee);
						break;
						
					}
					
					else
					{
						
						System.out.println(" ACCESS is DENIED !!");
						Utils.printSpace();
						break;
						
					}

				} //inner if ends
				
			} // end loop
	}
	

	
	public  void ptMenu(Employee employee)
	{
		
		EmployeeValidation.checkProfileCompleted(employee);
		
		listEmployeeMenu(employee);
		System.out.println(" 5. LogOut.");
		Utils.printSpace();
		System.out.println(" Choose an Option :");
		
		try 
		{
			int userInput = Utils.getIntInput();
			Utils.printSpace();
			
			boolean toLogOut = getInputFromEmployee(userInput, employee);
			
			if(toLogOut == true)
			{
				Utils.printLogOutMessage();
				return;
			}
			else
			{
				ptMenu(employee);
			}
		}
		catch(InputMismatchException e)
		{
			Utils.printSpace();
			Utils.printInvalidInputMessage();
			Utils.printSpace();
			Utils.scanner.nextLine();
			ptMenu(employee);
		}
	}
	
	
	
	public static boolean getInputFromEmployee(int userInput, Employee employeee)
	{
		
		switch (userInput)
		{
		
			case VIEW_PROFILE:
				displayProfile(employeee);
				displayPersonalInfo(employeee);   
				return false;
				
			case EDIT_PERSONAL_INFO:
				editPersonalInfo(employeee);    
				return false;
				
			case APPLY_LEAVE:
				//for apply leave
				return false;
				
			case VIEW_LEAVE_REQUEST:
				//view leave request
				return false;
				
			case LOG_OUT :
				return true;
				
			default:
				Utils.printInvalidInputMessage();
				return false;
				
		}
		
	} 

	
	public static  void editPersonalInfo(Employee employee)
	{
		
		Utils.printSpace();
		displayPersonalInfo(employee);
		
		System.out.println(" 1.Add Mobile.");
		System.out.println(" 2.Change Email ID.");
		System.out.println(" 3.Edit Address.");
		System.out.println(" 4.Add Work Experience.");
		System.out.println(" 5.Educational Qualification.");
		Utils.printSpace();
		System.out.println(" 6. Back to Menu");
		Utils.printSpace();
		
		getInputForEdit(employee);
		
	}
	
	
	private static void getInputForEdit(Employee employee)
	{
		
		System.out.println(" Select an Option :");
		
		try
		{
			int userInput = Utils.getIntInput();
			Utils.printSpace();
			
			switch(userInput)
			{
					case EDIT_MOBILE_NUM :
						editMobileNum(employee); 
						break;
						
					case EDIT_EMAIL :
						editMail(employee);   
						break;
						
					case EDIT_ADDRESS :    
						editAddress(employee);
						break;
						
					case EDIT_WORK :   
						editWorkExperience(employee);
						break;
						
					case EDIT_STUDIES :   
						editHighestQualification(employee);
						break;
						
					case BACK_MENU :
						break;
						
					default :
							Utils.printInvalidInputMessage();
							editPersonalInfo(employee);
							break;
							
			}
		
		}
		catch(InputMismatchException e)
		{
			
			Utils.printInvalidInputMessage();
			Utils.scanner.nextLine();	
			editPersonalInfo(employee);
			return;
		}
		
	}

	
	private static void editMail(Employee employee)
	{
		
		Utils.printSpace();
		System.out.println(" Enter Mail ID.");
		String mailID = Utils.getStringInput();
		Utils.printSpace();
		
		boolean isValid = EmployeeValidation.isEmailValid(mailID);
		
		if(isValid == true)
		{
			employee.setEmailID(mailID);
			System.out.println("   ~ Mail ID added successful");
			editPersonalInfo(employee);
		}
		else
		{
			Utils.printSpace();
			System.out.println(" * Invalid Mail ID *");
			editPersonalInfo(employee);
		}
	}
	
	
	private static void editMobileNum(Employee employee)
	{
		
			Utils.printSpace();	
			System.out.println(" Enter 10 Digit Number.");
			String number = Utils.getStringInput();
			Utils.printSpace();
			
			boolean isValid = EmployeeValidation.isMobileNumberValid(number);
					
			if( isValid == false)
			{
					Utils.printSpace();
					System.out.println("  * INVALID MOBILE NUMBER *");
					editPersonalInfo(employee);
			}
			else
			{
					employee.setMobileNum(number);
					System.out.println("   ~ Mobile Number added successful");
					editPersonalInfo(employee);
			}
	}

		
	private static void editAddress(Employee employee)
	{
		
		System.out.println(" Enter your Address in the below format.");
		Utils.printSpace();
		System.out.println(" Home Address, Street, City");
		Utils.printSpace();
		System.out.println(" sample address ->  1/12, Northcut Road, Coimbatore");
		Utils.printSpace();
		
		String address = Utils.getStringInput();
		Utils.printSpace();
		employee.setAddress(address);
		
		System.out.println("   ~ Address added successful");
		editPersonalInfo(employee);
		
	}
	
	
	private static void editWorkExperience(Employee employee)
	{
		
		System.out.println(" Enter Company Name : ");
		String companyName = Utils.getStringInput();
		Utils.printSpace();
		
		System.out.println(" Name of the Role : ");
		String role = Utils.getStringInput();
		Utils.printSpace();
		
		String timePeriod = getYearsOfExperience();
		Utils.printSpace();
		
		String WorkExperience = companyName+" - "+role+" - "+timePeriod+" years";
		employee.setWorkExperience(WorkExperience);
		
		System.out.println("   ~ Work Experience added Successful");
		editPersonalInfo(employee);
		

	}
	
	
	private static String getYearsOfExperience()
	{
		
		Utils.printSpace();
		System.out.println("  * Number of years you have worked : ");
		System.out.println("  * Minimum ~ 1 year || Maximum ~ 20 year ");
		Utils.printSpace();
		System.out.println("  NOTE : Experience less than 1 Year, Enter as -> 0");
		Utils.printSpace();
		
		String userInput = Utils.getStringInput();
		Utils.printSpace();
		
		boolean isYearValid = EmployeeValidation.isExperienceYearValid(userInput);
		
		if( isYearValid == true)
		{
			if( userInput.equalsIgnoreCase("0"))
			{
				return "less than 1";
			}
			return userInput;
		}
		else
		{
			return getYearsOfExperience();
		}
		
	}
	
	
	private static void editHighestQualification(Employee employee)
	{
		
		System.out.println(" Enter your Qualification");
		Utils.printSpace();
		System.out.println(" 1. B.E / B.Tech ");
		System.out.println(" 2. M.E / M.Tech ");
		System.out.println(" 3. Arts Stream (Bsc / Msc / BA)");
		System.out.println(" 4. Back to Menu");
		
		try
		{
			Utils.printSpace();
			System.out.println(" Choose a option.");
			Utils.printSpace();
			String degree = Utils.getDegreeInput();
			Utils.printSpace();
			
			if( degree == null)
			{
				return;
			}
			
			String passedOut = Utils.getPassedOutYear();
			Utils.printSpace();

			String studiesInfo = degree+" - "+passedOut+" passed out";
			employee.setEducation(studiesInfo);
			System.out.println("   ~ Educational Qualifications added successful");
			editPersonalInfo(employee);
			return;
				
		}
			
		catch(InputMismatchException e)
		{
			Utils.printInvalidInputMessage();
			Utils.scanner.nextLine();	
			editHighestQualification(employee);
			return;
			
		}
		
	}
	
	
	public static boolean listTeamName() 
	{
		
		Utils.printLine();

		if (Resource.teamMap.isEmpty()) 
		{
			return false;
		} 
		else
		{
			System.out.println(" List of Teams : ");
			Utils.printLine();
			
			for (int teamName : Resource.teamMap.keySet()) 
			{
				int key = teamName;
				String value = Resource.teamMap.get(teamName);
				System.out.println("  "+key+" ~ " + value);
			}
			
			Utils.printSpace();
			return true;
		}
	}

	
	public static String displayReportingToEmployees(String teamName, Role role)
	{
		
		int ReportingTochecker = 0;
		boolean noEmployeeInTeam = false;
		
		if(role.name().equalsIgnoreCase(Role.MANAGER.name()))
		{
			return Resource.employees.get(0).employeeName;
		}
		
		System.out.println("Choose Reporting to : ");
		Utils.printSpace();
		System.out.println(" Employee ID	Name	  Role	");
		Utils.printLine();
		
		for(Employee employee : Resource.employees)
		{
			if(employee.employeeTeamName.equals(teamName))
			{

				if(employee.role.getValue() < role.getValue())
				{
					noEmployeeInTeam = true;
					System.out.println("      "+employee.employeeID+"  	"+employee.employeeName+"       "+employee.role);
					
				}
				
			}
		}
		
		if(noEmployeeInTeam == false)
		{
			Utils.printSpace();
			System.out.println("       --        --         -- ");
			Utils.printSpace();
			System.out.println( "  * No Employee above the ROLE prefered are available.");
			System.out.println( "  * Temporary Reporting to, set as ~ null");
			Utils.printSpace();
			Utils.printSpace();
			return "null";
		}
		
		Utils.printSpace();
		System.out.println("Enter Employee ID : ");
		Utils.printSpace();
		
		try
		{
			
			int userID = Utils.getIntInput();
			Utils.printSpace();
			
			for(Employee employee : Resource.employees)
			{
				if(employee.employeeID == userID && employee.employeeTeamName.equals(teamName) && employee.role.getValue() < role.getValue())
				{
					ReportingTochecker = 1;
					return employee.employeeName;
				}
			}
			
		}
		catch(InputMismatchException e)
		{
			Utils.printInvalidInputMessage();
			Utils.printSpace();
			Utils.scanner.nextLine();
			return displayReportingToEmployees(teamName, role);
		}
		
		if( ReportingTochecker == 0)
		{
			Utils.printSpace();
			String reportName = displayReportingToEmployees(teamName, role);
			return reportName;
			
		}
		
		return null;
	}

	
	public static String displayPreferedLocation()
	{
		
		int checkLocationChanged = 0;
		System.out.println(" Enter User Work location : ");
		Utils.printSpace();

		PreferedLocation[]  location = PreferedLocation.values();
		
		for( PreferedLocation  places : location )
		{
			System.out.println(" "+places.getValue()+" - "+places);
			
		}
		
		try
		{
			int userInput = Utils.getIntInput();
			Utils.printSpace();
			for( PreferedLocation  places : location )
			{
				checkLocationChanged = 1;
				if( places.getValue() == userInput)
				{
					
					return places.toString();
				}
			}
		}
		catch(InputMismatchException e)
		{
			
			Utils.printInvalidInputMessage();
			Utils.scanner.nextLine();
			return displayPreferedLocation();
		}
		
		if( checkLocationChanged == 1)
		{
			Utils.printInvalidInputMessage();
			return displayPreferedLocation();
		}
		return null;
	}
	
	
	//overloaded method for changing the work location
	public static String displayPreferedLocation(String currentLocation)
	{
		int checkLocationChanged = 0;
		System.out.println(" Enter User Work locationnn : ");
		Utils.printSpace();
		
		PreferedLocation[]  location = PreferedLocation.values();

		for( PreferedLocation  places : location )
		{
			
			if( places.name().equalsIgnoreCase(currentLocation))
			{
				continue;
			}
			
			else
			{
				System.out.println(" "+places.getValue()+" - "+places);
			}
			
		}
		
		try
		{
			int userInput = Utils.getIntInput();
			Utils.printSpace();
			for( PreferedLocation  places : location )
			{
				checkLocationChanged = 1;
				if( places.getValue() == userInput && places.name().equalsIgnoreCase(currentLocation) == false)
				{
					
					return places.toString();
				}
			}
		}
		catch(InputMismatchException e)
		{
			
			Utils.printInvalidInputMessage();
			Utils.scanner.nextLine();
			return displayPreferedLocation(currentLocation);
		}
		
		if( checkLocationChanged == 1)
		{
			Utils.printInvalidInputMessage();
			return displayPreferedLocation(currentLocation);
		}
		return null;
	}

	
} //end of class
