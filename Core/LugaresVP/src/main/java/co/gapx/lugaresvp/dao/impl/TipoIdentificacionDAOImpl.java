package co.gapx.lugaresvp.dao.impl;

import co.gapx.lugaresvp.dao.TipoIdentificacionDAO;
import co.gapx.lugaresvp.dao.HibernateUtil;
import co.gapx.lugaresvp.domain.TipoIdentificacion;
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
public class TipoIdentificacionDAOImpl implements TipoIdentificacionDAO, Serializable{
    
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
    public boolean save(TipoIdentificacion tipoIdentificacion) {
        boolean x=false;
        try {
            this.getCurrentSession().saveOrUpdate(tipoIdentificacion);
            if(tipoIdentificacion.getId()!=null){
                x = true;
            }
        } catch (HibernateException ex) {
            this.logger.error("Error Guardando TipoIdentificacion");
            this.logger.error("Mensaje: "+ ex.getMessage());
            throw ex;
        }
        return x;
    }

    @Override
    @Transactional
    public List<TipoIdentificacion> listALl() {
        List lista = getCurrentSession().createQuery("from TipoIdentificacion p").list();
        this.evictUnProxy(lista);
        return lista;
    }

    @Override
    @Transactional
    public TipoIdentificacion get(int id) {
        TipoIdentificacion tipoIdentificacion= (TipoIdentificacion) getCurrentSession().createQuery("from TipoIdentificacion e where e.id= :id").setParameter("id", id).list().get(0);
        this.evictUnProxy(tipoIdentificacion);
        return tipoIdentificacion;
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