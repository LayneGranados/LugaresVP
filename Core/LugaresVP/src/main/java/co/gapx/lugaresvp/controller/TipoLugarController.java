package co.gapx.lugaresvp.controller;

import co.gapx.lugaresvp.business.CRUDService;
import co.gapx.lugaresvp.business.TipoLugarBusiness;
import co.gapx.lugaresvp.domain.TipoLugar;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author laynegranadosmogollon
 */

@Controller
@RequestMapping("/rest")
public class TipoLugarController {
    
    @Autowired
    private TipoLugarBusiness tipoLugarB;
    @Autowired
    private CRUDService crudS;
    
    @RequestMapping(value = "/tipoLugar", method = RequestMethod.GET)
    @Transactional
    public @ResponseBody List getList(HttpServletResponse response) {
        List<TipoLugar> tipoLugars = this.tipoLugarB.listALl();
        List l = new ArrayList();
        for (TipoLugar c: tipoLugars) {
            Map<String, Object> map = new HashMap();
            map.put("id", c.getId());
            map.put("nombre", c.getNombre());
            map.put("descripcion", c.getDescripcion());
        // aqui poner en el map todos los parametros que se quieran devolver en el metodo            
            l.add(map);
        }
        return l;
    }
    
    @RequestMapping(value = "/tipoLugar", method = RequestMethod.POST)
    @Transactional
    public @ResponseBody TipoLugar save(@RequestBody String json, HttpServletResponse response) {
        Map obj=(Map) JSONValue.parse(json);
        TipoLugar cv = new TipoLugar();
        cv.setNombre((String)obj.get("nombre"));
        cv.setDescripcion((String)obj.get("descripcion"));
     // aqui sacar los parametros del json y setearlo en el nuevo objeto
        TipoLugar saved = this.tipoLugarB.save(cv);
        return saved;
    }
    
    @RequestMapping(value = "/tipoLugar", method = RequestMethod.PUT)
    @Transactional
    public @ResponseBody TipoLugar put(@RequestBody String json, HttpServletResponse response) {
        Map obj=(Map) JSONValue.parse(json);
        TipoLugar update =this.tipoLugarB.get(((Long)obj.get("id")).intValue()); 
        update.setNombre((String)obj.get("nombre"));
        update.setDescripcion((String)obj.get("descripcion"));
        TipoLugar saved = this.tipoLugarB.save(update);
        return saved;
    }
    
    
    @RequestMapping(value = "/tipoLugar/del", method = RequestMethod.POST)
    @Transactional
    public @ResponseBody TipoLugar delete(@RequestBody String json, HttpServletResponse response) {
        Map obj=(Map) JSONValue.parse(json);
        TipoLugar toDelete =this.tipoLugarB.get(((Long)obj.get("id")).intValue()); 
        try{
            System.out.println("json: "+json);
            boolean deleted = this.tipoLugarB.delete(toDelete);
            if(deleted){
                toDelete.setId(-1);
            }
            return toDelete;
        } catch (Exception ex){
            return toDelete;
        }
    }

    
}
