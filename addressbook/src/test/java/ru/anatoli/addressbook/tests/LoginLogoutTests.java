package ru.anatoli.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.UserData;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Created by anatoli.anukevich on 7/30/2017.
 */
public class LoginLogoutTests extends TestBase {
    @DataProvider
    public Iterator<Object[]> validDataForLoginFromCsv() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        File file = new File("src/test/resources/userFileValid.csv");
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

    @DataProvider
    public Iterator<Object[]> validDataForLoginFromJson() throws IOException {
        File file = new File("src/test/resources/userFileValid.json");
        FileReader reader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line = bufferedReader.readLine();
        String json = "";
        while (line != null) {
            json += line;
            line = bufferedReader.readLine();
        }
        Gson gson = new Gson();
        Type collectionType = new TypeToken<List<UserData>>(){}.getType(); //List<UserData>.class
        List<UserData> list = gson.fromJson(json, collectionType);
        bufferedReader.close();
        reader.close();
        return list.stream().map((user) -> new Object[] {user}).iterator();
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

    @Test(enabled = true, dataProvider = "validDataForLoginFromJson")
    public void testLoginWithValidCreds(UserData user) {
        applicationManager.getSessionHelper().logout();
        applicationManager.getSessionHelper().loginViaObjectModel(user);

        //Asserting by presence of Logout button
        assertTrue(applicationManager.getSessionHelper().isElementPresent(By.linkText("Logout")));

        //Asserting by NOT presence of Login button
        assertFalse(applicationManager.getSessionHelper().isElementPresent(By.xpath("//input[@value = 'Login']")));

        String loggedUserName = applicationManager.getSessionHelper().loggedUserName();

        //Asserting by Logged User
        assertEquals(loggedUserName, user.getUserName());
    }
}
