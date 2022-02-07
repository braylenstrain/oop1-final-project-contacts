/*
 * Author: Braylen Strain
 * Date:
 * 
 * This class stores Contact information about people.
 */
public class Contact implements SetContact, Comparable<Contact>{
	private String firstName = "N/A";
	private String lastName = "N/A";
	private String personalPhoneNumber = "N/A";
	private String workPhoneNumber = "N/A";
	private String personalEmailAddress = "N/A";
	private String workEmailAddress = "N/A";
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
	public String getPersonalPhoneNumber() {
		return personalPhoneNumber;
	}
	public void setPersonalPhoneNumber(String personalPhoneNumber) {
		this.personalPhoneNumber = personalPhoneNumber;
	}
	
	
	public String getWorkPhoneNumber() {
		return workPhoneNumber;
	}
	public void setWorkPhoneNumber(String workPhoneNumber) {
		this.workPhoneNumber = workPhoneNumber;
	}
	
	
	public String getPersonalEmailAddress() {
		return personalEmailAddress;
	}
	public void setPersonalEmailAddress(String personalEmailAddress) {
		this.personalEmailAddress = personalEmailAddress;
	}
	
	
	public String getWorkEmailAddress() {
		return workEmailAddress;
	}
	public void setWorkEmailAddress(String workEmailAddress) {
		this.workEmailAddress = workEmailAddress;
	}
	
	@Override
	public String toString() {
		return String.format("First Name: %s, Last Name: %s, PPN: %s, WPN: %s, Personal EmailAddress: %s, Work EmailAddress: %s", getFirstName(), getLastName(), getPersonalPhoneNumber(), getWorkPhoneNumber(), getPersonalEmailAddress(), getWorkEmailAddress() );
	}

	//Meant to be overridden by a Set... Class
	@Override
	public void set(String s, Contact c) {
	}
	
	@Override
	public int compareTo(Contact o) {
		System.out.println("YOU USED CONTACT compareTo NOT A SUBCLASS");
		return 0;
	}
	
//	@Override
//	public boolean equals(Object o) {
//		if (o instanceof Contact) {
//			if (firstName.equal)
//		} else {
//			return false;
//		}
//	}
}
