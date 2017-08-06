package ru.anatoli.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.anatoli.addressbook.models.UserData;

/**
 * Created by anatoli.anukevich on 6/25/2017.
 */
public class SessionHelper extends HelperBase {
    //Constructor
    public SessionHelper(WebDriver wd) {
        super(wd);
    }

    public void login(String login, String password) {
        input(By.name("user"), login);
        input(By.name("pass"), password);
        submitLoginForm();
    }

    public void submitLoginForm() {
        click(By.xpath("//form[@id='LoginForm']/input[3]"));
    }

    public void logout() {
        click(By.linkText("Logout"));
    }

    public void loginViaObjectModel(UserData user) {
        input(By.name("user"), user.getUserName());
        input(By.name("pass"), user.getPassword());
        submitLoginForm();
    }
}
