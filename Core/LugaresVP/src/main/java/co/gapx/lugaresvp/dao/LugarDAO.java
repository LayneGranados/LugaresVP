package co.gapx.lugaresvp.dao;

import co.gapx.lugaresvp.domain.Lugar;
import java.util.List;

/**
 *
 * @author laynegranadosmogollon
 */
public interface LugarDAO {
    
    public List<Lugar> listALl();
    
    public Lugar save(Lugar lugar);
    
    public Lugar get(int id);
    
    public boolean delete(Lugar lugar);
}
