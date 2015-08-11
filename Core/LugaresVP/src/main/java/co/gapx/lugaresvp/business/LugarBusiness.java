package co.gapx.lugaresvp.business;

import co.gapx.lugaresvp.domain.Lugar;
import java.util.List;

/**
 *
 * @author laynegranadosmogollon
 */
public interface LugarBusiness {
    
    public List<Lugar> listALl();
    
    public boolean save(Lugar lugar);
    
    public Lugar get(int id);
    
}
