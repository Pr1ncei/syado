// Modify Accounts

public class Account {
  private String username;
  private String password;
  private double balance;

  public Account(String username, String password, double balance){
    this.username = username;
    this.password = password;
    this.balance = balance;
  }

  public String getUsername(){
    return username;
  }

  public void setUsername(String username){
    this.username = username;
  }

  public String getPassword(){
    return password;
  }

  public void setPassword(String password){
    this.password = password;
  }

  public double getBalance(){
    return balance;
  }

  public void setBalance(double balance){
    this.balance = balance;
  }

/*
 * Timer function based on the balance of the given user
 * Basically, more balance = more time
 *
 * @param username of the account
 */
  public void initiateTimer(int minutes){
    double cost = minutes * 0.50; //pricing calculation

    if (balance >= cost) {
      balance -= cost;
      System.out.println("Timer initiated for " + minutes + " minutes. Cost: P" + cost);
      System.out.println("Remaining balance: P" + balance);
    } else {
            System.out.println("Insufficient balance to initiate timer.");
        }
    }

/**
 *
 * @param amount
 */
    public void addFunds(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Funds added: P" + amount);
            System.out.println("New balance: P" + balance);
        } else {
            System.out.println("Invalid amount. Please enter a positive value.");
        }
    }

/**
 *
 */
    public void displayAccountInfo() {
        System.out.println("Username: " + username);
        System.out.println("Balance: P" + balance);
    }
}