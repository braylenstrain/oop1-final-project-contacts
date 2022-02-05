/*
 * Author: Braylen Strain
 * Date:
 * 
 * This class stores Contact information about people.
 */
public class Contact implements Set{
	private String firstName;
	private String lastName;
	private String personalPhoneNumber;
	private String workPhoneNumber;
	private String personalEmail;
	private String workEmail;
	
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
	
	
	public String getPersonalEmail() {
		return personalEmail;
	}
	public void setPersonalEmail(String personalEmail) {
		this.personalEmail = personalEmail;
	}
	
	
	public String getWorkEmail() {
		return workEmail;
	}
	public void setWorkEmail(String workEmail) {
		this.workEmail = workEmail;
	}
	
	@Override
	public String toString() {
		return String.format("First Name: %s, Last Name: %s, PPN: %s, WPN: %s, Personal Email: %s, Work Email: %s", getFirstName(), getLastName(), getPersonalPhoneNumber(), getWorkPhoneNumber(), getPersonalEmail(), getWorkEmail() );
	}

	
	@Override
	public void set(String s, Contact c) {
		
	}
}
