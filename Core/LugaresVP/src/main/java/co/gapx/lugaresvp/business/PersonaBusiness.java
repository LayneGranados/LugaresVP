package co.gapx.lugaresvp.business;

import co.gapx.lugaresvp.domain.Persona;
import java.util.List;

/**
 *
 * @author laynegranadosmogollon
 */
public interface PersonaBusiness {
    
    public List<Persona> listALl();
    
    public Persona save(Persona persona);
    
    public Persona get(int id);
    
}
