package hr_management;

import java.util.ArrayList;
import java.util.InputMismatchException;

import hr_management.EnumClass.Role;

public class HumanResource implements EmployerFeatures
{
	private static final int ADD_NEW_EMPLOYEE = 1;
	private static final int EDIT_EMPLOYEE_DETAILS = 2;
	private static final int ADD_NEW_TEAM = 3;
	private static final int VIEW_TEAM_INFO = 4;
	private static final int INBOX = 5;
	private static final int LOG_OUT = 6;
	
	private static final int CONFIRM = 1;
	private static final int BACK = 2;
	
	private static final int EDIT_TEAM_NAME = 1;
	private static final int EDIT_ROLE = 2;
	private static final int EDIT_REPORTING_ID = 3;
	private static final int EDIT_LOCATION = 4;
	private static final int BACK_MENU = 5;
	
	private static final int PROCESS_REQUEST = 1;
	private static final int IGNORE_MESSAGE = 2;

	
	public  void listEmployeeMenu(Employee employeee, ArrayList<Employee> employees)
	{
		
			displayHrMenu(employeee);
			
			boolean isAnyMethodCalled = getInputFromHR(employeee, employees);
			
			if( isAnyMethodCalled == true)
			{
				return;
			}
			else
			{
				
				listEmployeeMenu(employeee, employees);
			}
			 
	}
	
	
	public void inboxMessages(Employee employee) 
	{
		
		int senderID = 0;
		String msg = "";
		
		if(employee.inbox.isEmpty() == true)
		{
			Utils.printSpace();
			System.out.println("  ~ INBOX is EMPTY.");
			Utils.printSpace();
			return;
		}
		
		Utils.printSpace();
		System.out.println("  INBOX : ");
		Utils.printLine();
		
		//printing message received from others.
		for( int message = 0; message<employee.inbox.size(); message++)
		{
			
			String[] splitMessage = employee.inbox.get(message).split("-");
			 senderID = Integer.parseInt(splitMessage[0]); 
			String name = EmployeeValidation.getEmployeeName(senderID);  
			msg = splitMessage[1];			
		
			message++;
			System.out.println("  "+message+" - "+name);
			message--;
			
			Utils.printSpace();
			System.out.println("      ~ "+msg);
			Utils.printSpace();
			
		}
		
		Utils.printSpace();
		processInbox(employee, senderID, msg);
		
	}
	
	
	public void editEmployeeDetails()
	{
		
		Employee getLastIndex = Resource.employees.get(Resource.employees.size()-1);
		
		if(getLastIndex.employeeID <= 2)
		{
			System.out.println("  ~ No Employee Available to Edit.");
			Utils.printSpace();
			return;
		}
		
		System.out.println("Enter User ID : ");
		
		try
		{
			int userInput = Utils.getIntInput();
			Utils.printSpace();
			
			if(userInput == 1)
			{
				System.out.println(" ~ Can't edit CEO Details.");
				Utils.printSpace();
				return;
			}
			
			if(userInput == 2)
			{
				System.out.println(" ~ You can't edit your details without permissions.");
				Utils.printSpace();
				return;
			}
	
				
			if(EmployeeValidation.isEmployeePresent(Resource.employees, userInput) == true)
			{
					for(Employee employee : Resource.employees)
					{
						if(employee.employeeID == userInput)
						{
							processEdit(employee);
							break;
						}
					
					}
				
			}
			else
			{
				Utils.printSpace();
				System.out.println(" Invalid Employee ID");
				return;
			}
		}
		catch(InputMismatchException e)
		{
			
			Utils.printInvalidInputMessage();
			Utils.printSpace();
			Utils.scanner.nextLine();
			return;
			
		}
		
		
	}

	
	private void processEdit(Employee employee)
	{
		
		EmployeeManager.displayProfile(employee);
		System.out.println(" 1. Confirm User profile before Edit");
		System.out.println(" 2. Back");
		Utils.printSpace();
		
		try
		{
			int userInput = Utils.getIntInput();
			Utils.printSpace();
			
			switch(userInput)
			{
			
				case CONFIRM :
					displayEditOption(employee);
					break;
					
				case BACK :
					break;
					
				default :
					Utils.printInvalidInputMessage();
					Utils.printSpace();
					processEdit(employee);
					return;
			}
		}
		catch(InputMismatchException e)
		{
			
			Utils.printInvalidInputMessage();
			Utils.printSpace();
			Utils.scanner.nextLine();
			processEdit(employee);
			return;
			
		}
		
		
	}


