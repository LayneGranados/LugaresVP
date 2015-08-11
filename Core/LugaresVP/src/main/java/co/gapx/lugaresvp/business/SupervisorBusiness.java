package co.gapx.lugaresvp.business;

import co.gapx.lugaresvp.domain.Supervisor;
import java.util.List;

/**
 *
 * @author laynegranadosmogollon
 */
public interface SupervisorBusiness {
    
    public List<Supervisor> listALl();
    
    public boolean save(Supervisor supervisor);
    
    public Supervisor get(int id);
    
}
