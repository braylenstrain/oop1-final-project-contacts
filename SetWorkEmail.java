
public class SetWorkEmail extends Contact {
	
	private SetWorkEmail() {
	}
	
	@Override
	public void set(String workEmailAddress, Contact contact) {
		contact.setWorkEmailAddress(workEmailAddress);
	}
}
