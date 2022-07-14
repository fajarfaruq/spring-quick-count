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
@Table(name = "tb_tps")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbTps.findAll", query = "SELECT t FROM TbTps t"),
    @NamedQuery(name = "TbTps.findByKodeTPS", query = "SELECT t FROM TbTps t WHERE t.kodeTPS = :kodeTPS"),
    @NamedQuery(name = "TbTps.findByKodeKelurahan", query = "SELECT t FROM TbTps t WHERE t.kodeKelurahan = :kodeKelurahan"),
    @NamedQuery(name = "TbTps.findByNomorTPS", query = "SELECT t FROM TbTps t WHERE t.nomorTPS = :nomorTPS"),
    @NamedQuery(name = "TbTps.findByJumlahDPT", query = "SELECT t FROM TbTps t WHERE t.jumlahDPT = :jumlahDPT")})
public class TbTps implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Kode_TPS")
    private Integer kodeTPS;
    @Basic(optional = false)
    @Column(name = "Kode_Kelurahan")
    private String kodeKelurahan;
    @Basic(optional = false)
    @Column(name = "Nomor_TPS")
    private int nomorTPS;
    @Basic(optional = false)
    @Column(name = "Jumlah_DPT")
    private int jumlahDPT;

    public TbTps() {
    }

    public TbTps(Integer kodeTPS) {
        this.kodeTPS = kodeTPS;
    }

    public TbTps(Integer kodeTPS, String kodeKelurahan, int nomorTPS, int jumlahDPT) {
        this.kodeTPS = kodeTPS;
        this.kodeKelurahan = kodeKelurahan;
        this.nomorTPS = nomorTPS;
        this.jumlahDPT = jumlahDPT;
    }

    public Integer getKodeTPS() {
        return kodeTPS;
    }

    public void setKodeTPS(Integer kodeTPS) {
        this.kodeTPS = kodeTPS;
    }

    public String getKodeKelurahan() {
        return kodeKelurahan;
    }

    public void setKodeKelurahan(String kodeKelurahan) {
        this.kodeKelurahan = kodeKelurahan;
    }

    public int getNomorTPS() {
        return nomorTPS;
    }

    public void setNomorTPS(int nomorTPS) {
        this.nomorTPS = nomorTPS;
    }

    public int getJumlahDPT() {
        return jumlahDPT;
    }

    public void setJumlahDPT(int jumlahDPT) {
        this.jumlahDPT = jumlahDPT;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kodeTPS != null ? kodeTPS.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbTps)) {
            return false;
        }
        TbTps other = (TbTps) object;
        if ((this.kodeTPS == null && other.kodeTPS != null) || (this.kodeTPS != null && !this.kodeTPS.equals(other.kodeTPS))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.TbTps[ kodeTPS=" + kodeTPS + " ]";
    }
    
}
