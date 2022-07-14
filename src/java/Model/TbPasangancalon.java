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
 * @author Administrator
 */
@Entity
@Table(name = "tb_pasangancalon")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbPasangancalon.findAll", query = "SELECT t FROM TbPasangancalon t"),
    @NamedQuery(name = "TbPasangancalon.findByKodePasangancalon", query = "SELECT t FROM TbPasangancalon t WHERE t.kodePasangancalon = :kodePasangancalon"),
    @NamedQuery(name = "TbPasangancalon.findByGubernur", query = "SELECT t FROM TbPasangancalon t WHERE t.gubernur = :gubernur"),
    @NamedQuery(name = "TbPasangancalon.findByWakilGubernur", query = "SELECT t FROM TbPasangancalon t WHERE t.wakilGubernur = :wakilGubernur"),
    @NamedQuery(name = "TbPasangancalon.findByGambar", query = "SELECT t FROM TbPasangancalon t WHERE t.gambar = :gambar")})
public class TbPasangancalon implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Kode_Pasangancalon")
    private String kodePasangancalon;
    @Basic(optional = false)
    @Column(name = "Gubernur")
    private String gubernur;
    @Basic(optional = false)
    @Column(name = "Wakil_Gubernur")
    private String wakilGubernur;
    @Basic(optional = false)
    @Column(name = "Gambar")
    private String gambar;

    public TbPasangancalon() {
    }

    public TbPasangancalon(String kodePasangancalon) {
        this.kodePasangancalon = kodePasangancalon;
    }

    public TbPasangancalon(String kodePasangancalon, String gubernur, String wakilGubernur, String gambar) {
        this.kodePasangancalon = kodePasangancalon;
        this.gubernur = gubernur;
        this.wakilGubernur = wakilGubernur;
        this.gambar = gambar;
    }

    public String getKodePasangancalon() {
        return kodePasangancalon;
    }

    public void setKodePasangancalon(String kodePasangancalon) {
        this.kodePasangancalon = kodePasangancalon;
    }

    public String getGubernur() {
        return gubernur;
    }

    public void setGubernur(String gubernur) {
        this.gubernur = gubernur;
    }

    public String getWakilGubernur() {
        return wakilGubernur;
    }

    public void setWakilGubernur(String wakilGubernur) {
        this.wakilGubernur = wakilGubernur;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kodePasangancalon != null ? kodePasangancalon.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbPasangancalon)) {
            return false;
        }
        TbPasangancalon other = (TbPasangancalon) object;
        if ((this.kodePasangancalon == null && other.kodePasangancalon != null) || (this.kodePasangancalon != null && !this.kodePasangancalon.equals(other.kodePasangancalon))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.TbPasangancalon[ kodePasangancalon=" + kodePasangancalon + " ]";
    }
    
}
