package ru.anatoli.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Created by anatoli.anukevich on 7/30/2017.
 */
public class LoginLogoutTests extends TestBase {
    @Test(enabled = true)
    public void testLogout() {
        applicationManager.getSessionHelper().logout();

        //Asserting by presence of Login, Create account and Forgot password buttons
        assertTrue(applicationManager.getSessionHelper().isElementPresent(By.xpath("//input[@value = 'Login']")));
        assertTrue(applicationManager.getSessionHelper().isElementPresent(By.linkText("Create account")));
        assertTrue(applicationManager.getSessionHelper().isElementPresent(By.linkText("Forgot password")));

        //Asserting by NOT presence of Logout button
        assertFalse(applicationManager.getSessionHelper().isElementPresent(By.linkText("Logout")));
    }
}
