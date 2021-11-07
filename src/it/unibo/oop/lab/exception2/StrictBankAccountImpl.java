package it.unibo.oop.lab.exception2;

/**
 * Class modeling a BankAccount with strict policies: getting money is allowed
 * only with enough founds, and there are also a limited number of free ATM
 * transaction (this number is provided as a input in the constructor).
 * 
 */
public class StrictBankAccountImpl implements BankAccount {

    private final int usrID;
    private double balance;
    private int totalTransactionCount;
    private final int maximumAllowedATMTransactions;
    private static final double ATM_TRANSACTION_FEE = 1;
    private static final double MANAGEMENT_FEE = 5;
    private static final double TRANSACTION_FEE = 0.1;
    
    private int totalATMTransactionCount;

    /**
     * 
     * @param usrID
     *            user id
     * @param balance
     *            initial balance
     * @param maximumAllowedAtmTransactions
     *            max no of ATM transactions allowed
     */
    public StrictBankAccountImpl(final int usrID, final double balance, final int maximumAllowedAtmTransactions) {
        this.usrID = usrID;
        this.balance = balance;
        this.maximumAllowedATMTransactions = maximumAllowedAtmTransactions;
    }

    /**
     * 
     * {@inheritDoc}
     */
    public void deposit(final int usrID, final double amount) {
        if (checkUser(usrID)) {
            this.balance += amount;
            increaseTransactionsCount();
        }
    }

    /**
     * 
     * {@inheritDoc}
     */
    public void withdraw(final int usrID, final double amount) {
        if (checkUser(usrID) && isWithdrawAllowed(amount)) {
            this.balance -= amount;
            increaseTransactionsCount();
        }
    }

    /**
     * 
     * {@inheritDoc}
     */
    public void depositFromATM(final int usrID, final double amount) {
        if (isAllowedATMTransaction()) {
            this.deposit(usrID, amount);
            incATMTransactionCount();
        } else {
        	this.deposit(usrID, amount - StrictBankAccountImpl.ATM_TRANSACTION_FEE);
        	incATMTransactionCount();
            throw new TransactionsOverQuotaException();
		}
    }

    /**
     * 
     * {@inheritDoc}
     */
    public void withdrawFromATM(final int usrID, final double amount) throws TransactionsOverQuotaException {
        if (isAllowedATMTransaction()) {
            this.withdraw(usrID, amount);
            incATMTransactionCount();
        } else {
            this.withdraw(usrID, amount + StrictBankAccountImpl.ATM_TRANSACTION_FEE);
            incATMTransactionCount();
            throw new TransactionsOverQuotaException();
		}
    }
    
   	private void incATMTransactionCount() {
		this.totalATMTransactionCount++;
	}

	private boolean isAllowedATMTransaction(){
		return totalATMTransactionCount < maximumAllowedATMTransactions;
	}

    /**
     * 
     * {@inheritDoc}
     */
    public double getBalance() {
        return this.balance;
    }

    /**
     * 
     * {@inheritDoc}
     */
    public int getTransactionCount() {
        return this.totalTransactionCount;
    }

    /**
     * 
     * @param usrID
     *            id of the user related to these fees
     */
    public void computeManagementFees(final int usrID) {
        final double feeAmount = MANAGEMENT_FEE + (totalTransactionCount * StrictBankAccountImpl.TRANSACTION_FEE);
        if (checkUser(usrID) && isWithdrawAllowed(feeAmount)) {
            balance -= MANAGEMENT_FEE + totalTransactionCount * StrictBankAccountImpl.TRANSACTION_FEE;
            this.totalTransactionCount = 0;
            this.totalATMTransactionCount = 0;
        }
    }

    private boolean checkUser(final int id) throws WrongAccountHolderException {
        if (this.usrID != id) {
			throw new WrongAccountHolderException();
		}
    	return true;
    }

    private boolean isWithdrawAllowed(final double amount) throws NotEnoughFoundsException {
        if (amount > balance) {
			throw new NotEnoughFoundsException();
		}
    	return true;
    }

    private void increaseTransactionsCount() {
        this.totalTransactionCount++;
    }
}
