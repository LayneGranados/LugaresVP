package co.gapx.lugaresvp.business.impl;

import co.gapx.lugaresvp.business.EmpleadoBusiness;
import co.gapx.lugaresvp.dao.EmpleadoDAO;
import co.gapx.lugaresvp.domain.Empleado;
import co.gapx.lugaresvp.domain.Login;
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
    private EmpleadoDAO empleadoDAO;

    @Override
    @Transactional
    public Empleado save(Empleado e) {
        return this.empleadoDAO.save(e);
    }
    
    @Override
    @Transactional
    public List<Empleado> listALl() {
        return this.empleadoDAO.listALl();
    }

    @Override
    @Transactional
    public Empleado get(int id) {
        return this.empleadoDAO.get(id);
    }
    
    @Override
    @Transactional
    public List<Empleado> getForLogin(Login login){
        return this.empleadoDAO.getForLogin(login);
    }
    
    @Override
    @Transactional
    public boolean delete(Empleado e) {
        return this.empleadoDAO.delete(e);
    }

}