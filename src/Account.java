// Modify Accounts

public class Account {
  private String username;
  private String password;
  private double balance;
  private int timeSession;
  private boolean isActive;

  public Account(String username, String password){
    this.username = username;
    this.password = password;
    this.balance = 0.0;
    this.isActive = false;
    this.timeSession = 0;
  }

  public String getUsername(){ return username;}
  public void setUsername(String username){ this.username = username;}

  public String getPassword(){ return password; }
  public void setPassword(String password){ this.password = password; }

  public double getBalance(){ return balance; }
  public void setBalance(double balance){ this.balance = balance; }

  public boolean isActive(){return isActive;}
  public void setActive(boolean status){ this.isActive = status;}

  public void addTime(int minutes){this.timeSession += minutes;}
  public void reduceTime(int minutes){this.timeSession -= minutes;}

}