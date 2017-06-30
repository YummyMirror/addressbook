package ru.anatoli.addressbook.tests;

import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.GroupData;
import java.util.Set;
import static org.testng.Assert.assertEquals;

public class GroupCreationTests extends TestBase {
    @Test
    public void testGroupCreation() {
        GroupData groupData = new GroupData().withGroupName("GroupName")
                                            .withGroupHeader("GroupHeader")
                                            .withGroupFooter(null);

        applicationManager.getNavigationHelper().goToGroupsPage();

        Set<GroupData> before = applicationManager.getGroupHelper().getGroupsHash();
        applicationManager.getGroupHelper().initiateGroupCreation();
        applicationManager.getGroupHelper().fillGroupForm(groupData);
        applicationManager.getGroupHelper().submitGroupCreationForm();
        applicationManager.getGroupHelper().returnToGroupsPage();

        Set<GroupData> after = applicationManager.getGroupHelper().getGroupsHash();

        //Asserting collections by SIZE
        assertEquals(after.size(), before.size() + 1);
    }
}
