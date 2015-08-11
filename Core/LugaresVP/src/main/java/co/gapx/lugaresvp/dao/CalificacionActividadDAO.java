package co.gapx.lugaresvp.dao;

import co.gapx.lugaresvp.domain.Actividad;
import co.gapx.lugaresvp.domain.CalificacionActividad;
import java.util.List;

/**
 *
 * @author laynegranadosmogollon
 */
public interface CalificacionActividadDAO {
    
    public List<CalificacionActividad> listALl();
    
    public boolean save(CalificacionActividad calificacionActividad);
    
    public CalificacionActividad get(int id);
    
    public List<CalificacionActividad> getDeActividad(Actividad id);
    
}
