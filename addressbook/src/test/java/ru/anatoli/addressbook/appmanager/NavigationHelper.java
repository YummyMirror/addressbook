package ru.anatoli.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by anatoli.anukevich on 6/25/2017.
 */
public class NavigationHelper extends HelperBase {
    private FirefoxDriver wd;

    //Constructor
    public NavigationHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void goToGroupsPage() {
        click(By.linkText("groups"));
    }
}
