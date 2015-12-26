package co.gapx.lugaresvp.dao;

import co.gapx.lugaresvp.domain.Evaluacion;
import java.util.List;

/**
 *
 * @author laynegranadosmogollon
 */
public interface EvaluacionDAO {
    
    public List<Evaluacion> listALl();
    
    public Evaluacion save(Evaluacion evaluacion);
    
    public Evaluacion get(int id);
    
    public boolean delete(Evaluacion evaluacion);
    
}
