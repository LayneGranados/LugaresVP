package co.gapx.lugaresvp.business.impl;

import co.gapx.lugaresvp.business.TipoEmpleadoBusiness;
import co.gapx.lugaresvp.dao.TipoEmpleadoDAO;
import co.gapx.lugaresvp.domain.TipoEmpleado;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author laynegranadosmogollon
 */
@Service("tipoEmpleadoBusiness")
public class TipoEmpleadoBusinessImpl implements TipoEmpleadoBusiness, Serializable{
    
    @Autowired
    private TipoEmpleadoDAO tipoLugarDAO;

    @Override
    @Transactional
    public TipoEmpleado save(TipoEmpleado e) {
        return this.tipoLugarDAO.save(e);
    }
    
    @Override
    @Transactional
    public boolean delete(TipoEmpleado e) {
        return this.tipoLugarDAO.delete(e);
    }
    
    @Override
    @Transactional
    public List<TipoEmpleado> listALl() {
        return this.tipoLugarDAO.listALl();
    }

    @Override
    @Transactional
    public TipoEmpleado get(int id) {
        return this.tipoLugarDAO.get(id);
    }

}