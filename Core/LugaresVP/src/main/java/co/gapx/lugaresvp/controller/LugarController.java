package co.gapx.lugaresvp.controller;

import co.gapx.lugaresvp.business.CRUDService;
import co.gapx.lugaresvp.business.LugarBusiness;
import co.gapx.lugaresvp.business.TipoLugarBusiness;
import co.gapx.lugaresvp.domain.Lugar;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.type.ImageType;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestParam;
/**
 *
 * @author laynegranadosmogollon
 */

@Controller
@RequestMapping("/rest")
public class LugarController {
    
    @Autowired
    private LugarBusiness lugarB;
    @Autowired
    private TipoLugarBusiness tipolugarB;
    @Autowired
    private CRUDService crudS;
    
    @RequestMapping(value = "/lugar", method = RequestMethod.GET)
    @Transactional
    public @ResponseBody List getList(HttpServletResponse response) {
        List<Lugar> lugars = this.lugarB.listALl();
        List l = new ArrayList();
        for (Lugar c: lugars) {
            Map<String, Object> map = new HashMap();
            map.put("id", c.getId());
            map.put("nombre", c.getNombre());
            map.put("descripcion", c.getDescripcion());
            this.crudS.refresh(c.getTipoLugar());
            Map<String, Object> mapTipoLugar = new HashMap();
            mapTipoLugar.put("id", c.getTipoLugar().getId());         
            mapTipoLugar.put("nombre", c.getTipoLugar().getNombre());         
            mapTipoLugar.put("descripcion", c.getTipoLugar().getDescripcion());         
            map.put("tipoLugar", mapTipoLugar);
            l.add(map);
        }
        return l;
    }
    
    @RequestMapping(value = "/lugar", method = RequestMethod.POST)
    @Transactional
    public @ResponseBody Lugar save(@RequestBody String json, HttpServletResponse response) {
        Map obj=(Map) JSONValue.parse(json);
        System.out.println("json: "+json);
        Lugar updated = new Lugar();
        updated.setNombre((String)obj.get("nombre"));
        updated.setDescripcion((String)obj.get("descripcion"));
        Map mapLugar = (Map)((JSONObject)obj.get("tipoLugar"));
        updated.setTipoLugar(this.tipolugarB.get(((Long)mapLugar.get("id")).intValue()));
        Lugar saved = this.lugarB.save(updated);
        return saved;
    }
    
    @RequestMapping(value = "/lugar", method = RequestMethod.PUT)
    @Transactional
    public @ResponseBody Lugar put(@RequestBody String json, HttpServletResponse response) {
        Map obj=(Map) JSONValue.parse(json);
      
        Lugar update = this.lugarB.get(((Long)obj.get("id")).intValue()); 
        update.setNombre((String)obj.get("nombre"));
        update.setDescripcion((String)obj.get("descripcion"));
        Map mapLugar = (Map)((JSONObject)obj.get("tipoLugar"));
        update.setTipoLugar(this.tipolugarB.get(((Long)mapLugar.get("id")).intValue()));
        
        Lugar saved = this.lugarB.save(update);
        return saved;
    }
    
    @RequestMapping(value = "/lugar/identificador", method = RequestMethod.POST)
    @Transactional
    public @ResponseBody List getDeId(@RequestBody String json, HttpServletResponse response) {
        System.out.println("json id lugar: "+json);
        List l = new ArrayList();
        Map obj=(Map) JSONValue.parse(json);
        Lugar lu = this.lugarB.get(Integer.parseInt((String)obj.get("idlugar")));
        Map<String, Object> map = new HashMap();
        map.put("id", lu.getId());
        map.put("nombre", lu.getNombre());
        map.put("descripcion", lu.getDescripcion());
        this.crudS.refresh(lu.getTipoLugar());
        map.put("tipolugar", lu.getTipoLugar().getNombre());         
        l.add(map);
        return l;
    }
        
    @RequestMapping(value = "/lugar-delete", method = RequestMethod.POST)
    @Transactional
    public @ResponseBody Boolean delete(@RequestBody String json, HttpServletResponse response) {
        Map obj=(Map) JSONValue.parse(json);
        Lugar toDelete =this.lugarB.get(((Long)obj.get("id")).intValue()); 
        if(toDelete == null){
            return false;
        }
        try{
            boolean deleted = this.lugarB.delete(toDelete);
            return deleted;
        } catch (Exception ex){
            return false;
        }
    }
    
    
}
