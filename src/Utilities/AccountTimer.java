package Utilities;
import database.Accounts;
import java.util.*;

public class AccountTimer {
    private Accounts account;
    private double balance;
    private boolean running;
    private int remainingTime;
    private Timer timer;

    public AccountTimer(Accounts account) { // Accept Accounts reference
        this.account = account;
        this.running = false;
        this.remainingTime = 0;
        this.timer = new Timer();
    }

    public void initiateTimer(int minutes) {
        double cost = minutes * 0.50; // Pricing calculation

        if (account.getBalance() >= cost) { // Now it properly gets balance
            account.updateBalance(-cost); // Deduct balance from the actual account
            System.out.println("Timer initiated for " + minutes + " minutes. Cost: P" + cost);
            System.out.println("Remaining balance: P" + account.getBalance());
            running = true;
            remainingTime = minutes;

            // Schedule the countdown task
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    if (remainingTime > 0 && running) {
                        remainingTime--;
                    } else {
                        stopTimer();
                        System.out.println("Timer finished!");
                    }
                }
            }, 0, 60000);
        } else {
            System.out.println("Insufficient balance to initiate timer.");
        }
    }

    public void stopTimer() {
        if(running){
            timer.cancel(); // Cancel the timer
            timer.purge();
            remainingTime = 0; 
            running = false; // Stop the timer
            timer = new Timer();
            System.out.println("The Timer has been stopped.");
        } else {
            System.out.println("There is no timer running in the background");
        }
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

    public double getBalance() {
        return balance;
    }
}
