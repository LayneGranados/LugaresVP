package co.gapx.lugaresvp.controller;

import co.gapx.lugaresvp.business.CRUDService;
import co.gapx.lugaresvp.business.LoginBusiness;
import co.gapx.lugaresvp.business.PersonaBusiness;
import co.gapx.lugaresvp.business.EmpleadoBusiness;
import co.gapx.lugaresvp.business.TipoEmpleadoBusiness;
import co.gapx.lugaresvp.business.TipoIdentificacionBusiness;
import co.gapx.lugaresvp.domain.Login;
import co.gapx.lugaresvp.domain.Persona;
import co.gapx.lugaresvp.domain.Empleado;
import co.gapx.lugaresvp.domain.TipoEmpleado;
import co.gapx.lugaresvp.domain.TipoIdentificacion;
import co.gapx.lugaresvp.util.Util;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
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
    private TipoIdentificacionBusiness tipoIdentificacionB;
    @Autowired
    private TipoEmpleadoBusiness tipoEmpleadoB;
    @Autowired
    private CRUDService crudS;
    
    @RequestMapping(value = "/empleado", method = RequestMethod.GET)
    @Transactional
    public @ResponseBody List getListLoginFull(HttpServletResponse response) {
        List<Empleado> empleados = this.supervisorB.listALl();
        List l = new ArrayList();
        for (int i=0;i<empleados.size();i++) {
            Empleado e = empleados.get(i);
            Map<String, Object> map = new HashMap();
            map.put("id", e.getId());
            this.crudS.refresh(e.getPersona());
            this.crudS.refresh(e.getPersona().getTipoIdentificacion());
            this.crudS.refresh(e.getLogin());
            this.crudS.refresh(e.getTipoEmpleado());
            
            Map<String, Object> mapTipoIdentificacion = new HashMap();
            if(e.getPersona().getTipoIdentificacion() != null ){
                mapTipoIdentificacion.put("id", e.getPersona().getTipoIdentificacion().getId());
                mapTipoIdentificacion.put("codigo", e.getPersona().getTipoIdentificacion().getCodigo());
                mapTipoIdentificacion.put("nombre", e.getPersona().getTipoIdentificacion().getNombre());
            }else{
                mapTipoIdentificacion.put("id", "");
                mapTipoIdentificacion.put("codigo", "");
                mapTipoIdentificacion.put("nombre", "");
            }
            map.put("tipoIdentificacion", mapTipoIdentificacion);
            
            Map<String, Object> mapPersona = new HashMap();
            if(e.getPersona() != null){
                mapPersona.put("id", e.getPersona().getId());
                mapPersona.put("identificacion", e.getPersona().getIdentificacion());
                mapPersona.put("nombres", e.getPersona().getNombres());
                mapPersona.put("apellidos", e.getPersona().getApellidos());
            }else{
                mapPersona.put("id", "");
                mapPersona.put("identificacion", "");
                mapPersona.put("nombres", "");
                mapPersona.put("apellidos", "");
            }
            map.put("persona", mapPersona);
            
            Map<String, Object> mapTipoEmpleado = new HashMap();
            if(e.getTipoEmpleado() != null){
                mapTipoEmpleado.put("id", e.getTipoEmpleado().getId());
                mapTipoEmpleado.put("nombre", e.getTipoEmpleado().getNombre());
                mapTipoEmpleado.put("descripcion", e.getTipoEmpleado().getDescripcion());
            }else{
                mapTipoEmpleado.put("id", "");
                mapTipoEmpleado.put("nombre", "");
                mapTipoEmpleado.put("descripcion", "");
            }
            map.put("tipoEmpleado", mapTipoEmpleado);
            
            Map<String, Object> mapLogin = new HashMap();
            if(e.getLogin() != null){
                mapLogin.put("login_id", e.getLogin().getId());
                mapLogin.put("login_login", e.getLogin().getLogin());
                mapLogin.put("login_password", e.getLogin().getPassword());
            }else{
                mapLogin.put("login_id", "");
                mapLogin.put("login_login", "");
                mapLogin.put("login_password", "");
            }
            map.put("login", mapLogin);
            
            l.add(map);
        }
        return l;
    }
    
    @RequestMapping(value = "/empleado", method = RequestMethod.POST)
    @Transactional
    public @ResponseBody Empleado save(@RequestBody String json, HttpServletResponse response) throws NoSuchAlgorithmException {
        Map obj=(Map) JSONValue.parse(json);
        Empleado empleado = new Empleado();
        
        Map mapLogin = (Map)((JSONObject)obj.get("login"));
        Login login = this.loginB.get(((Long)mapLogin.get("id")).intValue());
        login.setLogin((String)mapLogin.get("login"));
        login.setPassword(Util.encriptar((String)mapLogin.get("password")));
        
        Map mapPersona = (Map)((JSONObject)obj.get("persona"));
        Persona persona = this.personaB.get(((Long)mapPersona.get("id")).intValue());
        persona.setNombres((String)mapPersona.get("nombres"));
        persona.setApellidos((String)mapPersona.get("apellidos"));
        persona.setIdentificacion((String)mapPersona.get("identificacion"));
        
        Map mapTipoIdentificacion = (Map)((JSONObject)obj.get("tipoIdentificacion"));
        TipoIdentificacion tipoIdentificacion = this.tipoIdentificacionB.get(((Long)mapTipoIdentificacion.get("id")).intValue());
        
        Map mapTipEmpleado = (Map)((JSONObject)obj.get("tipoEmpleado"));
        TipoEmpleado tipoEmpleado = this.tipoEmpleadoB.get(((Long)mapTipEmpleado.get("id")).intValue());
    
        empleado.setLogin(login);
        persona.setTipoIdentificacion(tipoIdentificacion);
        empleado.setPersona(persona);
        empleado.setTipoEmpleado(tipoEmpleado);
        Empleado saved = this.supervisorB.save(empleado);
        return saved;
    }
    
    @RequestMapping(value = "/empleado", method = RequestMethod.PUT)
    @Transactional
    public @ResponseBody Empleado put(@RequestBody String json, HttpServletResponse response) throws NoSuchAlgorithmException {
        Map obj=(Map) JSONValue.parse(json);
        Empleado updated =this.supervisorB.get(((Long)obj.get("id")).intValue()); 
        
        Map mapLogin = (Map)((JSONObject)obj.get("login"));
        Login login = this.loginB.get(((Long)mapLogin.get("id")).intValue());
        login.setLogin((String)mapLogin.get("login"));
        login.setPassword(Util.encriptar((String)mapLogin.get("password")));
        
        Map mapPersona = (Map)((JSONObject)obj.get("persona"));
        Persona persona = this.personaB.get(((Long)mapPersona.get("id")).intValue());
        persona.setNombres((String)mapPersona.get("nombres"));
        persona.setApellidos((String)mapPersona.get("apellidos"));
        persona.setIdentificacion((String)mapPersona.get("identificacion"));
        
        Map mapTipoIdentificacion = (Map)((JSONObject)obj.get("tipoIdentificacion"));
        TipoIdentificacion tipoIdentificacion = this.tipoIdentificacionB.get(((Long)mapTipoIdentificacion.get("id")).intValue());
        
        Map mapTipEmpleado = (Map)((JSONObject)obj.get("tipoEmpleado"));
        TipoEmpleado tipoEmpleado = this.tipoEmpleadoB.get(((Long)mapTipEmpleado.get("id")).intValue());
        
        updated.setLogin(login);
        persona.setTipoIdentificacion(tipoIdentificacion);
        updated.setPersona(persona);
        updated.setTipoEmpleado(tipoEmpleado);
        Empleado saved = this.supervisorB.save(updated);
        return saved;
    }
    
    
    @RequestMapping(value = "/empleado-delete", method = RequestMethod.POST)
    @Transactional
    public @ResponseBody Boolean delete(@RequestBody String json, HttpServletResponse response) {
        Map obj=(Map) JSONValue.parse(json);
        Empleado toDelete =this.supervisorB.get(((Long)obj.get("id")).intValue()); 
        if(toDelete == null){
            return false;
        }
        try{
            boolean deleted = this.supervisorB.delete(toDelete);
            return deleted;
        } catch (Exception ex){
            return false;
        }
    }
    
}
