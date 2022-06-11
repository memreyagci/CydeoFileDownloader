import org.openqa.selenium.WebDriver;
import utilities.ConfigurationReader;
import utilities.Driver;

import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        WebDriver driver = Driver.getDriver();
        Login login = new Login();
        Scanner scanner = new Scanner(System.in);
        int option;

        System.out.println("Do you want to type in the credentials (1, recommended) or them to be read from the configuration file (2)?");
        option = scanner.nextInt();

        if (option == 1) {
            System.out.println("Email address: ");
            String email = scanner.next();

            System.out.println("Password: ");
            String password = scanner.next();

            login.loginToCydeo(driver, email, password);
        } else {
            Map<String, String> credentials = (Map<String, String>) ConfigurationReader.getProperty("credentials");
            login.loginToCydeo(driver, credentials.get("email"), credentials.get("password"));
        }




    }
}