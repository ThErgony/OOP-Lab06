package it.unibo.oop.lab.exception2;

public class TransactionsOverQuotaException extends IllegalStateException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5186015990336694793L;

	@Override
	public String getMessage() {
		return this.toString();
	}

	@Override
	public String toString() {
		return " have finish free ATM transactions, now you pay fee every ATM transaction.";
	}
	
	

}
