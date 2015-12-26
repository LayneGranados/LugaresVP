package co.gapx.lugaresvp.dao.impl;

import co.gapx.lugaresvp.dao.LugarDAO;
import co.gapx.lugaresvp.dao.HibernateUtil;
import co.gapx.lugaresvp.domain.Lugar;
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
public class LugarDAOImpl implements LugarDAO, Serializable{
    
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
    public Lugar save(Lugar lugar) {
        try {
            this.getCurrentSession().saveOrUpdate(lugar);
            if(lugar.getId()==null){
                return null;
            }
        } catch (HibernateException ex) {
            this.logger.error("Error Guardando Lugar");
            this.logger.error("Mensaje: "+ ex.getMessage());
            throw ex;
        }
        return lugar;
    }

    @Override
    @Transactional
    public List<Lugar> listALl() {
        List lista = getCurrentSession().createQuery("from Lugar p").list();
        this.evictUnProxy(lista);
        return lista;
    }

    @Override
    @Transactional
    public Lugar get(int id) {
        Lugar lugar= (Lugar) getCurrentSession().createQuery("from Lugar e where e.id= :id").setParameter("id", id).list().get(0);
        this.evictUnProxy(lugar);
        return lugar;
    }
    
    @Override
    @Transactional
    public boolean delete(Lugar lugar) {
        try {
            this.getCurrentSession().delete(lugar);
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