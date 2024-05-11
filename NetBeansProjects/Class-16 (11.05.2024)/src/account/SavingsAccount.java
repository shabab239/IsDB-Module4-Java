package account;

/**
 *
 * @author Shabab-1281539
 */
public class SavingsAccount extends BankAccount {

    private double interestRate;

    public SavingsAccount() {
    }

    public SavingsAccount(double interestRate) {
        this.interestRate = interestRate;
    }

    @Override
    public void deposit(double amount) {
        super.setBalance(super.getBalance() + amount);
    }

    @Override
    public void withdraw(double amount) {
        super.setBalance(super.getBalance() - amount);
    }

    @Override
    public double calculateInterest() {
        return super.getBalance() * interestRate / 100;
    }

}
