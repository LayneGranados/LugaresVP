package co.gapx.lugaresvp.controller;

import co.gapx.lugaresvp.business.ActividadBusiness;
import co.gapx.lugaresvp.business.CRUDService;
import co.gapx.lugaresvp.domain.Actividad;
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
public class ActividadController {
    
    @Autowired
    private ActividadBusiness actividadB;
    @Autowired
    private CRUDService crudS;
    
    @RequestMapping(value = "/actividad", method = RequestMethod.GET)
    @Transactional
    public @ResponseBody List getList(HttpServletResponse response) {
        List<Actividad> actividads = this.actividadB.listALl();
        List l = new ArrayList();
        for (Actividad c: actividads) {
            Map<String, Object> map = new HashMap();
            map.put("id", c.getId());
            map.put("nombre", c.getNombre());
            map.put("descripcion", c.getDescripcion());          
            l.add(map);
        }
        return l;
    }
    
    @RequestMapping(value = "/actividad", method = RequestMethod.POST)
    @Transactional
    public @ResponseBody Actividad save(@RequestBody String json, HttpServletResponse response) {
        Map obj=(Map) JSONValue.parse(json);
        Actividad cv = new Actividad();
        cv.setNombre((String)obj.get("nombre"));
        cv.setDescripcion((String)obj.get("descripcion"));
        Actividad saved = this.actividadB.save(cv);
        return saved;
    }
    
    @RequestMapping(value = "/actividad", method = RequestMethod.PUT)
    @Transactional
    public @ResponseBody Actividad put(@RequestBody String json, HttpServletResponse response) {
        Map obj=(Map) JSONValue.parse(json);
        Actividad update =this.actividadB.get(((Long)obj.get("id")).intValue()); 
        update.setNombre((String)obj.get("nombre"));
        update.setDescripcion((String)obj.get("descripcion"));
        Actividad saved = this.actividadB.save(update);
        return saved;
    }
    
    @RequestMapping(value = "/actividad/del", method = RequestMethod.POST)
    @Transactional
    public @ResponseBody Actividad delete(@RequestBody String json, HttpServletResponse response) {
        Map obj=(Map) JSONValue.parse(json);
        Actividad toDelete =this.actividadB.get(((Long)obj.get("id")).intValue()); 
        try{
            System.out.println("json: "+json);
            boolean deleted = this.actividadB.delete(toDelete);
            if(deleted){
                toDelete.setId(-1);
            }
            return toDelete;
        } catch (Exception ex){
            return toDelete;
        }
    }
    
}
