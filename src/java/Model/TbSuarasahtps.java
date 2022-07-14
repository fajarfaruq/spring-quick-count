/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "tb_suarasahtps")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbSuarasahtps.findAll", query = "SELECT t FROM TbSuarasahtps t"),
    @NamedQuery(name = "TbSuarasahtps.findByKodeSuaraTPS", query = "SELECT t FROM TbSuarasahtps t WHERE t.kodeSuaraTPS = :kodeSuaraTPS"),
    @NamedQuery(name = "TbSuarasahtps.findByKodeTPS", query = "SELECT t FROM TbSuarasahtps t WHERE t.kodeTPS = :kodeTPS"),
    @NamedQuery(name = "TbSuarasahtps.findByKodePasanganCalon", query = "SELECT t FROM TbSuarasahtps t WHERE t.kodePasanganCalon = :kodePasanganCalon"),
    @NamedQuery(name = "TbSuarasahtps.findBySuaraSah", query = "SELECT t FROM TbSuarasahtps t WHERE t.suaraSah = :suaraSah")})
public class TbSuarasahtps implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Kode_SuaraTPS")
    private Integer kodeSuaraTPS;
    @Basic(optional = false)
    @Column(name = "Kode_TPS")
    private int kodeTPS;
    @Basic(optional = false)
    @Column(name = "Kode_PasanganCalon")
    private String kodePasanganCalon;
    @Basic(optional = false)
    @Column(name = "Suara_Sah")
    private int suaraSah;

    public TbSuarasahtps() {
    }

    public TbSuarasahtps(Integer kodeSuaraTPS) {
        this.kodeSuaraTPS = kodeSuaraTPS;
    }

    public TbSuarasahtps(Integer kodeSuaraTPS, int kodeTPS, String kodePasanganCalon, int suaraSah) {
        this.kodeSuaraTPS = kodeSuaraTPS;
        this.kodeTPS = kodeTPS;
        this.kodePasanganCalon = kodePasanganCalon;
        this.suaraSah = suaraSah;
    }

    public Integer getKodeSuaraTPS() {
        return kodeSuaraTPS;
    }

    public void setKodeSuaraTPS(Integer kodeSuaraTPS) {
        this.kodeSuaraTPS = kodeSuaraTPS;
    }

    public int getKodeTPS() {
        return kodeTPS;
    }

    public void setKodeTPS(int kodeTPS) {
        this.kodeTPS = kodeTPS;
    }

    public String getKodePasanganCalon() {
        return kodePasanganCalon;
    }

    public void setKodePasanganCalon(String kodePasanganCalon) {
        this.kodePasanganCalon = kodePasanganCalon;
    }

    public int getSuaraSah() {
        return suaraSah;
    }

    public void setSuaraSah(int suaraSah) {
        this.suaraSah = suaraSah;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kodeSuaraTPS != null ? kodeSuaraTPS.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbSuarasahtps)) {
            return false;
        }
        TbSuarasahtps other = (TbSuarasahtps) object;
        if ((this.kodeSuaraTPS == null && other.kodeSuaraTPS != null) || (this.kodeSuaraTPS != null && !this.kodeSuaraTPS.equals(other.kodeSuaraTPS))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.TbSuarasahtps[ kodeSuaraTPS=" + kodeSuaraTPS + " ]";
    }
    
}
