package ru.hh.school.dao;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.hh.school.entity.Area;

import javax.inject.Singleton;

@Singleton
@AllArgsConstructor
public class AreaDao {

    private SessionFactory sessionFactory;

    public void addArea(Area area) {
        Session session = getSession();
        System.out.println("add area dao");
        session.save(area);
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
