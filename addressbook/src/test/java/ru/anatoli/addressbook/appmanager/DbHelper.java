package ru.anatoli.addressbook.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.anatoli.addressbook.models.ContactData;
import ru.anatoli.addressbook.models.GroupData;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by anatoli.anukevich on 8/10/2017.
 */
public class DbHelper {
    private SessionFactory sessionFactory;
    private Session session;

    public DbHelper() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                                                                                    .configure() // configures settings from hibernate.cfg.xml
                                                                                    .build();
        sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
    }

    public Set<GroupData> getGroupSet() {
        session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupData> list = session.createQuery( "from GroupData" ).list();
        session.getTransaction().commit();
        session.close();
        return new HashSet<GroupData>(list);
    }

    public Set<ContactData> getContactSet() {
        session = sessionFactory.openSession();
        session.beginTransaction();
        List<ContactData> list = session.createQuery( "from ContactData WHERE deprecated = '0000-00-00 00:00:00'" ).list();
        session.getTransaction().commit();
        session.close();
        return new HashSet<ContactData>(list);
    }
}
