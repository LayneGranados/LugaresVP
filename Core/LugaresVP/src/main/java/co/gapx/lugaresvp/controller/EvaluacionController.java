package co.gapx.lugaresvp.controller;

import co.gapx.lugaresvp.business.ActividadBusiness;
import co.gapx.lugaresvp.business.ActividadTipoLugarBusiness;
import co.gapx.lugaresvp.business.CRUDService;
import co.gapx.lugaresvp.business.CalificacionActividadBusiness;
import co.gapx.lugaresvp.business.EvaluacionBusiness;
import co.gapx.lugaresvp.business.SupervisionBusiness;
import co.gapx.lugaresvp.domain.Evaluacion;
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
public class EvaluacionController {
    
    @Autowired
    private EvaluacionBusiness evaluacionB;
    @Autowired
    private SupervisionBusiness supervisionB;
    @Autowired
    private ActividadTipoLugarBusiness actividadTipoLugarB;
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
            map.put("supervisor", c.getSupervision().getSupervisor().getPersona().getNombres() + " " + c.getSupervision().getSupervisor().getPersona().getApellidos());
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
        Evaluacion cv = new Evaluacion();
        String idlugar = (String)obj.get("idlugar");
        String usuario = (String)obj.get("usuario");
        ArrayList<Object> objs = (ArrayList<Object>)obj.get("evaluacion");
        for(Object o : objs){
            System.out.println("o: "+o);
        }
     // aqui sacar los parametros del json y setearlo en el nuevo objeto
        boolean saved = this.evaluacionB.save(cv);
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
