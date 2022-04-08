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
        getSession().save(employer);
    }

    public Employer getEmployer(long employerId) {
        return getSession().get(Employer.class, employerId);
    }

    public List<Employer> getEmployers(int page, int perPage) {
        return getSession()
                .createQuery("SELECT employer FROM Employer employer", Employer.class)
                .setFirstResult(perPage * page).setMaxResults(perPage)
                .getResultList();
    }

    public void editEmployer(long employerId, String comment) {
        getSession()
                .createQuery("UPDATE Employer SET comment = :comment WHERE id = :employerId")
                .setParameter("comment", comment)
                .setParameter("employerId", employerId)
                .executeUpdate();
    }

    public void deleteEmployer(long employerId) {
        Employer employer = getSession().load(Employer.class, employerId);
        getSession().delete(employer);
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

}
