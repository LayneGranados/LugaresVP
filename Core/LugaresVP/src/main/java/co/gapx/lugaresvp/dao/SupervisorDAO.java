package co.gapx.lugaresvp.dao;

import co.gapx.lugaresvp.domain.Supervisor;
import java.util.List;

/**
 *
 * @author laynegranadosmogollon
 */
public interface SupervisorDAO {
    
    public List<Supervisor> listALl();
    
    public boolean save(Supervisor supervisor);
    
    public Supervisor get(int id);
    
}
