package co.gapx.lugaresvp.controller;

import co.gapx.lugaresvp.business.CRUDService;
import co.gapx.lugaresvp.business.TipoEmpleadoBusiness;
import co.gapx.lugaresvp.domain.TipoEmpleado;
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
public class TipoEmpleadoController {
    
    @Autowired
    private TipoEmpleadoBusiness tipoEmpleadoB;
    @Autowired
    private CRUDService crudS;
    
    @RequestMapping(value = "/tipoEmpleado", method = RequestMethod.GET)
    @Transactional
    public @ResponseBody List getList(HttpServletResponse response) {
        List<TipoEmpleado> tiposempleados = this.tipoEmpleadoB.listALl();
        List l = new ArrayList();
        for (TipoEmpleado c: tiposempleados) {
            Map<String, Object> map = new HashMap();
            map.put("id", c.getId());
            map.put("nombre", c.getNombre());
            map.put("descripcion", c.getDescripcion());
        // aqui poner en el map todos los parametros que se quieran devolver en el metodo            
            l.add(map);
        }
        return l;
    }
    
    @RequestMapping(value = "/tipoEmpleado", method = RequestMethod.POST)
    @Transactional
    public @ResponseBody TipoEmpleado save(@RequestBody String json, HttpServletResponse response) {
        Map obj=(Map) JSONValue.parse(json);
        TipoEmpleado cv = new TipoEmpleado();
        cv.setNombre((String)obj.get("nombre"));
        cv.setDescripcion((String)obj.get("descripcion"));
     // aqui sacar los parametros del json y setearlo en el nuevo objeto
        TipoEmpleado saved = this.tipoEmpleadoB.save(cv);
        return saved;
    }
    
    @RequestMapping(value = "/tipoEmpleado", method = RequestMethod.PUT)
    @Transactional
    public @ResponseBody TipoEmpleado put(@RequestBody String json, HttpServletResponse response) {
        Map obj=(Map) JSONValue.parse(json);
        TipoEmpleado update =this.tipoEmpleadoB.get(((Long)obj.get("id")).intValue()); 
        update.setNombre((String)obj.get("nombre"));
        update.setDescripcion((String)obj.get("descripcion"));
        TipoEmpleado saved = this.tipoEmpleadoB.save(update);
        return saved;
    }
    
    @RequestMapping(value = "/tipoEmpleado/del", method = RequestMethod.POST)
    @Transactional
    public @ResponseBody TipoEmpleado delete(@RequestBody String json, HttpServletResponse response) {
        Map obj=(Map) JSONValue.parse(json);
        TipoEmpleado toDelete =this.tipoEmpleadoB.get(((Long)obj.get("id")).intValue()); 
        try{
            System.out.println("json: "+json);
            boolean deleted = this.tipoEmpleadoB.delete(toDelete);
            if(deleted){
                toDelete.setId(-1);
            }
            return toDelete;
        } catch (Exception ex){
            return toDelete;
        }
    }
    
}
