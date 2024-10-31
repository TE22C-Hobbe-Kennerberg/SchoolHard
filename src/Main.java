public class Main {
    public static void main(String[] args) {
        Student student = new Student("Hobe", "Kneeburg", 2024);
        student.convertToJSON();
        System.out.println(student.uuid.toString());

        //UserInterface ui = UserInterface.getInstance();
        //ui.startUI();
        //ServerConnection database = ServerConnection.getInstance();
        //database.connect();

    }
}