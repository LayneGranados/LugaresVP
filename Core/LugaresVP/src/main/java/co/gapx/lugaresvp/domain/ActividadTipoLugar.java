package co.gapx.lugaresvp.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Layne Granados Mogollon<layne0205@gmail.com>
 */

@Entity
@Table(name = "actividad_tipo_lugar", schema = "public" )

public class ActividadTipoLugar implements Serializable{
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    
    @JoinColumn(name = "actividad_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Actividad actividad;
    
    @JoinColumn(name = "tipo_lugar_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private TipoLugar tipoLugar;
   
    public ActividadTipoLugar() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public TipoLugar getTipoLugar() {
        return tipoLugar;
    }

    public void setTipoLugar(TipoLugar tipoLugar) {
        this.tipoLugar = tipoLugar;
    }
    
    
}
