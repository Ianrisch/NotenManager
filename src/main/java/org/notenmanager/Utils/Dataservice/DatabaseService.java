package org.notenmanager.Utils.Dataservice;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.notenmanager.Exceptions.AlreadyExistsException;
import org.notenmanager.Exceptions.UserAlreadyExistsException;
import org.notenmanager.Models.Person;
import org.notenmanager.Models.SchoolClass;
import org.notenmanager.Models.SchoolSubject;
import org.notenmanager.Models.User;
import org.notenmanager.Utils.Consumer.IOConsumer;

import java.util.List;
import java.util.Objects;

public class DatabaseService implements DataService {
    private SessionFactory sessionFactory;

    public DatabaseService() {
        setUpSessionFactory();
    }

    public DatabaseService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setUpSessionFactory() {
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

    public void DisposeConnection() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
        sessionFactory = null;
    }

    @Override
    public void CreateUser(User user) throws UserAlreadyExistsException {
        CreateSessionAndExecute(session -> {

            if (GetSchoolClass(user.schoolClass.name) == null) {
                session.persist(user.schoolClass);
            }

            if (!UserExist(user.username)) {
                session.persist(user);
            } else {
                throw new UserAlreadyExistsException(user);
            }

            return null;
        });
    }

    private SchoolClass GetSchoolClass(String name) {
        return CreateSessionAndExecute(session ->
                session.createQuery(
                        "from SchoolClass class where class.name = '" + name + "'",
                        SchoolClass.class).getSingleResult()
        );
    }

    @Override
    public boolean UserExist(String username) {
        return GetUser(username) != null;
    }

    @Override
    public boolean EmailExist(String email) {
        User user = CreateSessionAndExecute(session ->
                session.createQuery(
                        "from User u where u.mail = '" + email + "'",
                        User.class).getSingleResult()
        );
        return user != null;
    }

    @Override
    public boolean AuthenticateUser(String username, String password) {
        User user = GetUser(username);
        return user != null ? Objects.equals(user.password, password) : false;
    }

    @Override
    public User GetUser(String username) {
        return CreateSessionAndExecute(session ->
                session.createQuery(
                        "from User u where u.username = '" + username + "'",
                        User.class).getSingleResult()
        );
    }

    @Override
    public void DeleteUser(String username) {
        User user = GetUser(username);
        CreateSessionAndExecute(session -> {
            session.remove(user);
            return null;
        });
    }

    @Override
    public List<SchoolSubject> GetSchoolSubjectsFromUser(User user) {
        return CreateSessionAndExecute(session -> {
            User query = session.createQuery(
                    "select u from User u " +
                            "where u.username = '" + user.username + "' "
                    , User.class).getSingleResult();


            return query.schoolSubjects;
        });
    }

    private SchoolSubject GetSchoolSubjectFromUser(User user, String nameOfSubject) {
        return CreateSessionAndExecute(session -> {
            SchoolSubject query = session.createQuery(
                    "select s from SchoolSubject s " +
                            "where s.name = '" + nameOfSubject + "' " +
                            "and s.user.username = '" + user.username + "' "
                    , SchoolSubject.class).getSingleResult();

            return query;
        });
    }

    private <Output> Output CreateSessionAndExecute(IOConsumer<Session, Output> consumer) {
        Output output = null;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            output = consumer.accept(session);

            session.getTransaction().commit();
        } catch (Exception e) {
            if (e instanceof AlreadyExistsException) {
                throw e;
            }
            e.printStackTrace();
            return null;
        }
        return output;
    }

    @Override
    public void CreateSchoolSubjectForUser(User user, SchoolSubject schoolSubject) {
        DeleteSchoolSubjectForUser(user, schoolSubject.name);
        CreateSessionAndExecute(session -> {

            try {
                CreateUser(user);
            }catch (Exception e){

            }

            if (!PersonExists(schoolSubject.teacher)) {
                session.persist(schoolSubject.teacher);
            }

            schoolSubject.addRelationPartner(user);
            session.persist(schoolSubject);



            schoolSubject.grades.forEach(grade -> {
                grade.addRelationPartner(schoolSubject);
                session.persist(grade);
            });
            return null;
        });
    }

    private boolean PersonExists(Person person) {
        return GetPerson(person) != null;
    }

    private Person GetPerson(Person person) {
        return CreateSessionAndExecute(session -> session.createQuery("select p from Person p " +
                        "where p.firstName = '" + person.firstName + "' " +
                        "and p.lastName = '" + person.lastName + "' "
                , Person.class).getSingleResult());
    }

    @Override
    public void UpdateSchoolSubjectForUser(User user, String nameOfSubjectToUpdate, SchoolSubject schoolSubject) {

    }

    @Override
    public void DeleteSchoolSubjectForUser(User user, String nameOfSubject) {
        SchoolSubject toDelete = GetSchoolSubjectFromUser(user, nameOfSubject);
        CreateSessionAndExecute(session -> {
            session.remove(toDelete);
            return null;
        });
    }

}
