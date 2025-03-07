import java.io.*;
import java.util.*;

public class Admin{
    private Map<String, Accounts> accounts;
    private static final String STRING_FILE = "database/accounts.csv";

    public Admin(){
        accounts = new HashMap<>();
        loadAccountsFromCSV(); // Users.java
    }

    /*
     * Loads Accounts from a CSV File (Database) and puts into a HashMap
     */
    private void loadAccountsFromCSV(){
        try (BufferedReader br = new BufferedReader(new FileReader(STRING_FILE))) {
            String line;
            br.readLine(); 
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens.length == 3) { 
                    String username = tokens[0].trim();
                    String password = tokens[1].trim();
                    double balance = Double.parseDouble(tokens[2].trim()); // Read balance
    
                    accounts.put(username, new Accounts(username, password, balance));
                }
            }
        } catch (IOException e) {
            System.out.println("No existing accounts found. Starting fresh.");
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid balance format in CSV.");
        }
    }
    
    /*
     * Saves the newly added accounts into a CSV
     */
    private void saveAccountsToCSV() {
        try {
            // Ensure "database" folder exists
            File dir = new File("database");
            if (!dir.exists()) {
                dir.mkdirs();
            }
    
            File file = new File("database/accounts.csv");
            boolean fileExists = file.exists();
    
            BufferedWriter bw = new BufferedWriter(new FileWriter(file, false)); // Overwrite file
            bw.write("Username,Password,Balance\n"); // CSV Header
    
            for (Accounts account : accounts.values()) {
                bw.write(account.getUsername() + "," + account.getPassword() + "," + account.getBalance() + "\n");
            }
    
            bw.close();
            System.out.println("Accounts saved successfully!");
    
        } catch (IOException e) {
            System.out.println("Error: Could not save accounts to CSV. " + e.getMessage());
        }
    }
    
    

    /*
     * Adds a new account to the system 
     * Makes sure that the username is unique
     * @param username The username for the account
     * @param account The account object
     * @throws IllegalArgumentException if the username already exists
     */


     public void addAccount(String username, String password, double balance) {
        username = username.trim();
    
        if (accounts.containsKey(username)) {
            System.out.println("Error: Username already exists! Please choose a different username.");
            return; // Stops execution if username is taken
        }
    
        // Proceed only if username is unique
        Accounts newAccount = new Accounts(username, password, balance);
        accounts.put(username, newAccount);
        saveAccountsToCSV();
        
        // This message only appears when the account is created
        System.out.println("Account successfully created! Please log in.");
    }
    
    

    /*
     * Deletes account from the system
     * @param username The username of the account to be deleted
     * @throws IllegalArgumentException if the account does not exists
     */

     public void deleteAccount(String username){
        username = username.trim();
        if(!accounts.containsKey(username)){
            System.out.println("Account is not found or no longer exists. Cannot delete");
            return;
        }
        accounts.remove(username);
        saveAccountsToCSV();
        System.out.println("Account " + username + " has been deleted.");
     }

     /*
      * Checks if the accounts exists in the system
      * @param username The username of the account to check. Should be a non-null, non-empty string
      * @return True if the account exists in the system, false otherwise
      */
      public boolean checkAccountExist(String username){
        return accounts.containsKey(username);
      }

      
        /*
        * Retrieves an account by username.
        * @param username The username of the account to retrieve.
        * @return The account object if found, or null if the account does not exist.
        */
      public Accounts getAccount(String username){
        return accounts.get(username);
      }
      
      /*
       * This displays all accounts in the system along with their details
       */
      public void displayAllAccounts(){
        if (accounts.isEmpty()){
            System.out.println("No accounts in the system");
        } else {
            System.out.println("List of accounts: ");
            for (Accounts account : accounts.values()){
                account.displayAccountInfo();
                System.out.println("---------------");
            }
        }
      }
}