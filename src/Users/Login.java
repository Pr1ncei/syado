package Users;

import database.Accounts;
import java.io.*;
import java.util.*;


/*
 * The Login class contains 
 */
public class Login {
    private static List<Accounts> users;

    /*
     * Reads the Users 
     */
    public static synchronized void readUsers() {
        if (users == null) {
            users = new ArrayList<>();
            String file = "src/database/accounts.csv"; // Adjust the path as necessary
            try (Scanner scan = new Scanner(new File(file))) {
                if (scan.hasNextLine()) {
                    scan.nextLine();
                }

                while (scan.hasNextLine()) {
                    String[] tokens = scan.nextLine().split(",");
                    users.add(new Accounts(tokens[0].trim(), tokens[1].trim(), Double.parseDouble(tokens[2].trim())));
                }
            } catch (FileNotFoundException e) {
                System.out.println("Error: File not found.");
            }
        }
    }    

    /*
     * Finds the user in the CSV file
     * 
     * @param username 
     * @param password
     * @return True if there's an account in the 
     */
    public static synchronized boolean find(String username, String password) {
        readUsers(); // Ensure data is loaded
        return users.stream()
            .anyMatch(u -> u.getUsername().equals(username) && u.getPassword().equals(password));
    }
}
