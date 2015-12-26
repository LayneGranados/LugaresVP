package co.gapx.lugaresvp.business;

import co.gapx.lugaresvp.domain.TipoLugar;
import java.util.List;

/**
 *
 * @author laynegranadosmogollon
 */
public interface TipoLugarBusiness {
    
    public List<TipoLugar> listALl();
    
    public TipoLugar save(TipoLugar tipoLugar);
    
    public TipoLugar get(int id);
    
    public boolean delete(TipoLugar tipoLugar);
    
}
