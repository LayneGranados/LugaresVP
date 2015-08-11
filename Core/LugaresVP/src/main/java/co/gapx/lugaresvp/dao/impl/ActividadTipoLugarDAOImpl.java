package co.gapx.lugaresvp.dao.impl;

import co.gapx.lugaresvp.dao.ActividadTipoLugarDAO;
import co.gapx.lugaresvp.dao.HibernateUtil;
import co.gapx.lugaresvp.domain.ActividadTipoLugar;
import co.gapx.lugaresvp.domain.TipoLugar;
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
public class ActividadTipoLugarDAOImpl implements ActividadTipoLugarDAO, Serializable{
    
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
    public boolean save(ActividadTipoLugar actividadTipoLugar) {
        boolean x=false;
        try {
            this.getCurrentSession().saveOrUpdate(actividadTipoLugar);
            if(actividadTipoLugar.getId()!=null){
                x = true;
            }
        } catch (HibernateException ex) {
            this.logger.error("Error Guardando ActividadTipoLugar");
            this.logger.error("Mensaje: "+ ex.getMessage());
            throw ex;
        }
        return x;
    }

    @Override
    @Transactional
    public List<ActividadTipoLugar> listALl() {
        List lista = getCurrentSession().createQuery("from ActividadTipoLugar p").list();
        this.evictUnProxy(lista);
        return lista;
    }

    @Override
    @Transactional
    public ActividadTipoLugar get(int id) {
        ActividadTipoLugar actividadTipoLugar= (ActividadTipoLugar) getCurrentSession().createQuery("from ActividadTipoLugar e where e.id= :id").setParameter("id", id).list().get(0);
        this.evictUnProxy(actividadTipoLugar);
        return actividadTipoLugar;
    }
    
    @Override
    @Transactional
    public List<ActividadTipoLugar> getDeTipoLugar(TipoLugar id) {
        List<ActividadTipoLugar> actividadTipoLugar = (List<ActividadTipoLugar>) getCurrentSession().createQuery("from ActividadTipoLugar e where e.tipoLugar= :id").setParameter("id", id).list();
        this.evictUnProxy(actividadTipoLugar);
        return actividadTipoLugar;
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