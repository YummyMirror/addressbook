package ru.anatoli.addressbook.generators;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.anatoli.addressbook.models.ContactData;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UnknownFormatConversionException;

/**
 * Created by anatoli.anukevich on 7/31/2017.
 */
public class ContactDataGenerator {
    public static void main(String[] args) throws IOException {
        int numberOfContacts = Integer.parseInt(args[0]);
        String pathToFile = args[1];
        String fileName = args[2];
        String format = args[3];
        File file = new File(pathToFile + fileName + "." + format);

        List<ContactData> listWithContacts = generateContacts(numberOfContacts);

        if (format.equals("csv")) {
            saveAsCsv(file, listWithContacts);
        } else if (format.equals("json")) {
            saveAsJson(file, listWithContacts);
        } else {
            System.out.println("Unknown format is input " + format);
            throw new UnknownFormatConversionException("Unknown format is input " + format);
        }
    }

    public static void saveAsCsv(File file, List<ContactData> listWithContacts) throws IOException {
        FileWriter writer = new FileWriter(file);
        for (ContactData contact : listWithContacts) {
            writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n", contact.getFirstName(),
                                                                                                                        contact.getMiddleName(),
                                                                                                                        contact.getLastName(),
                                                                                                                        contact.getNickname(),
                                                                                                                        contact.getPhoto().getAbsoluteFile(),
                                                                                                                        contact.getTitle(),
                                                                                                                        contact.getCompany(),
                                                                                                                        contact.getAddress(),
                                                                                                                        contact.getHomePhone(),
                                                                                                                        contact.getMobilePhone(),
                                                                                                                        contact.getWorkPhone(),
                                                                                                                        contact.getFax(),
                                                                                                                        contact.getEmail(),
                                                                                                                        contact.getEmail2(),
                                                                                                                        contact.getEmail3(),
                                                                                                                        contact.getHomepage(),
                                                                                                                        contact.getBirthDay(),
                                                                                                                        contact.getBirthMonth(),
                                                                                                                        contact.getBirthYear(),
                                                                                                                        contact.getAnniversaryDay(),
                                                                                                                        contact.getAnniversaryMonth(),
                                                                                                                        contact.getAnniversaryYear(),
                                                                                                                        contact.getSecondaryAddress(),
                                                                                                                        contact.getSecondaryPhone(),
                                                                                                                        contact.getSecondaryNotes()));
        }
        writer.flush();
        writer.close();
    }

    public static void saveAsJson(File file, List<ContactData> listWithContacts) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(listWithContacts);
        FileWriter writer = new FileWriter(file);
        writer.write(json);
        writer.flush();
        writer.close();
    }

    public static List<ContactData> generateContacts(int numberOfContacts) {
        List<ContactData> listWithContacts = new ArrayList<ContactData>();

        //Lists with DAYS and MONTHS
        List<String> daysInMonth = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
                                                "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
                                                "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31");
        List<String> monthsInYear = Arrays.asList("January", "February", "March", "April", "May", "June",
                                                    "July", "August", "September", "October", "November", "December");

        //Getting random item for DAYS and MONTHS
        String randomDay = daysInMonth.stream().findAny().get();
        String randomMonth = monthsInYear.stream().findAny().get();
        int id = 1;
        for (int i = 0; i < numberOfContacts; i++) {
            listWithContacts.add(new ContactData().withFirstName(String.format("firstName %s", id))
                                                    .withMiddleName(String.format("middleName %s", id))
                                                    .withLastName(String.format("lastName %s", id))
                                                    .withNickname(String.format("nickname %s", id))
                                                    .withPhoto(new File("src/test/resources/NBA.jpeg"))
                                                    .withTitle(String.format("Title %s", id))
                                                    .withCompany(String.format("Company %s", id))
                                                    .withAddress(String.format("Address %s", id))
                                                    .withHomePhone("111-111-111")
                                                    .withMobilePhone("222.222.222")
                                                    .withWorkPhone("333/444(555) 777")
                                                    .withFax("123")
                                                    .withEmail(String.format("%s@mail.ru", id))
                                                    .withEmail2(String.format("%s" + 1 + "@mail.ru", id))
                                                    .withEmail3(String.format("%s" + 2 + "@mail.ru", id))
                                                    .withHomepage(String.format("Homepage %s", id))
                                                    .withBirthDay(randomDay)
                                                    .withBirthMonth(randomMonth)
                                                    .withBirthYear("1990")
                                                    .withAnniversaryDay(randomDay)
                                                    .withAnniversaryMonth(randomMonth)
                                                    .withAnniversaryYear("1998")
                                                    .withSecondaryAddress(String.format("Secondary Address %s", id))
                                                    .withSecondaryPhone("444")
                                                    .withSecondaryNotes(String.format("Secondary Notes %s", id)));
            id++;
        }
        return listWithContacts;
    }
}
