package co.gapx.lugaresvp.business;

import co.gapx.lugaresvp.domain.TipoIdentificacion;
import java.util.List;

/**
 *
 * @author laynegranadosmogollon
 */
public interface TipoIdentificacionBusiness {
    
    public List<TipoIdentificacion> listALl();
    
    public TipoIdentificacion save(TipoIdentificacion tipoIdentificacion);
    
    public boolean delete(TipoIdentificacion tipoIdentificacion);
    
    public TipoIdentificacion get(int id);
    
}
