package ru.anatoli.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.ContactData;
import ru.anatoli.addressbook.models.GroupData;
import java.io.File;
import java.util.Set;
import static org.testng.Assert.*;

/**
 * Created by anatoli.anukevich on 8/27/2017.
 */
public class AddContactsToGroupsTests extends TestBase {
    @BeforeMethod
    public void ensurePrecondition() {
        if (applicationManager.getDbHelper().getGroupSet().size() == 0) {
            GroupData groupData = new GroupData().withGroupName("Test GroupName")
                                                 .withGroupHeader(null)
                                                 .withGroupFooter(null);
            applicationManager.getNavigationHelper().goToGroupsPage();
            applicationManager.getGroupHelper().createGroup(groupData);
        }

        if (applicationManager.getDbHelper().getContactSetNotAddedToGroups().size() == 0) {
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

        applicationManager.getNavigationHelper().goToHomePage();
    }

    @Test(enabled = true)
    public void testAddContactToGroup() {
        //Getting Set of ContactData object model without Groups BEFORE moving
        Set<ContactData> before = applicationManager.getDbHelper().getContactSetNotAddedToGroups();

        //Choosing the random Contact that will be moved
        ContactData movedContact = before.stream().findAny().get();

        //Getting the random GroupData name
        String randomGroupName = applicationManager.getDbHelper().getGroupSet().iterator().next().getGroupName();

        applicationManager.getContactHelper().addContactToGroup(movedContact, randomGroupName);

        String currentGroup = applicationManager.getContactHelper().getCurrentGroup();

        //Asserting by GroupNames
        assertTrue(randomGroupName.contains(currentGroup));

        //Asserting by 'Remove from group' button
        assertTrue(applicationManager.getContactHelper().getRemoveFromGroupButtonName().contains(currentGroup));

        //Getting Set of ContactData object model without Groups AFTER moving
        Set<ContactData> after = applicationManager.getDbHelper().getContactSetNotAddedToGroups();

        //Asserting by number of Contacts without Groups
        assertEquals(before.size()- 1, after.size());
    }

    @Test(enabled = true)
    public void testAddAllContactsToGroup() {
        //Getting the random GroupData name
        String randomGroupName = applicationManager.getDbHelper().getGroupSet().iterator().next().getGroupName();

        applicationManager.getContactHelper().addAllContactsToGroup(randomGroupName);

        String currentGroup = applicationManager.getContactHelper().getCurrentGroup();

        //Asserting by GroupNames
        assertTrue(randomGroupName.contains(currentGroup));

        String removeFromGroupButtonName = applicationManager.getContactHelper().getRemoveFromGroupButtonName();

        //Asserting by 'Remove from group' button
        assertTrue(removeFromGroupButtonName.contains(currentGroup));

        assertEquals(applicationManager.getDbHelper().getContactSetNotAddedToGroups().size(), 0);
    }

    @Test(enabled = true)
    public void testAddAllContactsWithoutGroupsToGroup() {
        //Getting Set of ContactData object model without Groups BEFORE moving
        Set<ContactData> before = applicationManager.getDbHelper().getContactSetNotAddedToGroups();

        //Getting the random GroupData name
        String randomGroupName = applicationManager.getDbHelper().getGroupSet().stream().findAny().get().getGroupName();

        applicationManager.getContactHelper().addAllContactsWithoutGroupsToGroup(before, randomGroupName);

        String currentGroup = applicationManager.getContactHelper().getCurrentGroup();

        //Asserting by GroupNames
        assertTrue(randomGroupName.contains(currentGroup));

        String removeFromGroupButtonName = applicationManager.getContactHelper().getRemoveFromGroupButtonName();

        //Asserting by 'Remove from group' button
        assertTrue(removeFromGroupButtonName.contains(currentGroup));

        assertEquals(applicationManager.getDbHelper().getContactSetNotAddedToGroups().size(), 0);
    }

    @Test(enabled = true)
    public void testAddContactToGroupWithoutSelection() {
        //Getting Set of ContactData object model without Groups BEFORE moving
        Set<ContactData> before = applicationManager.getDbHelper().getContactSetNotAddedToGroups();

        //Getting the random GroupData name
        String randomGroupName = applicationManager.getDbHelper().getGroupSet().stream().findAny().get().getGroupName();

        applicationManager.getContactHelper().selectValueInDropDownList(By.name("to_group"), randomGroupName);
        applicationManager.getContactHelper().addContactToGroup();

        //Getting Set of ContactData object model without Groups AFTER moving
        Set<ContactData> after = applicationManager.getDbHelper().getContactSetNotAddedToGroups();

        //Asserting by Error message
        assertEquals(applicationManager.getContactHelper().getErrorMessageWhileAddContactToGroupWithoutSelection(),
                "No users selected. Please use the checkbox to select a user.");

        //Asserting by not presenting the 'Remove from group' button
        assertFalse(applicationManager.getContactHelper().isElementPresent(By.name("remove")));

        //Asserting by SIZE of collections
        assertEquals(before.size(), after.size());
    }
}
