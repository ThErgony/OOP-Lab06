package it.unibo.oop.lab.exception1;

public class NotEnoughBatteryException extends IllegalStateException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -288979685441144222L;
    
    /**
     * 
     * @return the string representation of instances of this class
     */
    @Override
    public String toString() {
        return "battery exhaust.";
    }

    @Override
    public String getMessage() {
        return this.toString();
    }

}