	private void displayEditOption(Employee employee) 
	{
		
		Utils.printSpace();
		System.out.println(" Choose an Option : ");
		Utils.printLine();
		System.out.println(" 1. Change Team.");
		System.out.println(" 2. Change Role.");
		System.out.println(" 3. Edit Reporting to");
		System.out.println(" 4. Edit Work Location");
		System.out.println(" 5. Back to Menu");
		Utils.printSpace();
		
		try
		{
			int userInput = Utils.getIntInput();
			Utils.printSpace();
			
			switch(userInput)
			{
			
			case EDIT_TEAM_NAME :
				editTeamName(employee);		
				break;
				
			case EDIT_ROLE :
				editRole(employee);			
				break;
				
			case EDIT_REPORTING_ID :
				editReportingID(employee);	
				break;
				
			case EDIT_LOCATION :
				editLocation(employee);     
				break;
				
			case BACK_MENU :
				break;
				
			}
		}
		catch(InputMismatchException e)
		{
			
			Utils.printInvalidInputMessage();
			Utils.printSpace();
			Utils.scanner.nextLine();
			displayEditOption(employee);
			return;
			
		}

		
	}


	private void editReportingID(Employee employee)
	{
		
		String id = EmployeeManager.displayReportingToEmployees(employee.employeeTeamName, employee.role);
		Utils.printSpace();
		
		if(id.equalsIgnoreCase(null))
		{
			
				Utils.printLine();
				System.out.println(" Currently No Employee above your role are Available.");
				Utils.printSpace();
				return;
		}
		else
		{
			employee.employeeReportingTo = id;
			employee.reportingToID = EmployeeValidation.getEmployeeID(id);
			System.out.println("  ~ Reporting to changed Successful. ");
			Utils.printSpace();
			EmployeeManager.displayProfile(employee);
			displayEditOption(employee);
			return;
		}
		
	}


	private void editLocation(Employee employee) 
	{
		
		String location = EmployeeManager.displayPreferedLocation(employee.employeeWorkLocation);
		Utils.printSpace();
		employee.employeeWorkLocation = location;
		System.out.println("  ~ Work Location Changed Successful.");
		Utils.printSpace();
		EmployeeManager.displayProfile(employee);
		displayEditOption(employee);
		return;
		
	}


	private void editRole(Employee employee)
	{
		
		
		Role role = EmployeeManager.displayEmployeeRoles(employee.role);

		if( role.getValue() == employee.role.getValue())
		{
			displayEditOption(employee);
			return;
		}
		
		if( employee.role.getValue() > role.getValue())
		{
			employee.role = role;
			System.out.println("   ~ Role changed Successful. ");
			Utils.printSpace();
			EmployeeManager.displayProfile(employee);
			displayEditOption(employee);
			return;
		}
		else
		{
			System.out.println(" please, Choose Role above the previous position");
			Utils.printSpace();
			EmployeeManager.displayEmployeeRoles(employee.role);
			return;
		}
		
		
	}


	private void editTeamName(Employee employee)
	{
		int teamID = 0;
		try
		{
			if(EmployeeManager.listTeamName() == true)
			{
				System.out.println(" Enter Team ID from the List : ");
				teamID = Utils.getIntInput();
				boolean isTeamPresent = EmployeeValidation.isTeamIDAlreadyExists(teamID);
				
				if( isTeamPresent == true)
				{
					String teamName = EmployeeValidation.getTeamName(teamID);
					employee.employeeTeamName = teamName;
					System.out.println("  ~ Team Name changed Successful");
					Utils.printSpace();
					EmployeeManager.displayProfile(employee);
					displayEditOption(employee);
					return;
				}
				else
				{
					Utils.printSpace();
					System.out.println(" ~ No such team is Available");
					Utils.printSpace();
					displayEditOption(employee);
					return;
				}
			}
		}
		catch(InputMismatchException e)
		{
				Utils.printSpace();
				System.out.println("  Enter Team ID only..!");
				Utils.printSpace();
				Utils.scanner.nextLine();
				editTeamName(employee);
		}
		
		
	}
	
	
	//overloaded method for editing team name from request.
	private void editTeamName(int senderID, String newTeam, Employee hr)
	{
		
		Utils.printSpace();
		Employee employee = EmployeeValidation.getEmployeeObject(senderID);
		employee.employeeTeamName = newTeam;
		Utils.printSpace();
		System.out.println("   	 Reporting to ~ must be in the same team ");
		Utils.printSpace();
		editReportingID(employee);
		employee.inbox.add(hr.employeeID+"-"+"  ~ Team changed Successful.");
		Utils.printSpace();
		return;
	}
	

