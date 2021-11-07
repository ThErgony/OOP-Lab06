package it.unibo.oop.lab.exception2;

public class WrongAccountHolderException extends IllegalArgumentException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7190620326792084901L;

	@Override
	public String toString() {
		return "Wrong account!";
	}

	@Override
	public String getMessage() {
		return this.toString();
	}

}
