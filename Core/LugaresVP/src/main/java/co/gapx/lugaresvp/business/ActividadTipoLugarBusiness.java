package co.gapx.lugaresvp.business;

import co.gapx.lugaresvp.domain.Actividad;
import co.gapx.lugaresvp.domain.ActividadTipoLugar;
import co.gapx.lugaresvp.domain.TipoLugar;
import java.util.List;

/**
 *
 * @author laynegranadosmogollon
 */
public interface ActividadTipoLugarBusiness {
    
    public List<ActividadTipoLugar> listALl();
    
    public ActividadTipoLugar save(ActividadTipoLugar actividadTipoLugar);
    
    public ActividadTipoLugar get(int id);
    
    public List<ActividadTipoLugar> getDeTipoLugar(TipoLugar id) ;
    
    public ActividadTipoLugar getDeTipoLugarActividad(TipoLugar tipoLugar, Actividad actividad);
    
    public boolean delete(ActividadTipoLugar actividadTipoLugar);
    
    public List<ActividadTipoLugar> getDeNoTipoLugar(TipoLugar id);
}
