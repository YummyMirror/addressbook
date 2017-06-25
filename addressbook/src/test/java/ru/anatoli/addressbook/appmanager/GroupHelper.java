package ru.anatoli.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.anatoli.addressbook.models.GroupData;

/**
 * Created by anatoli.anukevich on 6/25/2017.
 */
public class GroupHelper {
    private FirefoxDriver wd;

    //Constructor
    public GroupHelper(FirefoxDriver wd) {
        this.wd = wd;
    }

    public void initiateGroupCreation() {
        wd.findElement(By.name("new")).click();
    }

    public void input(By locator, String value) {
        wd.findElement(locator).click();
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(value);
    }

    public void fillGroupForm(GroupData groupData) {
        input(By.name("group_name"), groupData.getGroupName());
        input(By.name("group_header"), groupData.getGroupHeader());
        input(By.name("group_footer"), groupData.getGroupFooter());
    }

    public void submitGroupForm() {
        wd.findElement(By.name("submit")).click();
    }

    public void returnToGroupsPage() {
        wd.findElement(By.linkText("group page")).click();
    }
}
