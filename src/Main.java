import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Admin admin = new Admin(); // Create an instance of Admin
        Accounts loggedInAccount = null; // To keep track of the logged-in account

        while (true) {
            if (loggedInAccount == null) {
                // Main menu for account management
                System.out.println("Welcome to the Computer Shop Account Management System");
                System.out.println("1. Create Account");
                System.out.println("2. Log In");
                System.out.println("3. Display All Accounts");
                System.out.println("4. Exit");
                System.out.print("Choose an option: ");
                int choice = input.nextInt();
                input.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Enter username: ");
                        String username = input.nextLine();
                        System.out.print("Enter password: ");
                        String password = input.nextLine();

                        try {
                            admin.addAccount(username, password);
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case 2:
                        Accounts account = admin.getAccount(username);
                        if (account != null && account.getPassword().equals(password)) {
                            loggedInAccount = account; // Set the logged-in account
                            System.out.println("You are logged in as " + loggedInAccount.getUsername());
                            System.out.println("Current Balance: P" + loggedInAccount.getBalance()); // Display balance

                            // User management options after logging in
                            while (true) {
                                System.out.println("1. Add Balance");
                                System.out.println("2. Initiate Timer");
                                System.out.println("3. Check Remaining Time");
                                System.out.println("4. Stop Timer");
                                System.out.println("5. Log Out");
                                System.out.println("6. Exit");
                                System.out.print("Choose an option: ");
                                int userChoice = input.nextInt();
                                input.nextLine();

                                switch (userChoice) {
                                    case 1:
                                        System.out.print("Enter the amount to add to your balance: ");
                                        double amountToAdd = input.nextDouble();
                                        if (amountToAdd > 0) {
                                            loggedInAccount.deposit(amountToAdd);
                                            System.out.println("Balance updated. New Balance: P" + loggedInAccount.getBalance());
                                        } else {
                                            System.out.println("Invalid amount. Please enter a positive value.");
                                        }
                                        break;

                                    case 2:
                                        System.out.print("Enter the number of minutes to initiate the timer: ");
                                        int minutes = input.nextInt();
                                        loggedInAccount.initiateTimer(minutes);
                                        // Do not show remaining time or balance text
                                        System.out.println("Timer initiated for " + minutes + " minutes.");
                                        break;

                                    case 3:
                                        if (loggedInAccount.isTimerRunning()) {
                                            System.out.println("Remaining Time: " + loggedInAccount.getRemainingTime() + " minutes");
                                        } else {
                                            System.out.println("Timer is not running.");
                                        }
                                        break;

                                    case 4:
                                        loggedInAccount.stopTimer();
                                        System.out.println("Timer stopped.");
                                        break;

                                    case 5:
                                        loggedInAccount.stopTimer();
                                        loggedInAccount = null; // Log out
                                        System.out.println("You have logged out.");
                                        break;

                                    case 6: // Exit
                                        System.out.println("Exiting the system. Goodbye!");
                                        input.close();
                                        return;

                                    default:
                                        System.out.println("Invalid option. Please try again.");
                                }

                                // If the user logs out, break the inner loop
                                if (loggedInAccount == null) {
                                    break;
                                }
                            }
                        } else {
                            System.out.println("Log in failed. Invalid username or password.");
                        }
                        break;

                    case 3:
                        admin.displayAllAccounts();
                        break;

                    case 4: // Exit
                        System.out.println("Exiting the system. Goodbye!");
                        input.close();
                        return;

                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        }
    }
}