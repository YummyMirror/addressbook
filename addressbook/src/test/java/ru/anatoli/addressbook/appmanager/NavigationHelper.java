package ru.anatoli.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by anatoli.anukevich on 6/25/2017.
 */
public class NavigationHelper extends HelperBase {
    //Constructor
    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void goToGroupsPage() {
        click(By.linkText("groups"));
    }
}
