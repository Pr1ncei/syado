// Reads all the accounts in CSV
import java.util.*;
public class Users{

    private static List<Accounts> users;

    private static synchronized void readUsers(){
        if(null == users){
            users = new ArrayList<Accounts>();
            String file = ("database/accounts.csv");
            Scanner scan = new Scanner(Accounts.class.getResourceAsStream(file));
            
            if (scan.hasNextLine()){
                scan.nextLine();
            }


            while(scan.hasNextLine()){
                String[] tokens = scan.nextLine().split(",");
                users.add(new Accounts(tokens[0].trim(), tokens[1].trim()));
            }
            scan.close();
        }
    }

    public static synchronized boolean find(String username, String password){
        readUsers(); // Ensure data is loaded
        return users.stream()
            .anyMatch(u -> u.getUsername().equals(username) && u.getPassword().equals(password));
    }

}