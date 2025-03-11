public class Timer {
    private double balance;
    private boolean running;
    private int remainingTime;

    public Timer(double initialBalance) {
        this.balance = initialBalance;
        this.running = false;
        this.remainingTime = 0;
    }

    public void initiateTimer(int minutes) {
        double cost = minutes * 0.50; // Pricing calculation

        if (balance >= cost) {
            balance -= cost;
            System.out.println("Timer initiated for " + minutes + " minutes. Cost: P" + cost);
            System.out.println("Remaining balance: P" + balance);
            running = true;
            remainingTime = minutes;

            // Start the countdown timer
            new Thread(() -> startCountdown()).start();
        } else {
            System.out.println("Insufficient balance to initiate timer.");
        }
    }

    private void startCountdown() {
        while (remainingTime > 0 && running) {
            try {
                Thread.sleep(60000); // Sleep for 1 minute
                remainingTime--;
            } catch (InterruptedException e) {
                System.out.println("Timer was interrupted.");
                return; // Exit the method if interrupted
            }
        }

        running = false; // Timer finished
        if (remainingTime == 0) {
            System.out.println("Timer finished!");
        }
    }

    public void stopTimer() {
        running = false; // Stop the timer
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