package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Driver {

    // Creating a private constructor to use Singleton design pattern.
    private Driver() {
    }

    private static WebDriver driver;
    private static ChromeOptions options;

    public static WebDriver getDriver() {

        if (driver == null) {
            // Read browser type from config.yml
            String browserType = (String) ConfigurationReader.getProperty("browser");

            /*
                Depending on the browserType that will be return from configuration.properties file
                switch statement will determine the case, and open the matching browser
            */
            switch (browserType) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(options);
                    driver.manage().window().maximize();
                    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    driver.manage().window().maximize();
                    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    break;
                case "safari":
                    WebDriverManager.safaridriver().setup();
                    driver = new SafariDriver();
                    driver.manage().window().maximize();
                    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    break;
            }
        }

        return driver;
    }

    /*
    This method will make sure our driver value is always null after using quit() method
    */
    public static void closeDriver() {
        if (driver != null) {
            driver.quit(); // this line will terminate the existing session. value will not even be null
            driver = null;
        }
    }

    public static ChromeOptions setChromeOptions(String path) {
        Map<String,Object> preferences= new HashMap<>();
        preferences.put("profile.default_content_settings.popups", 0);
        preferences.put("download.default_directory", path);

        options = new ChromeOptions();
        options.setExperimentalOption("prefs", preferences);

        return options;
    }
}