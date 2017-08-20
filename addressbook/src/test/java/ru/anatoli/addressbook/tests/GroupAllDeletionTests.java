package ru.anatoli.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.GroupData;
import java.util.Set;
import static org.testng.Assert.assertEquals;

/**
 * Created by anatoli.anukevich on 7/13/2017.
 */
public class GroupAllDeletionTests extends TestBase {
    @BeforeMethod
    public void ensurePrecondition() {
        applicationManager.getNavigationHelper().goToGroupsPage();

        GroupData groupData = new GroupData().withGroupName("Test name")
                                            .withGroupHeader(null)
                                            .withGroupFooter(null);
        int groupNumber = applicationManager.getDbHelper().getGroupSet().size();
        if (groupNumber == 0) {
            for (int i = 0; i < 3; i++) {
                applicationManager.getGroupHelper().createGroup(groupData);
            }
        } else if (groupNumber < 3) {
            for (int i = 0; i < 2; i++) {
                applicationManager.getGroupHelper().createGroup(groupData);
            }
        }
    }

    @Test(enabled = true)
    public void testGroupAllDeletion() {
        //Getting Set of GroupData object model BEFORE deletion
        Set<GroupData> before = applicationManager.getDbHelper().getGroupSet();

        applicationManager.getGroupHelper().deleteAllSelectedGroups(before);

        //Getting Set of GroupData object model AFTER deletion
        Set<GroupData> after = applicationManager.getDbHelper().getGroupSet();

        //Asserting collections by SIZE
        assertEquals(before.size(), after.size() + before.size());

        before.removeAll(before);

        //Asserting by COLLECTIONS
        assertEquals(before, after);

        //Asserting UI data vs DB data
        compareUiVsDbGroupData();
    }
}
