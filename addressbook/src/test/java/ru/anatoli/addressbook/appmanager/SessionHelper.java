package ru.anatoli.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.anatoli.addressbook.models.UserData;

/**
 * Created by anatoli.anukevich on 6/25/2017.
 */
public class SessionHelper extends HelperBase {
    private FirefoxDriver wd;

    //Constructor
    public SessionHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void login(UserData userData) {
        input(By.name("user"), userData.getUserName());
        input(By.name("pass"), userData.getPassword());
        submitLoginForm();
    }

    public void submitLoginForm() {
        click(By.xpath("//form[@id='LoginForm']/input[3]"));
    }
}
