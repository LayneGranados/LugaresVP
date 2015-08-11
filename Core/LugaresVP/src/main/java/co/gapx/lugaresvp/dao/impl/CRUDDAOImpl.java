package co.gapx.lugaresvp.dao.impl;

import co.gapx.lugaresvp.dao.CRUDDAO;
import co.gapx.lugaresvp.dao.HibernateUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import co.gapx.lugaresvp.exceptions.DataAccessLayerException;

@Repository
public class CRUDDAOImpl implements CRUDDAO {

    @Autowired
    SessionFactory sessionFactory;
    @Autowired
    private HibernateUtil hibernateUtil;
    private Log logger = LogFactory.getLog(this.getClass());

    /**
     *
     * @param <T>
     * @param klass
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> List<T> getAll(Class<T> klass) {

        List lista = getCurrentSession().createQuery("from " + klass.getSimpleName()).list();

        this.evictUnProxy(lista);

        return (List<T>) lista;
    }

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
     * @param klass
     */
    @Override
    public <T> void save(T klass) {
        getCurrentSession().saveOrUpdate(klass);
    }

    /**
     *
     * @param <T>
     * @param klass
     */
    @Override
    public <T> void delete(T klass) {
        getCurrentSession().delete(klass);

    }

    /**
     *
     * @param <T>
     * @param klass
     * @param values
     * @return
     */
    @Override
    public <T> List<T> get(Class<T> klass, Map values) {
        List lista;
        Session session = this.getCurrentSession();
        String sql = "FROM " + klass.getSimpleName() + " r ";

        if (!values.isEmpty()) {
            sql += " where ";
        }

        Set keys = values.keySet();
        Iterator it = keys.iterator();
        List keys2 = new ArrayList();
        int j = 0;
        while (it.hasNext()) {
            if (j > 0) {
                sql += " and ";
            }
            String key = it.next().toString();
            if (values.get(key) != null && (values.get(key).toString().charAt(0) == '%' || values.get(key).toString().charAt(values.get(key).toString().length() - 1) == '%')) {
                sql += " upper(r." + key + ") like :Arg" + j;
            } else {
                sql += " r." + key + " = :Arg" + j;
            }
            j++;
            keys2.add(key);
        }
        Query query = session.createQuery(sql);
        for (int i = 0; i < keys2.size(); i++) {

            if (values.get(keys2.get(i)) != null && (values.get(keys2.get(i)).toString().charAt(0) == '%' || values.get(keys2.get(i)).toString().charAt(values.get(keys2.get(i)).toString().length() - 1) == '%')) {
                query.setParameter("Arg" + i, values.get(keys2.get(i)).toString().toUpperCase());
            } else {
                query.setParameter("Arg" + i, values.get(keys2.get(i)));
            }
        }
        this.logger.debug("consulta: " + sql);
        try {
            lista = query.list();
        } catch (HibernateException ex) {
            this.logger.error("Error Listando Por Una Serie De Objetos");
            this.logger.error("Mensaje: " + ex.getMessage());
            throw new DataAccessLayerException("Error Buscando " + klass.getClass().getName());
        }
        this.evictUnProxy(lista);

        return lista;

    }

    /**
     *
     * @param lista
     */
    private void evictUnProxy(List lista) {
        if (lista != null) {
            //borra los objetos de la cache 
            for (int i = 0; i < lista.size(); getCurrentSession().evict(lista.get(i)), ++i);
            //deja los objetos como pojos
            for (int i = 0; i < lista.size(); hibernateUtil.initializeAndUnproxy(lista.get(i)), ++i);
        }
    }
}
