import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.CanvasPage;
import utilities.Driver;

import java.util.*;

public class CanvasCydeo {
    private final String canvasUrl = "https://learn.cybertekschool.com/";
    private CanvasPage canvasPage;

    private WebDriver driver;

    private final String[] validFiles = new String[] {
            ".pdf",
            ".txt",
            "classnote",
            "class note",
    };

    public CanvasCydeo() {
        canvasPage = new CanvasPage();
        driver = Driver.getDriver();
    }

    public void goToCanvasPage() {
        driver.get(canvasUrl);
    }

    public Map<String, String> getCourses() {
        Map<String, String> courses = new HashMap<>();

        for (WebElement div : canvasPage.courseContainers) {
            String courseName = div.getAttribute("aria-label");

            if(!courseName.equals("Welcome Kit")) {
                String courseLink = div.findElement(By.tagName("a")).getAttribute("href");
                courses.put(courseName, courseLink);
            }
        }

        return courses;
    }


    public List<String> getModulesNames(String chosenCourseLink) {
        String chosenCourseNumber = chosenCourseLink.split("https://learn.cybertekschool.com/courses/")[1];
        String moduleBaseXpath = "//*[contains(@data-module-url, '/courses/" + chosenCourseNumber + "/modules/')]";

        List<WebElement> modulesContainers = driver.findElements(By.xpath(moduleBaseXpath));
        List<String> modulesNames = new ArrayList<>();

        for(WebElement container : modulesContainers) {
            modulesNames.add(container.getAttribute("aria-label"));
        }

        return modulesNames;
    }


    public List<String> getModuleFilesList(String chosenModuleName) {
        List<String> fileLinks = new ArrayList<>();
        List<WebElement> fileList = driver.findElements(By.xpath("//*[@aria-label='" + chosenModuleName + "']/div[2]/ul/li"));

        for (WebElement fileItem : fileList) {
            WebElement fileATag;
            try {
                fileATag = fileItem.findElement(By.xpath(".//div/a"));
            } catch(NoSuchElementException e) {
                continue;
            }

            String fileName = fileATag.getAttribute("aria-label");

            if(Arrays.stream(validFiles).anyMatch(fileName.toLowerCase()::contains)) {
                String fileLink = fileATag.getAttribute("href");
                fileLinks.add(fileLink);
            }
        }

        return fileLinks;
    }


    public String getClassNotesText() {
        List<WebElement> paragraphs = driver.findElements(By.xpath("//div[@class='show-content user_content clearfix enhanced']/p"));
        String classNotes = "";

        for(WebElement paragraph : paragraphs) {
            classNotes += paragraph.getText() + "\n";
        }

        return classNotes;
    }
}