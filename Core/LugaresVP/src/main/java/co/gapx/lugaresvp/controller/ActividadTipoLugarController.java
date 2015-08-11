package co.gapx.lugaresvp.controller;

import co.gapx.lugaresvp.business.ActividadBusiness;
import co.gapx.lugaresvp.business.CRUDService;
import co.gapx.lugaresvp.business.ActividadTipoLugarBusiness;
import co.gapx.lugaresvp.business.TipoLugarBusiness;
import co.gapx.lugaresvp.domain.ActividadTipoLugar;
import co.gapx.lugaresvp.domain.TipoLugar;
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
public class ActividadTipoLugarController {
    
    @Autowired
    private ActividadTipoLugarBusiness actividadTipoLugarB;
    @Autowired
    private TipoLugarBusiness tipoLugarB;
    @Autowired
    private ActividadBusiness actividadB;
    @Autowired
    private CRUDService crudS;
    
    @RequestMapping(value = "/actividadTipoLugar", method = RequestMethod.GET)
    @Transactional
    public @ResponseBody List getList(HttpServletResponse response) {
        List<ActividadTipoLugar> actividadTipoLugars = this.actividadTipoLugarB.listALl();
        List l = new ArrayList();
        for (ActividadTipoLugar c: actividadTipoLugars) {
            Map<String, Object> map = new HashMap();
            map.put("id", c.getId());
            this.crudS.refresh(c.getActividad());
            this.crudS.refresh(c.getTipoLugar());
            map.put("actividad", c.getActividad().getNombre());
            map.put("tipolugar", c.getTipoLugar().getNombre());           
            l.add(map);
        }
        return l;
    }
    
    @RequestMapping(value = "/actividadTipoLugar", method = RequestMethod.POST)
    @Transactional
    public @ResponseBody boolean save(@RequestBody String json, HttpServletResponse response) {
        Map obj=(Map) JSONValue.parse(json);
        ActividadTipoLugar cv = new ActividadTipoLugar();
        int idTipoLugar = ((Long)obj.get("tipolugar")).intValue();
        int idActividad = ((Long)obj.get("actividad")).intValue();
        cv.setActividad(this.actividadB.get(idActividad));
        cv.setTipoLugar(this.tipoLugarB.get(idTipoLugar));
        boolean saved = this.actividadTipoLugarB.save(cv);
        return saved;
    }
    
    @RequestMapping(value = "/actividadTipoLugar", method = RequestMethod.PUT)
    @Transactional
    public @ResponseBody boolean put(@RequestBody String json, HttpServletResponse response) {
        Map obj=(Map) JSONValue.parse(json);
        int id = ((Long)obj.get("id")).intValue();
        int idTipoLugar = ((Long)obj.get("tipolugar")).intValue();
        int idActividad = ((Long)obj.get("actividad")).intValue();
        ActividadTipoLugar update =this.actividadTipoLugarB.get(id); 
        update.setActividad(this.actividadB.get(idActividad));
        update.setTipoLugar(this.tipoLugarB.get(idTipoLugar));
        boolean saved = this.actividadTipoLugarB.save(update);
        return saved;
    }
    
}
