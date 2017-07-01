package ru.anatoli.addressbook.tests;

import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.GroupData;
import java.util.Set;
import static org.testng.Assert.assertEquals;

/**
 * Created by anatoli.anukevich on 7/1/2017.
 */
public class GroupDeletionTests extends TestBase {
    @Test
    public void testGroupDeletion() {
        applicationManager.getNavigationHelper().goToGroupsPage();

        //Getting Set of GroupData object model BEFORE deletion
        Set<GroupData> before = applicationManager.getGroupHelper().getGroupSet();

        //Choosing the random Group that will be removed
        GroupData removedGroup = before.iterator().next();

        applicationManager.getGroupHelper().deleteGroup(removedGroup);

        //Getting Set of GroupData object model AFTER deletion
        Set<GroupData> after = applicationManager.getGroupHelper().getGroupSet();

        //Asserting collections by SIZE
        assertEquals(before.size() - 1, after.size());

        before.remove(removedGroup);

        //Asserting by COLLECTIONS
        assertEquals(before, after);
    }
}
