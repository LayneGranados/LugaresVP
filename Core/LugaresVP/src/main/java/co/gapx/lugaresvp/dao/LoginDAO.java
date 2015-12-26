package co.gapx.lugaresvp.dao;

import co.gapx.lugaresvp.domain.Login;
import java.util.List;

/**
 *
 * @author laynegranadosmogollon
 */
public interface LoginDAO {
    
    public List<Login> listALl();
    
    public Login save(Login login);
    
    public Login get(int id);
    
    public Boolean login(String login, String password);
    
    public Login login(String login);
    
    public boolean delete(Login login);
    
}
