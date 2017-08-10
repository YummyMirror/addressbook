package ru.anatoli.addressbook.appmanager;


import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Created by anatoli.anukevich on 8/10/2017.
 */
public class DbHelper {
    private SessionFactory sessionFactory;

    public DbHelper() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                                                                                    .configure() // configures settings from hibernate.cfg.xml
                                                                                    .build();
        sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
    }
}
