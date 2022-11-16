package hr_management;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Utils 
{
	
	//gender input
	private static final int MALE = 1;
	private static final int FEMALE = 2;
	private static final int OTHERS = 3;
	
	//Educational Qualifications input
	private static final int BE_BTECH = 1;
	private static final int ME_MTECH = 2;
	private static final int ARTS = 3;
	private static final int BACK = 4;
	
	static Scanner scanner = new Scanner(System.in);
	
	
	public static String getStringInput()
	{
		return scanner.nextLine();
	}
	
	
	public static int getIntInput()
	{
		int input = scanner.nextInt();
		scanner.nextLine();
		return input;
	}
	
	
	public static String getNameInput()
	{
		
		printSpace();
		System.out.println(" Enter User Name : ");
		printSpace();
		
		String name = getStringInput();
		printSpace();
		boolean isNameValid = EmployeeValidation.isNameValid(name);
		
		if(isNameValid == true)
		{
			return name;
		}
		else
		{
			printSpace();
			System.out.println("   Name should be minimum of 4 characters & doesn't have Numberic values. !!");
			printSpace();
			return getNameInput();
		}
		
	}
	
	
	public static String getGenderInput() 
	{
		
		String gender;
		Utils.printSpace();
		System.out.println(" Choose Gender :");
		Utils.printSpace();
		System.out.println(" 1. MALE.");
		System.out.println(" 2. FEMALE.");
		System.out.println(" 3. Prefer not to say.");
		
		try
		{
			int userInput = Utils.getIntInput();
			printSpace();
			switch(userInput)
			{
				case MALE :
					gender = "MALE";
					break;
					
				case FEMALE :
					gender = "FEMALE";
					break;
					
				case OTHERS :
					gender = "OTHERS";
					break;
					
				default :
					Utils.printInvalidInputMessage();
					gender = getGenderInput();
			}
			
		}
		catch(InputMismatchException e)
		{
			Utils.printInvalidInputMessage();
			Utils.scanner.nextLine();
			gender = getGenderInput();
		}
		return gender;
		
	}

	
	 public static void printLoginFailMessage()
	 {
		 System.out.println(" Incorrect User name or Login ID.");
		 printSpace();
		 printLine();
	 }
	 
	 
	 public static void printSpace()
	 {
		 System.out.print("\n");
	 }
	 
	 
	 public static void printLine()
	 {
		 System.out.println(" ----------------------------------------");
	 }	 
	 
	 
	 public static void printInvalidInputMessage()
	 {
		 	printSpace();
			System.out.println(" Please Enter a Valid Input.");
			printSpace();
	 }
	 
	 
	 public static void printWelcomeMessage(String name)
	 {
			System.out.println("       Welcome "+name+" !!");
			Utils.printLine();
	 }
	 
	 
	 public static void printHeader()
	 {
		 	Utils.printSpace();
			System.out.println(" FEATURES :");
	 }


	public static void printLogOutMessage()
	{
		printSpace();
		System.out.println("	~ Logged Out ~	");	
		printSpace();
	}
	
	
	public static void printTeamAddedSuccessful()
	{
		printSpace();
		System.out.println(" Successfully added a Team. ");
		printSpace();
	}
	 
	 
	public static String generateMail()
	{
		
		System.out.println("  * Add some Characters [a-z] or Digits [0-9] or Special Characters [ -.&$_] ");
		printSpace();
		System.out.println(" Please enter USERNAME only. Domain Name will be automatically generates.");
		printSpace();
		String mail = Utils.getStringInput()+"@zoho.in";
		printSpace();
		
		if( EmployeeValidation.isEmailValid(mail) == true && EmployeeValidation.isOfficialMailExists(mail) == false)
		{
			return mail;
		}
		else
		{
			Utils.printSpace();
			System.out.println(" Invalid User Name.");
			Utils.printSpace();
			return generateMail();
		}
		
	}
	
	
	public static String getDegreeInput( )
	{
		
		String degree = null;
		int userInput = Utils.getIntInput();
		printSpace();
		
		switch(userInput)
		{
		
			case BE_BTECH :
				degree = "B.E/B.Tech";
				return degree;
				
			case ME_MTECH :
				degree = "M.E/M.Tech";
				return degree;
				
			case ARTS :
				degree = "Arts&Science";
				return degree;
				
			case BACK :
				return degree;
				
			default :
				printInvalidInputMessage();
				return degree = getDegreeInput();
			
		}
		
	}
	
	public static String getPassedOutYear( )
	{
		
		System.out.println(" Passed Out year     Format  -> [ yyyy ]");
		String passedOut = Utils.getStringInput();
		printSpace();
		
		if(EmployeeValidation.isPassedOutYearValid(passedOut) == true)
		{
			return passedOut;
		}
		else
		{
			
			System.out.println(" Year must be greater than 1985 or equals to Present Year.");
			Utils.printSpace();
			return getPassedOutYear();
		}
		
	}

	 
	 
}  // class ends.
