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
 * Note: This is only a Class template for now
 *
 * @author PROGSDATS Group 4 [CS-101]
 * @since 2024-02-24
 * @version 1.0
 */

import java.util.HashMap;
import java.util.Map;

public class ComShop{
  // A Map to store the accounts
  private Map<String, Account> accounts;

  // Constructor to initialize the account map
  public ComShop(){
    accounts = new HashMap<>();
  }

  // Method Header Template with Explanation

  /**
   * Adds a new account to the system
   * @param username The username for the account
   * @param account The account object
   */
  public void addAccount(String username, Account account){
    // Note: Username must be unique
  }

  /**
   * Deletes account from the system
   * @param username account that will be deleted from the system
   */
  public void deleteAccount(String username){
  }

  /**
   * Checks if the account exists
   * @param username the username of the account to check
   * @return True
   */
  public boolean checkAccountExist(String username){
    return false;
  }

  /**
   * Retrieves an account by username
   * @param username of the account that will retrieve
   * @return if the account is found
   */
  public Account getAccount(String username){
    return null;
  }
}