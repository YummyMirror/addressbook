package ru.anatoli.addressbook.generators;

import ru.anatoli.addressbook.models.ContactData;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by anatoli.anukevich on 7/31/2017.
 */
public class ContactDataGenerator {
    public static void main(String[] args) {
        int numberOfContacts = Integer.parseInt(args[0]);
        String pathToFile = args[1];
        String fileName = args[2];
        String format = args[3];
        File file = new File(pathToFile + fileName + "." + format);

        List<ContactData> listWithContacts = generateContacts(numberOfContacts);
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
