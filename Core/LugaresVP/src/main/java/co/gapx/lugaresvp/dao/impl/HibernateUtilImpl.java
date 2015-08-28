package co.gapx.lugaresvp.dao.impl;

import co.gapx.lugaresvp.dao.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Layne O. Granados M. <layne0205@gmail.com>
 * @author Felix E. Orduz G. <felix.orduz@gmail.com>
 */
@Repository
public class HibernateUtilImpl implements HibernateUtil {

    @Autowired
    SessionFactory sessionFactory;

    /**
     *
     * @return
     */
    protected final Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     *
     * @param <T>
     * @param entity
     * @return
     */
    @Override
    public <T> T initializeAndUnproxy(T entity) {
        if (entity == null) {
            throw new NullPointerException("Entity passed for initialization is null");
        }

        getCurrentSession().refresh(entity);
        org.hibernate.Hibernate.initialize(entity);
        if (entity instanceof HibernateProxy) {
            entity = (T) ((HibernateProxy) entity).getHibernateLazyInitializer()
                    .getImplementation();
        }
        getCurrentSession().evict(entity);
        return entity;
    }
}
