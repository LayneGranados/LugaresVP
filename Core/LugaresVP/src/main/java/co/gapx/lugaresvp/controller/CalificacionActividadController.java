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
    
    @RequestMapping(value = "/calificacionActividad", method = RequestMethod.GET)
    @Transactional
    public @ResponseBody List getList(HttpServletResponse response) {
        List<CalificacionActividad> calificacionActividads = this.calificacionActividadB.listALl();
        List l = new ArrayList();
        for (CalificacionActividad c: calificacionActividads) {
            Map<String, Object> map = new HashMap();
            map.put("id", c.getId());
            this.crudS.refresh(c.getActividad());
            map.put("nombre", c.getNombre());
            map.put("actividad", c.getActividad().getNombre());
            l.add(map);
        }
        return l;
    }
    
    @RequestMapping(value = "/calificacionActividad", method = RequestMethod.POST)
    @Transactional
    public @ResponseBody boolean save(@RequestBody String json, HttpServletResponse response) {
        Map obj=(Map) JSONValue.parse(json);
        CalificacionActividad cv = new CalificacionActividad();
        int idActividad = ((Long)obj.get("actividad")).intValue();
        cv.setActividad(this.actividadB.get(idActividad));
        cv.setNombre((String)obj.get("nombre"));
        boolean saved = this.calificacionActividadB.save(cv);
        return saved;
    }
    
    @RequestMapping(value = "/calificacionActividad", method = RequestMethod.PUT)
    @Transactional
    public @ResponseBody boolean put(@RequestBody String json, HttpServletResponse response) {
        Map obj=(Map) JSONValue.parse(json);
        int id = Integer.parseInt((String)obj.get("id"));
        CalificacionActividad update =this.calificacionActividadB.get(id); 
        int idActividad = ((Long)obj.get("actividad")).intValue();
        update.setActividad(this.actividadB.get(idActividad));
        update.setNombre((String)obj.get("nombre"));
        boolean saved = this.calificacionActividadB.save(update);
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
    
}
