package co.gapx.lugaresvp.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author Layne Granados Mogollon<layne0205@gmail.com>
 */
@Entity
@Table(name = "supervision", catalog = "lugares_ventura_plaza", schema = "public" )

public class Supervision implements Serializable{
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    
    @JoinColumn(name = "supervisor_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Supervisor supervisor;
    
    @JoinColumn(name = "lugar_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Lugar lugar;
    
    @Basic(optional = false)
    @Column(name = "fecha", nullable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fecha;
    

    public Supervision() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Supervisor getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Supervisor supervisor) {
        this.supervisor = supervisor;
    }

    public Lugar getLugar() {
        return lugar;
    }

    public void setLugar(Lugar lugar) {
        this.lugar = lugar;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    
}