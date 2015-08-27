package co.gapx.lugaresvp.controller;

import co.gapx.lugaresvp.business.ActividadBusiness;
import co.gapx.lugaresvp.business.ActividadTipoLugarBusiness;
import co.gapx.lugaresvp.business.CRUDService;
import co.gapx.lugaresvp.business.CalificacionActividadBusiness;
import co.gapx.lugaresvp.business.EmpleadoBusiness;
import co.gapx.lugaresvp.business.EvaluacionBusiness;
import co.gapx.lugaresvp.business.LoginBusiness;
import co.gapx.lugaresvp.business.LugarBusiness;
import co.gapx.lugaresvp.business.SupervisionBusiness;
import co.gapx.lugaresvp.business.TipoLugarBusiness;
import co.gapx.lugaresvp.domain.Actividad;
import co.gapx.lugaresvp.domain.ActividadTipoLugar;
import co.gapx.lugaresvp.domain.CalificacionActividad;
import co.gapx.lugaresvp.domain.Empleado;
import co.gapx.lugaresvp.domain.Evaluacion;
import co.gapx.lugaresvp.domain.Login;
import co.gapx.lugaresvp.domain.Lugar;
import co.gapx.lugaresvp.domain.Supervision;
import java.lang.ProcessBuilder.Redirect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.gson.*;
import java.util.Date;

/**
 *
 * @author laynegranadosmogollon
 */

@Controller
@RequestMapping("/rest")
public class EvaluacionController {
    
    @Autowired
    private EvaluacionBusiness evaluacionB;
    @Autowired
    private SupervisionBusiness supervisionB;
    @Autowired
    private ActividadTipoLugarBusiness actividadTipoLugarB;
    @Autowired
    private TipoLugarBusiness tipoLugarB;
    @Autowired
    private LugarBusiness lugarB;
    @Autowired
    private LoginBusiness loginB;
    @Autowired
    private ActividadBusiness actividadB;
    @Autowired
    private EmpleadoBusiness empleadoB;
    @Autowired
    private CalificacionActividadBusiness calificacionActividadB;
    @Autowired
    private CRUDService crudS;
    
    @RequestMapping(value = "/evaluacion", method = RequestMethod.GET)
    @Transactional
    public @ResponseBody List getList(HttpServletResponse response) {
        List<Evaluacion> evaluacions = this.evaluacionB.listALl();
        List l = new ArrayList();
        for (Evaluacion c: evaluacions) {
            Map<String, Object> map = new HashMap();
            map.put("id", c.getId());
            this.crudS.refresh(map);
            map.put("supervisor", c.getSupervision().getEmpleado().getPersona().getNombres() + " " + c.getSupervision().getEmpleado().getPersona().getApellidos());
            map.put("lugar", c.getSupervision().getLugar().getNombre());
            map.put("tipolugar", c.getSupervision().getLugar().getTipoLugar().getNombre());
            map.put("actividadtipolugar", c.getId());
            map.put("calificacionactividad", c.getId());
            map.put("fecha", c.getId());
        // aqui poner en el map todos los parametros que se quieran devolver en el metodo            
            l.add(map);
        }
        return l;
    }
    
    @RequestMapping(value = "/evaluacion", method = RequestMethod.POST)
    @Transactional
    public @ResponseBody boolean save(@RequestBody String json, HttpServletResponse response) {
        System.out.println("json: "+json);
        
        Map obj=(Map) JSONValue.parse(json);
        Evaluacion eva = new Evaluacion();
        String idlugar = (String)obj.get("idlugar");
        
        Lugar lugar = this.lugarB.get(Integer.parseInt(idlugar)) ;
        this.crudS.refresh(lugar.getTipoLugar());
        String usuario = (String)obj.get("usuario");
        Login login = this.loginB.login(usuario);
        
        List<Empleado> empleados =  this.empleadoB.getForLogin(login);
        Empleado em = new Empleado();
        for(Empleado e: empleados){
            this.crudS.refresh(e.getTipoEmpleado());
            if(e.getTipoEmpleado().getNombre().equalsIgnoreCase("supervisor")){
                em = e;
            }
        }
        
        Supervision supervision = new Supervision();
        supervision.setFecha(new Date());
        supervision.setLugar(lugar);
        supervision.setEmpleado(em);
        
        Supervision sup = this.supervisionB.saveWithGet(supervision);
        eva.setSupervision(sup);
        
        String cadenaEvaluacion = (String)obj.get("evaluacion");
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(cadenaEvaluacion);
        JsonArray jasonArray = element.getAsJsonArray();
        
        for(Object j : jasonArray){
            Map ca = (Map) JSONValue.parse(String.valueOf(j));
            Actividad acti = this.actividadB.get(Integer.parseInt((String)ca.get("idactividad")));
            ActividadTipoLugar atl = this.actividadTipoLugarB.getDeTipoLugarActividad(lugar.getTipoLugar(), acti);
            eva.setActividadTipoLugar(atl);
            
            List<CalificacionActividad> calificaciones = this.calificacionActividadB.getDeActividad(acti);
            for(CalificacionActividad cali: calificaciones){
                if(cali.getNombre().equalsIgnoreCase((String)ca.get("nombrecalificacion"))){
                    eva.setCalificacionActividad(cali);
                }
            }
        }
        boolean saved = this.evaluacionB.save(eva);
        return saved;
    }
    
    @RequestMapping(value = "/evaluacion", method = RequestMethod.PUT)
    @Transactional
    public @ResponseBody boolean put(@RequestBody String json, HttpServletResponse response) {
        Map obj=(Map) JSONValue.parse(json);
        int id = Integer.parseInt((String)obj.get("id"));
        Evaluacion update =this.evaluacionB.get(id); 
       //Aqui se deben sacar del json los parametros y seterarlos en el objeto update 
        boolean saved = this.evaluacionB.save(update);
        return saved;
    }
    
}
