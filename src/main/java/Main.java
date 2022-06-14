import utilities.Driver;
import utilities.Menu;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        // Get the download path
        String downloadPath = Menu.chooseDownloadPath();
        Driver.setChromeOptions(downloadPath);

        Login login = new Login();

        // Get login method, credentials and login
        String[] credentials = Menu.chooseLoginMethod();
        login.loginToCydeo(credentials[0], credentials[1]);

        // Go to canvas page
        CanvasCydeo canvasCydeo = new CanvasCydeo();
        canvasCydeo.goToCanvasPage();

        // Get a Map of courses and their links
        Map<String, String> courses = canvasCydeo.getCourses();

        // Choose a course and go to the link
        String chosenCourseLink = Menu.chooseCourse(courses);
        Driver.getDriver().get(chosenCourseLink);

        // Get module names of chosen course and list them to be chosen.
        List<String> modulesNames = canvasCydeo.getModulesNames(chosenCourseLink);
        String chosenModuleName = Menu.chooseModule(modulesNames);

        // Get a list of links of files of chosen module.
        List<String> chosenModuleFileLinks = canvasCydeo.getModuleFilesList(chosenModuleName);

        // TODO:
        System.out.println(chosenModuleFileLinks);

    }
}