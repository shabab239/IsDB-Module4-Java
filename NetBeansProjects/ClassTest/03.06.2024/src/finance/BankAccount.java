package finance;

/**
 *
 * @author Shabab-1281539
 */
public abstract class BankAccount {

    private double balance;

    abstract void deposit(double amount);

    abstract void withdraw(double amount);

    abstract void calculateInterest();

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

}
