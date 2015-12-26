package co.gapx.lugaresvp.dao;

import co.gapx.lugaresvp.domain.TipoEmpleado;
import java.util.List;

/**
 *
 * @author laynegranadosmogollon
 */
public interface TipoEmpleadoDAO {
    
    public List<TipoEmpleado> listALl();
    
    public TipoEmpleado save(TipoEmpleado tipoEmpleado);
    
    public boolean delete(TipoEmpleado tipoEmpleado);
    
    public TipoEmpleado get(int id);
    
}
