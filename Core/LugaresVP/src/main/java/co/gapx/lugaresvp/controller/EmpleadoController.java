package co.gapx.lugaresvp.controller;

import co.gapx.lugaresvp.business.CRUDService;
import co.gapx.lugaresvp.business.EmpleadoBusiness;
import co.gapx.lugaresvp.business.LoginBusiness;
import co.gapx.lugaresvp.business.PersonaBusiness;
import co.gapx.lugaresvp.domain.Empleado;
import co.gapx.lugaresvp.domain.Login;
import co.gapx.lugaresvp.domain.Persona;
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
public class EmpleadoController {
    
    @Autowired
    private EmpleadoBusiness supervisorB;
    @Autowired
    private PersonaBusiness personaB;
    @Autowired
    private LoginBusiness loginB;
    @Autowired
    private CRUDService crudS;
    
    @RequestMapping(value = "/empleado", method = RequestMethod.GET)
    @Transactional
    public @ResponseBody List getList(HttpServletResponse response) {
        List<Empleado> supervisors = this.supervisorB.listALl();
        List l = new ArrayList();
        for (Empleado c: supervisors) {
            Map<String, Object> map = new HashMap();
            map.put("id", c.getId());
            this.crudS.refresh(c.getLogin());
            this.crudS.refresh(c.getPersona());
            map.put("login", c.getLogin().getLogin());
            this.crudS.refresh(c.getPersona().getTipoIdentificacion());
            map.put("tipoidentificacion", c.getPersona().getTipoIdentificacion().getCodigo());
            map.put("identificacion", c.getPersona().getIdentificacion());
            map.put("nombres", c.getPersona().getNombres());
            map.put("apellidos", c.getPersona().getApellidos());
            l.add(map);
        }
        return l;
    }
    
    @RequestMapping(value = "/empleado", method = RequestMethod.POST)
    @Transactional
    public @ResponseBody Empleado save(@RequestBody String json, HttpServletResponse response) {
        Map obj=(Map) JSONValue.parse(json);
        Empleado cv = new Empleado();
        Login l = this.loginB.get(((Long)obj.get("login")).intValue());
        Persona p = this.personaB.get(((Long)obj.get("persona")).intValue());
        cv.setLogin(l);
        cv.setPersona(p);
        Empleado saved = this.supervisorB.save(cv);
        return saved;
    }
    
    @RequestMapping(value = "/empleado", method = RequestMethod.PUT)
    @Transactional
    public @ResponseBody Empleado put(@RequestBody String json, HttpServletResponse response) {
        Map obj=(Map) JSONValue.parse(json);
        Empleado update =this.supervisorB.get(((Long)obj.get("id")).intValue()); 
        Login l = this.loginB.get(((Long)obj.get("login")).intValue());
        Persona p = this.personaB.get(((Long)obj.get("persona")).intValue());
        update.setLogin(l);
        update.setPersona(p);
        Empleado saved = this.supervisorB.save(update);
        return saved;
    }
    
    
    @RequestMapping(value = "/empleado/del", method = RequestMethod.POST)
    @Transactional
    public @ResponseBody Empleado delete(@RequestBody String json, HttpServletResponse response) {
        Map obj=(Map) JSONValue.parse(json);
        Empleado toDelete =this.supervisorB.get(((Long)obj.get("id")).intValue()); 
        try{
            System.out.println("json: "+json);
            boolean deleted = this.supervisorB.delete(toDelete);
            if(deleted){
                toDelete.setId(-1);
            }
            return toDelete;
        } catch (Exception ex){
            return toDelete;
        }
    }
    
}
