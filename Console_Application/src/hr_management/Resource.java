package hr_management;

import java.util.ArrayList;
import java.util.SortedMap;
import java.util.TreeMap;


public class Resource
{
	
	//for storing all employees
	static ArrayList<Employee> employees = new ArrayList<>();
	
	
	//for mapping team ID with team Name
	static SortedMap<Integer, String> teamMap = new TreeMap<>(); 
	
	
	//for MAIL ID generation
	static ArrayList<String> officialMail = new ArrayList<>();
	

}
