package co.gapx.lugaresvp.business;

import co.gapx.lugaresvp.domain.Actividad;
import java.util.List;

/**
 *
 * @author laynegranadosmogollon
 */
public interface ActividadBusiness {
    
    public List<Actividad> listALl();
    
    public boolean save(Actividad actividad);
    
    public Actividad get(int id);
    
}
