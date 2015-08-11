package co.gapx.lugaresvp.business;

import co.gapx.lugaresvp.domain.ActividadTipoLugar;
import co.gapx.lugaresvp.domain.TipoLugar;
import java.util.List;

/**
 *
 * @author laynegranadosmogollon
 */
public interface ActividadTipoLugarBusiness {
    
    public List<ActividadTipoLugar> listALl();
    
    public boolean save(ActividadTipoLugar actividadTipoLugar);
    
    public ActividadTipoLugar get(int id);
    
    public List<ActividadTipoLugar> getDeTipoLugar(TipoLugar id) ;
}
