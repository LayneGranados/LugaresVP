package co.gapx.lugaresvp.dao;

import co.gapx.lugaresvp.domain.Empleado;
import co.gapx.lugaresvp.domain.Login;
import java.util.List;

/**
 *
 * @author laynegranadosmogollon
 */
public interface EmpleadoDAO {
    
    public List<Empleado> listALl();
    
    public boolean save(Empleado supervisor);
    
    public Empleado get(int id);
    
    public List<Empleado> getForLogin(Login login);
    
}
