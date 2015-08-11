package co.gapx.lugaresvp.dao;

import co.gapx.lugaresvp.domain.Persona;
import java.util.List;

/**
 *
 * @author laynegranadosmogollon
 */
public interface PersonaDAO {
    
    public List<Persona> listALl();
    
    public boolean save(Persona persona);
    
    public Persona get(int id);
    
}
