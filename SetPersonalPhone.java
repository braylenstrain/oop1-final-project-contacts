
public class SetPersonalPhone extends Contact {
	
	private SetPersonalPhone() {
	}
	
	@Override
	public void set(String personalPhoneNumber, Contact contact) {
		contact.setPersonalPhoneNumber(personalPhoneNumber);
	}
}
