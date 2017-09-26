package ru.anatoli.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by anatoli.anukevich on 6/25/2017.
 */
public class ApplicationManager {
    WebDriver wd;
    private ContactHelper contactHelper;
    private SessionHelper sessionHelper;
    private GroupHelper groupHelper;
    private NavigationHelper navigationHelper;
    private DbHelper dbHelper;
    private Properties properties;
    private String browser;

    //Constructor
    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void init() throws IOException {
        System.setProperty("webdriver.gecko.driver", "F:\\Private\\Programs\\geckodriver\\geckodriver.exe");

        dbHelper = new DbHelper();
        //Choosing the Browser
        if (browser.equals(BrowserType.FIREFOX)) {
            wd = new FirefoxDriver();
        } else if (browser.equals(BrowserType.CHROME)) {
            wd = new ChromeDriver();
        } else if (browser.equals(BrowserType.IE)) {
            wd = new InternetExplorerDriver();
        }
        wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

        //Initializing of Helpers
        navigationHelper = new NavigationHelper(wd);
        groupHelper = new GroupHelper(wd);
        sessionHelper = new SessionHelper(wd);
        contactHelper = new ContactHelper(wd);

        //Getting values of necessary attributes from 'local.properties' file
        File file = new File("src/test/resources/local.properties");
        FileReader reader = new FileReader(file);
        properties = new Properties();
        properties.load(reader);

        getUrl(properties.getProperty("baseUrl"));
        sessionHelper.login(properties.getProperty("user"), properties.getProperty("password"));
    }

    public void getUrl(String url) {
        wd.get(url);
    }

    public void stop() {
        wd.quit();
    }

    //Getters of Delegates
    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }

    public GroupHelper getGroupHelper() {
        return groupHelper;
    }

    public SessionHelper getSessionHelper() {
        return sessionHelper;
    }

    public ContactHelper getContactHelper() {
        return contactHelper;
    }

    public DbHelper getDbHelper() {
        return dbHelper;
    }
}
