package ru.anatoli.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.anatoli.addressbook.models.GroupData;
import ru.anatoli.addressbook.models.UserData;
import java.util.concurrent.TimeUnit;

/**
 * Created by anatoli.anukevich on 6/25/2017.
 */
public class ApplicationManager {
    FirefoxDriver wd;

    private NavigationHelper navigationHelper;

    public void init() {
        System.setProperty("webdriver.gecko.driver", "E:\\Private\\Programs\\geckodriver\\geckodriver.exe");
        wd = new FirefoxDriver();
        navigationHelper = new NavigationHelper(wd);
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

        UserData userData = new UserData().withUserName("admin")
                                            .withPassword("secret");
        getUrl("http://localhost/addressbook/");
        login(userData);
    }

    public void login(UserData userData) {
        input(By.name("user"), userData.getUserName());
        input(By.name("pass"), userData.getPassword());
        submitLoginForm();
    }

    public void input(By locator, String value) {
        wd.findElement(locator).click();
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(value);
    }

    public void submitLoginForm() {
        wd.findElement(By.xpath("//form[@id='LoginForm']/input[3]")).click();
    }

    public void getUrl(String url) {
        wd.get(url);
    }

    public void fillGroupForm(GroupData groupData) {
        input(By.name("group_name"), groupData.getGroupName());
        input(By.name("group_header"), groupData.getGroupHeader());
        input(By.name("group_footer"), groupData.getGroupFooter());
    }

    public void returnToGroupsPage() {
        wd.findElement(By.linkText("group page")).click();
    }

    public void submitGroupForm() {
        wd.findElement(By.name("submit")).click();
    }

    public void initiateGroupCreation() {
        wd.findElement(By.name("new")).click();
    }

    public void stop() {
        wd.quit();
    }

    public static boolean isAlertPresent(FirefoxDriver wd) {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    //Getters of Delegates
    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }
}
