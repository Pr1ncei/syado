package Users;

import database.Accounts;
import database.DatabaseManager;
import java.util.*;

// import org.junit.platform.reporting.shadow.org.opentest4j.reporting.events.core.Data;

/*
 * The Admin class manages and modifies user accounts 
 * by loading and saving accounts from a CSV file,
 * deleting and adding accounts, and also finding 
 * existing accounts as well
 */
public class Admin {
    private Map<String, Accounts> accounts;
    private static DatabaseManager dm = new DatabaseManager();
    
    // Admin credentials (change if needed)
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin123";

    /*
     * Initializes the Admin class and tools to manage the CSV file
     */
    public Admin() {
        dm.loadAccounts();
        this.accounts = dm.getAccounts();
    }

    public void addAccount(String username, String password) {
        if (accounts.containsKey(username)) {
            throw new IllegalArgumentException("This username already exists!");
        }
        Accounts account = new Accounts(username, password);
        accounts.put(username, account);
        dm.saveAccounts();
        System.out.println("Account successfully created! Please log in.");
    }

    public void deleteAccount(String username) {
        if (!accounts.containsKey(username)) {
            throw new IllegalArgumentException("Account not found. Cannot delete.");
        }
        accounts.remove(username);
        dm.saveAccounts();
        System.out.println("Account " + username + " has been deleted.");
    }

    public boolean checkAccountExist(String username) {
        return accounts.containsKey(username);
    }       

    public Accounts getAccount(String username) {
        return accounts.get(username);
    }

    public void displayAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts in the system");
        } else {
            System.out.println("---------------");
            System.out.println("List of accounts: ");
            for (Accounts account : accounts.values()) {
                account.displayAccountInfo();
                System.out.println("---------------");
            }
        }
    }

    /*
     * Checks if the inputted credentials matches with the admin account 
     */
    public boolean isAdmin(String username, String password) {
        return username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD);
    }

    
}
