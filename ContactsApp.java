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
			String answer = USERINPUT.nextLine().trim();
			System.out.println();
			
			//If user inputs File, execute file upload method.
			if (answer.equalsIgnoreCase("File")) {
				//TODO
				System.out.println("Execute file upload method");
			//If user inputs New, execute create new contacts method. Break if the user enters 0 for number of new contacts.	
			} else if (answer.equalsIgnoreCase("New")){
				addContacts(contacts);
				break;
			//Any other input restarts the while loop.	
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
			case "3": deleteContact(contacts); break;
			case "4": System.out.println("modifyContacts"); break;
			case "5": searchContacts(contacts); break;
			case "6": System.out.println("exitProgram"); break;
			default: System.out.println("Invalid input, please type a number 1-6.");
			}
		}
	}
	
	//Add contacts to contacts ArrayList
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
		
		if (newContactsAmount > 0) {
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
	}
	
	//Display the data fields of all the Contact objects currently in the contacts ArrayList
	public static void displayContacts(ArrayList<Contact> contacts) {
		String[] temp = new String[contacts.size()];
		int tempIndex = 0;
		boolean alphabetizeFirstName = true;
		
		//If contacts are in contacts ArrayList, display them.
		if (contacts.size() > 0) {
			//User decides to alphabetize by first or last name. Invalid input returns to Homepage.
			System.out.print("Alphabetize by first name or last name? (Type \"First\" or \"Last\"): ");
			String choice = USERINPUT.nextLine().trim();
			
			if (choice.equalsIgnoreCase("First")) {
				alphabetizeFirstName = true;
			} else if (choice.equalsIgnoreCase("Last")) {
				alphabetizeFirstName = false;
			} else {
				System.out.println("Invalid input. Returning to Homepage.");
				return;
			}
			
			System.out.println();
			
			//Print header for contacts display
			System.out.printf("%-30s%-30s%-30s%-30s%-30s\n", "Name", "Personal Phone #", "Work Phone #", "Personal E-mail Address", "Work E-mail Address");
			repeat("-", 150);
			//System.out.printf("-".repeat(150));
			System.out.println();
			
	
			
			//Store each contact's info from contacts ArrayList into temp Array, then sort temp based on what user decided and print to console
			for (Contact contact: contacts) {
				String fullName = "";
				
				//Alphabetize by first name, exclude any names with "N/A"
				if (alphabetizeFirstName) {
					if (contact.getFirstName().equals("N/A")) {
						fullName = "~(No first name)~ " + contact.getLastName();
					} else if (contact.getLastName().equals("N/A")) {
						fullName = contact.getFirstName();
					} else {
						fullName = contact.getFirstName() + " " + contact.getLastName();
					}
				//Alphabetize by last name, exclude any names with "N/A"	
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
		//No contacts to display	
		} else {
			System.out.println("You have currently have no contacts.\n");
		}
	}
	
	//Search for contact info based on any data field
	public static void searchContacts(ArrayList<Contact> contacts) {
		ArrayList<Contact> temp = new ArrayList<>();
		
		//User chooses to search by name or contact info. Invalid input returns to the Homepage.
		System.out.print("Would you like to search by contact name or contact info? (Type \"Name\" or \"Info\"): ");
		String choice = USERINPUT.nextLine().trim();
		System.out.println();
		
		//Use searchByName method to create an array of matching contacts
		if (choice.equalsIgnoreCase("Name")) {
			temp = searchByName(contacts);
//			if (temp.size() > 0) {
//				System.out.println("Results found.");
//			displayContacts(temp);
//			} else {
//				System.out.println("No matches found.\n");
//			}
		//Store matching contacts by info into temp ArrayList
		} else if (choice.equalsIgnoreCase("Info")) {
			System.out.print("Type in all or part of the phone number or email address you want to search for(Phone # format must be the same(e.g. using hyphens)): ");
			String searchString = USERINPUT.nextLine();
			System.out.println();
			
			for (Contact contact: contacts) {
				if (contact.getPersonalPhoneNumber().contains(searchString)
				|| contact.getWorkPhoneNumber().contains(searchString)
				|| contact.getPersonalEmailAddress().toLowerCase().contains(searchString.toLowerCase())
				|| contact.getWorkEmailAddress().toLowerCase().contains(searchString.toLowerCase())) {
					temp.add(contact);
				}
			}
		//Invalid input returns to Homepage	
		} else {
			System.out.println("Invalid input. Returning to Homepage.");
			return;
		}
		
		//If contacts are found based on search, display temp ArrayList
		if (temp.size() > 0) {
			System.out.println("Results found.");
		displayContacts(temp);
		} else {
			System.out.println("No matches found.\n");
		}
	}
	
	//Delete a contact from contacts ArrayList
	public static void deleteContact(ArrayList<Contact> contacts) {
		ArrayList<Contact> temp = new ArrayList<>();
		
		//Search for contact to delete by name
		System.out.println("Input the name of the contact you would like to delete.");
		temp = searchByName(contacts);
		
		//If contact was found, delete contact. If multiple contacts were found, user chooses which to delete.
		if (temp.size() > 0) {
			if (temp.size() > 1) {
				Contact tempContact = multipleMatchesFound(temp, "delete");
				contacts.remove(tempContact);
			} else {
				contacts.remove(0);
			}
			
		System.out.println("Deletion complete.\n");
		} else {
			System.out.println("No matches found. \n");
		}
	}
	
	//Returns an Array of every contact with the matching name and their info
	public static ArrayList<Contact> searchByName(ArrayList<Contact> contacts) {
		ArrayList<Contact> temp = new ArrayList<>();
		
		//Get first and/or last name from user
		System.out.print("Type in first name (or 0 if N/A): ");
		String firstName = USERINPUT.nextLine();
		System.out.print("Type in last name (or 0 if N/A): ");
		String lastName = USERINPUT.nextLine();
		System.out.println();
		
		//If user input matches a contact, add it to temp ArrayList
		for (Contact contact: contacts) {
			if (contact.getFirstName().equalsIgnoreCase(firstName) && (contact.getLastName().equalsIgnoreCase(lastName) || lastName.trim().equals("0")) 
			|| (contact.getLastName().equalsIgnoreCase(lastName) && firstName.trim().equals("0"))) {
				temp.add(contact);
			}
		}
		return temp;
	}
	
	//Return a single contact when multiple matches are found for deleteContact or modifyContact. String s signifies which method it's coming from.
	public static Contact multipleMatchesFound(ArrayList<Contact> temp, String s) {
		//If temp is not empty, print all Contact objects prefixed with a number.
		if (temp.size() > 0) {
			int count = 1;
			System.out.println("Multiple matches found.");
			for (Contact contact: temp) {
				System.out.println(count + ") " + contact);
				count++;
			}
			
			//User chooses which Contact to return
			boolean correctInput = false;
			int choice = 0;
			while (!correctInput) {
				System.out.printf("Please choose the number of which contact you would like to %s\n", s);
				try {
					choice = USERINPUT.nextInt();
					USERINPUT.nextLine();
					correctInput = true;
				} catch (InputMismatchException ex) {
					System.out.println("Invalid Input. Must be an integer");
					USERINPUT.nextLine();
				}
			}
			
			//If user choice is on the list, return that Contact. Else return a new Contact.
			if (choice >= 1 && choice <= temp.size()) {
				return temp.get(choice -1);
			} else {
				return new Contact();
			}
		//If temp is empty, return a new Contact.
		} else {
			return new Contact();
		}
	}
	
	//Substitute for String.repeat method since it doesn't work with JavaSE 1.8
	public static void repeat(String s, int count) {
		for (int i = 0; i < count; i++) {
			System.out.print(s);
		}
	}
	
	//for loop to print out contact info
//	for (Contact contact: contacts) {
//		System.out.println(contact);
//	}
}
