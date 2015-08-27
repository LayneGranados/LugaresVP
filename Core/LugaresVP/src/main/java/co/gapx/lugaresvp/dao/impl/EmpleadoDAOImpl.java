package co.gapx.lugaresvp.dao.impl;

import co.gapx.lugaresvp.dao.EmpleadoDAO;
import co.gapx.lugaresvp.dao.HibernateUtil;
import co.gapx.lugaresvp.domain.Empleado;
import co.gapx.lugaresvp.domain.Login;
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
public class EmpleadoDAOImpl implements EmpleadoDAO, Serializable{
    
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
    public boolean save(Empleado supervisor) {
        boolean x=false;
        try {
            this.getCurrentSession().saveOrUpdate(supervisor);
            if(supervisor.getId()!=null){
                x = true;
            }
        } catch (HibernateException ex) {
            this.logger.error("Error Guardando Supervisor");
            this.logger.error("Mensaje: "+ ex.getMessage());
            throw ex;
        }
        return x;
    }

    @Override
    @Transactional
    public List<Empleado> listALl() {
        List lista = getCurrentSession().createQuery("from Supervisor p").list();
        this.evictUnProxy(lista);
        return lista;
    }

    @Override
    @Transactional
    public Empleado get(int id) {
        Empleado supervisor= (Empleado) getCurrentSession().createQuery("from Supervisor e where e.id= :id").setParameter("id", id).list().get(0);
        this.evictUnProxy(supervisor);
        return supervisor;
    }
    
    @Override
    @Transactional
    public List<Empleado> getForLogin(Login login) {
        List<Empleado> empleados= (List<Empleado>) getCurrentSession().createQuery("from Empleado e where e.login= :login").setParameter("login", login).list();
        this.evictUnProxy(empleados);
        return empleados;
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