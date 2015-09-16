package co.gapx.lugaresvp.business.impl;

import co.gapx.lugaresvp.business.LoginBusiness;
import co.gapx.lugaresvp.dao.LoginDAO;
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
@Service("loginBusiness")
public class LoginBusinessImpl implements LoginBusiness, Serializable{
    
    @Autowired
    private LoginDAO loginDAO;

    @Override
    @Transactional
    public Login save(Login e) {
        return this.loginDAO.save(e);
    }
    
    @Override
    @Transactional
    public List<Login> listALl() {
        return this.loginDAO.listALl();
    }

    @Override
    @Transactional
    public Login get(int id) {
        return this.loginDAO.get(id);
    }

    @Override
    @Transactional
    public Boolean login(String login, String password) {
        return this.loginDAO.login(login, password);
    }
    
    @Override
    @Transactional
    public Login login(String login){
        return this.loginDAO.login(login);
    }

}