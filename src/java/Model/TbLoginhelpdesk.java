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
@Table(name = "tb_loginhelpdesk")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbLoginhelpdesk.findAll", query = "SELECT t FROM TbLoginhelpdesk t"),
    @NamedQuery(name = "TbLoginhelpdesk.findByUsername", query = "SELECT t FROM TbLoginhelpdesk t WHERE t.username = :username"),
    @NamedQuery(name = "TbLoginhelpdesk.findByPassword", query = "SELECT t FROM TbLoginhelpdesk t WHERE t.password = :password"),
    @NamedQuery(name = "TbLoginhelpdesk.findByKodeHakAkses", query = "SELECT t FROM TbLoginhelpdesk t WHERE t.kodeHakAkses = :kodeHakAkses")})
public class TbLoginhelpdesk implements Serializable {
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

    public TbLoginhelpdesk() {
    }

    public TbLoginhelpdesk(String username) {
        this.username = username;
    }

    public TbLoginhelpdesk(String username, String password, String kodeHakAkses) {
        this.username = username;
        this.password = password;
        this.kodeHakAkses = kodeHakAkses;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbLoginhelpdesk)) {
            return false;
        }
        TbLoginhelpdesk other = (TbLoginhelpdesk) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.TbLoginhelpdesk[ username=" + username + " ]";
    }
    
}
