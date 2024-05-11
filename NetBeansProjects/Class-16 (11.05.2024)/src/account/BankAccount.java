package account;

/**
 *
 * @author Shabab-1281539
 */
public abstract class BankAccount {

    private double balance;

    public abstract void deposit(double amount);

    public abstract void withdraw(double amount);

    public abstract double calculateInterest();

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

}
