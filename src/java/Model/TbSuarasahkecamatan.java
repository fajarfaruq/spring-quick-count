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
 * @author Administrator
 */
@Entity
@Table(name = "tb_suarasahkecamatan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbSuarasahkecamatan.findAll", query = "SELECT t FROM TbSuarasahkecamatan t"),
    @NamedQuery(name = "TbSuarasahkecamatan.findByKodeSuaraKecamatan", query = "SELECT t FROM TbSuarasahkecamatan t WHERE t.kodeSuaraKecamatan = :kodeSuaraKecamatan"),
    @NamedQuery(name = "TbSuarasahkecamatan.findByKodeKecamatan", query = "SELECT t FROM TbSuarasahkecamatan t WHERE t.kodeKecamatan = :kodeKecamatan"),
    @NamedQuery(name = "TbSuarasahkecamatan.findByKodePasanganCalon", query = "SELECT t FROM TbSuarasahkecamatan t WHERE t.kodePasanganCalon = :kodePasanganCalon"),
    @NamedQuery(name = "TbSuarasahkecamatan.findBySuaraSah", query = "SELECT t FROM TbSuarasahkecamatan t WHERE t.suaraSah = :suaraSah")})
public class TbSuarasahkecamatan implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Kode_SuaraKecamatan")
    private Integer kodeSuaraKecamatan;
    @Basic(optional = false)
    @Column(name = "Kode_Kecamatan")
    private String kodeKecamatan;
    @Basic(optional = false)
    @Column(name = "Kode_PasanganCalon")
    private String kodePasanganCalon;
    @Basic(optional = false)
    @Column(name = "Suara_Sah")
    private int suaraSah;

    public TbSuarasahkecamatan() {
    }

    public TbSuarasahkecamatan(Integer kodeSuaraKecamatan) {
        this.kodeSuaraKecamatan = kodeSuaraKecamatan;
    }

    public TbSuarasahkecamatan(Integer kodeSuaraKecamatan, String kodeKecamatan, String kodePasanganCalon, int suaraSah) {
        this.kodeSuaraKecamatan = kodeSuaraKecamatan;
        this.kodeKecamatan = kodeKecamatan;
        this.kodePasanganCalon = kodePasanganCalon;
        this.suaraSah = suaraSah;
    }

    public Integer getKodeSuaraKecamatan() {
        return kodeSuaraKecamatan;
    }

    public void setKodeSuaraKecamatan(Integer kodeSuaraKecamatan) {
        this.kodeSuaraKecamatan = kodeSuaraKecamatan;
    }

    public String getKodeKecamatan() {
        return kodeKecamatan;
    }

    public void setKodeKecamatan(String kodeKecamatan) {
        this.kodeKecamatan = kodeKecamatan;
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
        hash += (kodeSuaraKecamatan != null ? kodeSuaraKecamatan.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbSuarasahkecamatan)) {
            return false;
        }
        TbSuarasahkecamatan other = (TbSuarasahkecamatan) object;
        if ((this.kodeSuaraKecamatan == null && other.kodeSuaraKecamatan != null) || (this.kodeSuaraKecamatan != null && !this.kodeSuaraKecamatan.equals(other.kodeSuaraKecamatan))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.TbSuarasahkecamatan[ kodeSuaraKecamatan=" + kodeSuaraKecamatan + " ]";
    }
    
}
