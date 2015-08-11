package co.gapx.lugaresvp.business;

import co.gapx.lugaresvp.domain.Actividad;
import co.gapx.lugaresvp.domain.CalificacionActividad;
import java.util.List;

/**
 *
 * @author laynegranadosmogollon
 */
public interface CalificacionActividadBusiness {
    
    public List<CalificacionActividad> listALl();
    
    public boolean save(CalificacionActividad calificacionActividad);
    
    public CalificacionActividad get(int id);
    
    public List<CalificacionActividad> getDeActividad(Actividad id);
    
}
