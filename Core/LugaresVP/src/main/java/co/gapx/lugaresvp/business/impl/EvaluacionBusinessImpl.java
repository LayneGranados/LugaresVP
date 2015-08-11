package co.gapx.lugaresvp.business.impl;

import co.gapx.lugaresvp.business.EvaluacionBusiness;
import co.gapx.lugaresvp.dao.EvaluacionDAO;
import co.gapx.lugaresvp.domain.Evaluacion;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author laynegranadosmogollon
 */
@Service("evaluacionBusiness")
public class EvaluacionBusinessImpl implements EvaluacionBusiness, Serializable{
    
    @Autowired
    private EvaluacionDAO evaluacionDAO;

    @Override
    @Transactional
    public boolean save(Evaluacion e) {
        return this.evaluacionDAO.save(e);
    }
    
    @Override
    @Transactional
    public List<Evaluacion> listALl() {
        return this.evaluacionDAO.listALl();
    }

    @Override
    @Transactional
    public Evaluacion get(int id) {
        return this.evaluacionDAO.get(id);
    }

}