package co.gapx.lugaresvp.business.impl;

import co.gapx.lugaresvp.business.EmpleadoBusiness;
import co.gapx.lugaresvp.dao.SupervisorDAO;
import co.gapx.lugaresvp.domain.Empleado;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author laynegranadosmogollon
 */
@Service("supervisorBusiness")
public class EmpleadoBusinessImpl implements EmpleadoBusiness, Serializable{
    
    @Autowired
    private SupervisorDAO supervisorDAO;

    @Override
    @Transactional
    public boolean save(Empleado e) {
        return this.supervisorDAO.save(e);
    }
    
    @Override
    @Transactional
    public List<Empleado> listALl() {
        return this.supervisorDAO.listALl();
    }

    @Override
    @Transactional
    public Empleado get(int id) {
        return this.supervisorDAO.get(id);
    }

}