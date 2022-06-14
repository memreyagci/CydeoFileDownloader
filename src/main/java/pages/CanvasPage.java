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


    @FindBy(xpath = "//div[@class='ic-DashboardCard__box__container']/div['class=ic-DashboardCard']")
    public List<WebElement> courseContainers;

    @FindBy(xpath = "//div[@id='doc_preview']")
    public WebElement docPreview;

    @FindBy(xpath = "//div[@class='show-content user_content clearfix enhanced']")
    public WebElement textContainer;

}
