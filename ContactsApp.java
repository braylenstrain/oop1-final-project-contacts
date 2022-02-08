/*
 * Author: Braylen Strain
 * Date:
 * 
 * This program allows you to store and view contact information for any number of people.
 */
import java.util.*;
import java.io.*;

public class ContactsApp {
	private static final Scanner USERINPUT = new Scanner(System.in);
	final static int NUMBER_OF_DATA_FIELDS = 6;

	public static void main(String[] args) throws FileNotFoundException {
	
		ArrayList<Contact> contacts = new ArrayList<>();
		
		//Check for file name as command line argument to upload contacts
		if (args.length == 1) {
			//File equals arg, if file exists input contacts
			File file = new File(args[0]);
			if (file.exists()) {
				addContactsFromFile(contacts, file);
			//else say no file and offer to handwrite file or manually input contacts
			} else {
				System.out.println("Command Line Argument file not found.\n");
			}
		//If no command line argument file was input, get file name from user or user creates new contacts list
		} else {
			while (contacts.isEmpty()) {
				System.out.println("Would you like to input a file name to input contacts, or create a new contacts list?");
				System.out.print("Type \"File\" or \"New\": ");
				String answer = USERINPUT.nextLine().trim();
				System.out.println();
				
				//If user inputs File, execute file upload method.
				if (answer.equalsIgnoreCase("File")) {
					System.out.print("Input file name: ");
					File file = new File(USERINPUT.nextLine());
					System.out.println();
					if (file.length() != 0) {
					addContactsFromFile(contacts, file);
					} else {
						System.out.println("File not found or is empty.");
					}
				//If user inputs New, execute create new contacts method. Break if the user enters 0 for number of new contacts.	
				} else if (answer.equalsIgnoreCase("New")){
					addContacts(contacts);
					break;
				//Any other input restarts the while loop.	
				} else {
					System.out.println("Invalid input.");
				}
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
			switch (choice) {
			case "1": displayContacts(contacts); break;
			case "2": addContacts(contacts); break;
			case "3": deleteContact(contacts); break;
			case "4": modifyContact(contacts); break;
			case "5": searchContacts(contacts); break;
			case "6": exit(contacts); break;
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
				System.out.println("\nInvalid input. Returning to Homepage.");
				return;
			}
			
			System.out.println();
			
			//Print header for contacts display
			System.out.printf("%-30s%-30s%-30s%-30s%-30s\n", "Name", "Personal Phone #", "Work Phone #", "Personal E-mail Address", "Work E-mail Address");
			repeat("-", 150); //System.out.printf("-".repeat(150));
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
					if (contact.getLastName().equals("N/A")) {
						fullName = "~(No last name)~, " + contact.getFirstName();
					} else if (contact.getFirstName().equals("N/A")) {
						fullName = contact.getLastName();
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
			System.out.println("You currently have no contacts.\n");
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
			Contact tempContact;
			if (temp.size() > 1) {
				tempContact = multipleMatchesFound(temp, "delete");
			} else {
				tempContact = temp.get(0);
			}
			contacts.remove(tempContact);
		System.out.println("Deletion complete.\n");
		//No matches found
		} else {
			System.out.println("No matches found. \n");
		}
	}
	
	public static void modifyContact(ArrayList<Contact> contacts) {
		ArrayList<Contact> temp = new ArrayList<>();
		Contact tempContact;
		//Search for contact to modify by name
		System.out.println("Input the name of the contact you would like to modify.");
		temp = searchByName(contacts);
		
		//If contact was found, set tempContact to that contact. If multiple contacts were found, user chooses which to modify.
		if (temp.size() > 0) {
			if (temp.size() > 1) {
				tempContact = multipleMatchesFound(temp, "modify");
			} else {
				tempContact = temp.get(0);
			}
		
			//Combine first and last name of Contact so it will print to console pretty
			StringBuilder fullName = new StringBuilder();
			if (!tempContact.getFirstName().equals("N/A")) {
				fullName.append(tempContact.getFirstName());
			}
			
			if (!tempContact.getLastName().equals("N/A")) {
				fullName.append(" " + tempContact.getLastName());
			}
			
			//Get keyword and new info from user
			System.out.printf("Use the following keywords to modify %s's info:\n", fullName);
			System.out.println("FN = First Name, LN = Last Name, PPN = Personal Phone number, WPN = Work Phone Number, PE = Personal Email Address, WE = Work Email Address");
			System.out.println("Type in a keyword, followed by a space, followed by the new information about the contact. (e.g. \"PPN 5551234567\" changes the contact's personal phone number)");
			String keyword = USERINPUT.next().toUpperCase();
			String newInfo = USERINPUT.nextLine().trim();
			if (newInfo.equals("")) {
				newInfo = "N/A";
			}
		
			switch (keyword) {
			case "FN": tempContact.setFirstName(newInfo); break;
			case "LN": tempContact.setLastName(newInfo); break;
			case "PPN": tempContact.setPersonalPhoneNumber(newInfo); break;
			case "WPN": tempContact.setWorkPhoneNumber(newInfo); break;
			case "PE": tempContact.setPersonalEmailAddress(newInfo); break;
			case "WE": tempContact.setWorkEmailAddress(newInfo); break;
			default: System.out.println("Invalid input for keyword/new info. Nothing was changed.");
		}
		System.out.println("Modification complete.\n");
		//No matches found
		} else {
			System.out.println("No matches found. \n");
		}
	}
	
	public static void exit(ArrayList<Contact> contacts) throws FileNotFoundException {
		boolean goodInput = false;
		File file;
		
		while (!goodInput) {
			System.out.print("Would you like to save your contacts before exiting? (Type \"Yes\" or \"No\"): ");
			String answer = USERINPUT.nextLine().trim();
			System.out.println();
			
			//Save contacts to file
			if (answer.equalsIgnoreCase("Yes") || answer.equalsIgnoreCase("Y")) {
				goodInput = true;
				System.out.printf("Type in the name of the file you would like to save your contacts to: ");
				file = new File (USERINPUT.nextLine());
				PrintWriter fileOutput = new PrintWriter(file);
				for (Contact contact: contacts) {
					fileOutput.printf("%s %s %s %s %s %s", contact.getFirstName(), contact.getLastName(), contact.getPersonalPhoneNumber(), contact.getWorkPhoneNumber(), contact.getPersonalEmailAddress(), contact.getWorkEmailAddress());
					fileOutput.println();
				}
				System.out.println("Save Successful.");
				fileOutput.close();
			//Don't save contacts	
			} else if (answer.equalsIgnoreCase("No") || answer.equalsIgnoreCase("N")) {
				goodInput = true;
			} else {
				System.out.println("Invalid Input.");
			}
		}
		System.out.println("Program terminated.");
		System.exit(0);
	}
	
	public static void addContactsFromFile(ArrayList<Contact> contacts, File file) throws FileNotFoundException {
			Scanner fileInput = new Scanner(file);	
				while (fileInput.hasNext()) {
						Contact contact = new Contact();
						contact.setFirstName(fileInput.next());
						contact.setLastName(fileInput.next());
						contact.setPersonalPhoneNumber(fileInput.next());
						contact.setWorkPhoneNumber(fileInput.next());
						contact.setPersonalEmailAddress(fileInput.next());
						contact.setWorkEmailAddress(fileInput.next());
						contacts.add(contact);
				}
				fileInput.close();
	}
	
	//Returns an Array of every contact with the matching name and their info
	public static ArrayList<Contact> searchByName(ArrayList<Contact> contacts) {
		ArrayList<Contact> temp = new ArrayList<>();
		
		//Get first and/or last name from user
		System.out.print("Type in first name (or 0 if none/don't know): ");
		String firstName = USERINPUT.nextLine().trim();
		System.out.print("Type in last name (or 0 if none/don't know): ");
		String lastName = USERINPUT.nextLine().trim();
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
	
}
