package ru.anatoli.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.anatoli.addressbook.appmanager.ApplicationManager;

/**
 * Created by anatoli.anukevich on 6/25/2017.
 */
public class TestBase {
    protected static final ApplicationManager applicationManager = new ApplicationManager(BrowserType.IE);

    @BeforeSuite
    public void setUp() throws Exception {
        applicationManager.init();
    }

    @AfterSuite
    public void tearDown() {
        applicationManager.stop();
    }
}