	private boolean getInputFromHR(Employee employee, ArrayList<Employee> employees)
	{
		 System.out.println("Choose an Option : ");
		 
		 try
		 {
			 int userInput = Utils.getIntInput();
			 Utils.printSpace();
			 
			 switch(userInput)
			 {
			 
			 	case ADD_NEW_EMPLOYEE :
			 		EmployeeManager.addEmployee(employees);   //done
			 		return false;
			 		
			 	case EDIT_EMPLOYEE_DETAILS :
			 		editEmployeeDetails();				//done		
			 		return false;
			 		
			 	case ADD_NEW_TEAM :
			 		EmployeeManager.addTeam();				//done
			 		return false;
			 				 		
			 	case VIEW_TEAM_INFO :
			 		EmployeeManager.viewTeamList();					
			 		return false;
			 			 		
			 	case INBOX :
			 		inboxMessages(employee);
			 		return false;
			 		
			 	case LOG_OUT :
			 		Utils.printLogOutMessage();
			 		return true;
			 		
			 	default :
			 		Utils.printInvalidInputMessage();
				 	return false;
				 				 	
			 }	//switch ends
		 }
		 catch(InputMismatchException e)
		 {
				 Utils.printInvalidInputMessage();
				 Utils.printSpace();
				 Utils.scanner.nextLine();
				 listEmployeeMenu(employee, employees);

		 }
		 return true;
 
	} 

	
	private  void processInbox(Employee employee, int id, String request)
	{
		
		System.out.println(" Choose Index number.");
		Utils.printSpace();
		
		try
		{
			
			int userInput = Utils.getIntInput();
			userInput--;
			Utils.printSpace();
			
			for( String messages : employee.inbox)
			{
				
				if(messages.indexOf(messages) == userInput)
				{
					proceedMessage(employee, request, userInput, id);
					break;
				}
			}
			
		}
		catch(InputMismatchException e)
		{
			
			Utils.printInvalidInputMessage();
			Utils.scanner.nextLine();
			processInbox(employee, id, request);
			return;
			
		}
	}


	private  void proceedMessage(Employee employee, String message, int indexOfMessage, int senderID) 
	{
		
		try
		{
			System.out.println(" Choose a option :");
			Utils.printSpace();
			System.out.println(" 1. Change Team");
			System.out.println(" 2. Back.");
			Utils.printSpace();
			int userInput = Utils.getIntInput();
			Utils.printSpace();
			switch(userInput)
			{
				case PROCESS_REQUEST :
					String[] getTeamName = message.split("~ ");
					String teamName = getTeamName[1];
					editTeamName(senderID, teamName, employee);
					employee.inbox.remove(indexOfMessage);
					break;
					
				case IGNORE_MESSAGE :
					break;
					
					default :
						Utils.printInvalidInputMessage();
						inboxMessages(employee);
						return;
			}
			
		}
		catch(InputMismatchException e)
		{
			Utils.printInvalidInputMessage();
			Utils.scanner.nextLine();
			inboxMessages(employee);
			return;
		}	
		
	}


	private void displayHrMenu(Employee employee)
	{

		 System.out.println(" Features : ");
		 Utils.printLine();
		 System.out.println(" 1. Add Employee");  
		 System.out.println(" 2. Edit Employee Info");  
		 System.out.println(" 3. Add Team ");  
		 System.out.println(" 4. View Team Info");
		 System.out.print(" 5. Inbox ");
		 
		 if(EmployeeValidation.isInboxEmpty(employee) == false)
		 {
			 int messageCount = EmployeeValidation.printmessageCount(employee);
			 System.out.print(" ~ ["+messageCount+"] Unread Messages");
		 }
		 
		 Utils.printSpace();
		 Utils.printSpace();
		 System.out.println(" 6. Logout.");
		 Utils.printLine();
		 Utils.printSpace();

	}


	
} // class end.
