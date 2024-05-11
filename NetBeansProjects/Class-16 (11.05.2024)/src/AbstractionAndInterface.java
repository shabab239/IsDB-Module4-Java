
import account.BankAccount;
import account.CheckingAccount;
import account.SavingsAccount;
import geometry.Circle;
import geometry.GeometricObject;

/**
 *
 * @author Shabab-1281539
 */
public class AbstractionAndInterface {

    public static void main(String[] args) {

//        GeometricObject circle = new Circle(5);
//        circle.setColor("Green");
//        
//        System.out.println("Area: " + circle.getArea());
//        System.out.println("Perimeter: " + circle.getPerimeter());
        System.out.println("=========SAVINGS=========");
        BankAccount savingsAccount = new SavingsAccount(10);
        savingsAccount.setBalance(50000);
        System.out.println("Initial Balance: " + savingsAccount.getBalance());
        savingsAccount.deposit(5000);
        System.out.println("Balance After Deposit: " + savingsAccount.getBalance());
        double interest = savingsAccount.calculateInterest();
        System.out.println("Interest: " + interest);

        System.out.println("=========CHECKING=========");
        BankAccount checkingAccount = new CheckingAccount();
        System.out.println("Initial Balance: " + checkingAccount.getBalance());
        checkingAccount.deposit(50000);
        System.out.println("Balance After Deposit: " + checkingAccount.getBalance());
        checkingAccount.withdraw(7000);
        System.out.println("Balance After Withdraw: " + checkingAccount.getBalance());
    }

}
