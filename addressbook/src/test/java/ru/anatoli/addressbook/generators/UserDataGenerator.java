package ru.anatoli.addressbook.generators;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.anatoli.addressbook.models.UserData;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UnknownFormatConversionException;

/**
 * Created by anatoli.anukevich on 8/2/2017.
 */
public class UserDataGenerator {
    public static void main(String[] args) throws IOException {
        int numberOfLogins = Integer.parseInt(args[0]);
        String pathToFile = args[1];
        String fileName = args[2];
        String format = args[3];
        File file = new File(pathToFile + fileName + "." + format);

        List<UserData> listWithUsers = generateUsers(numberOfLogins);

        if (format.equals("csv")) {
            saveAsCsv(file, listWithUsers);
        } else if (format.equals("json")) {
            saveAsJson(file, listWithUsers);
        } else {
            System.out.println("Unknown format is input " + format);
            throw new UnknownFormatConversionException("Unknown format is input " + format);
        }
    }

    public static void saveAsCsv(File file, List<UserData> listWithUsers) throws IOException {
        FileWriter writer = new FileWriter(file);
        for (UserData user : listWithUsers) {
            writer.write(String.format("%s;%s\n", user.getUserName(), user.getPassword()));
        }
        writer.flush();
        writer.close();
    }

    public static void saveAsJson(File file, List<UserData> listWithUsers) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(listWithUsers);
        FileWriter writer = new FileWriter(file);
        writer.write(json);
        writer.flush();
        writer.close();
    }

    public static List<UserData> generateUsers(int numberOfLogins) {
        List<UserData> list = new ArrayList<UserData>();
        for (int i = 0; i < numberOfLogins; i++) {
            list.add(new UserData().withUserName("admin")
                                    .withPassword("secret"));
        }
        return list;
    }
}
