
public class SetPersonalEmail extends Contact {
	@Override
	public void set(String personalEmailAddress, Contact contact) {
		contact.setPersonalEmailAddress(personalEmailAddress);
	}
}
