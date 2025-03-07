  /*
  * Case Study: Computer Shop Account Management System
  *
  * This class contains different methods for managing
  * accounts such as add, delete, check balances, and open.
  *
  * Features:
  * - Add new accounts
  * - Delete account
  * - Check account does exists
  * - Retrieve account
  *
  * The implementations are briefly described in the documentation
  * for the methods in this class. These kinds of descriptions ought
  * to be considered implementation notes.
  *
  * @author PROGSDATS Group 4 [CS-101]
  * @since 2024-02-24
  * @version 1.11
  */

 import java.util.*;

 public class Main{
     public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        
        Admin admin = new Admin();

         // Variables
         String username;
         String password;
         double balance;
         boolean active;
 
         /* 
          * Sample Code: 
          * account.setUsername("Masaharu");
          * System.out.println(account.getUsername());
         */
 
         // Log-in Function (TESTING PURPOSES)
        //  System.out.print("Enter username: ");
        //  username = input.nextLine();
        //  System.out.print("Enter password: ");        
        //  password = input.nextLine();
 
        //  if(Users.find(username, password)){
        //      System.out.println("You are logged in");
        //  } else {
        //      System.out.println("Log in failed");
        //  }
 
        // Testing purposes: Displaying All Accounts
        admin.displayAllAccounts();

     }
 }