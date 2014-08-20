package exceptions;

public class NodeNotFound extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public NodeNotFound(int n) {
		super("Node "+n+" doesn't exist");
	}
}
