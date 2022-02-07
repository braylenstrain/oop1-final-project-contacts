
public class CompareFirstName extends Contact {
	@Override
	public int compareTo(Contact o) {
		if (super.getFirstName().compareToIgnoreCase(o.getFirstName()) > 0) {
			return 1;
		} else if (super.getFirstName().compareToIgnoreCase(o.getFirstName()) > 0) {
			return -1;
		} else {
			return 0;
		}
	}
}
