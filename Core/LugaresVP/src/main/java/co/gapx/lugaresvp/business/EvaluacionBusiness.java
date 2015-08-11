package co.gapx.lugaresvp.business;

import co.gapx.lugaresvp.domain.Evaluacion;
import java.util.List;

/**
 *
 * @author laynegranadosmogollon
 */
public interface EvaluacionBusiness {
    
    public List<Evaluacion> listALl();
    
    public boolean save(Evaluacion evaluacion);
    
    public Evaluacion get(int id);
    
}
