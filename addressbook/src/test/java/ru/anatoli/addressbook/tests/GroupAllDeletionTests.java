package ru.anatoli.addressbook.tests;

import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.GroupData;
import java.util.Set;

/**
 * Created by anatoli.anukevich on 7/13/2017.
 */
public class GroupAllDeletionTests extends TestBase {
    @Test(enabled = true)
    public void testGroupAllDeletion() {
        applicationManager.getNavigationHelper().goToGroupsPage();

        //Getting Set of GroupData object model BEFORE deletion
        Set<GroupData> before = applicationManager.getGroupHelper().getGroupSet();

        for (GroupData group : before) {
            applicationManager.getGroupHelper().selectGroupById(group.getGroupId());
        }
        applicationManager.getGroupHelper().deleteSelectedGroup();
        applicationManager.getGroupHelper().returnToGroupsPage();

        //Getting Set of GroupData object model AFTER deletion
        Set<GroupData> after = applicationManager.getGroupHelper().getGroupSet();
    }
}
