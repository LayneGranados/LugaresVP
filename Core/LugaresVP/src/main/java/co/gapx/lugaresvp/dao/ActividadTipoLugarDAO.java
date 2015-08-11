package co.gapx.lugaresvp.dao;

import co.gapx.lugaresvp.domain.ActividadTipoLugar;
import co.gapx.lugaresvp.domain.TipoLugar;
import java.util.List;

/**
 *
 * @author laynegranadosmogollon
 */
public interface ActividadTipoLugarDAO {
    
    public List<ActividadTipoLugar> listALl();
    
    public boolean save(ActividadTipoLugar actividadTipoLugar);
    
    public ActividadTipoLugar get(int id);
    
    public List<ActividadTipoLugar> getDeTipoLugar(TipoLugar id) ;
    
}
