package it.unibo.oop.lab.exception2;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * JUnit test to test
 * {@link StrictBankAccountImpl}.
 * 
 */
public final class TestStrictBankAccount {

    private static final int AMOUNT = 10_000;
    private static final int N_MAX_ATM_TRANSACTIONS = 10;
	/**
     * Used to test Exceptions on {@link StrictBankAccountImpl}.
     */
    @Test
    public void testBankOperations() {
        
    	
    	/*
         * 1) Creare due StrictBankAccountImpl assegnati a due AccountHolder a
         * scelta, con ammontare iniziale pari a 10000 e nMaxATMTransactions=10
         */
    	
    	final var rFonti = new AccountHolder("Riccardo", "Fonti", 1);
    	final var rRaffaelli = new AccountHolder("Rita", "Raffaelli", 2);
    	final var fontiR = 
    			new StrictBankAccountImpl(rFonti.getUserID(), AMOUNT, N_MAX_ATM_TRANSACTIONS);
    	final var raffaelliR = 
    			new StrictBankAccountImpl(rRaffaelli.getUserID(), AMOUNT, N_MAX_ATM_TRANSACTIONS);
    	
        /*
         *  2) Effetture un numero di operazioni a piacere per verificare che le
         * eccezioni create vengano lanciate soltanto quando opportuno, cioè in
         * presenza di un id utente errato, oppure al superamento del numero di
         * operazioni ATM gratuite.
         */
    	
    	try {
			for (int i = 0; i < N_MAX_ATM_TRANSACTIONS + 1; i++) {
				fontiR.withdrawFromATM(rFonti.getUserID(), 500);
				System.out.println(fontiR.getTransactionCount() + " " + fontiR.getBalance());
			}
		} catch (TransactionsOverQuotaException e) {
			System.out.println(rFonti.getName() + " " + rFonti.getSurname() + e);
		}
    	assertEquals("after 10 atm transaction 4990", 4990, fontiR.getBalance(), 0);
    	assertEquals("expected 10", 10, fontiR.getTransactionCount());
    	fontiR.computeManagementFees(rFonti.getUserID());
    	assertEquals("now i've reset transaction count", 0, fontiR.getTransactionCount());
    	
    	try {
			fontiR.withdraw(2, 1000);
		} catch (WrongAccountHolderException e) {
			System.out.println(e);
		}
    	
    	try {
			raffaelliR.withdraw(rRaffaelli.getUserID(), 15_000);
		} catch (NotEnoughFoundsException e) {
			System.out.println(rRaffaelli.getName() + " " + rRaffaelli.getSurname() + e);
			System.out.println("Max withdraw allowed " + raffaelliR.getBalance());
		}
    	
    	assertEquals("account not change", AMOUNT, raffaelliR.getBalance(), 0);
    }
    
}
