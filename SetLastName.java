
public class SetLastName extends Contact {
	
	private SetLastName() {
	}
	
	@Override
	public void set(String lastName, Contact contact) {
		contact.setLastName(lastName);
	}
}
