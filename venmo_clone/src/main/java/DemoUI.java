import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DemoUI {
    private final AccountManager accountManager = new AccountManager();
    private final Map<String, UserAccount> users = new HashMap<>();

    public static void main(String[] args) {
        new DemoUI().run();
    }

    private void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Venmo Clone Demo");
        boolean running = true;
        while (running) {
            printMenu();
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim();
            try {
                switch (choice) {
                    case "1" -> createUser(scanner);
                    case "2" -> depositFunds(scanner);
                    case "3" -> sendMoney(scanner);
                    case "4" -> showUser(scanner);
                    case "5" -> listUsers();
                    case "6" -> running = false;
                    default -> System.out.println("Unknown option. Please try again.");
                }
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
            System.out.println();
        }
        System.out.println("Goodbye!");
    }

    private void printMenu() {
        System.out.println("==== Menu ====");
        System.out.println("1) Create user");
        System.out.println("2) Deposit funds");
        System.out.println("3) Send money");
        System.out.println("4) Show user");
        System.out.println("5) List users");
        System.out.println("6) Exit");
    }

    private void createUser(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine().trim();
        if (username.isEmpty()) {
            System.out.println("Username cannot be empty.");
            return;
        }
        if (users.containsKey(username)) {
            System.out.println("Username already exists.");
            return;
        }
        // Use existing constructor that accepts an AccountManager to share IDs across users
        UserAccount user = new UserAccount(username, accountManager);
        accountManager.addUser(user);
        users.put(username, user);
        System.out.println("Created user: " + user);
    }

    private void depositFunds(Scanner scanner) {
        UserAccount user = promptUser(scanner, "Enter username to deposit into: ");
        if (user == null) return;
        Integer amount = promptAmount(scanner, "Enter deposit amount (integer >= 0): ");
        if (amount == null) return;
        boolean ok = user.depositFunds(amount);
        if (ok) {
            System.out.println("New balance for " + user.getUsername() + ": " + user.getAccountBalance());
        } else {
            System.out.println("Deposit failed.");
        }
    }

    private void sendMoney(Scanner scanner) {
        UserAccount sender = promptUser(scanner, "Enter sender username: ");
        if (sender == null) return;
        UserAccount receiver = promptUser(scanner, "Enter receiver username: ");
        if (receiver == null) return;
        Integer amount = promptAmount(scanner, "Enter amount to send (integer >= 0): ");
        if (amount == null) return;
        boolean ok = sender.sendMoney(amount, receiver);
        if (ok) {
            System.out.println("Transfer successful.");
            System.out.println("Sender new balance: " + sender.getAccountBalance());
            System.out.println("Receiver new balance: " + receiver.getAccountBalance());
        } else {
            System.out.println("Transfer failed.");
        }
    }

    private void showUser(Scanner scanner) {
        UserAccount user = promptUser(scanner, "Enter username to show: ");
        if (user != null) {
            System.out.println(user);
        }
    }

    private void listUsers() {
        if (users.isEmpty()) {
            System.out.println("No users created yet.");
            return;
        }
        System.out.println("Users:");
        users.values().forEach(u -> System.out.println(" - " + u));
    }

    private UserAccount promptUser(Scanner scanner, String prompt) {
        System.out.print(prompt);
        String username = scanner.nextLine().trim();
        UserAccount user = users.get(username);
        if (user == null) {
            System.out.println("User not found: " + username);
        }
        return user;
    }

    private Integer promptAmount(Scanner scanner, String prompt) {
        System.out.print(prompt);
        String value = scanner.nextLine().trim();
        try {
            int amount = Integer.parseInt(value);
            if (amount < 0) {
                System.out.println("Amount must be >= 0.");
                return null;
            }
            return amount;
        } catch (NumberFormatException nfe) {
            System.out.println("Invalid number.");
            return null;
        }
    }
}

