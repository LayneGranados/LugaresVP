package co.gapx.lugaresvp.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author Layne Granados Mogollon<layne0205@gmail.com>
 */
@Entity
@Table(name = "evaluacion", catalog = "lugares_ventura_plaza", schema = "public" )

public class Evaluacion implements Serializable{
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    
    @JoinColumn(name = "supervision_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Supervision supervision;
    
    @JoinColumn(name = "actividad_tipo_lugar_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ActividadTipoLugar actividadTipoLugar;
    
    @JoinColumn(name = "calificacion_actividad_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CalificacionActividad calificacionActividad;
    
    @Basic(optional = false)
    @Column(name = "fecha", nullable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fecha;
    

    public Evaluacion() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Supervision getSupervision() {
        return supervision;
    }

    public void setSupervision(Supervision supervision) {
        this.supervision = supervision;
    }

    public ActividadTipoLugar getActividadTipoLugar() {
        return actividadTipoLugar;
    }

    public void setActividadTipoLugar(ActividadTipoLugar actividadTipoLugar) {
        this.actividadTipoLugar = actividadTipoLugar;
    }

    public CalificacionActividad getCalificacionActividad() {
        return calificacionActividad;
    }

    public void setCalificacionActividad(CalificacionActividad calificacionActividad) {
        this.calificacionActividad = calificacionActividad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    
    
}
