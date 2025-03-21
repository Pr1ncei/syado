package database;

import Utilities.AccountTimer;

public class Accounts {
    private String username;
    private String password;
    private double balance;
    private AccountTimer timer; // Timer instance

    // Main Constructor
    public Accounts(String username, String password) {
        this.username = username;
        this.password = password;
        this.balance = 0.0;
        this.timer = new AccountTimer(this); // Pass the Accounts instance itself
    }
    
    public Accounts(String username, String password, double balance) {
        this.username = username;
        this.password = password;
        this.balance = balance;
        this.timer = new AccountTimer(this); // Pass the Accounts instance itself
    }
    

    // Getter methods

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public double getBalance() {
        return balance;
    }

    // Deposit funds
    public void deposit(double amount) {
        if (balance + amount < 0) {
            System.out.println("Error: Insufficient funds.");
            return;
        }
        balance += amount;
    }

    // Method to display account information
    public void displayAccountInfo() {
        System.out.println("Username: " + username);
        System.out.println("Balance: P" + balance);
    }

    // Initiate the timer
    public void initiateTimer(int minutes) {
        if (timer != null) {
            timer.initiateTimer(minutes);
        } else {
            System.out.println("Timer is not initialized.");
        }
    }

    public boolean isTimerRunning() {
        return timer.isRunning(); // Check if the timer is running
    }

    public int getRemainingTime() {
        return timer.getRemainingTime(); // Get remaining time from Timer
    }

    public void stopTimer() {
        timer.stopTimer(); // Stop the timer
    }

    public void updateBalance(double amount) {
        this.balance += amount; // Update the balance in memory
        DatabaseManager dm = new DatabaseManager();
        dm.getAccounts().put(this.username, this); // Ensure the updated account is in the database
        dm.saveAccounts(); // Save the new balance to CSV
        dm.refreshAccounts();
    }
    
}
