import org.openqa.selenium.WebElement;
import utilities.ConfigurationReader;
import utilities.DownloadOperations;
import utilities.Driver;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Login login = new Login();
        Canvas canvas = new Canvas();

        Scanner scanner = new Scanner(System.in);
        int option;

        System.out.println("\n===============================================");
        System.out.println("How do you want to login?");
        System.out.println("\t(1) By typing the credentials (recommended)");
        System.out.println("\t(2) By using configuration file");
        System.out.println("===============================================");

        // TODO: Handle InputMismatchException.
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

        Driver.getDriver().get(lectureLink);

        List<WebElement> modules = canvas.getModules(lectureLink);

        System.out.println("\n===============================================");
        System.out.println("\tPlease type in the number of module: \n");
        index = 1;
        for(WebElement module : modules) {
            System.out.println("(" + index++ + ")" + " " + canvas.getModuleTitle(module));
        }
        System.out.println("===============================================");

        option = scanner.nextInt();
        while(option < 1 || option >= modules.size()) {
            System.out.print("Invalid number, please try again: ");
            option = scanner.nextInt();
        }

        List<String> filesLinks = canvas.getModuleFilesList(modules.get(option - 1));

//        for(String link : filesLinks) {
//            Driver.getDriver().get(link);
//        }

    }

//    public void loginMenu() {
//
//    }
}