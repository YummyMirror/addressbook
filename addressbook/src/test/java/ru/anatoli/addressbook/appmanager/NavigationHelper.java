package ru.anatoli.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by anatoli.anukevich on 6/25/2017.
 */
public class NavigationHelper extends HelperBase {
    //Constructor
    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void goToGroupsPage() {
        if (wd.findElement(By.tagName("h1")).getText().equals("Groups") && isElementPresent(By.name("new"))) {
            return;
        } else {
            click(By.linkText("groups"));
        }
    }

    public void goToHomePage() {
        click(By.linkText("home"));
    }
}
