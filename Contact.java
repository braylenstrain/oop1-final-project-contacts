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
		return String.format("First Name: %s, Last Name: %s, Personal Phone Number: %s, Work Phone Number: %s, Personal Email Address: %s, Work Email Address: %s", getFirstName(), getLastName(), getPersonalPhoneNumber(), getWorkPhoneNumber(), getPersonalEmailAddress(), getWorkEmailAddress() );
	}

	//Meant to be overridden by a Set... Class
	@Override
	public void set(String s, Contact c) {
	}
	
	@Override
	public int compareTo(Contact o) {
		if (firstName.compareToIgnoreCase(o.getFirstName()) > 0) {
			return 1;
		} else if (firstName.compareToIgnoreCase(o.getFirstName()) > 0) {
			return -1;
		} else {
			return 0;
		}
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Contact) {
			if (toString().equals(((Contact)o).toString())) {
				return true;
			}
		}
		return false;
	}
}
