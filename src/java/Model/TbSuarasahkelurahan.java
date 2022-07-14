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
@Table(name = "tb_suarasahkelurahan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbSuarasahkelurahan.findAll", query = "SELECT t FROM TbSuarasahkelurahan t"),
    @NamedQuery(name = "TbSuarasahkelurahan.findByKodeSuaraKelurahan", query = "SELECT t FROM TbSuarasahkelurahan t WHERE t.kodeSuaraKelurahan = :kodeSuaraKelurahan"),
    @NamedQuery(name = "TbSuarasahkelurahan.findByKodeKelurahan", query = "SELECT t FROM TbSuarasahkelurahan t WHERE t.kodeKelurahan = :kodeKelurahan"),
    @NamedQuery(name = "TbSuarasahkelurahan.findByKodePasanganCalon", query = "SELECT t FROM TbSuarasahkelurahan t WHERE t.kodePasanganCalon = :kodePasanganCalon"),
    @NamedQuery(name = "TbSuarasahkelurahan.findBySuaraSah", query = "SELECT t FROM TbSuarasahkelurahan t WHERE t.suaraSah = :suaraSah")})
public class TbSuarasahkelurahan implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Kode_SuaraKelurahan")
    private Integer kodeSuaraKelurahan;
    @Basic(optional = false)
    @Column(name = "kode_Kelurahan")
    private String kodeKelurahan;
    @Basic(optional = false)
    @Column(name = "Kode_PasanganCalon")
    private String kodePasanganCalon;
    @Basic(optional = false)
    @Column(name = "Suara_Sah")
    private int suaraSah;

    public TbSuarasahkelurahan() {
    }

    public TbSuarasahkelurahan(Integer kodeSuaraKelurahan) {
        this.kodeSuaraKelurahan = kodeSuaraKelurahan;
    }

    public TbSuarasahkelurahan(Integer kodeSuaraKelurahan, String kodeKelurahan, String kodePasanganCalon, int suaraSah) {
        this.kodeSuaraKelurahan = kodeSuaraKelurahan;
        this.kodeKelurahan = kodeKelurahan;
        this.kodePasanganCalon = kodePasanganCalon;
        this.suaraSah = suaraSah;
    }

    public Integer getKodeSuaraKelurahan() {
        return kodeSuaraKelurahan;
    }

    public void setKodeSuaraKelurahan(Integer kodeSuaraKelurahan) {
        this.kodeSuaraKelurahan = kodeSuaraKelurahan;
    }

    public String getKodeKelurahan() {
        return kodeKelurahan;
    }

    public void setKodeKelurahan(String kodeKelurahan) {
        this.kodeKelurahan = kodeKelurahan;
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
        hash += (kodeSuaraKelurahan != null ? kodeSuaraKelurahan.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbSuarasahkelurahan)) {
            return false;
        }
        TbSuarasahkelurahan other = (TbSuarasahkelurahan) object;
        if ((this.kodeSuaraKelurahan == null && other.kodeSuaraKelurahan != null) || (this.kodeSuaraKelurahan != null && !this.kodeSuaraKelurahan.equals(other.kodeSuaraKelurahan))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.TbSuarasahkelurahan[ kodeSuaraKelurahan=" + kodeSuaraKelurahan + " ]";
    }
    
}
