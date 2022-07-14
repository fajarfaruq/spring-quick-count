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
@Table(name = "tb_suaratidaksahkelurahan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbSuaratidaksahkelurahan.findAll", query = "SELECT t FROM TbSuaratidaksahkelurahan t"),
    @NamedQuery(name = "TbSuaratidaksahkelurahan.findByKodeKelurahan", query = "SELECT t FROM TbSuaratidaksahkelurahan t WHERE t.kodeKelurahan = :kodeKelurahan"),
    @NamedQuery(name = "TbSuaratidaksahkelurahan.findBySuaraTidakSah", query = "SELECT t FROM TbSuaratidaksahkelurahan t WHERE t.suaraTidakSah = :suaraTidakSah"),
    @NamedQuery(name = "TbSuaratidaksahkelurahan.findByTervalidasi", query = "SELECT t FROM TbSuaratidaksahkelurahan t WHERE t.tervalidasi = :tervalidasi")})
public class TbSuaratidaksahkelurahan implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Kode_Kelurahan")
    private String kodeKelurahan;
    @Basic(optional = false)
    @Column(name = "Suara_Tidak_Sah")
    private int suaraTidakSah;
    @Basic(optional = false)
    @Column(name = "Tervalidasi")
    private String tervalidasi;

    public TbSuaratidaksahkelurahan() {
    }

    public TbSuaratidaksahkelurahan(String kodeKelurahan) {
        this.kodeKelurahan = kodeKelurahan;
    }

    public TbSuaratidaksahkelurahan(String kodeKelurahan, int suaraTidakSah, String tervalidasi) {
        this.kodeKelurahan = kodeKelurahan;
        this.suaraTidakSah = suaraTidakSah;
        this.tervalidasi = tervalidasi;
    }

    public String getKodeKelurahan() {
        return kodeKelurahan;
    }

    public void setKodeKelurahan(String kodeKelurahan) {
        this.kodeKelurahan = kodeKelurahan;
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
        hash += (kodeKelurahan != null ? kodeKelurahan.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbSuaratidaksahkelurahan)) {
            return false;
        }
        TbSuaratidaksahkelurahan other = (TbSuaratidaksahkelurahan) object;
        if ((this.kodeKelurahan == null && other.kodeKelurahan != null) || (this.kodeKelurahan != null && !this.kodeKelurahan.equals(other.kodeKelurahan))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.TbSuaratidaksahkelurahan[ kodeKelurahan=" + kodeKelurahan + " ]";
    }
    
}
