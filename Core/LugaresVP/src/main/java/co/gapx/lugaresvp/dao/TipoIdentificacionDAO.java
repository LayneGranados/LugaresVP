package co.gapx.lugaresvp.dao;

import co.gapx.lugaresvp.domain.TipoIdentificacion;
import java.util.List;

/**
 *
 * @author laynegranadosmogollon
 */
public interface TipoIdentificacionDAO {
    
    public List<TipoIdentificacion> listALl();
    
    public boolean save(TipoIdentificacion tipoIdentificacion);
    
    public TipoIdentificacion get(int id);
    
}
