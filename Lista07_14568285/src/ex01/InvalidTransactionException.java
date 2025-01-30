package ex01;

public class InvalidTransactionException extends Exception {
	private static final long serialVersionUID = -268959986322969507L;
	
	public String toString() {
		return("InvalidTransaction");
	}
}
