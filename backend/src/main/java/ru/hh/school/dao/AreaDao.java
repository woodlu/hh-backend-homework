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

    public Area getArea(long areaId) {
        return getSession().get(Area.class, areaId);
    }

    public void detach(Area area) {
        getSession().detach(area);
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
