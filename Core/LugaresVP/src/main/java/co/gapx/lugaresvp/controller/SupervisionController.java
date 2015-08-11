package co.gapx.lugaresvp.controller;

import co.gapx.lugaresvp.business.CRUDService;
import co.gapx.lugaresvp.business.LugarBusiness;
import co.gapx.lugaresvp.business.SupervisionBusiness;
import co.gapx.lugaresvp.business.SupervisorBusiness;
import co.gapx.lugaresvp.domain.Lugar;
import co.gapx.lugaresvp.domain.Supervision;
import co.gapx.lugaresvp.domain.Supervisor;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
public class SupervisionController {
    
    @Autowired
    private SupervisionBusiness supervisionB;
    @Autowired
    private SupervisorBusiness supervisorB;
    @Autowired
    private LugarBusiness lugarB;
    @Autowired
    private CRUDService crudS;
    
    @RequestMapping(value = "/supervision", method = RequestMethod.GET)
    @Transactional
    public @ResponseBody List getList(HttpServletResponse response) {
        List<Supervision> supervisions = this.supervisionB.listALl();
        List l = new ArrayList();
        for (Supervision c: supervisions) {
            Map<String, Object> map = new HashMap();
            map.put("id", c.getId());
            this.crudS.refresh(c.getLugar());
            map.put("lugar", c.getLugar().getNombre());
            this.crudS.refresh(c.getSupervisor());
            this.crudS.refresh(c.getSupervisor().getPersona());
            map.put("supervisor", c.getSupervisor().getPersona().getNombres() + " "+ c.getSupervisor().getPersona().getApellidos());
            map.put("fecha", c.getFecha().toString());            
            l.add(map);
        }
        return l;
    }
    
    @RequestMapping(value = "/supervision", method = RequestMethod.POST)
    @Transactional
    public @ResponseBody boolean save(@RequestBody String json, HttpServletResponse response) {
        Map obj=(Map) JSONValue.parse(json);
        Supervision cv = new Supervision();
        Lugar l = this.lugarB.get(((Long)obj.get("lugar")).intValue());
        Supervisor s = this.supervisorB.get(((Long)obj.get("supervisor")).intValue());
        cv.setLugar(l);
        cv.setSupervisor(s);
        cv.setFecha(new Date((String)obj.get("fecha")));
        boolean saved = this.supervisionB.save(cv);
        return saved;
    }
    
    @RequestMapping(value = "/supervision", method = RequestMethod.PUT)
    @Transactional
    public @ResponseBody boolean put(@RequestBody String json, HttpServletResponse response) {
        Map obj=(Map) JSONValue.parse(json);
        int id = Integer.parseInt((String)obj.get("id"));
        Supervision update =this.supervisionB.get(id); 
        Lugar l = this.lugarB.get(((Long)obj.get("lugar")).intValue());
        Supervisor s = this.supervisorB.get(((Long)obj.get("supervisor")).intValue());
        update.setLugar(l);
        update.setSupervisor(s);
        update.setFecha(new Date((String)obj.get("fecha")));
        boolean saved = this.supervisionB.save(update);
        return saved;
    }
    
    
    @RequestMapping(value = "/supervision/lugar", method = RequestMethod.POST)
    @Transactional
    public @ResponseBody List getDeLugar(@RequestBody String json, HttpServletResponse response) {
        System.out.println("json id lugar: "+json);
        Map obj=(Map) JSONValue.parse(json);
        String idlugar = (String)obj.get("idlugar");
        Lugar lu = this.lugarB.get(Integer.parseInt(idlugar));
        List<Supervision> supervisions = this.supervisionB.getDeLugar(lu);
        List l = new ArrayList();
        
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd 'a las' h:m:s");
        
        for (Supervision c: supervisions) {
            Map<String, Object> map = new HashMap();
            map.put("id", c.getId());
            this.crudS.refresh(c.getLugar());
            map.put("lugartitle", c.getLugar().getNombre());
            map.put("lugardesc", c.getLugar().getDescripcion());
            this.crudS.refresh(c.getSupervisor());
            this.crudS.refresh(c.getSupervisor().getPersona());
            map.put("supervisor", c.getSupervisor().getPersona().getNombres() + " "+ c.getSupervisor().getPersona().getApellidos());
            map.put("fecha", dateFormatter.format(c.getFecha()));            
            l.add(map);
        }
        return l;
    }
    
}