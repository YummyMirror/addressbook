package ru.anatoli.addressbook;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;

public class GroupCreationTests {
    FirefoxDriver wd;
    
    @BeforeMethod
    public void setUp() throws Exception {
        System.setProperty("webdriver.gecko.driver", "E:\\Private\\Programs\\geckodriver\\geckodriver.exe");
        wd = new FirefoxDriver();
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        getUrl("http://localhost/addressbook/");
        input(By.name("user"), "admin");
        input(By.name("pass"), "secret");
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

    @Test
    public void testGroupCreation() {
        GroupData groupData = new GroupData().withGroupName("GroupName")
                                            .withGroupHeader("GroupHeader")
                                            .withGroupFooter("GroupFooter");
        goToGroupsPage();
        initiateGroupCreation();
        fillGroupForm(groupData);
        submitGroupForm();
        returnToGroupsPage();
    }

    private void fillGroupForm(GroupData groupData) {
        input(By.name("group_name"), groupData.getGroupName());
        input(By.name("group_header"), groupData.getGroupHeader());
        input(By.name("group_footer"), groupData.getGroupFooter());
    }

    private void returnToGroupsPage() {
        wd.findElement(By.linkText("group page")).click();
    }

    private void submitGroupForm() {
        wd.findElement(By.name("submit")).click();
    }

    private void initiateGroupCreation() {
        wd.findElement(By.name("new")).click();
    }

    private void goToGroupsPage() {
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
