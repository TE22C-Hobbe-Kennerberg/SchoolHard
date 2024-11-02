import java.security.MessageDigest;
import java.util.Scanner;

public final class UserInterface {

    // Singleton pattern.
    private static UserInterface instance;
    private UserInterface() {}
    public static UserInterface getInstance() {
        if (instance == null) {
            instance = new UserInterface();
        }
        return instance;
    }

    public void startUI(){
        startMenu();
    }
    private void startMenu(){
        int choice = userInput("Welcome!", "Login", "Register");
        //System.out.println(choice);
        switch (choice){
            case 1:
                loginMenu();
                break;
            case 2:
                registerMenu();
                break;
        }
    }
    private void loginMenu(){

    }
    private void registerMenu(){
        String username = userInput("Choose a username: ").toLowerCase();

        // Add username validation. (Check if it already exists).
        System.out.println("Username: " + username);

        // Add password validation. (Repeat the same password twice.)
        String rawPassword = userInput("Choose a password: ");
        PasswordHelper pw = new PasswordHelper();
        String hashedPassword = pw.getHash(rawPassword);


        // Uploads to database
        DatabaseConnection db = new DatabaseConnection();
        db.createUser();


        // Redirects to log-in menu.
        loginMenu();
    }


    // Returns an integer based on a prompt and the users selection of alternatives.
    private int userInput(String prompt, String ... options) {
        // Loops until prompt has been answered properly
        do {
            // Prints prompt + options.
            System.out.println(prompt);
            for (int i = 0; i < options.length; i++) {
                System.out.println(i + 1 + "." + options[i]);
            }

            System.out.println();
            // Gets user input.
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            // Handles all user cases.
            boolean badInput;

            try {
                int option = Integer.parseInt(input);
                if(option < 1 || option > options.length) throw new Exception("Number not in range");
                return option;
            }catch (NumberFormatException e) {
                // If it's not a number.
                clearConsole();
                System.out.println("Please enter a valid alternative.");
            }catch (Exception e){
                // If the number is outside the valid range.
                clearConsole();
                System.out.println("Alternative does not exist.");
            }
        } while (true);
    }
    // Returns a string based on a prompt given to the user.
    private String userInput(String prompt){
        System.out.println(prompt);

        // Gets user input.
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input;
    }

    // Clears console using newline character.
    private void clearConsole(){
        for(int i = 0; i < 100; i++){
            System.out.println("\n");
        }
    }
}
