package ru.anatoli.addressbook.tests;

import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.ContactData;
import static org.testng.Assert.assertEquals;

/**
 * Created by anatoli.anukevich on 8/27/2017.
 */
public class AddContactsToGroupsTests extends TestBase {
    @Test(enabled = true)
    public void testAddContactToGroup() {
        applicationManager.getNavigationHelper().goToHomePage();

        //Choosing the random Contact that will be moved
        ContactData movedContact = applicationManager.getContactHelper().getContactSet().iterator().next();

        //Getting the random GroupData name
        String randomGroupName = applicationManager.getDbHelper().getGroupSet().iterator().next().getGroupName();

        applicationManager.getContactHelper().addContactToGroup(movedContact, randomGroupName);

        String currentGroup = applicationManager.getContactHelper().getCurrentGroup();

        //Asserting by GroupNames
        assertEquals(currentGroup, randomGroupName);
    }
}
