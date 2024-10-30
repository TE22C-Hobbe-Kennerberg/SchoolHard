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
        int choice = button("Class", "Add member", "Remove member", "Delete Class");
        System.out.println(choice);
    }
    private void LoginMenu(){

    }

    // Returns an integer based on a question and a set of alternatives.
    private int button(String question, String ... options) {
        // Loops until question has been answered properly
        do {
            // Prints question + options.
            System.out.println(question);
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
            } catch (NumberFormatException e) {
                clearConsole();
                System.out.println("Please enter a valid alternative.");
            }
            catch (Exception e){
                clearConsole();
                System.out.println("Alternative does not exist.");
            }
        } while (true);
    }

    // Clears console using newline character.
    private void clearConsole(){
        for(int i = 0; i < 100; i++){
            System.out.println("\n");
        }
    }
}
