package co.gapx.lugaresvp.business;

import co.gapx.lugaresvp.domain.TipoEmpleado;
import java.util.List;

/**
 *
 * @author laynegranadosmogollon
 */
public interface TipoEmpleadoBusiness {
    
    public List<TipoEmpleado> listALl();
    
    public TipoEmpleado save(TipoEmpleado tipoLugar);
    
    public boolean delete(TipoEmpleado tipoEmpleado);
    
    public TipoEmpleado get(int id);
    
}
