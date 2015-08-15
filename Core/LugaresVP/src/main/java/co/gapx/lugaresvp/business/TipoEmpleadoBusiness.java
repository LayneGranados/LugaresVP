package co.gapx.lugaresvp.business;

import co.gapx.lugaresvp.domain.TipoEmpleado;
import java.util.List;

/**
 *
 * @author laynegranadosmogollon
 */
public interface TipoEmpleadoBusiness {
    
    public List<TipoEmpleado> listALl();
    
    public boolean save(TipoEmpleado tipoLugar);
    
    public TipoEmpleado get(int id);
    
}
