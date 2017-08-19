package ru.anatoli.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.GroupData;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import static org.testng.Assert.assertEquals;

public class GroupCreationTests extends TestBase {
    @DataProvider
    public Iterator<Object[]> validDataForGroupCreationFromCsv() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        File file = new File("src/test/resources/groupFileCreation.csv");
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

    @DataProvider
    public Iterator<Object[]> validDataForGroupCreationFromJson() throws IOException {
        File file = new File("src/test/resources/groupFileCreation.json");
        FileReader reader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line = bufferedReader.readLine();
        String json = "";
        while (line != null) {
            json += line;
            line = bufferedReader.readLine();
        }
        Gson gson = new Gson();
        Type collectionType = new TypeToken<List<GroupData>>(){}.getType(); //List<GroupData>.class
        List<GroupData> list = gson.fromJson(json, collectionType);
        bufferedReader.close();
        reader.close();
        return list.stream().map((group) -> new Object[]{group}).iterator();
    }

    @BeforeMethod
    public void ensurePrecondition() {
        applicationManager.getNavigationHelper().goToGroupsPage();
    }
    
    @Test(dataProvider = "validDataForGroupCreationFromJson")
    public void testGroupCreation(GroupData groupData) {
        //Getting Set of GroupData object model BEFORE creation
        Set<GroupData> before = applicationManager.getDbHelper().getGroupSet();

        //Creating new GROUPs
        applicationManager.getGroupHelper().createGroup(groupData);

        //Getting Set of GroupData object model AFTER creation
        Set<GroupData> after = applicationManager.getDbHelper().getGroupSet();

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

        //Asserting UI data vs DB data
        Set<GroupData> uiData = applicationManager.getGroupHelper().getGroupSet();
        Set<GroupData> dbData = applicationManager.getDbHelper().getGroupSet();

        assertEquals(uiData, dbData.stream()
                                   .map((group) -> new GroupData().withGroupId(group.getGroupId())
                                                                  .withGroupName(group.getGroupName()))
                                   .collect(Collectors.toSet()));
    }
}
