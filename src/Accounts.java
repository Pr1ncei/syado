
public class Accounts {

    /*
  * Private Variables
     */
    private String username;
    private String password;
    private double balance;
    private boolean isActive; // Returns True if the Account is active, needs a new java file 
    private int timeSession; // Needed for Timer.java

    // Default Constructor
    public Accounts() {
    }

    // Main Constructor
    public Accounts(String username, String password, double balance) {
        this.username = username;
        this.password = password;
        this.balance = balance;
    }

    // Logging in 
    public Accounts(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getter and Setter 
    // Username
    public String getUsername() {
        return username;
    }

    public void setUsername(String newUsername) {
        this.username = newUsername;
    }

    // Password
    public String getPassword() {
        return password;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    // Balance
    public double getBalance() {
        return balance;
    }

    public void setBalance(double newBalance) {
        this.balance = newBalance;
    }

    // Balance Methods
    // Deposit: Use for Depositing Money for Time Session
    // Use for Timer.java
    public void deposit(double amount) {
        balance += amount;
    }

    // Information Methods
    // Will only work if you managed to login 
    public String getAccountInfo() {
        return "Name: " + username + "\n Balance: " + balance;
    }

    public boolean isActive() {
        return isActive; 
      }
    
      public void setActive(boolean active) {
        this.isActive = active;
      }

    public void displayAccountInfo() {
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        System.out.println("Balance: " + balance);
        System.out.println("Status: " + (isActive ? "Active" : "Inactive")); 
        // Active/Inactive status (still testing)
    }
}
