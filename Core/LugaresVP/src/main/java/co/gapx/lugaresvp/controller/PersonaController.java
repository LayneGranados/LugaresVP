package co.gapx.lugaresvp.controller;

import co.gapx.lugaresvp.business.CRUDService;
import co.gapx.lugaresvp.business.PersonaBusiness;
import co.gapx.lugaresvp.business.TipoIdentificacionBusiness;
import co.gapx.lugaresvp.domain.Persona;
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
        Map obj=(Map) JSONValue.parse(json);
        Persona cv = new Persona();
        TipoIdentificacion t = tipoIdentificacionB.get(((Long)obj.get("tipoidentificacion")).intValue());
        cv.setTipoIdentificacion(t);
        cv.setApellidos((String)obj.get("apellidos"));
        cv.setNombres((String)obj.get("nombres"));
        cv.setIdentificacion((String)obj.get("identificacion"));
        boolean saved = this.personaB.save(cv);
        return saved;
    }
    
    @RequestMapping(value = "/persona", method = RequestMethod.PUT)
    @Transactional
    public @ResponseBody boolean put(@RequestBody String json, HttpServletResponse response) {
        Map obj=(Map) JSONValue.parse(json);
        Persona update =this.personaB.get(((Long)obj.get("id")).intValue()); 
        TipoIdentificacion t = tipoIdentificacionB.get(((Long)obj.get("tipoidentificacion")).intValue());
        update.setTipoIdentificacion(t);
        update.setApellidos((String)obj.get("apellidos"));
        update.setNombres((String)obj.get("nombres"));
        update.setIdentificacion((String)obj.get("identificacion"));
        boolean saved = this.personaB.save(update);
        return saved;
    }
    
}
