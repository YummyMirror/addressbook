package ru.anatoli.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import java.io.File;

/**
 * Created by anatoli.anukevich on 6/25/2017.
 */
public class HelperBase {
    protected WebDriver wd;

    //Constructor
    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    public void click(By locator) {
        wd.findElement(locator).click();
    }

    public String getText(By locator) {
        return wd.findElement(locator).getText();
    }

    public String getAttribute(By locator, String value) {
        return wd.findElement(locator).getAttribute(value);
    }

    public void input(By locator, String value) {
        click(locator);
        if (value != null) {
            String existingValue = wd.findElement(locator).getAttribute("value");
            if (!value.equals(existingValue)) {
                wd.findElement(locator).clear();
                wd.findElement(locator).sendKeys(value);
            }
        }
    }

    public void attach(By locator, File photo) {
        if (!photo.getPath().equals("")) {
            wd.findElement(locator).sendKeys(photo.getAbsolutePath());
        }
    }

    public void select(By locator, String value) {
        if (value != null) {
            String existingValue = wd.findElement(locator).getAttribute("value");
            if (!value.equals(existingValue)) {
                new Select(wd.findElement(locator)).selectByVisibleText(value);
            }
        }
    }

    public boolean isElementPresent(By locator) {
        try {
            wd.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void confirmAlert() {
        wd.switchTo().alert().accept();
    }

    public void cancelAlert() {
        wd.switchTo().alert().dismiss();
    }

    public void back() {
        wd.navigate().back();
    }
}
