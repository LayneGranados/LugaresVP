package co.gapx.lugaresvp.dao.impl;

import co.gapx.lugaresvp.dao.LoginDAO;
import co.gapx.lugaresvp.dao.HibernateUtil;
import co.gapx.lugaresvp.domain.Login;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author laynegranadosmogollon
 */
@Repository
public class LoginDAOImpl implements LoginDAO, Serializable{
    
    @Autowired
    SessionFactory sessionFactory;
    @Autowired
    private HibernateUtil hibernateUtil;
    private Log logger = LogFactory.getLog(this.getClass());
    
    
    protected final Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
    
    @Override
    @Transactional
    public boolean save(Login login) {
        boolean x=false;
        try {
            login.setPassword(this.encriptar(login.getPassword()));
            this.getCurrentSession().saveOrUpdate(login);
            if(login.getId()!=null){
                x = true;
            }
        } catch (HibernateException ex) {
            this.logger.error("Error Guardando Login");
            this.logger.error("Mensaje: "+ ex.getMessage());
            throw ex;
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(LoginDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return x;
    }

    @Override
    @Transactional
    public List<Login> listALl() {
        List lista = getCurrentSession().createQuery("from Login p").list();
        this.evictUnProxy(lista);
        return lista;
    }

    @Override
    @Transactional
    public Login get(int id) {
        Login login= (Login) getCurrentSession().createQuery("from Login e where e.id= :id").setParameter("id", id).list().get(0);
        this.evictUnProxy(login);
        return login;
    }
    
    @Override
    @Transactional
    public Boolean login(String login, String password) {
        Login log= (Login) getCurrentSession().createQuery("from Login e where e.login= :login and e.password= :password").setParameter("login", login).setParameter("password", password).list().get(0);
        if(log!=null){
            return true;
        }
        else
            return false;
    }
    
    private void evictUnProxy(List lista) {
        for (int i = 0; i < lista.size(); getCurrentSession().evict(lista.get(i)), ++i);
        for (int i = 0; i < lista.size(); hibernateUtil.initializeAndUnproxy(lista.get(i)), ++i);
    }
    
    private void evictUnProxy(Object obj) {
        getCurrentSession().evict(obj);
        hibernateUtil.initializeAndUnproxy(obj);
    }    
    
    private String encriptar(String password) throws NoSuchAlgorithmException{

    MessageDigest md = MessageDigest.getInstance("MD5");
    md.update(password.getBytes());

    byte byteData[] = md.digest();

    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < byteData.length; i++)
        sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));

        return sb.toString();
    }
}