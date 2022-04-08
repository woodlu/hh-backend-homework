package ru.hh.school.dao;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.hh.school.entity.Vacancy;

import javax.inject.Singleton;
import java.util.List;

@Singleton
@AllArgsConstructor
public class VacancyDao {
    private SessionFactory sessionFactory;

    public void addVacancyToFavourite(Vacancy vacancy) {
            getSession().save(vacancy);
    }

    public List<Vacancy> getVacancies(int page, int perPage) {
        return getSession()
                .createQuery("SELECT vacancy FROM Vacancy vacancy", Vacancy.class)
                .setFirstResult(perPage * page).setMaxResults(perPage)
                .getResultList();
    }

    public Vacancy getVacancy(long vacancyId) {
        return getSession().get(Vacancy.class, vacancyId);
    }

    public void deleteVacancy(long vacancyId) {
        Vacancy vacancy = getSession().load(Vacancy.class, vacancyId);
        getSession().delete(vacancy);
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
