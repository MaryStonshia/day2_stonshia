import java.util.*;

class AccountNotFoundException extends Exception {
    public AccountNotFoundException(String message) {
        super(message);
    }
}

class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

class NegativeAmountException extends Exception {
    public NegativeAmountException(String message) {
        super(message);
    }
}

class Bank {
    private Map<String, Double> accounts = new HashMap<>();

    public void createAccount(String accountNumber, double initialBalance) {
        if (accounts.containsKey(accountNumber)) {
            System.out.println("Account already exists.");
        } else {
            accounts.put(accountNumber, initialBalance);
            System.out.println("Account created successfully.");
        }
    }

    public void deposit(String accountNumber, double amount) throws AccountNotFoundException, NegativeAmountException {
        if (amount <= 0) {
            throw new NegativeAmountException("Deposit amount must be positive.");
        }
        Double balance = accounts.get(accountNumber);
        if (balance == null) {
            throw new AccountNotFoundException("Account not found.");
        }
        accounts.put(accountNumber, balance + amount);
        System.out.println("Deposited " + amount + " to account " + accountNumber);
    }

    public void withdraw(String accountNumber, double amount) throws AccountNotFoundException, InsufficientFundsException, NegativeAmountException {
        if (amount <= 0) {
            throw new NegativeAmountException("Withdrawal amount must be positive.");
        }
        Double balance = accounts.get(accountNumber);
        if (balance == null) {
            throw new AccountNotFoundException("Account not found.");
        }
        if (balance < amount) {
            throw new InsufficientFundsException("Insufficient funds.");
        }
        accounts.put(accountNumber, balance - amount);
        System.out.println("Withdrew " + amount + " from account " + accountNumber);
    }

    public void checkBalance(String accountNumber) throws AccountNotFoundException {
        Double balance = accounts.get(accountNumber);
        if (balance == null) {
            throw new AccountNotFoundException("Account not found.");
        }
        System.out.println("Balance for account " + accountNumber + ": " + balance);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bank bank = new Bank();

        while (true) {
            System.out.println("\nBank Menu:");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter account number: ");
                        String createAccNumber = scanner.nextLine();
                        System.out.print("Enter initial balance: ");
                        double initialBalance = scanner.nextDouble();
                        bank.createAccount(createAccNumber, initialBalance);
                        break;
                    case 2:
                        System.out.print("Enter account number: ");
                        String depositAccNumber = scanner.nextLine();
                        System.out.print("Enter amount to deposit: ");
                        double depositAmount = scanner.nextDouble();
                        bank.deposit(depositAccNumber, depositAmount);
                        break;
                    case 3:
                        System.out.print("Enter account number: ");
                        String withdrawAccNumber = scanner.nextLine();
                        System.out.print("Enter amount to withdraw: ");
                        double withdrawAmount = scanner.nextDouble();
                        bank.withdraw(withdrawAccNumber, withdrawAmount);
                        break;
                    case 4:
                        System.out.print("Enter account number: ");
                        String balanceAccNumber = scanner.nextLine();
                        bank.checkBalance(balanceAccNumber);
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (AccountNotFoundException | InsufficientFundsException | NegativeAmountException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
