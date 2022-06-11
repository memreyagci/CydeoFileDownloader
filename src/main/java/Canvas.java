import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.CanvasPage;
import utilities.Driver;

import java.util.ArrayList;
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

    public List<WebElement> getModules(String courseLink) {
        String courseNumber = courseLink.split("https://learn.cybertekschool.com/courses/")[1];

        return driver.findElements(
                By.xpath("//*[contains(@data-module-url, '/courses/" + courseNumber + "/modules/')]")
        );
    }

    public String getModuleTitle(WebElement module) {
        return module.getAttribute("aria-label");
    }

    public static List<String> getModuleFilesList(WebElement moduleDiv) {
        List<String> downloadableFileList = new ArrayList<>();
        String fileName;
        String fileLink;
        List<WebElement> fileList = moduleDiv.findElements(By.xpath(".//div[2]/ul/li"));

        for (WebElement fileItem : fileList) {
            fileName = fileItem.findElement(By.xpath(".//div/a")).getAttribute("aria-label");
            System.out.println("fileName: " + fileName);

            if(!(fileName.contains("RECORDING") || fileName.contains("---"))) {
                fileLink = fileItem.getAttribute("href");

                downloadableFileList.add(fileLink);
            }
        }


        return downloadableFileList;
    }
}
