package co.gapx.lugaresvp.business.impl;

import co.gapx.lugaresvp.business.ActividadBusiness;
import co.gapx.lugaresvp.dao.ActividadDAO;
import co.gapx.lugaresvp.domain.Actividad;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author laynegranadosmogollon
 */
@Service("actividadBusiness")
public class ActividadBusinessImpl implements ActividadBusiness, Serializable{
    
    @Autowired
    private ActividadDAO actividadDAO;

    @Override
    @Transactional
    public Actividad save(Actividad e) {
        return this.actividadDAO.save(e);
    }
    
    @Override
    @Transactional
    public List<Actividad> listALl() {
        return this.actividadDAO.listALl();
    }

    @Override
    @Transactional
    public Actividad get(int id) {
        return this.actividadDAO.get(id);
    }
    
    @Override
    @Transactional
    public boolean delete(Actividad e) {
        return this.actividadDAO.delete(e);
    }

}