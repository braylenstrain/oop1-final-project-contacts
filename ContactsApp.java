/*
 * Author: Braylen Strain
 * Date:
 * 
 * This program allows you to store and view contact information for any number of people.
 */
import java.util.*;

public class ContactsApp {

	public static void main(String[] args) {
		
		ArrayList<Contact> contacts = new ArrayList<>();
		
		//Check for file name as command line argument to upload contacts
		if (args.length == 1) {
			//TODO upload contacts from file in CL arg
			//File equals arg, if file exists input contacts
			//else say no file and offer to handwrite file or manually input contacts
		}
		
		Scanner userInput = new Scanner(System.in);
		
		//If no command line argument file was input, or input file did not exist, get file name from user or user creates new contacts list
		while (contacts.isEmpty()) {
			System.out.println("Would you like to input a file name to input contacts, or create a new contacts list?");
			System.out.print("Type \"File\" or \"New\": ");
			String answer = userInput.next();
			System.out.println();
			
			//If user inputs File, execute file upload method.
			//If user inputs New, execute create new contacts method.
			//Any other input restarts the while loop.
			if (answer.equalsIgnoreCase("File")) {
				//TODO
				System.out.println("Execute file upload method");
				
			} else if (answer.equalsIgnoreCase("New")){
				//TODO
				System.out.println("Execute create new contacts method(Same as add contacts)");
				
			} else {
				System.out.println("Invalid input.\n");
			}
		}
		//Send user to homepage
		//TODO
		System.out.println("Execute homepage method/write out homepage in main method");
		userInput.close();
	}
	
	public static void addContacts(ArrayList<Contact> contacts) {
		
	}
}
