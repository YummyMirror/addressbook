package ru.anatoli.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.GroupData;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import static org.testng.Assert.assertEquals;

public class GroupCreationTests extends TestBase {
    @DataProvider
    public Iterator<Object[]> validDataForGroupCreationFromCsv() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        File file = new File("src/test/resources/groupFile.csv");
        FileReader reader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line = bufferedReader.readLine();
        while (line != null) {
            String[] splitData = line.split(";");
            list.add(new Object[] {new GroupData().withGroupName(splitData[0])
                                                    .withGroupHeader(splitData[1])
                                                    .withGroupFooter(splitData[2])});
            line = bufferedReader.readLine();
        }
        bufferedReader.close();
        reader.close();
        return list.iterator();
    }

    @BeforeMethod
    public void ensurePrecondition() {
        applicationManager.getNavigationHelper().goToGroupsPage();
    }
    
    @Test(dataProvider = "validDataForGroupCreationFromCsv")
    public void testGroupCreation(GroupData groupData) {
        //Getting Set of GroupData object model BEFORE creation
        Set<GroupData> before = applicationManager.getGroupHelper().getGroupSet();

        //Creating new GROUPs
        applicationManager.getGroupHelper().createGroup(groupData);

        //Getting Set of GroupData object model AFTER creation
        Set<GroupData> after = applicationManager.getGroupHelper().getGroupSet();

        //Asserting collections by SIZE
        assertEquals(after.size(), before.size() + 1);

        //Setting MAX id if  for object model GroupData
        groupData.withGroupId(after
                            .stream()
                            .max((group1, group2) -> Integer.compare(group1.getGroupId(), group2.getGroupId()))
                            .get()
                            .getGroupId());
        before.add(groupData);

        //Asserting by COLLECTIONS
        assertEquals(before, after);
    }
}
