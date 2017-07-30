package ru.anatoli.addressbook.generators;

import ru.anatoli.addressbook.models.GroupData;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UnknownFormatConversionException;

/**
 * Created by anatoli.anukevich on 7/30/2017.
 */
public class GroupDataGenerator {
    public static void main(String[] args) throws IOException {
        int numberOfGroups = Integer.parseInt(args[0]);
        String pathToFile = args[1];
        String fileName = args[2];
        String format = args[3];
        File file = new File(pathToFile + fileName + "." + format);

        List<GroupData> listWithGroups = generateGroups(numberOfGroups);

        if (format.equals("csv")) {
            saveAsCsv(file, listWithGroups);
        } else if (format.equals("json")) {

        } else {
            System.out.println("Unknown format is input " + format);
            throw new UnknownFormatConversionException("Unknown format is input " + format);
        }
    }

    public static void saveAsCsv(File file, List<GroupData> listWithGroups) throws IOException {
        FileWriter writer = new FileWriter(file);
        for (GroupData group : listWithGroups) {
            writer.write(String.format("%s;%s;%s\n", group.getGroupName(), group.getGroupHeader(), group.getGroupFooter()));
        }
        writer.flush();
        writer.close();
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
