package account;

/**
 *
 * @author Shabab-1281539
 */
public class CheckingAccount extends BankAccount {

    private double withdrawalLimit;
    private double fee;

    @Override
    public void deposit(double amount) {
        super.setBalance(super.getBalance() + amount);
    }

    @Override
    public void withdraw(double amount) {
        if (amount > withdrawalLimit) {
            amount += fee;
        }
        super.setBalance(super.getBalance() - amount);
    }

    @Override
    public double calculateInterest() {
        return 0.0;
    }

}