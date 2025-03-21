/*
 * Syado: Account Management System
 * 
 * @author: PROGSDATS: Group 5 
 * @version: v1.0.0-beta.1 
 */

 package main;
 import Users.Admin;
import Users.Login;
import database.Accounts;
import database.DatabaseManager;
import java.util.*;
 
 public class Main {
     private static final Scanner input = new Scanner(System.in);
     private static final Admin admin = new Admin();
     private static final DatabaseManager dm = new DatabaseManager();
          
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
                  dm.refreshAccounts();
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
              } else if (Login.find(loginUsername, loginPassword)) { // Retrieve the actual account object
                  Accounts account = admin.getAccount(loginUsername);
                  if (account != null) {
                      
                      System.out.println("\n=== Welcome " + account.getUsername() + " ===");
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
              boolean status = false; 
             
              System.out.println("Logged in as ADMIN");
      
              while (!status) {
                  System.out.println("\n=== Admin Options ===");
                  System.out.println("1. Delete Account");
                  System.out.println("2. Add Account");
                  System.out.println("3. Display All Accounts");
                  System.out.println("4. Log Out");
                  System.out.print("Choose an option: ");
                  
                  int adminChoice = input.nextInt();
                  input.nextLine();
      
                  switch (adminChoice) {
                      case 1 -> {
                          // Delete Account
                          System.out.print("Enter username to delete: ");
                          String deleteUsername = input.nextLine();
                          try {
                              admin.deleteAccount(deleteUsername);
                          } catch (IllegalArgumentException e) {
                              System.out.println(e.getMessage());
                          }
                      }
      
                      case 2 -> {
                          // Add new account
                          System.out.print("Enter username to add: ");
                          String newUsername = input.nextLine();
                          System.out.print("Enter password to add: ");
                          String newPassword = input.nextLine();
                          try{
                              admin.addAccount(newUsername, newPassword);
                              dm.refreshAccounts();
                          } catch (IllegalArgumentException e){
                              System.out.println(e.getMessage());
                          }
                      }
     
                      case 3 -> // Display All Accounts
                          admin.displayAllAccounts();
      
                      case 4 -> {
                          System.out.println("Admin logged out.");
                          status = true;
                      }
      
                      default -> System.out.println("Invalid option. Try again.");
     
                  
                  }
              }
          }
      
          private static void userActions(Accounts account){            
            while (true){
                  System.out.println("\n=== User Options ===");
                  System.out.println("1. Add Balance");
                  System.out.println("2. Check Balance");
                  System.out.println("3. Initiate Timer");
                  System.out.println("4. Check Remaining Time");
                  System.out.println("5. Stop Timer");
                  System.out.println("6. Log Out");
                  System.out.println("7. Exit");
                  System.out.print("Choose an option: ");
                  int userChoice = input.nextInt();
                  input.nextLine();
      
                  switch(userChoice){
                      case 1 -> addBalance(account);
                      case 2 -> System.out.println("\nCurrent Balance: P" + account.getBalance());
                      case 3 -> initiateTimer(account);
                      case 4 -> checkRemainingTime(account);
                      case 5 -> stopTimer(account);
                 case 6 -> {
                     System.out.println("Logged out.");
                     return;
                 }
                 case 7 -> {
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
             dm.getAccounts().put(account.getUsername(), account);
             
             // Save and Refresh
             dm.saveAccounts();
             dm.refreshAccounts();
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
                System.out.println("===================");
                System.out.println("Welcome to the Computer Shop Account Management System");
                System.out.println("1. Create Account");
                System.out.println("2. Log In");
                System.out.println("3. Exit");
                System.out.println("===================");
    
                System.out.print("Choose an option: ");
                if (input.hasNextInt()) {
                    System.out.println("===================");
                    int choice = input.nextInt();
                    input.nextLine(); 
    
                    switch (choice) {
                        case 1 -> createAccount();
                        case 2 -> loggedInAccount = login(); // User logs in here
                        case 3 -> {
                            System.out.println("Exiting the system.");
                            input.close();
                            System.exit(0);
                        }
                        default -> System.out.println("Invalid Option. Try Again.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a number.");
                    input.nextLine(); // Clear the invalid input
                }
            } 
            
            if (loggedInAccount != null) {
                userActions(loggedInAccount);
                loggedInAccount = null; // Reset after logging out
            }
        }
    }
 }