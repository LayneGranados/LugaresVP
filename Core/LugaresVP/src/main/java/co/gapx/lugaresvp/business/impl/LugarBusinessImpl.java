package co.gapx.lugaresvp.business.impl;

import co.gapx.lugaresvp.business.LugarBusiness;
import co.gapx.lugaresvp.dao.LugarDAO;
import co.gapx.lugaresvp.domain.Lugar;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author laynegranadosmogollon
 */
@Service("lugarBusiness")
public class LugarBusinessImpl implements LugarBusiness, Serializable{
    
    @Autowired
    private LugarDAO lugarDAO;

    @Override
    @Transactional
    public boolean save(Lugar e) {
        return this.lugarDAO.save(e);
    }
    
    @Override
    @Transactional
    public List<Lugar> listALl() {
        return this.lugarDAO.listALl();
    }

    @Override
    @Transactional
    public Lugar get(int id) {
        return this.lugarDAO.get(id);
    }

}