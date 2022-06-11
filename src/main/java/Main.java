import org.openqa.selenium.WebDriver;
import utilities.ConfigurationReader;
import utilities.Driver;

import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        WebDriver driver = Driver.getDriver();

        Login login = new Login(driver);
        Canvas canvas = new Canvas(driver);

        Scanner scanner = new Scanner(System.in);
        int option;

        System.out.println("\n===============================================");
        System.out.println("How do you want to login?");
        System.out.println("\t(1) By typing the credentials (recommended)");
        System.out.println("\t(2) By using configuration file");
        System.out.println("===============================================");

        option = scanner.nextInt();
        while(option != 1 && option != 2) {
            System.out.print("Invalid number, please try again: ");
            option = scanner.nextInt();
        }

        if (option == 1) {
            System.out.println("\n");
            System.out.print("Email address: ");
            String email = scanner.next();

            System.out.print("Password: ");
            String password = scanner.next();

            login.loginToCydeo(email, password);
        } else {
            Map<String, String> credentials = (Map<String, String>) ConfigurationReader.getProperty("credentials");
            login.loginToCydeo(credentials.get("email"), credentials.get("password"));
        }

        canvas.goToCanvasPage();

        Map<String, String> lecturesMap = canvas.getLectureMap();

        System.out.println("\n===============================================");
        System.out.println("Please type in the number of lecture: \n");
        int index = 1;
        for (String lectureName : lecturesMap.keySet()) {
            System.out.println("\t(" + index++ + ")" + " " + lectureName);
        }
        System.out.println("===============================================");

        option = scanner.nextInt();
        while(option < 1 || option >= lecturesMap.size()) {
            System.out.print("Invalid number, please try again: ");
            option = scanner.nextInt();
        }

        String lectureLink = lecturesMap.values().toArray()[option - 1].toString();
        System.out.println(lectureLink);

    }

//    public void loginMenu() {
//
//    }
}