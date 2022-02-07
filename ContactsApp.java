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
			case "1": displayContacts(contacts); break;
			case "2": addContacts(contacts); break;
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
			SetContact[] command = {new SetFirstName(), new SetLastName(), new SetPersonalPhone(), new SetWorkPhone(), new SetPersonalEmail(), new SetWorkEmail()};
			
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
	
	//Display the data fields of all the Contact objects currently in the contacts ArrayList
	public static void displayContacts(ArrayList<Contact> contacts) {
		String[] temp = new String[contacts.size()];
		int tempIndex = 0;
		
		//User decides to alphabetize by first or last name
		boolean alphabetizeFirstName = true;
		System.out.print("Alphabetize by first name or last name? (Type \"First\" or \"Last\"): ");
		if (USERINPUT.nextLine().trim().equalsIgnoreCase("Last")) {
			alphabetizeFirstName = false;
		}
		System.out.println();
		
		//Print header for contacts display
		System.out.printf("%-30s%-30s%-30s%-30s%-30s\n", "Name", "Personal Phone #", "Work Phone #", "Personal E-mail Address", "Work E-mail Address");
		System.out.printf("-".repeat(150));
		System.out.println();
		

		
		//Store each contact's info from contacts ArrayList into temp Array, then sort temp based on what user decided and print to console
		for (Contact contact: contacts) {
			String fullName = "";
			
			//Alphabetize by first name, exclude any fields with "N/A"
			if (alphabetizeFirstName) {
				if (contact.getFirstName().equals("N/A")) {
					fullName = "~(No first name)~ " + contact.getLastName();
				} else if (contact.getLastName().equals("N/A")) {
					fullName = contact.getFirstName();
				} else {
					fullName = contact.getFirstName() + " " + contact.getLastName();
				}
			
			//Alphabetize by last name, exclude any fields with "N/A"	
			} else {
				if (contact.getFirstName().equals("N/A")) {
					fullName = contact.getLastName();
				} else if (contact.getLastName().equals("N/A")) {
					fullName = "~(No last name)~, " + contact.getFirstName();
				} else {
					fullName = contact.getLastName() + ", " + contact.getFirstName();
				}
			}
			
			temp[tempIndex] = String.format("%-30s%-30s%-30s%-30s%-30s", fullName, contact.getPersonalPhoneNumber(), contact.getWorkPhoneNumber(), contact.getPersonalEmailAddress(), contact.getWorkEmailAddress());
			tempIndex++;
		}
		
		//Print sorted temp Array
		Arrays.sort(temp);
		for (int i = 0; i < temp.length; i++) {
			System.out.println(temp[i]);
		}
		System.out.println();
	}
	
	//for loop to print out contact info
//	for (Contact contact: contacts) {
//		System.out.println(contact);
//	}
}
