package co.gapx.lugaresvp.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Layne Granados Mogollon<layne0205@gmail.com>
 */
@Entity
@Table(name = "supervisor", catalog = "lugares_ventura_plaza", schema = "public" )

public class Supervisor implements Serializable{
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    
    @JoinColumn(name = "persona_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Persona persona;
    
    @JoinColumn(name = "login_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Login login;

    public Supervisor() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }
    
    
}
