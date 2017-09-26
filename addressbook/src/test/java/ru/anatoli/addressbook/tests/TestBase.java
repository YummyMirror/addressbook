package ru.anatoli.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.anatoli.addressbook.appmanager.ApplicationManager;
import ru.anatoli.addressbook.models.ContactData;
import ru.anatoli.addressbook.models.GroupData;
import java.util.Set;
import java.util.stream.Collectors;
import static org.testng.Assert.assertEquals;

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

    public void compareUiVsDbGroupData() {
        Set<GroupData> ui = applicationManager.getGroupHelper().getGroupSet();
        Set<GroupData> db = applicationManager.getDbHelper().getGroupSet().stream()
                                                                          .map((group) -> new GroupData().withGroupId(group.getGroupId())
                                                                                                         .withGroupName(group.getGroupName()))
                                                                          .collect(Collectors.toSet());
        assertEquals(ui, db);
    }

    public void compareUiVsDbContactData() {
        Set<ContactData> ui = applicationManager.getContactHelper().getContactSet().stream()
                                                                                   .map((contact) -> new ContactData().withContactId(contact.getContactId())
                                                                                                                      .withFirstName(contact.getFirstName())
                                                                                                                      .withLastName(contact.getLastName())
                                                                                                                      .withAddress(contact.getAddress()))
                                                                                   .collect(Collectors.toSet());
        Set<ContactData> db = applicationManager.getDbHelper().getContactSet().stream()
                                                                              .map((contact) -> new ContactData().withContactId(contact.getContactId())
                                                                                                                 .withFirstName(contact.getFirstName())
                                                                                                                 .withLastName(contact.getLastName())
                                                                                                                 .withAddress(contact.getAddress()))
                                                                              .collect(Collectors.toSet());
        assertEquals(ui, db);
    }
}
