package finance;

/**
 *
 * @author Shabab-1281539
 */
public class SavingsAccount extends BankAccount {

    private final double interestRate = 0.5; //5%

    @Override
    public void deposit(double amount) {
        System.out.println("Amount Deposited: " + amount);
        super.setBalance(super.getBalance() + amount);
    }

    @Override
    public void withdraw(double amount) {
        System.out.println("Amount Withdrawn: " + amount);
        super.setBalance(super.getBalance() - amount);
    }

    @Override
    public void calculateInterest() {
        System.out.println("Calculated Interest: " + super.getBalance() * interestRate);
    }

    public double getInterestRate() {
        return interestRate;
    }

}
