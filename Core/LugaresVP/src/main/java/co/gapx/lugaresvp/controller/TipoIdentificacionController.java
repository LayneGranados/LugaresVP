package co.gapx.lugaresvp.controller;

import co.gapx.lugaresvp.business.CRUDService;
import co.gapx.lugaresvp.business.TipoIdentificacionBusiness;
import co.gapx.lugaresvp.domain.TipoIdentificacion;
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
public class TipoIdentificacionController {
    
    @Autowired
    private TipoIdentificacionBusiness tipoIdentificacionB;
    @Autowired
    private CRUDService crudS;
    
    @RequestMapping(value = "/tipoIdentificacion", method = RequestMethod.GET)
    @Transactional
    public @ResponseBody List getList(HttpServletResponse response) {
        List<TipoIdentificacion> tipoIdentificacions = this.tipoIdentificacionB.listALl();
        List l = new ArrayList();
        for (TipoIdentificacion c: tipoIdentificacions) {
            Map<String, Object> map = new HashMap();
            map.put("id", c.getId());
            map.put("nombre", c.getNombre());
            map.put("codigo", c.getCodigo());
        // aqui poner en el map todos los parametros que se quieran devolver en el metodo            
            l.add(map);
        }
        return l;
    }
    
    @RequestMapping(value = "/tipoIdentificacion", method = RequestMethod.POST)
    @Transactional
    public @ResponseBody TipoIdentificacion save(@RequestBody String json, HttpServletResponse response) {
        Map obj=(Map) JSONValue.parse(json);
        TipoIdentificacion cv = new TipoIdentificacion();
        cv.setNombre((String)obj.get("nombre"));
        cv.setCodigo((String)obj.get("codigo"));
     // aqui sacar los parametros del json y setearlo en el nuevo objeto
        TipoIdentificacion saved = this.tipoIdentificacionB.save(cv);
        return saved;
    }
    
    @RequestMapping(value = "/tipoIdentificacion", method = RequestMethod.PUT)
    @Transactional
    public @ResponseBody TipoIdentificacion put(@RequestBody String json, HttpServletResponse response) {
        Map obj=(Map) JSONValue.parse(json);
        TipoIdentificacion update =this.tipoIdentificacionB.get(((Long)obj.get("id")).intValue()); 
        update.setNombre((String)obj.get("nombre"));
        update.setCodigo((String)obj.get("codigo"));
       //Aqui se deben sacar del json los parametros y seterarlos en el objeto update 
        TipoIdentificacion saved = this.tipoIdentificacionB.save(update);
        return saved;
    }
    
    @RequestMapping(value = "/tipoIdentificacion/del", method = RequestMethod.POST)
    @Transactional
    public @ResponseBody TipoIdentificacion delete(@RequestBody String json, HttpServletResponse response) {
        Map obj=(Map) JSONValue.parse(json);
        TipoIdentificacion toDelete =this.tipoIdentificacionB.get(((Long)obj.get("id")).intValue()); 
        try{
            System.out.println("json: "+json);
            boolean deleted = this.tipoIdentificacionB.delete(toDelete);
            if(deleted){
                toDelete.setId(-1);
            }
            return toDelete;
        } catch (Exception ex){
            return toDelete;
        }
    }
    
}
