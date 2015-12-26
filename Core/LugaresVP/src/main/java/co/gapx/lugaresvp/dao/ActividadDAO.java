package co.gapx.lugaresvp.dao;

import co.gapx.lugaresvp.domain.Actividad;
import java.util.List;

/**
 *
 * @author laynegranadosmogollon
 */
public interface ActividadDAO {
    
    public List<Actividad> listALl();
    
    public Actividad save(Actividad actividad);
    
    public Actividad get(int id);
    
    public boolean delete(Actividad actividad);
    
}
