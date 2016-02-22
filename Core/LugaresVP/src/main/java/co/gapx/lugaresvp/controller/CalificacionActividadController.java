package co.gapx.lugaresvp.controller;

import co.gapx.lugaresvp.business.ActividadBusiness;
import co.gapx.lugaresvp.business.ActividadTipoLugarBusiness;
import co.gapx.lugaresvp.business.CRUDService;
import co.gapx.lugaresvp.business.CalificacionActividadBusiness;
import co.gapx.lugaresvp.business.LugarBusiness;
import co.gapx.lugaresvp.business.TipoLugarBusiness;
import co.gapx.lugaresvp.domain.ActividadTipoLugar;
import co.gapx.lugaresvp.domain.CalificacionActividad;
import co.gapx.lugaresvp.domain.Lugar;
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
public class CalificacionActividadController {
    
    @Autowired
    private CalificacionActividadBusiness calificacionActividadB;
    @Autowired
    private ActividadBusiness actividadB;
    @Autowired
    private LugarBusiness lugarB;
    @Autowired
    private TipoLugarBusiness tipoLugarB;
    @Autowired
    private ActividadTipoLugarBusiness actividadTipoLugarB;
    @Autowired
    private CRUDService crudS;
    
    @RequestMapping(value = "/calificacion-actividad", method = RequestMethod.GET)
    @Transactional
    public @ResponseBody List getList(HttpServletResponse response) {
        List<CalificacionActividad> calificacionActividads = this.calificacionActividadB.listALl();
        List l = new ArrayList();
        for (CalificacionActividad c: calificacionActividads) {
            Map<String, Object> map = new HashMap();
            map.put("id", c.getId());
            map.put("nombre", c.getNombre()); 
            this.crudS.refresh(c.getActividad());
            Map<String, Object> mapActividad = new HashMap();
            mapActividad.put("id", c.getActividad().getId());
            mapActividad.put("nombre", c.getActividad().getNombre());
            mapActividad.put("descripcion", c.getActividad().getDescripcion());
            map.put("actividad",mapActividad);
            l.add(map);
        }
        return l;
    }
    
    @RequestMapping(value = "/calificacion-actividad", method = RequestMethod.POST)
    @Transactional
    public @ResponseBody CalificacionActividad save(@RequestBody String json, HttpServletResponse response) {
        Map obj=(Map) JSONValue.parse(json);
        CalificacionActividad cv = new CalificacionActividad();
        Map actividad = (Map)((JSONObject)obj.get("actividad"));
        int idActividad = ((Long)actividad.get("id")).intValue();
        cv.setActividad(this.actividadB.get(idActividad));
        cv.setNombre((String)obj.get("nombre"));
        CalificacionActividad saved = this.calificacionActividadB.save(cv);
        return saved;
    }
    
    @RequestMapping(value = "/calificacion-actividad", method = RequestMethod.PUT)
    @Transactional
    public @ResponseBody CalificacionActividad put(@RequestBody String json, HttpServletResponse response) {
        Map obj=(Map) JSONValue.parse(json);
        int id = ((Long)obj.get("id")).intValue();
        CalificacionActividad updated =this.calificacionActividadB.get(id);
        Map actividad = (Map)((JSONObject)obj.get("actividad"));
        int idActividad = ((Long)actividad.get("id")).intValue();
        updated.setActividad(this.actividadB.get(idActividad));
        updated.setNombre((String)obj.get("nombre"));
        CalificacionActividad saved = this.calificacionActividadB.save(updated);
        return saved;
    }
    
    @RequestMapping(value = "/calificacionActividad/lugar", method = RequestMethod.POST)
    @Transactional
    public @ResponseBody List getDeLugar(@RequestBody String json, HttpServletResponse response) {
        System.out.println("json id calificacionActividad: "+json);
        List l = new ArrayList();
        List<CalificacionActividad> calificaciones;
        
        Map obj=(Map) JSONValue.parse(json);
        Lugar lu = this.lugarB.get(Integer.parseInt((String)obj.get("idlugar")));
        this.crudS.refresh(lu.getTipoLugar());
        
        List<ActividadTipoLugar> atl = this.actividadTipoLugarB.getDeTipoLugar(lu.getTipoLugar());
        for(ActividadTipoLugar a: atl){
            this.crudS.refresh(a.getActividad());
            calificaciones = this.calificacionActividadB.getDeActividad(a.getActividad());
                for(CalificacionActividad ca : calificaciones){
                    
                    Map<String, Object> map = new HashMap();
                    map.put("idcalificacion", ca.getId());
                    map.put("nombrecalificacion", ca.getNombre());
                    this.crudS.refresh(ca.getActividad());
                    map.put("idactividad", ca.getActividad().getId());
                    map.put("nombreactividad", ca.getActividad().getNombre());   
                    l.add(map);
                }
        }
        return l;
    }
    
    @RequestMapping(value = "/calificacion-actividad-delete", method = RequestMethod.POST)
    @Transactional
    public @ResponseBody Boolean delete(@RequestBody String json, HttpServletResponse response) {
        Map obj=(Map) JSONValue.parse(json);
        CalificacionActividad toDelete =this.calificacionActividadB.get(((Long)obj.get("id")).intValue()); 
        if(toDelete == null){
            return false;
        }
        try{
            boolean deleted = this.calificacionActividadB.delete(toDelete);
            return deleted;
        } catch (Exception ex){
            return false;
        }
    }
    
}
