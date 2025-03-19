/*
 * Syado: Account Management System
 * 
 * @author: PROGSDATS: Group 5 
 * @version: v1.0.0-beta.1 
 */

package main;
import database.Accounts;
import database.Admin;
import database.Users;
import java.util.*;

public class Main {
    private static final Scanner input = new Scanner(System.in);
    private static final Admin admin = new Admin();

    /*
     * Registers and Creates Account to add in the database 
     */
    private static void createAccount(){
        System.out.print("Enter username: ");
        String username = input.nextLine();
        System.out.print("Enter password: ");
        String password = input.nextLine();

        try {
            admin.addAccount(username, password);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /*
     * Login System for Accounts
     */
    private static Accounts login(){
        System.out.print("Enter username: ");
        String loginUsername = input.nextLine();
        System.out.print("Enter password: ");
        String loginPassword = input.nextLine();

        if (admin.isAdmin(loginUsername, loginPassword)){
            adminActions();
            return null;
        } else if (Users.find(loginUsername, loginPassword)) { // Retrieve the actual account object
            Accounts account = admin.getAccount(loginUsername);
            if (account != null) {
                System.out.println("You are logged in as " + account.getUsername());
                System.out.println("Current Balance: P" + account.getBalance());
                return account;
            } else {
                System.out.println("Error: Account exists in file but not in memory.");
            }
        } else {
            System.out.println("Invalid login.");
        }
        return null;
    }

    /*
     * Admin Actions if its an Admin Account
     */
    private static void adminActions(){
        System.out.println("Logged in as ADMIN");

        while (true) {
            System.out.println("\n=== Admin Options ===");
            System.out.println("1. Delete Account");
            System.out.println("2. Display All Accounts");
            System.out.println("3. Log Out");
            System.out.print("Choose an option: ");
            
            int adminChoice = input.nextInt();
            input.nextLine();

            switch (adminChoice) {
                case 1: // Delete Account
                    System.out.print("Enter username to delete: ");
                    String deleteUsername = input.nextLine();
                    try {
                        admin.deleteAccount(deleteUsername);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 2: // Display All Accounts
                    admin.displayAllAccounts();
                    break;

                case 3:
                    System.out.println("Admin logged out.");
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void userActions(Accounts account){
        while (true){
            System.out.println("\n=== User Options ===");
            System.out.println("1. Add Balance");
            System.out.println("2. Initiate Timer");
            System.out.println("3. Check Remaining Time");
            System.out.println("4. Stop Timer");
            System.out.println("5. Log Out");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int userChoice = input.nextInt();
            input.nextLine();

            switch(userChoice){
                case 1 -> addBalance(account);
                case 2 -> initiateTimer(account);
                case 3 -> checkRemainingTime(account);
                case 4 -> stopTimer(account);
                case 5 -> {
                    System.out.println("Logged out.");
                    return;
                }
                case 6 -> {
                    System.out.println("Exiting the system.");
                    input.close();
                    System.exit(0);
                }
            }
        }
    }

    private static void addBalance(Accounts account){ 
        System.out.print("Enter amount to add: ");
        double amountToAdd = input.nextDouble();
        if (amountToAdd > 0) {
            account.deposit(amountToAdd);
            System.out.println("Balance updated: P" + account.getBalance());
        } else {
            System.out.println("Invalid amount.");
        } 
    }

    private static void initiateTimer(Accounts account){
        System.out.print("Enter timer minutes: ");
        int minutes = input.nextInt();
        account.initiateTimer(minutes);
        System.out.println("Timer started for " + minutes + " minutes.");
    }

    private static void checkRemainingTime(Accounts account){
        System.out.println("Remaining Time: " + account.getRemainingTime() + " minutes");
    }

    private static void stopTimer(Accounts account){
        account.stopTimer();
        System.out.println("Timer stopped.");
    }

    public static void main(String[] args) {
        Accounts loggedInAccount = null; 

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
                    case 1 -> createAccount();
                    case 2 -> loggedInAccount = login();
                    case 3 -> admin.displayAllAccounts();
                    case 4 -> {
                        System.out.println("Exiting the system.");
                        input.close();
                        System.exit(0);
                    }
                    default -> System.out.println("Invalid Option. Try Again.");
                }
            } else {
                userActions(loggedInAccount);
                loggedInAccount = null;
            }
        }
    }
}

