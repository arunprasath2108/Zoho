package hr_management;

import java.util.ArrayList;
import java.util.InputMismatchException;
import hr_management.EnumClass.Role;


public class SeniorEmployee extends EmployeeManager implements EmployerFeatures 
{
	
	
	private static final int VIEW_REPORTEE = 5;
	private static final int APPROVE_LEAVE = 6;
	private static final int REQUEST_TEAM_CHANGE = 7;
	private static final int INBOX = 8;
	private static final int LOG_OUT = 9;
	
	private static final int REPLY = 1;
	private static final int BACK = 2;
	
	private static final int PROCEED_TO_HR = 1;
	private static final int REJECT = 2;
	
	int viewCheck = 0;
	static Integer keyForHashMap = 0;
	
	
	
	public void listEmployeeMenu(Employee employee, ArrayList<Employee> employees)
	{
		
		//check profile updated or not
		EmployeeValidation.checkProfileCompleted(employee);
		
		//List the Basic Employee features
		super.listEmployeeMenu(employee);
		
		//Listing Senior Employees features
		listSeniorEmployeeMenu(employee);
		
		//selecting option in Switch case
		chooseOptionFromList(employee, employees);
		
	} 
	

	public  void inboxMessages(Employee employee)
	{
		
		int senderID = 0;
		
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
		
		for(int message = 0; message<employee.inbox.size(); message++)
		{
			
			String[] splitMessage = employee.inbox.get(message).split("-");
			
			senderID = Integer.parseInt(splitMessage[0]);  
			String name = EmployeeValidation.getEmployeeName(senderID); 
			String msg = splitMessage[1];		
			
			message++;
			System.out.println("  "+message+" - "+name);
			message--;
			
			Utils.printSpace();
			System.out.println("      ~ "+msg);
			Utils.printSpace();
			
			
		}
		
		Utils.printSpace();
		Role senderRole = EmployeeValidation.getRole(senderID);
		
		if(employee.role.getValue() < senderRole.getValue())
		{
		
			replyInboxMessages(employee);
			return;
		}
		else
		{
			System.out.println("   * Deleted Automatically After Viewed Once.");
			Utils.printSpace();
			employee.inbox.clear();
		}
		
	}


	
	
	private void chooseOptionFromList(Employee employee, ArrayList<Employee> employees)
	{
		
		try
		{
			System.out.println("Choose an Option :");
			int userInput = Utils.getIntInput();
			Utils.printSpace();
			
			switch(userInput)
			{
				
				case VIEW_REPORTEE : 
					viewReportees(employee);
					listEmployeeMenu(employee, employees);
					break;
				
				case APPROVE_LEAVE :  
					System.out.println("apply leave");
					listEmployeeMenu(employee, employees);
					break;
					
				case REQUEST_TEAM_CHANGE :	
					teamChangeRequest(employee);
					listEmployeeMenu(employee, employees);
					break;
					
				case INBOX :
					inboxMessages(employee);
					//displayInbox(employee);
					listEmployeeMenu(employee, employees);
					break;
				
				case LOG_OUT :
			 		Utils.printLogOutMessage();
					return;
					
				default :
				 	getInputFromEmployee(userInput, employee);
				 	listEmployeeMenu(employee, employees);
				 	break;
			}
			
		}
		catch(InputMismatchException e)
		{
				Utils.printInvalidInputMessage();
				Utils.printSpace();
				Utils.scanner.nextLine();
				chooseOptionFromList(employee,employees);
		}
		
	}
	
	
	

	private static void replyInboxMessages(Employee employee) 
	{
		
		Utils.printSpace();
		
		try
		{
			
			System.out.println(" 1. Reply.");
			System.out.println(" 2. Back.");
			Utils.printSpace();
			System.out.println(" Choose a option.");
			Utils.printSpace();
			int userInput = Utils.getIntInput();
			Utils.printSpace();
			
			switch(userInput)
			{
				
				case REPLY :
					processMessage(employee);
					break;
					
				case BACK :
					break;
					
				default :
					Utils.printInvalidInputMessage();
					replyInboxMessages(employee);
					 return;
			}
			
		}
		catch(InputMismatchException e)
		{
			Utils.printInvalidInputMessage();
			Utils.scanner.nextLine();
			replyInboxMessages(employee);
			return;
		}
		
		
	}
	
	
	
	private static void processMessage(Employee employee)
	{
		
		Utils.printSpace();
		System.out.println(" Choose Index number to Reply.");
		Utils.printSpace();
		
		try
		{
			int userInput = Utils.getIntInput();
			userInput--;
			
			if(userInput < 0)
			{
				processMessage(employee);
				return;
			}
			Utils.printSpace();
			
			for ( String message : employee.inbox)
			{
					if( message.indexOf(message) == userInput)
					{
						confirmMessageBeforeSend(message, employee, userInput);	
						break;
					}
					else
					{
						Utils.printInvalidInputMessage();
						processMessage(employee);
						return;
					}
				
			}
			
		}
		catch(InputMismatchException e)
		{
			Utils.printInvalidInputMessage();
			Utils.scanner.nextLine();
			processMessage(employee);
			return;
		}
		
		
	}


