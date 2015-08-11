package co.gapx.lugaresvp.controller;

import co.gapx.lugaresvp.business.CRUDService;
import co.gapx.lugaresvp.business.LoginBusiness;
import co.gapx.lugaresvp.domain.Login;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author laynegranadosmogollon
 */

@Controller
@RequestMapping("/rest")
public class LoginController {
    
    @Autowired
    private LoginBusiness loginB;
    @Autowired
    private CRUDService crudS;
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @Transactional
    public @ResponseBody List getList(HttpServletResponse response) {
        List<Login> logins = this.loginB.listALl();
        List l = new ArrayList();
        for (Login c: logins) {
            Map<String, Object> map = new HashMap();
            map.put("id", c.getId());
            map.put("login", c.getLogin());
            map.put("password", c.getPassword());
            l.add(map);
        }
        return l;
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @Transactional
    public @ResponseBody boolean save(@RequestBody String json, HttpServletResponse response) {
        Map obj=(Map) JSONValue.parse(json);
        Login cv = new Login();
        cv.setLogin((String)obj.get("login"));
        cv.setPassword((String)obj.get("password"));
        boolean saved = this.loginB.save(cv);
        return saved;
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.PUT)
    @Transactional
    public @ResponseBody boolean put(@RequestBody String json, HttpServletResponse response) {
        Map obj=(Map) JSONValue.parse(json);
        Login update =this.loginB.get(((Long)obj.get("id")).intValue()); 
        update.setLogin((String)obj.get("login"));
        update.setPassword((String)obj.get("password"));
        boolean saved = this.loginB.save(update);
        return saved;
    }
    
    @RequestMapping(value = "/login/inicio", method = RequestMethod.POST)
    @Transactional
    public @ResponseBody Boolean login(@RequestBody String json, HttpServletResponse response) {
        System.out.println("json user: "+json);
        Map obj=(Map) JSONValue.parse(json);
        String login = (String)obj.get("login");
        String password = (String)obj.get("password");
        String role = (String)obj.get("rol");
        Boolean log =this.loginB.login(login, password); 
        return log;
    }
    
    
    
}
