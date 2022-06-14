package utilities;


import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Menu {

    private static final String menuStart = "\n===============================================";
    private static final String menuEnd = "===============================================";

    private static Scanner scanner = new Scanner(System.in);

    private Menu() { }

    public static String chooseDownloadPath() {
        FileOperations fileOperations = new FileOperations();

        System.out.println(menuStart);
        System.out.println("Choose a download path: ");
        String downloadPath = fileOperations.chooseDownloadPath();
        System.out.println(menuEnd);

        return downloadPath;
    }


    public static String[] chooseLoginMethod() {
        System.out.println(menuStart);
        System.out.println("How do you want to login?");
        System.out.println("\t(1) By typing the credentials (recommended)");
        System.out.println("\t(2) By using configuration file");
        System.out.println(menuEnd);

        // TODO: Handle InputMismatchException.
        int option = scanner.nextInt();
        while (option != 1 && option != 2) {
            System.out.print("Invalid number, please try again: ");
            option = scanner.nextInt();
        }

        if (option == 1) {
            System.out.print("\nEmail address: ");
            String email = scanner.next();

            System.out.print("Password: ");
            String password = scanner.next();

            return new String[]{email, password};
        } else {
            Map<String, String> credentials = (Map<String, String>) ConfigurationReader.getProperty("credentials");
            return new String[]{credentials.get("email"), credentials.get("password")};
        }
    }


    public static String chooseCourse(Map<String, String> courses) {
        System.out.println(menuStart);
        System.out.println("Please type in number of the course: \n");
        for (int i = 0; i < courses.size(); i++) {
            System.out.println("\t(" + (i + 1) + ") " + courses.keySet().toArray()[i]);
        }
        System.out.println(menuEnd);

        int option = scanner.nextInt();
        while (option < 1 || option >= courses.size()) {
            System.out.print("Invalid number, please try again: ");
            option = scanner.nextInt();
        }

        return courses.values().toArray()[option - 1].toString();
    }


    public static String chooseModule(List<String> modulesNames) {
        System.out.println(menuStart);
        System.out.println("Please type in the number of module: \n");
        for (int i = 0; i < modulesNames.size(); i++) {
            if (modulesNames.get(i).isEmpty()) continue;
            System.out.println("\t(" + (i+1) + ")" + " " + modulesNames.get(i));
        }
        System.out.println(menuEnd);

        int option = scanner.nextInt();
        while (option < 1 || option >= modulesNames.size()) {
            System.out.print("Invalid number, please try again: ");
            option = scanner.nextInt();
        }

        return modulesNames.get(option - 1);
    }

}
