/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.List;
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
@Table(name = "tb_kecamatan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbKecamatan.findAll", query = "SELECT t FROM TbKecamatan t"),
    @NamedQuery(name = "TbKecamatan.findByKodeKecamatan", query = "SELECT t FROM TbKecamatan t WHERE t.kodeKecamatan = :kodeKecamatan"),
    @NamedQuery(name = "TbKecamatan.findByNamaKecamatan", query = "SELECT t FROM TbKecamatan t WHERE t.namaKecamatan = :namaKecamatan")})
public class TbKecamatan implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Kode_Kecamatan")
    private String kodeKecamatan;
    @Basic(optional = false)
    @Column(name = "Nama_Kecamatan")
    private String namaKecamatan;

    
    public TbKecamatan() {
    }

    public TbKecamatan(String kodeKecamatan) {
        this.kodeKecamatan = kodeKecamatan;
    }

    public TbKecamatan(String kodeKecamatan, String namaKecamatan) {
        this.kodeKecamatan = kodeKecamatan;
        this.namaKecamatan = namaKecamatan;
    }

    public String getKodeKecamatan() {
        return kodeKecamatan;
    }

    public void setKodeKecamatan(String kodeKecamatan) {
        this.kodeKecamatan = kodeKecamatan;
    }

    public String getNamaKecamatan() {
        return namaKecamatan;
    }

    public void setNamaKecamatan(String namaKecamatan) {
        this.namaKecamatan = namaKecamatan;
    }

    /*ARRAY */


    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kodeKecamatan != null ? kodeKecamatan.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbKecamatan)) {
            return false;
        }
        TbKecamatan other = (TbKecamatan) object;
        if ((this.kodeKecamatan == null && other.kodeKecamatan != null) || (this.kodeKecamatan != null && !this.kodeKecamatan.equals(other.kodeKecamatan))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.TbKecamatan[ kodeKecamatan=" + kodeKecamatan + " ]";
    }
    
}
