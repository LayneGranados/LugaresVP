package co.gapx.lugaresvp.dao;

import co.gapx.lugaresvp.domain.TipoEmpleado;
import java.util.List;

/**
 *
 * @author laynegranadosmogollon
 */
public interface TipoEmpleadoDAO {
    
    public List<TipoEmpleado> listALl();
    
    public boolean save(TipoEmpleado tipoLugar);
    
    public TipoEmpleado get(int id);
    
}
