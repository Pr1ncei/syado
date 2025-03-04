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
  * @version 1.1
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
     * @throws IllegalArgumentException if the username already exists.
     */
    // Note: Username must be unique
    public void addAccount(String username, Account account){
      if (accounts.containsKey(username)) {
        throw new IllegalArgumentException("This username already exists!");
      }
      accounts.put(username, account);
      System.out.println("Account is created successfully for " + username);
    }

    /**
     * Deletes account from the system
     * @param username The username of the account to be deleted
     * @throws IllegalArgumentException if the account already exists
     */

    public void deleteAccount(String username){
      if (!accounts.containsKey(username)) {
        throw new IllegalArgumentException("Account is not found. Cannot delete.");
      }
      accounts.remove(username);
      System.out.println("Account " + username + " has been deleted.");
    }

    /**
     * Checks if an account exists in the system
     * @param username The username of the account to check. Should be a non-null, non-empty string
     * @return True if the account exists in the system, false otherwise
     */
    public boolean checkAccountExist(String username){
      return accounts.containsKey(username);
    }

     /**
     * Retrieves an account by username.
     * @param username The username of the account to retrieve.
     * @return The Account object if found, or null if the account does not exist.
     */
    public Account getAccount(String username){
      return accounts.get(username); //returns the account if it exists, otherwise null
    }

  /* This displays all accounts in the system along with their details
   * If there are no accounts, it notifies the user.
   *
   */
    public void displayAllAccounts(){
      if (accounts.isEmpty()) {
        System.out.println("No accounts in the system.");
      } else {
        System.out.println("List of Accounts:");
        for (Account account : accounts.values()) {
          account.displayAccountInfo();
          System.out.println("---------------"); //separator for readability
      }
    }
  }
}

