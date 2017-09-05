package ru.anatoli.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.ContactData;
import ru.anatoli.addressbook.models.GroupData;
import java.io.File;
import java.util.Set;
import static org.testng.Assert.assertEquals;

/**
 * Created by anatoli.anukevich on 9/5/2017.
 */
public class RemoveContactsFromGroupTests extends TestBase {
    @BeforeMethod
    public void ensurePrecondition() {
        if (applicationManager.getDbHelper().getGroupSet().size() == 0) {
            GroupData groupData = new GroupData().withGroupName("Test GroupName")
                                                 .withGroupHeader(null)
                                                 .withGroupFooter(null);
            applicationManager.getNavigationHelper().goToGroupsPage();
            applicationManager.getGroupHelper().createGroup(groupData);
        }

        if (applicationManager.getDbHelper().getContactSet().size() == 0) {
            ContactData contactData = new ContactData().withFirstName("Temp first name")
                                                       .withMiddleName(null)
                                                       .withLastName("")
                                                       .withNickname(null)
                                                       .withPhoto(new File(""))
                                                       .withTitle(null)
                                                       .withCompany(null)
                                                       .withAddress(null)
                                                       .withHomePhone(null)
                                                       .withMobilePhone(null)
                                                       .withWorkPhone(null)
                                                       .withFax(null)
                                                       .withEmail(null)
                                                       .withEmail2(null)
                                                       .withEmail3(null)
                                                       .withHomepage(null)
                                                       .withBirthDay(null)
                                                       .withBirthMonth(null)
                                                       .withBirthYear(null)
                                                       .withAnniversaryDay(null)
                                                       .withAnniversaryMonth(null)
                                                       .withAnniversaryYear(null)
                                                       .withSecondaryAddress(null)
                                                       .withSecondaryPhone(null)
                                                       .withSecondaryNotes(null);
            applicationManager.getContactHelper().initiateContactCreation();
            applicationManager.getContactHelper().createContact(contactData);
        }

        if (applicationManager.getDbHelper().getContactSetAddedToGroups().size() == 0) {
            //Getting Set of ContactData object model without Groups BEFORE moving
            Set<ContactData> before = applicationManager.getDbHelper().getContactSetNotAddedToGroups();

            //Choosing the random Contact that will be moved
            ContactData movedContact = before.stream().findAny().get();

            //Getting the random GroupData name
            String randomGroupName = applicationManager.getDbHelper().getGroupSet().stream().findAny().get().getGroupName();

            applicationManager.getContactHelper().addContactToGroup(movedContact, randomGroupName);
        }

        applicationManager.getNavigationHelper().goToHomePage();
    }

    @Test(enabled = true)
    public void testRemoveContactFromGroup() {
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

    @Test(enabled = true)
    public void testRemoveContactFromGroupWithoutSelection() {
        //Getting Set of ContactData object model without Groups BEFORE removing
        Set<ContactData> beforeWithoutGroups = applicationManager.getDbHelper().getContactSetNotAddedToGroups();

        //Getting Set of ContactData object model with Groups BEFORE removing
        Set<ContactData> beforeWithGroups = applicationManager.getDbHelper().getContactSetAddedToGroups();

        String groupWithContacts = beforeWithGroups.stream().findAny().get().getGroups().stream().findAny().get().getGroupName();

        applicationManager.getContactHelper().removeContactFromGroupWithoutSelection(groupWithContacts);

        //Getting Set of ContactData object model without Groups AFTER removing
        Set<ContactData> afterWithoutGroups = applicationManager.getDbHelper().getContactSetNotAddedToGroups();

        //Getting Set of ContactData object model with Groups AFTER removing
        Set<ContactData> afterWithGroups = applicationManager.getDbHelper().getContactSetAddedToGroups();

        assertEquals(beforeWithGroups.size(), afterWithGroups.size());

        assertEquals(beforeWithoutGroups.size(), afterWithoutGroups.size());
    }
}
