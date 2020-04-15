package exceptions;

public class DatabaseConnectionProblem extends Exception {

	/**
	 * This exception is thrown when database connection exception is thrown
	 */
	private static final long serialVersionUID = 1L;
	
	public DatabaseConnectionProblem(){
		super("There is a problem while connecting database.");
	}

}