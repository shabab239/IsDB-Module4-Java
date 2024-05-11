package account;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Shabab-1281539
 */
public abstract class BankAccount implements MyBank {

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

    @Override
    public List getClientList() {
        ArrayList<String> clients = new ArrayList();
        clients.add("Skip");

        return clients;
    }

}
