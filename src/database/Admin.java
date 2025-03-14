package database;

import java.io.*;
import java.util.*;

public class Admin {
    private Map<String, Accounts> accounts;
    private static final String STRING_FILE = new File("src", "database/accounts.csv").getPath();

    public Admin() {
        accounts = new HashMap<>();
        loadAccountsFromCSV(); // Load existing accounts
    }

    /*
     * Loads Accounts from a CSV File (Database) and puts into a HashMap
     */
    private void loadAccountsFromCSV() {
        File file = new File(STRING_FILE);
        
        if (!file.exists()) {
            System.out.println("No existing accounts found. Starting fresh.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens.length == 3) { // Adjusted to read balance
                    String username = tokens[0].trim();
                    String password = tokens[1].trim();
                    double balance = Double.parseDouble(tokens[2].trim());
                    accounts.put(username, new Accounts(username, password, balance));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading accounts file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid balance format in CSV.");
        }
    }

    /*
     * Saves the newly added accounts into a CSV
     */
    private void saveAccountsToCSV() {
        File file = new File(STRING_FILE);
        file.getParentFile().mkdirs(); // Ensure "src/database" folder exists

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write("Username,Password,Balance\n");
            for (Accounts account : accounts.values()) {
                bw.write(account.getUsername() + "," + account.getPassword() + "," + account.getBalance() + "\n");
            }
            System.out.println("Accounts saved successfully at: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error saving accounts: " + e.getMessage());
        }
    }

    public void addAccount(String username, String password) {
        if (accounts.containsKey(username)) {
            throw new IllegalArgumentException("This username already exists!");
        }
        Accounts account = new Accounts(username, password);
        accounts.put(username, account);
        saveAccountsToCSV();
        
        // This message only appears when the account is created
        System.out.println("Account successfully created! Please log in.");
    }

    public void deleteAccount(String username) {
        if (!accounts.containsKey(username)) {
            throw new IllegalArgumentException("Account is not found or no longer exists. Cannot delete");
        }
        accounts.remove(username);
        saveAccountsToCSV();
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
            System.out.println("List of accounts: ");
            for (Accounts account : accounts.values()) {
                account.displayAccountInfo();
                System.out.println("---------------");
            }
        }
    }
}
