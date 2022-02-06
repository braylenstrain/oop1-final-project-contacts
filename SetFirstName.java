public class SetFirstName extends Contact {
	
	private SetFirstName() {
	}
	
	@Override
	public void set(String firstName, Contact contact) {
		contact.setFirstName(firstName);
	}
}
