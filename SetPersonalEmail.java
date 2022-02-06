
public class SetPersonalEmail extends Contact {
	
	private SetPersonalEmail() {
	}
	
	@Override
	public void set(String personalEmailAddress, Contact contact) {
		contact.setPersonalEmailAddress(personalEmailAddress);
	}
}
