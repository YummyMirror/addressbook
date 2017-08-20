package ru.anatoli.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.GroupData;
import java.util.Set;
import static org.testng.Assert.assertEquals;

/**
 * Created by anatoli.anukevich on 7/1/2017.
 */
public class GroupDeletionTests extends TestBase {
    @BeforeMethod
    public void ensurePrecondition() {
        applicationManager.getNavigationHelper().goToGroupsPage();
        if (applicationManager.getDbHelper().getGroupSet().size() == 0) {
            GroupData groupData = new GroupData().withGroupName("Temp name")
                                                .withGroupHeader(null)
                                                .withGroupFooter(null);
            applicationManager.getGroupHelper().createGroup(groupData);
        }
    }

    @Test(enabled = true)
    public void testGroupDeletion() {
        //Getting Set of GroupData object model BEFORE deletion
        Set<GroupData> before = applicationManager.getDbHelper().getGroupSet();

        //Choosing the random Group that will be removed
        GroupData removedGroup = before.iterator().next();

        applicationManager.getGroupHelper().deleteGroup(removedGroup);

        //Getting Set of GroupData object model AFTER deletion
        Set<GroupData> after = applicationManager.getDbHelper().getGroupSet();

        //Asserting collections by SIZE
        assertEquals(before.size() - 1, after.size());

        before.remove(removedGroup);

        //Asserting by COLLECTIONS
        assertEquals(before, after);

        //Asserting UI data vs DB data
        compareUiVsDbGroupData();
    }

    @Test(enabled = true)
    public void testGroupDeletionWithoutSelection() {
        //Getting Set of GroupData object model BEFORE deletion
        Set<GroupData> before = applicationManager.getDbHelper().getGroupSet();

        applicationManager.getGroupHelper().deleteSelectedGroup();
        String noticeTitle = applicationManager.getGroupHelper().getErrorMessageDuringDeletionAndModification();

        //Asserting by NOTICE title
        assertEquals(noticeTitle, "Notice");

        applicationManager.getGroupHelper().returnToGroupsPage();

        //Getting Set of GroupData object model AFTER deletion
        Set<GroupData> after = applicationManager.getDbHelper().getGroupSet();

        //Asserting collections by SIZE
        assertEquals(before.size(), after.size());

        //Asserting by COLLECTIONS
        assertEquals(before, after);

        //Asserting UI data vs DB data
        compareUiVsDbGroupData();
    }
}
