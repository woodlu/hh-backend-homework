package ru.hh.school.dao;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.hh.school.entity.Employer;

import javax.inject.Singleton;
import java.util.List;

@Singleton
@AllArgsConstructor
public class EmployerDao {
    private SessionFactory sessionFactory;

    public void addEmployerToFavourite(Employer employer) {
        Session session = getSession();
        System.out.println("add emp dao");
        session.save(employer);
    }

    public List<Employer> getEmployers() {
        return getSession()
                .createQuery("SELECT employer FROM Employer employer", Employer.class)
                .getResultList();
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
