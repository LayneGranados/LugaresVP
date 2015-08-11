package co.gapx.lugaresvp.business;

import co.gapx.lugaresvp.domain.Login;
import java.util.List;

/**
 *
 * @author laynegranadosmogollon
 */
public interface LoginBusiness {
    
    public List<Login> listALl();
    
    public boolean save(Login login);
    
    public Login get(int id);

    public Boolean login(String login, String password);
    
}