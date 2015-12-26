package co.gapx.lugaresvp.business.impl;

import co.gapx.lugaresvp.business.PersonaBusiness;
import co.gapx.lugaresvp.dao.PersonaDAO;
import co.gapx.lugaresvp.domain.Persona;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author laynegranadosmogollon
 */
@Service("personaBusiness")
public class PersonaBusinessImpl implements PersonaBusiness, Serializable{
    
    @Autowired
    private PersonaDAO personaDAO;

    @Override
    @Transactional
    public Persona save(Persona e) {
        return this.personaDAO.save(e);
    }
    
    @Override
    @Transactional
    public List<Persona> listALl() {
        return this.personaDAO.listALl();
    }

    @Override
    @Transactional
    public Persona get(int id) {
        return this.personaDAO.get(id);
    }
    
    @Override
    @Transactional
    public boolean delete(Persona e) {
        return this.personaDAO.delete(e);
    }

}