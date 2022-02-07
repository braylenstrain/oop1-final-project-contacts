
public class CompareLastName extends Contact {
	@Override
	public int compareTo(Contact o) {
		if (super.getLastName().compareToIgnoreCase(o.getLastName()) > 0) {
			return 1;
		} else if (super.getLastName().compareToIgnoreCase(o.getLastName()) > 0) {
			return -1;
		} else {
			return 0;
		}
	}
}