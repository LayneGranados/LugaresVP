package co.gapx.lugaresvp.dao;

import co.gapx.lugaresvp.domain.Empleado;
import java.util.List;

/**
 *
 * @author laynegranadosmogollon
 */
public interface SupervisorDAO {
    
    public List<Empleado> listALl();
    
    public boolean save(Empleado supervisor);
    
    public Empleado get(int id);
    
}
