package ru.anatoli.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.UserData;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Created by anatoli.anukevich on 7/30/2017.
 */
public class LoginLogoutTests extends TestBase {
    @DataProvider
    public Iterator<Object[]> validDataForLoginFromCsv() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        File file = new File("src/test/resources/userFile.csv");
        FileReader reader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line = bufferedReader.readLine();
        while (line != null) {
            String splitData[] = line.split(";");
            list.add(new Object[] {new UserData().withUserName(splitData[0])
                                                    .withPassword(splitData[1])});
            line = bufferedReader.readLine();
        }
        bufferedReader.close();
        reader.close();
        return list.iterator();
    }

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
