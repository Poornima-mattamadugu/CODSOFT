import java.util.Scanner;

class BankAccount {
    private String owner;
    private double balance;

    public BankAccount(String owner, double balance) {
        this.owner = owner;
        this.balance = balance;
    }

    public String deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return "Deposited $" + amount + ". New balance: $" + balance;
        } else {
            return "Deposit amount must be positive.";
        }
    }

    public String withdraw(double amount) {
        if (amount <= 0) {
            return "Withdrawal amount must be positive.";
        } else if (amount > balance) {
            return "Insufficient balance.";
        } else {
            balance -= amount;
            return "Withdrew $" + amount + ". Remaining balance: $" + balance;
        }
    }

    public String checkBalance() {
        return "Current balance: $" + balance;
    }
}

public class ATM {
    private BankAccount account;
    private Scanner scanner;

    public ATM(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        System.out.println("\n=== ATM Menu ===");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Exit");
        System.out.print("Enter your choice (1-4): ");
    }

    public void run() {
        boolean running = true;

        while (running) {
            showMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println(account.checkBalance());
                    break;
                case "2":
                    double depositAmount = getAmountInput("Enter amount to deposit: ");
                    System.out.println(account.deposit(depositAmount));
                    break;
                case "3":
                    double withdrawAmount = getAmountInput("Enter amount to withdraw: ");
                    System.out.println(account.withdraw(withdrawAmount));
                    break;
                case "4":
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please select 1, 2, 3, or 4.");
            }
        }

        scanner.close();
    }

    private double getAmountInput(String prompt) {
        double amount = -1;
        while (amount <= 0) {
            try {
                System.out.print(prompt);
                amount = Double.parseDouble(scanner.nextLine());

                if (amount <= 0) {
                    System.out.println("Please enter a positive number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
        return amount;
    }

    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount("Poornima", 1000.0);
        ATM atm = new ATM(userAccount);
        atm.run();
    }
}
