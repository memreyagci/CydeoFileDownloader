import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.CanvasPage;
import utilities.Driver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Canvas {
    private final String canvasUrl = "https://learn.cybertekschool.com/";
    private CanvasPage canvasPage;

    private WebDriver driver;

    public Canvas() {
        canvasPage = new CanvasPage();
        driver = Driver.getDriver();
    }

    public void goToCanvasPage() {
        driver.get(canvasUrl);
    }

    public Map<String, String> getLectureMap() {
        String lectureName;
        String lectureLink;
        List<WebElement> lectureDivs = canvasPage.lectureContainer.findElements(By.className("ic-DashboardCard"));
        Map<String, String> lecturesMap = new HashMap<>();

        for (WebElement div : lectureDivs) {
            lectureName = div.getAttribute("aria-label");

            if(!lectureName.equals("Welcome Kit")) {
                lectureLink = div.findElement(By.tagName("a")).getAttribute("href");
                lecturesMap.put(lectureName, lectureLink);
            }
        }

        return lecturesMap;
    }
}
