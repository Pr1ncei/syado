package database;

import java.util.HashMap;
import java.util.Map;
import java.io.*;

public class DatabaseManager {
    private Map<String, Accounts> accounts;
    private static final String STRING_FILE = new File("src", "database/accounts.csv").getPath();

    public DatabaseManager() {
        accounts = new HashMap<>();
        loadAccountsFromCSV();
    }

    /*
     * Loads Accounts from CSV 
     * If the file does not exist, starts with an empty list
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
     * Saves Account to the CSV File. 
     * Used for creating new accounts
     */
    private void saveAccountsToCSV() {
        File file = new File(STRING_FILE);
        file.getParentFile().mkdirs();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write("Username,Password,Balance\n");
            for (Accounts account : accounts.values()) {
                bw.write(account.getUsername() + "," + account.getPassword() + "," + account.getBalance() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving accounts: " + e.getMessage());
        }
    }

    /*
     * Reloads the files from the CSV file.
     * This is used when adding newly created accounts 
     */
    public void refreshAccounts() {
        accounts.clear(); 
        loadAccountsFromCSV(); 
    }

    public void loadAccounts(){
        loadAccountsFromCSV();
    }

    public void saveAccounts(){
        saveAccountsToCSV();
    }

    public Map<String, Accounts> getAccounts() {
        return accounts;
    }
}
