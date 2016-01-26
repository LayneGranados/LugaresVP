package co.gapx.lugaresvp.business.impl;

import co.gapx.lugaresvp.business.ActividadTipoLugarBusiness;
import co.gapx.lugaresvp.dao.ActividadTipoLugarDAO;
import co.gapx.lugaresvp.domain.Actividad;
import co.gapx.lugaresvp.domain.ActividadTipoLugar;
import co.gapx.lugaresvp.domain.TipoLugar;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author laynegranadosmogollon
 */
@Service("actividadTipoLugarBusiness")
public class ActividadTipoLugarBusinessImpl implements ActividadTipoLugarBusiness, Serializable{
    
    @Autowired
    private ActividadTipoLugarDAO actividadTipoLugarDAO;

    @Override
    @Transactional
    public ActividadTipoLugar save(ActividadTipoLugar e) {
        return this.actividadTipoLugarDAO.save(e);
    }
    
    @Override
    @Transactional
    public List<ActividadTipoLugar> listALl() {
        return this.actividadTipoLugarDAO.listALl();
    }

    @Override
    @Transactional
    public ActividadTipoLugar get(int id) {
        return this.actividadTipoLugarDAO.get(id);
    }
    
    @Override
    @Transactional
    public List<ActividadTipoLugar> getDeTipoLugar(TipoLugar id) {
        return this.actividadTipoLugarDAO.getDeTipoLugar(id);
    }
    
    @Override
    @Transactional
    public ActividadTipoLugar getDeTipoLugarActividad(TipoLugar tipoLugar, Actividad actividad){
        return this.actividadTipoLugarDAO.getDeTipoLugarActividad(tipoLugar, actividad);
    }
    
    @Override
    @Transactional
    public boolean delete(ActividadTipoLugar e) {
        return this.actividadTipoLugarDAO.delete(e);
    }
    
    @Override
    @Transactional
    public List<ActividadTipoLugar> getDeNoTipoLugar(TipoLugar id){
        return this.actividadTipoLugarDAO.getDeNoTipoLugar(id);
    }

}