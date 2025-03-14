import java.util.TimerTask;

public class Timer {
    private double balance;
    private boolean running;
    private int remainingTime;
    private java.util.Timer timer; // Use java.util.Timer

    public Timer(double initialBalance) {
        this.balance = initialBalance;
        this.running = false;
        this.remainingTime = 0;
        this.timer = new java.util.Timer(); // Initialize the Timer
    }

    public void initiateTimer(int minutes) {
        double cost = minutes * 0.50; // Pricing calculation

        if (balance >= cost) {
            balance -= cost;
            System.out.println("Timer initiated for " + minutes + " minutes. Cost: P" + cost);
            System.out.println("Remaining balance: P" + balance);
            running = true;
            remainingTime = minutes;

            // Schedule the countdown task
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    if (remainingTime > 0 && running) {
                        remainingTime--;
                    } else {
                        running = false; // Timer finished
                        System.out.println("Timer finished!");
                        cancel(); // Stop the timer
                    }
                }
            }, 0, 60000); // Run every minute
        } else {
            System.out.println("Insufficient balance to initiate timer.");
        }
    }

    public void stopTimer() {
        running = false; // Stop the timer
        timer.cancel(); // Cancel the timer
    }

    public boolean isRunning() {
        return running; // Return the timer status
    }

    public int getRemainingTime() {
        return remainingTime; // Return the remaining time
    }

    public void addFunds(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Funds added: P" + amount);
            System.out.println("New balance: P" + balance);
        } else {
            System.out.println("Invalid amount. Please enter a positive value.");
        }
    }
}
