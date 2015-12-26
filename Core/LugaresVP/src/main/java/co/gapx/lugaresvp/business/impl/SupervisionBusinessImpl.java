package co.gapx.lugaresvp.business.impl;

import co.gapx.lugaresvp.business.SupervisionBusiness;
import co.gapx.lugaresvp.dao.SupervisionDAO;
import co.gapx.lugaresvp.domain.Lugar;
import co.gapx.lugaresvp.domain.Supervision;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author laynegranadosmogollon
 */
@Service("supervisionBusiness")
public class SupervisionBusinessImpl implements SupervisionBusiness, Serializable{
    
    @Autowired
    private SupervisionDAO supervisionDAO;

    @Override
    @Transactional
    public Supervision save(Supervision e) {
        return this.supervisionDAO.save(e);
    }
    
    @Override
    @Transactional
    public List<Supervision> listALl() {
        return this.supervisionDAO.listALl();
    }

    @Override
    @Transactional
    public Supervision get(int id) {
        return this.supervisionDAO.get(id);
    }
    
    @Override
    @Transactional
    public List<Supervision> getDeLugar(Lugar id) {
        return this.supervisionDAO.getDeLugar(id);
    }
    
    @Override
    @Transactional
    public Supervision saveWithGet(Supervision supervision){
        return this.supervisionDAO.saveWithGet(supervision);
    }
    
    @Override
    @Transactional
    public boolean delete(Supervision supervision) {
        return this.supervisionDAO.delete(supervision);
    }
    
}