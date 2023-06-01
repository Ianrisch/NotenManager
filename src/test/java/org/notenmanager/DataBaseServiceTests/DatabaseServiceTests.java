package org.notenmanager.DataBaseServiceTests;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.notenmanager.Models.*;
import org.notenmanager.Utils.Consumer.IOConsumer;
import org.notenmanager.Utils.Dataservice.DatabaseService;

import java.util.ArrayList;
import java.util.List;



public class DatabaseServiceTests {

    public DatabaseServiceTests() {
        setUp();
        sut = new DatabaseService(sessionFactory);
    }

    public SessionFactory sessionFactory;
    protected Person person = new Person("Kek", "dietrich");
    protected List<Grade> grades = new ArrayList();
    protected SchoolSubject subject = new SchoolSubject(person, "LF5", grades);

    protected SchoolClass classe = new SchoolClass("Someclass");
    protected User existentUser = new User("thunfischeis", "securepaswd", "mail@trash.internet", classe);
    protected DatabaseService sut;


    public void setUp() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }


    public void teardown() {

        if (sessionFactory != null) {
            sessionFactory.close();
        }


    }


    public void SetupData() {
        CreateSessionAndExecute(session -> {

            session.persist(person);

            grades.add(new Grade(1, 1));
            grades.add(new Grade(2, 1));
            grades.add(new Grade(3, 1));


            List<SchoolSubject> subjects = new ArrayList();
            subjects.add(subject);

            session.persist(classe);

            existentUser.schoolSubjects = subjects;
            session.persist(existentUser);

            subjects.forEach(subj -> {
                subj.addRelationPartner(existentUser);
                session.persist(subj);
            });

            grades.forEach(grade -> {
                grade.addRelationPartner(subject);
                session.persist(grade);
            });
            return null;
        });
    }

    private <Output> Output CreateSessionAndExecute(IOConsumer<Session, Output> consumer) {
        Output output;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            output = consumer.accept(session);

            session.getTransaction().commit();
        }
        return output;
    }
}
