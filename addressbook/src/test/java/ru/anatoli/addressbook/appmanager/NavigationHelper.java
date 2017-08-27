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
        String nameOfPage = wd.findElement(By.xpath("//div[@id = 'content']")).getText().split("\n")[0];
        if (nameOfPage.contains("Number of results")) {
            return;
        } else {
            click(By.linkText("home"));
        }
    }

    public void goToNextBirthdaysPage() {
        String nameOfPage = wd.findElement(By.xpath("//div[@id = 'content']")).getText().split("\n")[0];
        if (nameOfPage.equals("Next birthdays")) {
            return;
        } else {
            click(By.linkText("next birthdays"));
        }
    }
}
