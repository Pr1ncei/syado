public class Accounts {
    private String username;
    private String password;
    private double balance;
    private Timer timer; // Timer instance

    // Main Constructor
    public Accounts(String username, String password) {
        this.username = username;
        this.password = password;
        this.balance = 0.0;
        this.timer = new Timer(balance); // Initialize Timer with the account balance
    }

    // Overloaded constructor to set balance
    public Accounts(String username, String password, double balance) {
        this.username = username;
        this.password = password;
        this.balance = balance;
        this.timer = new Timer(balance); // Initialize Timer with the account balance
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
        balance += amount;
        if (timer != null) {
            timer.addFunds(amount); // Update timer balance
        }
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
        if (timer != null) {
            timer.stopTimer(); // Stop the timer
        }
    }
}
