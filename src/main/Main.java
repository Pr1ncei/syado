package main;
import database.Accounts;
import database.Admin;
import database.Users;
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
                        System.out.print("Enter username: ");
                        String loginUsername = input.nextLine();
                        System.out.print("Enter password: ");
                        String loginPassword = input.nextLine();

                        if (admin.isAdmin(loginUsername, loginPassword)) {
                            // Admin-specific options
                            System.out.println("Logged in as ADMIN");

                            while (true) {
                                System.out.println("\nAdmin Options:");
                                System.out.println("1. Delete Account");
                                System.out.println("2. Display All Accounts");
                                System.out.println("3. Log Out");
                                System.out.print("Choose an option: ");
                                int adminChoice = input.nextInt();
                                input.nextLine();

                                switch (adminChoice) {
                                    case 1:
                                        System.out.print("Enter username to delete: ");
                                        String deleteUsername = input.nextLine();
                                        try {
                                            admin.deleteAccount(deleteUsername);
                                        } catch (IllegalArgumentException e) {
                                            System.out.println(e.getMessage());
                                        }
                                        break;

                                    case 2:
                                        admin.displayAllAccounts();
                                        break;

                                    case 3:
                                        System.out.println("Admin logged out.");
                                        break;

                                    default:
                                        System.out.println("Invalid option. Try again.");
                                }
                                if (adminChoice == 3) break;
                            }
                        } else if (Users.find(loginUsername, loginPassword)) { // Retrieve the actual account object
                            loggedInAccount = admin.getAccount(loginUsername);

                            if (loggedInAccount != null) {
                                System.out.println("You are logged in as " + loggedInAccount.getUsername());
                                System.out.println("Current Balance: P" + loggedInAccount.getBalance());
                            } else {
                                System.out.println("Error: Account exists in file but not in memory.");
                            }

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
                                        System.out.print("Enter amount to add: ");
                                        double amountToAdd = input.nextDouble();
                                        if (amountToAdd > 0) {
                                            loggedInAccount.deposit(amountToAdd);
                                            System.out.println("Balance updated: P" + loggedInAccount.getBalance());
                                        } else {
                                            System.out.println("Invalid amount.");
                                        }
                                        break;

                                    case 2:
                                        System.out.print("Enter timer minutes: ");
                                        int minutes = input.nextInt();
                                        loggedInAccount.initiateTimer(minutes);
                                        System.out.println("Timer started for " + minutes + " minutes.");
                                        break;

                                    case 3:
                                        System.out.println("Remaining Time: " + loggedInAccount.getRemainingTime() + " minutes");
                                        break;

                                    case 4:
                                        loggedInAccount.stopTimer();
                                        System.out.println("Timer stopped.");
                                        break;

                                    case 5:
                                        loggedInAccount = null;
                                        System.out.println("Logged out."); // Log out
                                        break;

                                    case 6: // Exit
                                        System.out.println("Exiting system.");
                                        input.close();
                                        return;

                                    default:
                                        System.out.println("Invalid option.");
                                }
                                if (loggedInAccount == null) break; // If the user logs out, break the inner loop
                            }
                        } else {
                            System.out.println("Invalid login.");
                        }
                        break;

                    case 3:
                        admin.displayAllAccounts();
                        break;

                    case 4: // Exit
                        System.out.println("Exiting the system.");
                        input.close();
                        return;

                    default:
                        System.out.println("Invalid option.");
                }
            }
        }
    }
}

