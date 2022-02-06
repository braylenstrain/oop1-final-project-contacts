/*
 * Author: Braylen Strain
 * Date:
 * 
 * This program allows you to store and view contact information for any number of people.
 */
import java.util.*;

public class ContactsApp {
	private static final Scanner USERINPUT = new Scanner(System.in);
	final static int NUMBER_OF_DATA_FIELDS = 6;

	public static void main(String[] args) {
	
		ArrayList<Contact> contacts = new ArrayList<>();
		
		//Check for file name as command line argument to upload contacts
		if (args.length == 1) {
			//TODO upload contacts from file in CL arg
			//File equals arg, if file exists input contacts
			//else say no file and offer to handwrite file or manually input contacts
		}
		
		//If no command line argument file was input, or input file did not exist, get file name from user or user creates new contacts list
		while (contacts.isEmpty()) {
			System.out.println("Would you like to input a file name to input contacts, or create a new contacts list?");
			System.out.print("Type \"File\" or \"New\": ");
			String answer = USERINPUT.nextLine();
			System.out.println();
			
			//If user inputs File, execute file upload method.
			//If user inputs New, execute create new contacts method.
			//Any other input restarts the while loop.
			if (answer.equalsIgnoreCase("File")) {
				//TODO
				System.out.println("Execute file upload method");
				
			} else if (answer.equalsIgnoreCase("New")){
				addContacts(contacts);
			
			} else {
				System.out.println("Invalid input.");
			}
		}
		//Send user to homepage
		String choice = "";
		while (!choice.equals("6")) {
			System.out.println("Homepage:");
			System.out.println("1) Display Contacts");
			System.out.println("2) Add Contacts");
			System.out.println("3) Delete Contacts");
			System.out.println("4) Modify Contacts");
			System.out.println("5) Search Contacts");
			System.out.println("6) Exit Program");
			System.out.print("What would you like to do? (Choose a number): ");
			choice = USERINPUT.nextLine().trim();
			System.out.println();
			
			//Activate action based on user input
			//TODO replace print statements with methods
			switch (choice) {
			case "1": System.out.println("displayContacts"); break;
			case "2": System.out.println("addContacts"); break;
			case "3": System.out.println("deleteContacts"); break;
			case "4": System.out.println("modifyContacts"); break;
			case "5": System.out.println("searchContacts"); break;
			case "6": System.out.println("exitProgram"); break;
			default: System.out.println("Invalid input, please type a number 1-6.");
			}
		}
	}
	
	//Add contacts to ArrayList contacts
	public static void addContacts(ArrayList<Contact> contacts) {
		int newContactsAmount = -1;
		
		//Get number of contacts user would like to create
		while (newContactsAmount == -1) {
			try {
				System.out.print("How many contacts would you like to add?: ");
				newContactsAmount = USERINPUT.nextInt();
				System.out.println();
				USERINPUT.nextLine();
				
			} catch (InputMismatchException ex) {
				System.out.println("\nInvalid input, must be an integer.");
				USERINPUT.nextLine();
			}
		}
		
		//User inputs information for each data field in each Contact object
		System.out.println("Type in the information for each contact, or press enter to skip.\n");
		for (int i = 0; i < newContactsAmount; i++) {
			Contact contact = new Contact();
			String[] contactInfo = new String[NUMBER_OF_DATA_FIELDS];
			Set[] command = {new SetFirstName(), new SetLastName(), new SetPersonalPhone(), new SetWorkPhone(), new SetPersonalEmail(), new SetWorkEmail()};
			
			//Store user input in contactInfo array
			System.out.println("Contact #" + (i + 1));
			System.out.print("First Name: ");
			contactInfo[0] = USERINPUT.nextLine();
			System.out.print("Last Name: ");
			contactInfo[1] = USERINPUT.nextLine();
			System.out.print("Personal Phone Number: ");
			contactInfo[2] = USERINPUT.nextLine();
			System.out.print("Work Phone Number: ");
			contactInfo[3] = USERINPUT.nextLine();
			System.out.print("Personal E-mail: ");
			contactInfo[4] = USERINPUT.nextLine();
			System.out.print("Work E-mail: ");
			contactInfo[5] = USERINPUT.nextLine();
			System.out.println();
			
			//Import data from contactInfo array into contact data fields using the different Set... classes that implement the Set interface
			for (int j = 0; j < NUMBER_OF_DATA_FIELDS; j++) {
				if (contactInfo[j].trim().length() != 0) {
					command[j].set(contactInfo[j].trim(), contact);
				}
			}
			
			//Store contact in contacts
			contacts.add(contact);
		}
	}
	
	//for loop to print out contact info
//	for (Contact contact: contacts) {
//		System.out.println(contact);
//	}
}
