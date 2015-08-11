package co.gapx.lugaresvp.dao;

import co.gapx.lugaresvp.domain.TipoLugar;
import java.util.List;

/**
 *
 * @author laynegranadosmogollon
 */
public interface TipoLugarDAO {
    
    public List<TipoLugar> listALl();
    
    public boolean save(TipoLugar tipoLugar);
    
    public TipoLugar get(int id);
    
}
