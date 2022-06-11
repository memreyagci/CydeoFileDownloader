import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;

public class Login {
    private final String cydeoUrl = "https://cydeo.com";
    private LoginPage loginPage;

    private WebDriver driver;

    public Login(WebDriver driver) {
        this.driver = driver;
        loginPage = new LoginPage();
    }

    public void loginToCydeo(   String email, String password) {
        driver.get(cydeoUrl);
        driver.findElement(By.linkText("Log In")).click();

        loginPage.inputEmail.sendKeys(email);
        loginPage.signInButton.click();
        loginPage.inputPassword.sendKeys(password);

        if(isSignInButtonDisabled()) {
            throw new Error("Neither email nor password field can be empty. Exiting..");
        } else {
            loginPage.signInButton.click();

            if(!isLoginSuccessful()) {
                throw new Error("Either email or password is wrong. Exiting..");
            }
        }
    }

    private boolean isSignInButtonDisabled() {
        try {
            if(loginPage.signInButtonDisabled.isDisplayed()) {
                return true;
            } else return false;
        } catch(NoSuchElementException e) {
            return false;
        }
    }

    private boolean isLoginSuccessful() {
        try {
            if(!loginPage.wrongEmailOrPasswordErrorMessage.isDisplayed()) {
                return true;
            } else return false;
        } catch(NoSuchElementException e) {
            return true;
        }
    }
}