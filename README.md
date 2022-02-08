# Object Oriented Programming 1 Final Project *Contacts*

## Synopsis
This project simulates a Contacts App you would use on your phone or computer. You have the option to create a new set of contacts, or input a list of contacts from a previously saved file. The features of the program are:
1. Display all of your current contacts
2. Add new contacts
3. Delete a contact
4. Modify a contact's information
5. Search for a contact's information based on their name, phone number, or email
6. Exit the program with or without saving the contacts to a file

## Motivation
This is a project to display my understanding of the topics that I have learned from this course.

## How to Run
The program is used entirely through the console.

## Code Example
This code snippet is how the program displays all the contacts currently stored. User chooses to display them alphabetized by first name or last name.
```
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
```
