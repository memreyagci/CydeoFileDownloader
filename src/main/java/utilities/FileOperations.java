package utilities;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CanvasPage;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FileOperations extends Component {
//    WebDriver.Options driverOptions =

    public String chooseDownloadPath() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Choose download directory");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        while(chooser.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) {
            chooser.showOpenDialog(this);
        }

        return chooser.getSelectedFile().toString();
    }

    public boolean isFile() {
        try {
            if(new CanvasPage().docPreview.isDisplayed()) {
                return true;
            } else return false;
        } catch(NoSuchElementException e) {
            return false;
        }
    }

    public boolean isText() {
        try {
            if(new CanvasPage().textContainer.isDisplayed()) {
                return true;
            } else return false;
        } catch(NoSuchElementException e) {
            return false;
        }
    }

    public void downloadFile(String filePageLink, String path) {
        Driver.getDriver().get(filePageLink);
        Driver.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        if(isFile()) {
            Driver.getDriver().findElement(By.xpath("//a[@download='true']")).click();
        } else if (isText()) {
            List<WebElement> paragraphs = Driver.getDriver().findElements(By.xpath("//div[@class='show-content user_content clearfix enhanced']/p"));
            String classNotes = "";

            for(WebElement paragraph : paragraphs) {
                classNotes += paragraph.getText() + "\n";
            }

            try {
                // write string to a file
                Files.writeString(Path.of(path + "classnotes.txt"), classNotes);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

}
