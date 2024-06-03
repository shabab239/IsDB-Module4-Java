package finance;

/**
 *
 * @author Shabab-1281539
 */
public class CheckingAccount extends BankAccount {
    
    private final double freeWithdrawLimit = 50000.0;
    private final double withdrawFee = 100.0;

    @Override
    public void deposit(double amount) {
        System.out.println("Amount Deposited: " + amount);
        super.setBalance(super.getBalance() + amount);
    }

    @Override
    public void withdraw(double amount) {
        if (amount > freeWithdrawLimit) {
            amount = amount + withdrawFee;
        }
        System.out.println("Amount Withdrawn: " + amount);
        super.setBalance(super.getBalance() - amount);
    }

    @Override
    public void calculateInterest() {}

    

}
