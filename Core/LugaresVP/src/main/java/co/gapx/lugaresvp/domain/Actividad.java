package co.gapx.lugaresvp.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Layne Granados Mogollon<layne0205@gmail.com>
 */

@Entity
@Table(name = "actividad", schema = "public" )
public class Actividad implements Serializable{
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    
    @Basic(optional = false)
    @Column(name = "descripcion", nullable = false)
    private String descripcion ;
    
    @Basic(optional = false)
    @Column(name = "nombre", nullable = false)
    private String nombre ;
    

    public Actividad() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
