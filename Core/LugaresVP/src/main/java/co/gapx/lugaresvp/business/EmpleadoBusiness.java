package co.gapx.lugaresvp.business;

import co.gapx.lugaresvp.domain.Empleado;
import java.util.List;

/**
 *
 * @author laynegranadosmogollon
 */
public interface EmpleadoBusiness {
    
    public List<Empleado> listALl();
    
    public boolean save(Empleado supervisor);
    
    public Empleado get(int id);
    
}
