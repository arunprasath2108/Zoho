package hr_management;

import java.util.ArrayList;
import hr_management.EnumClass.Role;


public class Employee 
{
	
	//basic info fields
	int employeeID;
	int reportingToID;
	Role role;
	String employeeTeamName;
	String employeeName;
	String dateOfJoining;
	String employeeReportingTo;
	String employeeWorkLocation;
	String companyMailId;
	String gender;
	
	//personal info fields
	String mobileNumber;
	String emailID;
	String address;
	String workExperience;
	String education;
	
	//list for storing  inbox messages 
	ArrayList<String> inbox = new ArrayList<>();
	
	
	Employee(int id, String name, Role role, String teamName ,  String reportingTo, int reportingToID, String workLocation, String doj, String mail, String gender)
	{
		
		employeeID = id;
		employeeName = name;
		this.role = role;
		employeeTeamName = teamName;
		employeeReportingTo = reportingTo;
		this.reportingToID = reportingToID;
		employeeWorkLocation = workLocation;
		dateOfJoining = doj;
		companyMailId = mail;
		this.gender = gender;
		
	}
	
	
	
	public void setMobileNum(String number)
	{
		mobileNumber = number;
	}
	
	public String getMobileNum()
	{
		return mobileNumber;
	}
	
	public void setEmailID(String mail)
	{
		emailID = mail;
	}
	
	public String getEmailID()
	{
		return emailID;
	}
	
	public void setAddress(String adress)
	{
		address = adress;
	}
	
	public String getAddress()
	{
		return address;
	}
	
	public void setWorkExperience(String work)
	{
		workExperience = work;
	}
	
	public String getWorkExperience()
	{
		return workExperience;
	}
	
	public void setEducation(String studies)
	{
		education = studies;
	}
	
	public String getEducation()
	{
		return education;
	}
	
	public void setInbox(ArrayList<String> inbox) 
	{
		this.inbox = inbox;
	}
	
	public ArrayList<String> getInbox() 
	{
		return inbox;
	}

	
	
	
}   //class ends
