package ru.anatoli.addressbook.generators;

import ru.anatoli.addressbook.models.ContactData;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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

    public static List<ContactData> generateContacts(int numberOfContacts) {
        List<ContactData> listWithContacts = new ArrayList<ContactData>();
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
                                                    .withBirthDay("8")
                                                    .withBirthMonth("June")
                                                    .withBirthYear("1990")
                                                    .withAnniversaryDay("12")
                                                    .withAnniversaryMonth("July")
                                                    .withAnniversaryYear("1998")
                                                    .withSecondaryAddress(String.format("Secondary Address %s", id))
                                                    .withSecondaryPhone("444")
                                                    .withSecondaryNotes(String.format("Secondary Notes %s", id)));
            id++;
        }
        return listWithContacts;
    }
}
