package ru.anatoli.addressbook.tests;

import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

/**
 * Created by anatoli.anukevich on 7/10/2017.
 */
public class ContactDeletionAllTests extends TestBase {
    @Test
    public void testContactDeletionAll() {
        applicationManager.getNavigationHelper().goToHomePage();

        applicationManager.getContactHelper().selectAllContacts();
        applicationManager.getContactHelper().deleteSelectedContact();
        applicationManager.getContactHelper().confirmContactDeletion();
        applicationManager.getNavigationHelper().goToHomePage();

        int after = applicationManager.getContactHelper().getContactSet().size();

        //Asserting by SIZE
        assertTrue(after == 0);
    }
}
