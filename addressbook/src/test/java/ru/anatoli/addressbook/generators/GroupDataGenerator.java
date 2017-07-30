package ru.anatoli.addressbook.generators;

import ru.anatoli.addressbook.models.GroupData;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by anatoli.anukevich on 7/30/2017.
 */
public class GroupDataGenerator {
    public static void main(String[] args) {
        int numberOfGroups = Integer.parseInt(args[0]);
        String pathToFile = args[1];
        String fileName = args[2];
        String format = args[3];
        File file = new File(pathToFile + fileName);

        List<GroupData> listWithGroups = generateGroups(numberOfGroups);
    }

    public static List<GroupData> generateGroups(int numberOfGroups) {
        List<GroupData> listWithGroups = new ArrayList<GroupData>();
        int id = 1;
        for (int i = 0; i < numberOfGroups; i++) {
            listWithGroups.add(new GroupData().withGroupName(String.format("GroupName %s", id))
                                                .withGroupHeader(String.format("GroupHeader %s", id))
                                                .withGroupFooter(String.format("GroupFooter %s", id)));
            id++;
        }
        return listWithGroups;
    }
}
