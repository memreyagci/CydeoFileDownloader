package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class CanvasPage {

    public CanvasPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(className = "ic-DashboardCard__box__container")
    public WebElement lectureContainer;

//    @FindBy(xpath = "//input[@type='password']")
//    public List<WebElement> lectureList;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement signInButton;

    @FindBy(xpath = "//button[@disabled='']")
    public WebElement signInButtonDisabled;

    @FindBy(xpath = "//div[.='Wrong email or password.']")
    public WebElement wrongEmailOrPasswordErrorMessage;
}
