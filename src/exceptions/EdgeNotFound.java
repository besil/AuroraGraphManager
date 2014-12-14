package exceptions;

public class EdgeNotFound extends RuntimeException {
	public EdgeNotFound(int a, int b) {
		super("No edge between "+a+" and "+b);
	}
	public EdgeNotFound(int eid) {
		super("No edge with id "+eid);
	}

	private static final long serialVersionUID = 1L;
}
