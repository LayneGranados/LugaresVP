package co.gapx.lugaresvp.dao.impl;

import co.gapx.lugaresvp.dao.TipoEmpleadoDAO;
import co.gapx.lugaresvp.dao.HibernateUtil;
import co.gapx.lugaresvp.domain.TipoEmpleado;
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
public class TipoEmpleadoDAOImpl implements TipoEmpleadoDAO, Serializable{
    
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
    public TipoEmpleado save(TipoEmpleado tipoEmpleado) {
        try {
            this.getCurrentSession().saveOrUpdate(tipoEmpleado);
            if(tipoEmpleado.getId()==null){
                return null;
            }
        } catch (HibernateException ex) {
            this.logger.error("Error Guardando TipoEmpleado");
            this.logger.error("Mensaje: "+ ex.getMessage());
            throw ex;
        }
        return tipoEmpleado;
    }

    @Override
    @Transactional
    public List<TipoEmpleado> listALl() {
        List lista = getCurrentSession().createQuery("from TipoEmpleado p").list();
        this.evictUnProxy(lista);
        return lista;
    }

    @Override
    @Transactional
    public TipoEmpleado get(int id) {
        List<TipoEmpleado> list = (List<TipoEmpleado>)getCurrentSession().createQuery("from TipoEmpleado e where e.id= :id").setParameter("id", id).list();
        if(list != null || !list.isEmpty()){
            TipoEmpleado tipoEmpleado= list.get(0);
            this.evictUnProxy(tipoEmpleado);
            return tipoEmpleado;
        }else{
            return null;
        }
    }
    
    @Override
    @Transactional
    public boolean delete(TipoEmpleado tipoEmpleado) {
        try {
            this.getCurrentSession().delete(tipoEmpleado);
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