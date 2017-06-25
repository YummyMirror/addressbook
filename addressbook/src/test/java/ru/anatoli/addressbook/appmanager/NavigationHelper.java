package ru.anatoli.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by anatoli.anukevich on 6/25/2017.
 */
public class NavigationHelper {
    private FirefoxDriver wd;

    //Constructor
    public NavigationHelper(FirefoxDriver wd) {
        this.wd = wd;
    }

    public void goToGroupsPage() {
        wd.findElement(By.linkText("groups")).click();
    }
}
