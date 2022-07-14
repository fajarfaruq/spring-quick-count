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
@Table(name = "tb_suaratidaksahkecamatan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbSuaratidaksahkecamatan.findAll", query = "SELECT t FROM TbSuaratidaksahkecamatan t"),
    @NamedQuery(name = "TbSuaratidaksahkecamatan.findByKodeKecamatan", query = "SELECT t FROM TbSuaratidaksahkecamatan t WHERE t.kodeKecamatan = :kodeKecamatan"),
    @NamedQuery(name = "TbSuaratidaksahkecamatan.findBySuaraTidakSah", query = "SELECT t FROM TbSuaratidaksahkecamatan t WHERE t.suaraTidakSah = :suaraTidakSah"),
    @NamedQuery(name = "TbSuaratidaksahkecamatan.findByTervalidasi", query = "SELECT t FROM TbSuaratidaksahkecamatan t WHERE t.tervalidasi = :tervalidasi")})
public class TbSuaratidaksahkecamatan implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Kode_Kecamatan")
    private String kodeKecamatan;
    @Basic(optional = false)
    @Column(name = "Suara_Tidak_Sah")
    private int suaraTidakSah;
    @Basic(optional = false)
    @Column(name = "Tervalidasi")
    private String tervalidasi;

    public TbSuaratidaksahkecamatan() {
    }

    public TbSuaratidaksahkecamatan(String kodeKecamatan) {
        this.kodeKecamatan = kodeKecamatan;
    }

    public TbSuaratidaksahkecamatan(String kodeKecamatan, int suaraTidakSah, String tervalidasi) {
        this.kodeKecamatan = kodeKecamatan;
        this.suaraTidakSah = suaraTidakSah;
        this.tervalidasi = tervalidasi;
    }

    public String getKodeKecamatan() {
        return kodeKecamatan;
    }

    public void setKodeKecamatan(String kodeKecamatan) {
        this.kodeKecamatan = kodeKecamatan;
    }

    public int getSuaraTidakSah() {
        return suaraTidakSah;
    }

    public void setSuaraTidakSah(int suaraTidakSah) {
        this.suaraTidakSah = suaraTidakSah;
    }

    public String getTervalidasi() {
        return tervalidasi;
    }

    public void setTervalidasi(String tervalidasi) {
        this.tervalidasi = tervalidasi;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kodeKecamatan != null ? kodeKecamatan.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbSuaratidaksahkecamatan)) {
            return false;
        }
        TbSuaratidaksahkecamatan other = (TbSuaratidaksahkecamatan) object;
        if ((this.kodeKecamatan == null && other.kodeKecamatan != null) || (this.kodeKecamatan != null && !this.kodeKecamatan.equals(other.kodeKecamatan))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.TbSuaratidaksahkecamatan[ kodeKecamatan=" + kodeKecamatan + " ]";
    }
    
}
