package hr_management;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import hr_management.EnumClass.Role;


public class EmployeeValidation 
{
	
	
	public static boolean isEmployeePresent(ArrayList<Employee> employees, int userId)
	{
		
		for(Employee employee : employees)
		{
			if(employee.employeeID == userId)
			{
				Utils.printSpace();
				return true;
			}
		}
		return false;
		
	}
	
	 public static void checkProfileCompleted(Employee employeee) 
	 {
		 
		 	boolean isUpdated = EmployeeValidation.isPersonalDetailUpdated(employeee);
		 		
		 	if( isUpdated == false)
		 	{
		 		Utils.printSpace();
		 		System.out.println("   * PROFILE IS INCOMPLETE  ");
		 		Utils.printSpace();
		 	}		
	 }
	 

	public static boolean isPersonalDetailUpdated(Employee employeee)
	{
		
		if(employeee.emailID == null || employeee.education == null || employeee.workExperience == null)
		{
			return false;
		}
		
		Utils.printSpace();
		return true;
		
	}
	
	
	public static boolean isEmailValid(String mailID)
	{
		
		String mail = mailID;
		Pattern pattern = Pattern.compile("^(?=.{1,64}@)[a-z]+(\\.[a-z0-9_-]+)*@+[^-][a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,9})$");
		Matcher matcher = pattern.matcher(mail);
		
		while(matcher.find())
		{
			return true;
		}
		
		return false;
		
	}
	
	
	public static boolean isMobileNumberValid(String mobileNum)
	{
		
		 String number = mobileNum;
		 Pattern pattern = Pattern.compile("^[6-9]{1}[0-9]{9}$");
		 Matcher matcher = pattern.matcher(number);
		 
		 while(matcher.find())
		 {
			 return true;
		 }
		return false;
		
	}
	
	
	public static boolean isPassedOutYearValid(String year)
	{
		
		int passOut = Integer.parseInt(year);
		
		if( passOut >= 1985 && passOut <=2024)
		{
			return true;
		}
		
		return false;
		
	}
	
	
	public static boolean isTeamAlreadyExists(String name)
	{
		
		if(Resource.teamMap.containsValue(name.toUpperCase()))
		{
			return true;
		}
		
		return false;
		
	}
	
	public static boolean isTeamIDAlreadyExists(int id)
	{
		
		if(Resource.teamMap.containsKey(id))
		{
			return true;
		}
		
		return false;
	}
	
	public static String getTeamName(int id)
	{
		
		for( Entry<Integer, String> entries : Resource.teamMap.entrySet())
		{
			if(entries.getKey() == (Integer)id)
			{
				
				return (String) entries.getValue();
			}
		}
		
		return null;
		
	}

	public static boolean isExperienceYearValid(String userInput)
	{
		
		
			try
			{
				 int year = Integer.parseInt(userInput);
				
				 if(year <= 20 && year >= 0)
				 {
					 return true;
				 }
			}
			catch(NumberFormatException e)
			{
				return false;
			}
			
			 return false;
		
	}
	
	public static boolean isOfficialMailExists(String mail)
	{
		
		for( String mails : Resource.officialMail)
		{
			if( mails.equalsIgnoreCase(mail))
			{
				return true;
			}
			
		}

		return false;
		
	}
	
	
	public static boolean isInboxEmpty(Employee employee)
	{
		
		if(employee.getInbox().isEmpty() == true)
		{
			return true;
		}
		
		return false;
	}

	
	public static int printmessageCount(Employee employee) 
	{
		
		return employee.inbox.size();
		
	}
	
	
	public static String getEmployeeName(int id)
	{
		
		for(Employee employee : Resource.employees)
		{
			if(employee.employeeID == id)
			{
				return employee.employeeName;
			}
		}
		
		return null;
		
	}
	
	
	public static Role getRole( int id)
	{
		
		for(Employee employee : Resource.employees)
		{
			if(employee.employeeID == id)
			{
				return employee.role;
			}
		}
		return null;
	}
	
	
	public static Employee getEmployeeObject( int id)
	{
		
		for(Employee employee : Resource.employees)
		{
			if(employee.employeeID == id)
			{
				return employee;
			}
		}
		return null;
		
	}
	
	
	public static int getEmployeeID( String name)
	{
		
		for(Employee employee : Resource.employees)
		{
			if(employee.employeeName.equalsIgnoreCase(name))
			{
				return employee.employeeID;
			}
		}
		
		return 0;
		
	}

	public static boolean isNameValid(String name) 
	{
		
		 String input = name;
		 Pattern pattern = Pattern.compile("[A-Za-z]{4,}");
		 Matcher matcher = pattern.matcher(input);
		 
		 while(matcher.find())
		 {
			 return true;
		 }
		
		return false;
		
	}


	
	
	
	
}
