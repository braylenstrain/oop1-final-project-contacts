
public class SetWorkPhone extends Contact {
	
	private SetWorkPhone() {
	}
	
	@Override
	public void set(String workPhoneNumber, Contact contact) {
		contact.setWorkPhoneNumber(workPhoneNumber);
	}
}
