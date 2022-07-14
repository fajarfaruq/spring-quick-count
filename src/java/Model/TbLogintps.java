/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author user
 */
@Entity
@Table(name = "tb_logintps")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbLogintps.findAll", query = "SELECT t FROM TbLogintps t"),
    @NamedQuery(name = "TbLogintps.findByUsername", query = "SELECT t FROM TbLogintps t WHERE t.username = :username"),
    @NamedQuery(name = "TbLogintps.findByPassword", query = "SELECT t FROM TbLogintps t WHERE t.password = :password"),
    @NamedQuery(name = "TbLogintps.findByKodeHakAkses", query = "SELECT t FROM TbLogintps t WHERE t.kodeHakAkses = :kodeHakAkses"),
    @NamedQuery(name = "TbLogintps.findByKodeTPS", query = "SELECT t FROM TbLogintps t WHERE t.kodeTPS = :kodeTPS")})
public class TbLogintps implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Username")
    private String username;
    @Basic(optional = false)
    @Column(name = "Password")
    private String password;
    @Basic(optional = false)
    @Column(name = "Kode_Hak_Akses")
    private String kodeHakAkses;
    @Basic(optional = false)
    @Column(name = "Kode_TPS")
    private int kodeTPS;

    public TbLogintps() {
    }

    public TbLogintps(String username) {
        this.username = username;
    }

    public TbLogintps(String username, String password, String kodeHakAkses, int kodeTPS) {
        this.username = username;
        this.password = password;
        this.kodeHakAkses = kodeHakAkses;
        this.kodeTPS = kodeTPS;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getKodeHakAkses() {
        return kodeHakAkses;
    }

    public void setKodeHakAkses(String kodeHakAkses) {
        this.kodeHakAkses = kodeHakAkses;
    }

    public int getKodeTPS() {
        return kodeTPS;
    }

    public void setKodeTPS(int kodeTPS) {
        this.kodeTPS = kodeTPS;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbLogintps)) {
            return false;
        }
        TbLogintps other = (TbLogintps) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.TbLogintps[ username=" + username + " ]";
    }
    
}
