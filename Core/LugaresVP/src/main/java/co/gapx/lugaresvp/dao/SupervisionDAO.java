package co.gapx.lugaresvp.dao;

import co.gapx.lugaresvp.domain.Lugar;
import co.gapx.lugaresvp.domain.Supervision;
import java.util.List;

/**
 *
 * @author laynegranadosmogollon
 */
public interface SupervisionDAO {
    
    public List<Supervision> listALl();
    
    public Supervision save(Supervision supervision);
    
    public Supervision get(int id);
    
    public List<Supervision> getDeLugar(Lugar id);
    
    public Supervision saveWithGet(Supervision supervision);
    
    public boolean delete(Supervision supervision);
    
    
}
