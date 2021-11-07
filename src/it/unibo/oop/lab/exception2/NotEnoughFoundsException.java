package it.unibo.oop.lab.exception2;

public class NotEnoughFoundsException extends IllegalStateException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1159808475879283028L;

	@Override
	public String getMessage() {
		return this.toString();
	}

	@Override
	public String toString() {
		return ", not enought found in your account.";
	}
	
	

}
