import java.io.*;

public class Users {
    public static synchronized boolean find(String username, String password) {
        try {
            File file = new File("database/accounts.csv");
            if (!file.exists()) {
                System.out.println("Error: accounts.csv file not found.");
                return false;
            }

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens.length >= 2) {
                    String storedUsername = tokens[0].trim();
                    String storedPassword = tokens[1].trim();

                    if (storedUsername.equals(username) && storedPassword.equals(password)) {
                        br.close();
                        System.out.println("Login successful!");
                        return true;
                    }
                }
            }

            br.close();
        } catch (IOException e) {
            System.out.println("Error reading user data: " + e.getMessage());
        }

        System.out.println("Login failed.");
        return false;
    }
}
