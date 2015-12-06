package co.gapx.lugaresvp.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Layne Granados Mogollon<layne0205@gmail.com>
 */

@Entity
@Table(name = "persona", schema = "public" )

public class Persona implements Serializable{
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    
    @JoinColumn(name = "tipo_identificacion_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private TipoIdentificacion tipoIdentificacion;
    
    @Basic(optional = false)
    @Column(name = "identificacion", nullable = false)
    private String identificacion ;
    
    @Basic(optional = false)
    @Column(name = "nombres", nullable = false)
    private String nombres ;
    
    @Basic(optional = false)
    @Column(name = "apellidos", nullable = false)
    private String apellidos ;
    

    public Persona() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TipoIdentificacion getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(TipoIdentificacion tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    
    
}
