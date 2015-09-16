package co.gapx.lugaresvp.controller;

import co.gapx.lugaresvp.business.CRUDService;
import co.gapx.lugaresvp.business.EmpleadoBusiness;
import co.gapx.lugaresvp.business.LoginBusiness;
import co.gapx.lugaresvp.business.PersonaBusiness;
import co.gapx.lugaresvp.business.TipoEmpleadoBusiness;
import co.gapx.lugaresvp.business.TipoIdentificacionBusiness;
import co.gapx.lugaresvp.domain.Empleado;
import co.gapx.lugaresvp.domain.Login;
import co.gapx.lugaresvp.domain.Persona;
import co.gapx.lugaresvp.domain.TipoEmpleado;
import co.gapx.lugaresvp.domain.TipoIdentificacion;
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
public class PersonaController {
    
    @Autowired
    private PersonaBusiness personaB;
    @Autowired
    private TipoIdentificacionBusiness tipoIdentificacionB;
    @Autowired
    private TipoEmpleadoBusiness tipoEmpleadoB;
    @Autowired
    private EmpleadoBusiness empleadoB;
    @Autowired
    private LoginBusiness loginB;
    @Autowired
    private CRUDService crudS;
    
    @RequestMapping(value = "/persona", method = RequestMethod.GET)
    @Transactional
    public @ResponseBody List getList(HttpServletResponse response) {
        List<Persona> personas = this.personaB.listALl();
        List l = new ArrayList();
        for (Persona c: personas) {
            Map<String, Object> map = new HashMap();
            map.put("id", c.getId());
            this.crudS.refresh(c.getTipoIdentificacion());
            map.put("tipoidentificacion", c.getTipoIdentificacion().getCodigo());
            map.put("identificacion", c.getIdentificacion());
            map.put("nombres", c.getNombres());
            map.put("apellidos", c.getApellidos());           
            l.add(map);
        }
        return l;
    }
    
    @RequestMapping(value = "/persona", method = RequestMethod.POST)
    @Transactional
    public @ResponseBody boolean save(@RequestBody String json, HttpServletResponse response) {
        System.out.println("json Persona: "+json);
        Map obj=(Map) JSONValue.parse(json);
        Persona persona = new Persona();
        TipoIdentificacion t = tipoIdentificacionB.get(((Long)obj.get("tipoidentificacion")).intValue());
        TipoEmpleado tipoEmpleado = this.tipoEmpleadoB.get(((Long)obj.get("tipoempleado")).intValue());
        persona.setTipoIdentificacion(t);
        persona.setApellidos((String)obj.get("apellidos"));
        persona.setNombres((String)obj.get("nombres"));
        persona.setIdentificacion((String)obj.get("identificacion"));
        Persona personaSaved = this.personaB.save(persona);
        Login login = new Login();
        
        String password = (String)obj.get("password");
        Login loginSaved = null;
        String passwordrep = (String)obj.get("passwordrep");
        if(password.equals(passwordrep)){
            login.setLogin((String)obj.get("usuario"));
            login.setPassword(password);
            loginSaved = this.loginB.save(login);
        }
        Empleado empleado = new Empleado();
        empleado.setLogin(loginSaved);
        empleado.setPersona(personaSaved);
        empleado.setTipoEmpleado(tipoEmpleado);
        boolean empleadoSaved = this.empleadoB.save(empleado);
        return empleadoSaved;
    }
    
    @RequestMapping(value = "/persona", method = RequestMethod.PUT)
    @Transactional
    public @ResponseBody int put(@RequestBody String json, HttpServletResponse response) {
        Map obj=(Map) JSONValue.parse(json);
        Persona update =this.personaB.get(((Long)obj.get("id")).intValue()); 
        TipoIdentificacion t = tipoIdentificacionB.get(((Long)obj.get("tipoidentificacion")).intValue());
        update.setTipoIdentificacion(t);
        update.setApellidos((String)obj.get("apellidos"));
        update.setNombres((String)obj.get("nombres"));
        update.setIdentificacion((String)obj.get("identificacion"));
        Persona saved = this.personaB.save(update);
        return saved.getId();
    }
    
}
