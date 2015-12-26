package co.gapx.lugaresvp.dao.impl;

import co.gapx.lugaresvp.dao.PersonaDAO;
import co.gapx.lugaresvp.dao.HibernateUtil;
import co.gapx.lugaresvp.domain.Persona;
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
public class PersonaDAOImpl implements PersonaDAO, Serializable{
    
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
    public Persona save(Persona persona) {
        Persona x=new Persona();
        try {
            this.getCurrentSession().saveOrUpdate(persona);
            if(persona.getId()!=null){
                x = persona;
            }
        } catch (HibernateException ex) {
            this.logger.error("Error Guardando Persona");
            this.logger.error("Mensaje: "+ ex.getMessage());
            throw ex;
        }
        return x;
    }

    @Override
    @Transactional
    public List<Persona> listALl() {
        List lista = getCurrentSession().createQuery("from Persona p").list();
        this.evictUnProxy(lista);
        return lista;
    }

    @Override
    @Transactional
    public Persona get(int id) {
        Persona persona= (Persona) getCurrentSession().createQuery("from Persona e where e.id= :id").setParameter("id", id).list().get(0);
        this.evictUnProxy(persona);
        return persona;
    }
    
    @Override
    @Transactional
    public boolean delete(Persona persona) {
        try {
            this.getCurrentSession().delete(persona);
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