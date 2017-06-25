package ru.anatoli.addressbook;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.util.concurrent.TimeUnit;

/**
 * Created by anatoli.anukevich on 6/25/2017.
 */
public class TestBase {
    FirefoxDriver wd;

    @BeforeMethod
    public void setUp() throws Exception {
        System.setProperty("webdriver.gecko.driver", "E:\\Private\\Programs\\geckodriver\\geckodriver.exe");
        wd = new FirefoxDriver();
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

        UserData userData = new UserData().withUserName("admin")
                                            .withPassword("secret");
        getUrl("http://localhost/addressbook/");
        login(userData);
    }

    private void login(UserData userData) {
        input(By.name("user"), userData.getUserName());
        input(By.name("pass"), userData.getPassword());
        submitLoginForm();
    }

    private void input(By locator, String value) {
        wd.findElement(locator).click();
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(value);
    }

    private void submitLoginForm() {
        wd.findElement(By.xpath("//form[@id='LoginForm']/input[3]")).click();
    }

    private void getUrl(String url) {
        wd.get(url);
    }

    protected void fillGroupForm(GroupData groupData) {
        input(By.name("group_name"), groupData.getGroupName());
        input(By.name("group_header"), groupData.getGroupHeader());
        input(By.name("group_footer"), groupData.getGroupFooter());
    }

    protected void returnToGroupsPage() {
        wd.findElement(By.linkText("group page")).click();
    }

    protected void submitGroupForm() {
        wd.findElement(By.name("submit")).click();
    }

    protected void initiateGroupCreation() {
        wd.findElement(By.name("new")).click();
    }

    protected void goToGroupsPage() {
        wd.findElement(By.linkText("groups")).click();
    }

    @AfterMethod
    public void tearDown() {
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
}
