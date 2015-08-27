package co.gapx.lugaresvp.dao.impl;

import co.gapx.lugaresvp.dao.CalificacionActividadDAO;
import co.gapx.lugaresvp.dao.HibernateUtil;
import co.gapx.lugaresvp.domain.Actividad;
import co.gapx.lugaresvp.domain.CalificacionActividad;
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
public class CalificacionActividadDAOImpl implements CalificacionActividadDAO, Serializable{
    
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
    public boolean save(CalificacionActividad calificacionActividad) {
        boolean x=false;
        try {
            this.getCurrentSession().saveOrUpdate(calificacionActividad);
            if(calificacionActividad.getId()!=null){
                x = true;
            }
        } catch (HibernateException ex) {
            this.logger.error("Error Guardando CalificacionActividad");
            this.logger.error("Mensaje: "+ ex.getMessage());
            throw ex;
        }
        return x;
    }

    @Override
    @Transactional
    public List<CalificacionActividad> listALl() {
        List lista = getCurrentSession().createQuery("from CalificacionActividad p").list();
        this.evictUnProxy(lista);
        return lista;
    }

    @Override
    @Transactional
    public CalificacionActividad get(int id) {
        CalificacionActividad calificacionActividad= (CalificacionActividad) getCurrentSession().createQuery("from CalificacionActividad e where e.id= :id").setParameter("id", id).list().get(0);
        this.evictUnProxy(calificacionActividad);
        return calificacionActividad;
    }
    
    @Override
    @Transactional
    public List<CalificacionActividad> getDeActividad(Actividad id) {
        List<CalificacionActividad> calificacionActividad= (List<CalificacionActividad>) getCurrentSession().createQuery("from CalificacionActividad e where e.actividad= :id").setParameter("id", id).list();
        this.evictUnProxy(calificacionActividad);
        return calificacionActividad;
    }

    @Transactional
    public CalificacionActividad getDeLugar(int id) {
        CalificacionActividad calificacionActividad= (CalificacionActividad) getCurrentSession().createQuery(""
              + "SELECT l.ID, l.NOMBRE, ac.ID, ac.NOMBRE, ca.NOMBRE\n" +
                "FROM Actividad ac \n" +
                "INNER JOIN ActividadTipoLugar atl ON ac.id = atl.actividad \n" +
                "INNER JOIN TipoLugar tl ON atl.TIPO_LUGAR_ID = TL.ID\n" +
                "INNER JOIN LUGAR L ON L.TIPO_LUGAR_ID = TL.ID\n" +
                "INNER JOIN CALIFICACION_ACTIVIDAD CA ON CA.ACTIVIDAD_ID= AC.ID\n" +
                "WHERE L.ID=1 where e.id= :id").setParameter("id", id).list().get(0);
        this.evictUnProxy(calificacionActividad);
        return calificacionActividad;
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