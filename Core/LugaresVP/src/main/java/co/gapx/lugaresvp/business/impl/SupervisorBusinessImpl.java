package co.gapx.lugaresvp.business.impl;

import co.gapx.lugaresvp.business.SupervisorBusiness;
import co.gapx.lugaresvp.dao.SupervisorDAO;
import co.gapx.lugaresvp.domain.Supervisor;
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
public class SupervisorBusinessImpl implements SupervisorBusiness, Serializable{
    
    @Autowired
    private SupervisorDAO supervisorDAO;

    @Override
    @Transactional
    public boolean save(Supervisor e) {
        return this.supervisorDAO.save(e);
    }
    
    @Override
    @Transactional
    public List<Supervisor> listALl() {
        return this.supervisorDAO.listALl();
    }

    @Override
    @Transactional
    public Supervisor get(int id) {
        return this.supervisorDAO.get(id);
    }

}