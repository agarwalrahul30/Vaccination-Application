package com.example.Vaccination;

import java.util.Scanner;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;
@SpringBootApplication
public class VaccinationApplication {

    public static void main(String[] args) {

        /*
		You need to complete this application as mentioned in the problem 
		statement build your own logic and perform the following tasks.

		 Tasks:
		1. Fetch context from ApplicationContext.xml and initiate Scanner.
		2. Fetch vaccine and User type choice.
		3. Get the required bean from context.
		4. Get the appointment details form user
		5. Display the appointment details
		6. Run the loop again to book for another user or else exit.
		 */
    	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
    	Scanner sc = new Scanner(System.in);
    	System.out.println("Welcome to the Vaccination Application!");
    	
    	while(true) {
	    	System.out.println("Please choose your vaccination preference: \n1. Covid \n2. Polio \n3. Typhoid");
	    	int userVaccineChoice = sc.nextInt();
	    	
	    	String vaccine = "";
	    	switch(userVaccineChoice) {
	    	case 1 -> {
	    		vaccine = "Covid";
	    		break;
	    	}
	    	case 2 -> {
	    		vaccine = "Polio";
	    		break;
	    	}
	    	case 3 -> {
	    		vaccine = "Typhoid";
	    		break;
	    	}
	    	default -> {
	    		System.out.println("Invalid Choice");
	    		return;
	    	}
	    	}
	    	
	    	System.out.println("Whom do you want to vaccinate? \n1. Father \n2. Mother \n3. Self \n4. Spouse \n5. Exit");
	    	int userMemberChoice = sc.nextInt();
	    	
	    	String familyMember = ""; 
	    	
	    	switch(userMemberChoice) {
	    	case 1 -> {
	    		familyMember = "Father";
	    		break;
	    	}
	    	case 2 -> {
	    		familyMember = "Mother";
	    		break;
	    	}
	    	case 3 -> {
	    		familyMember = "Self";
	    		break;
	    	}
	    	case 4 -> {
	    		familyMember = "Spouse";
	    		break;
	    	}
	    	case 5 -> {
	    		System.out.println("Exiting...");
	    		return;
	    	}
	    	default -> {
	    		System.out.println("Invalid Input");
	    		return;
	    	}
	    	}
	    	sc.nextLine();
	    	
	    	User user = (User) context.getBean(familyMember.toLowerCase()+vaccine);
	    	if(user.IsVaccinated()) {
	    		System.out.println("User is already vaccinated.");
	    	} else {
	    		System.out.println("Please enter "+familyMember+" details:");
		    	
		    	System.out.println("Name: ");
		    	String memberName = sc.nextLine();
		    	System.out.println("Age: ");
		    	int memberAge = sc.nextInt();
		    	sc.nextLine();
		    	System.out.println("Appointment Date (DD-MM-YYYY): ");
		    	String appointmentDate = sc.nextLine();
		    	System.out.println("Appointment Time (HH:MM AM/PM): ");
		    	String appointmentTime = sc.nextLine();
		    	System.out.println("Appointment Location: ");
		    	String appointmentLocation = sc.nextLine();
		    	
		    	TimeAndLocation timeAndLocation = (TimeAndLocation) context.getBean("timeAndLocation");
//		    	timeAndLocation.setDetails(appointmentTime, appointmentLocation, appointmentDate);
		    	timeAndLocation.setDate(appointmentDate);
		    	timeAndLocation.setTimeSlot(appointmentTime);
		    	timeAndLocation.setLocation(appointmentLocation);		    	
	    		
	    		user.setUserDetails(memberName, memberAge, timeAndLocation);
	        	user.setAppointment();
	    	}
	    	
	    	System.out.println("Do you want to register for someone else? \n1. Yes \n2. No");
	    	int register = sc.nextInt();
	    	if(register == 2) {
	    		return;
	    	} 
    	}
    }
}