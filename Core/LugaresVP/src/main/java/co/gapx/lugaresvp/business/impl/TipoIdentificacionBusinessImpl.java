package co.gapx.lugaresvp.business.impl;

import co.gapx.lugaresvp.business.TipoIdentificacionBusiness;
import co.gapx.lugaresvp.dao.TipoIdentificacionDAO;
import co.gapx.lugaresvp.domain.TipoIdentificacion;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author laynegranadosmogollon
 */
@Service("tipoIdentificacionBusiness")
public class TipoIdentificacionBusinessImpl implements TipoIdentificacionBusiness, Serializable{
    
    @Autowired
    private TipoIdentificacionDAO tipoIdentificacionDAO;

    @Override
    @Transactional
    public boolean save(TipoIdentificacion e) {
        return this.tipoIdentificacionDAO.save(e);
    }
    
    @Override
    @Transactional
    public List<TipoIdentificacion> listALl() {
        return this.tipoIdentificacionDAO.listALl();
    }

    @Override
    @Transactional
    public TipoIdentificacion get(int id) {
        return this.tipoIdentificacionDAO.get(id);
    }

}