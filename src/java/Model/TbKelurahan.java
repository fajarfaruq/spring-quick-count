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
@Table(name = "tb_kelurahan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbKelurahan.findAll", query = "SELECT t FROM TbKelurahan t"),
    @NamedQuery(name = "TbKelurahan.findByKodeKelurahan", query = "SELECT t FROM TbKelurahan t WHERE t.kodeKelurahan = :kodeKelurahan"),
    @NamedQuery(name = "TbKelurahan.findByKodeKecamatan", query = "SELECT t FROM TbKelurahan t WHERE t.kodeKecamatan = :kodeKecamatan"),
    @NamedQuery(name = "TbKelurahan.findByNamaKelurahan", query = "SELECT t FROM TbKelurahan t WHERE t.namaKelurahan = :namaKelurahan")})
public class TbKelurahan implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Kode_Kelurahan")
    private String kodeKelurahan;
    @Basic(optional = false)
    @Column(name = "Kode_Kecamatan")
    private String kodeKecamatan;
    @Basic(optional = false)
    @Column(name = "Nama_Kelurahan")
    private String namaKelurahan;

    public TbKelurahan() {
    }

    public TbKelurahan(String kodeKelurahan) {
        this.kodeKelurahan = kodeKelurahan;
    }

    public TbKelurahan(String kodeKelurahan, String kodeKecamatan, String namaKelurahan) {
        this.kodeKelurahan = kodeKelurahan;
        this.kodeKecamatan = kodeKecamatan;
        this.namaKelurahan = namaKelurahan;
    }

    public String getKodeKelurahan() {
        return kodeKelurahan;
    }

    public void setKodeKelurahan(String kodeKelurahan) {
        this.kodeKelurahan = kodeKelurahan;
    }

    public String getKodeKecamatan() {
        return kodeKecamatan;
    }

    public void setKodeKecamatan(String kodeKecamatan) {
        this.kodeKecamatan = kodeKecamatan;
    }

    public String getNamaKelurahan() {
        return namaKelurahan;
    }

    public void setNamaKelurahan(String namaKelurahan) {
        this.namaKelurahan = namaKelurahan;
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
        if (!(object instanceof TbKelurahan)) {
            return false;
        }
        TbKelurahan other = (TbKelurahan) object;
        if ((this.kodeKelurahan == null && other.kodeKelurahan != null) || (this.kodeKelurahan != null && !this.kodeKelurahan.equals(other.kodeKelurahan))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.TbKelurahan[ kodeKelurahan=" + kodeKelurahan + " ]";
    }
    
}
