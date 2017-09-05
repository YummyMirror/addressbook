package ru.anatoli.addressbook.tests;

import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.ContactData;
import java.util.Set;
import static org.testng.Assert.assertEquals;

/**
 * Created by anatoli.anukevich on 9/5/2017.
 */
public class RemoveContactsFromGroupTests extends TestBase {
    @Test(enabled = true)
    public void testRemoveContactFromGroup() {
        applicationManager.getNavigationHelper().goToHomePage();

        //Getting Set of ContactData object model without Groups BEFORE removing
        Set<ContactData> beforeWithoutGroups = applicationManager.getDbHelper().getContactSetNotAddedToGroups();

        //Getting Set of ContactData object model with Groups BEFORE removing
        Set<ContactData> beforeWithGroups = applicationManager.getDbHelper().getContactSetAddedToGroups();

        ContactData removedContact = beforeWithGroups.stream().findAny().get();

        String groupWithContacts = beforeWithGroups.stream().findAny().get().getGroups().stream().findAny().get().getGroupName();

        applicationManager.getContactHelper().removeContactFromGroup(removedContact, groupWithContacts);

        //Getting Set of ContactData object model without Groups AFTER removing
        Set<ContactData> afterWithoutGroups = applicationManager.getDbHelper().getContactSetNotAddedToGroups();

        //Getting Set of ContactData object model with Groups AFTER removing
        Set<ContactData> afterWithGroups = applicationManager.getDbHelper().getContactSetAddedToGroups();

        assertEquals(beforeWithoutGroups.size() + 1, afterWithoutGroups.size());

        assertEquals(beforeWithGroups.size() - 1, afterWithGroups.size());
    }
}