	private static void confirmMessageBeforeSend( String message, Employee employee, int userInput)
	{
		
		String[] splitMessage = message.split("-");
		int id = Integer.parseInt(splitMessage[0]);  
		String name = EmployeeValidation.getEmployeeName(id);  
		String msg = splitMessage[1];	       				
		
		
		Utils.printLine();
		System.out.println("  From : "+name);
		Utils.printSpace();
		System.out.println("      "+msg);
		
		Utils.printSpace();
		Utils.printLine();
		Utils.printSpace();
		Utils.printSpace();
		
		System.out.println(" 1. Proceed to HR.");
		System.out.println(" 2. Reject.");
		Utils.printSpace();
		
		try
		{
			int input = Utils.getIntInput();
			
			switch( input )
			{
				
				case PROCEED_TO_HR :
					forwardRequestToHR(message, id, employee);
					employee.inbox.remove(userInput);
					break;
					
				
				case REJECT :
					replyToSender(employee, message);
					employee.inbox.remove(userInput);
					break;
					
				default :
					Utils.printInvalidInputMessage();
					processMessage(employee);
					return;
					
			}
			
			
		}
		catch(InputMismatchException e)
		{
			Utils.printInvalidInputMessage();
			Utils.scanner.nextLine();
			processMessage(employee);
			return;
		}
		
	}


	private static void replyToSender(Employee employee, String message) 
	{
		
		String[] splitMessage = message.split("-");
		int receiverID = Integer.parseInt(splitMessage[0]);  
		
		for( Employee employeee : Resource.employees)
		{
			
			if(employeee.employeeID == receiverID)
			{
				employeee.inbox.add(employee.employeeID+"-"+" Contact your Manager for Further Reference.");
				Utils.printSpace();
				System.out.println("      ~ Message Sent !!");
				Utils.printSpace();
				break;
			}
			
		}

		
	}

	


	private static void forwardRequestToHR(String message, int receiverID, Employee  sender) 
	{
		
		for( Employee employeee : Resource.employees)
		{
			if(employeee.role == Role.HR)
			{
				employeee.inbox.add(message);
				Utils.printSpace();
				System.out.println("     ~ Request sent to HR");
				Utils.printSpace();
				break;
			}
		}
		
		for( Employee employeee : Resource.employees)
		{
			if(employeee.employeeID == receiverID)
			{
				
				employeee.inbox.add(sender.employeeID+"-"+" Your Request sent to HR team.");
				Utils.printSpace();
				break;
			}
		}
		
	}
	
	

	private void teamChangeRequest(Employee employee) 
	{
		
		try
		{
			if( Resource.teamMap.size() <= 1)
			{
				Utils.printSpace();
				System.out.println(" Sorry!! only "+ employee.employeeTeamName+" Team is Available ");
				Utils.printSpace();
				return;
			}
		
		    	System.out.println(" Choose Team from List.");
		    	
				if(EmployeeManager.listTeamName() == true)
				{
				
					Utils.printSpace();
					int teamID = Utils.getIntInput();
					boolean isTeamPresent = EmployeeValidation.isTeamIDAlreadyExists(teamID);
					
					if(isTeamPresent == true)
					{
						String teamName = EmployeeValidation.getTeamName(teamID);
						if( teamName.equals(employee.employeeTeamName))
						{
							Utils.printSpace();
							System.out.println(" You are Already in "+employee.employeeTeamName+" Team");
							Utils.printSpace();
							teamChangeRequest(employee);
							return;
						}
						
						if(EmployeeValidation.isTeamAlreadyExists(teamName) == true)
						{
							
							Utils.printSpace();
							System.out.println(" We're processing your Team Change Request...");
							Utils.printSpace();
							int receiverID = employee.reportingToID;
							
							for( Employee employeee : Resource.employees)
							{
									if(employeee.employeeID == receiverID)
									{
										
										employeee.inbox.add(employee.employeeID+"-"+" Request for change team to ~ "+teamName.toUpperCase());
										break;
										
									}
							}
							
							Utils.printSpace();
							System.out.println("  	   ~ Message sent !!  ");
							Utils.printSpace();
							Utils.printSpace();
							
						}
						else
						{
							Utils.printInvalidInputMessage();
							teamChangeRequest(employee);
							return;
						}
						
		     		}
					else
					{
						Utils.printSpace();
						System.out.println(" Sorry! please enter only Available Team ID.");
						Utils.printSpace();
						teamChangeRequest(employee);
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
				teamChangeRequest(employee);
		}
		
	}
		




	private void listSeniorEmployeeMenu(Employee employee)
	{
		
		System.out.println(" 5. View Reportees.");
		System.out.println(" 6. Approve Leave Request. ");
		System.out.println(" 7. Request Team Change.");
		System.out.print(" 8. Inbox");

		
		if(EmployeeValidation.isInboxEmpty(employee) == false)
		 {
			 int messageCount = EmployeeValidation.printmessageCount(employee);
			 System.out.println(" ~ ["+messageCount+"] Unread Messages");
		 }
		
		Utils.printSpace();
		Utils.printSpace();
		System.out.println(" 9. Logout");
		Utils.printLine();
		
		
	}
	
	
	
	private void viewReportees(Employee employee)
	{
		
		System.out.println(" Reportee List : ");
		Utils.printSpace();
		System.out.println(" Employee ID	Name	Role");
		Utils.printLine();
		
		for( Employee employeee : Resource.employees)
		{
			
			if(employeee.employeeTeamName.equals(employee.employeeTeamName) && employeee.role.getValue() > employee.role.getValue() && employeee.employeeReportingTo.equalsIgnoreCase(employee.employeeName))
			{
				viewCheck = 1;
				System.out.println(" 	"+employeee.employeeID+"       "+employeee.employeeName+"     "+employeee.role);
				
			}
			
		}
		
		if( viewCheck == 0)
		{
			Utils.printSpace();
			System.out.println(" 	  No Reportees!!!");
			Utils.printSpace();
		}
		
	}




	



} // class ends
