package co.gapx.lugaresvp.business.impl;

import co.gapx.lugaresvp.business.CalificacionActividadBusiness;
import co.gapx.lugaresvp.dao.CalificacionActividadDAO;
import co.gapx.lugaresvp.domain.Actividad;
import co.gapx.lugaresvp.domain.CalificacionActividad;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author laynegranadosmogollon
 */
@Service("calificacionActividadBusiness")
public class CalificacionActividadBusinessImpl implements CalificacionActividadBusiness, Serializable{
    
    @Autowired
    private CalificacionActividadDAO calificacionActividadDAO;

    @Override
    @Transactional
    public CalificacionActividad save(CalificacionActividad e) {
        return this.calificacionActividadDAO.save(e);
    }
    
    @Override
    @Transactional
    public List<CalificacionActividad> listALl() {
        return this.calificacionActividadDAO.listALl();
    }

    @Override
    @Transactional
    public CalificacionActividad get(int id) {
        return this.calificacionActividadDAO.get(id);
    }
    
    @Override
    @Transactional
    public List<CalificacionActividad> getDeActividad(Actividad id){
        return this.calificacionActividadDAO.getDeActividad(id);
    }
    
    @Override
    @Transactional
    public boolean delete(CalificacionActividad e) {
        return this.calificacionActividadDAO.delete(e);
    }

}