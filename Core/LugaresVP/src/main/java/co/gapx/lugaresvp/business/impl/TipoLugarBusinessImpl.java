package co.gapx.lugaresvp.business.impl;

import co.gapx.lugaresvp.business.TipoLugarBusiness;
import co.gapx.lugaresvp.dao.TipoLugarDAO;
import co.gapx.lugaresvp.domain.TipoLugar;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author laynegranadosmogollon
 */
@Service("tipoLugarBusiness")
public class TipoLugarBusinessImpl implements TipoLugarBusiness, Serializable{
    
    @Autowired
    private TipoLugarDAO tipoLugarDAO;

    @Override
    @Transactional
    public boolean save(TipoLugar e) {
        return this.tipoLugarDAO.save(e);
    }
    
    @Override
    @Transactional
    public List<TipoLugar> listALl() {
        return this.tipoLugarDAO.listALl();
    }

    @Override
    @Transactional
    public TipoLugar get(int id) {
        return this.tipoLugarDAO.get(id);
    }

}