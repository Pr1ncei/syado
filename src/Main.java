import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Admin admin = new Admin();
        boolean running = true;
        boolean loggedIn = false;
        String username = "";
        String password = "";

        while (running) {
            System.out.println("\n=== Computer Shop Account Management System ===");
            System.out.println("1. Log In");
            System.out.println("2. Create Account");
            System.out.println("3. Delete Account");
            System.out.println("4. Add Balance");
            System.out.println("5. Start Session");
            System.out.println("6. Exit");

            System.out.print("Enter choice: ");

            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1: // LOG IN
                    if (loggedIn) {
                        System.out.println("You are already logged in as " + username + ".");
                    } else {
                        System.out.print("Enter username: ");
                        username = input.nextLine();
                        System.out.print("Enter password: ");
                        password = input.nextLine();

                        if (Users.find(username, password)) {
                            System.out.println("Login successful! Welcome, " + username + "!");
                            loggedIn = true;
                        } else {
                            System.out.println("Login failed. Please try again.");
                        }
                    }
                    break;
                case 2: // CREATE ACCOUNT
                    System.out.print("Enter new username: ");
                    String newUsername = input.nextLine();
                    System.out.print("Enter new password: ");
                    String newPassword = input.nextLine();

                    System.out.print("Enter initial balance: ");
                    double balance = input.nextDouble();
                    input.nextLine(); 

                    admin.addAccount(newUsername, newPassword, balance);
                    break;

                case 3: // DELETE ACCOUNT
                    System.out.print("Enter username to delete: ");
                    String deleteUser = input.nextLine();
                    admin.deleteAccount(deleteUser);
                    break;

                case 4: // ADD BALANCE
                    System.out.print("Enter username: ");
                    String userToAddBalance = input.nextLine();
                    if (admin.checkAccountExist(userToAddBalance)) {
                        System.out.print("Enter amount to add: ");
                        double amount = input.nextDouble();
                        input.nextLine(); 
                        admin.getAccount(userToAddBalance).deposit(amount);
                        System.out.println("Balance added successfully.");
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;

                case 5: // START SESSION
                    if (!loggedIn) {
                        System.out.println("You must log in first to start a session.");
                    } else {
                        System.out.println("Starting session for " + username + "...");
                        //timer code here
                    }
                    break;

                case 6: // EXIT
                    System.out.println("Exiting system...");
                    input.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
            }
        }
    }
}

/*  
 * Implemented:  
 * - Prevents duplicate usernames (throws error if username exists in CSV)  
 * - Successfully appends new account data to CSV  
 * - creates new csv if it doesn't exist
 *  
 * Pending Implementation:  
 * - Balance calculation after session  
 */

