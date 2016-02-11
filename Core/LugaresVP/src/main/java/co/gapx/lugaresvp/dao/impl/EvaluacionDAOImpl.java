package co.gapx.lugaresvp.dao.impl;

import co.gapx.lugaresvp.dao.EvaluacionDAO;
import co.gapx.lugaresvp.dao.HibernateUtil;
import co.gapx.lugaresvp.domain.Evaluacion;
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
public class EvaluacionDAOImpl implements EvaluacionDAO, Serializable{
    
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
    public Evaluacion save(Evaluacion evaluacion) {
        boolean x=false;
        try {
            this.getCurrentSession().saveOrUpdate(evaluacion);
            System.out.println("entro"+evaluacion.getId());
            if(evaluacion.getId()==null){
                return null;
            }
        } catch (HibernateException ex) {
            this.logger.error("Error Guardando Evaluacion");
            this.logger.error("Mensaje: "+ ex.getMessage());
            throw ex;
        }
        return evaluacion;
    }

    @Override
    @Transactional
    public List<Evaluacion> listALl() {
        List lista = getCurrentSession().createQuery("from Evaluacion p").list();
        this.evictUnProxy(lista);
        return lista;
    }

    @Override
    @Transactional
    public Evaluacion get(int id) {
        Evaluacion evaluacion= (Evaluacion) getCurrentSession().createQuery("from Evaluacion e where e.id= :id").setParameter("id", id).list().get(0);
        this.evictUnProxy(evaluacion);
        return evaluacion;
    }
    
    @Override
    @Transactional
    public boolean delete(Evaluacion evaluacion) {
        try {
            this.getCurrentSession().delete(evaluacion);
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