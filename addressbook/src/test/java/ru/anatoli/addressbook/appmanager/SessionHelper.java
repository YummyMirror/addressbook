package ru.anatoli.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.anatoli.addressbook.models.UserData;

/**
 * Created by anatoli.anukevich on 6/25/2017.
 */
public class SessionHelper {
    private FirefoxDriver wd;

    //Constructor
    public SessionHelper(FirefoxDriver wd) {
        this.wd = wd;
    }

    public void input(By locator, String value) {
        wd.findElement(locator).click();
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(value);
    }

    public void login(UserData userData) {
        input(By.name("user"), userData.getUserName());
        input(By.name("pass"), userData.getPassword());
        submitLoginForm();
    }

    public void submitLoginForm() {
        wd.findElement(By.xpath("//form[@id='LoginForm']/input[3]")).click();
    }
}
