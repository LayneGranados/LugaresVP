package co.gapx.lugaresvp.dao.impl;

import co.gapx.lugaresvp.dao.ActividadDAO;
import co.gapx.lugaresvp.dao.HibernateUtil;
import co.gapx.lugaresvp.domain.Actividad;
import java.io.Serializable;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author laynegranadosmogollon
 */
@Repository
public class ActividadDAOImpl implements ActividadDAO, Serializable{
    
    @Autowired
    SessionFactory sessionFactory;
    @Autowired
    private HibernateUtil hibernateUtil;
    private Log logger = LogFactory.getLog(this.getClass());
    
    
    protected final Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
    
    @Override
    @Transactional
    public Actividad save(Actividad actividad) {
        boolean x=false;
        try {
            this.getCurrentSession().saveOrUpdate(actividad);
            if(actividad.getId()!=null){
                x = true;
            }
        } catch (HibernateException ex) {
            this.logger.error("Error Guardando Actividad");
            this.logger.error("Mensaje: "+ ex.getMessage());
            throw ex;
        }
        return actividad;
    }

    @Override
    @Transactional
    public List<Actividad> listALl() {
        List lista = getCurrentSession().createQuery("from Actividad p").list();
        this.evictUnProxy(lista);
        return lista;
    }

    @Override
    @Transactional
    public Actividad get(int id) {
        Actividad actividad= (Actividad) getCurrentSession().createQuery("from Actividad e where e.id= :id").setParameter("id", id).list().get(0);
        this.evictUnProxy(actividad);
        return actividad;
    }
    
    @Override
    @Transactional
    public boolean delete(Actividad actividad) {
        try {
            this.getCurrentSession().delete(actividad);
            return true;
        } catch (HibernateException hb){
            return false;
        } catch (Exception ex) {
            return false;
        }
    }
    
    private void evictUnProxy(List lista) {
        for (int i = 0; i < lista.size(); getCurrentSession().evict(lista.get(i)), ++i);
        for (int i = 0; i < lista.size(); hibernateUtil.initializeAndUnproxy(lista.get(i)), ++i);
    }
    
    private void evictUnProxy(Object obj) {
        getCurrentSession().evict(obj);
        hibernateUtil.initializeAndUnproxy(obj);
    }    
}