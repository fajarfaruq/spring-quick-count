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
@Table(name = "tb_suaratidaksahtps")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbSuaratidaksahtps.findAll", query = "SELECT t FROM TbSuaratidaksahtps t"),
    @NamedQuery(name = "TbSuaratidaksahtps.findByKodeTPS", query = "SELECT t FROM TbSuaratidaksahtps t WHERE t.kodeTPS = :kodeTPS"),
    @NamedQuery(name = "TbSuaratidaksahtps.findBySuaraTidakSah", query = "SELECT t FROM TbSuaratidaksahtps t WHERE t.suaraTidakSah = :suaraTidakSah"),
    @NamedQuery(name = "TbSuaratidaksahtps.findByTervalidasi", query = "SELECT t FROM TbSuaratidaksahtps t WHERE t.tervalidasi = :tervalidasi")})
public class TbSuaratidaksahtps implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Kode_TPS")
    private Integer kodeTPS;
    @Basic(optional = false)
    @Column(name = "Suara_Tidak_Sah")
    private int suaraTidakSah;
    @Basic(optional = false)
    @Column(name = "Tervalidasi")
    private String tervalidasi;

    public TbSuaratidaksahtps() {
    }

    public TbSuaratidaksahtps(Integer kodeTPS) {
        this.kodeTPS = kodeTPS;
    }

    public TbSuaratidaksahtps(Integer kodeTPS, int suaraTidakSah, String tervalidasi) {
        this.kodeTPS = kodeTPS;
        this.suaraTidakSah = suaraTidakSah;
        this.tervalidasi = tervalidasi;
    }

    public Integer getKodeTPS() {
        return kodeTPS;
    }

    public void setKodeTPS(Integer kodeTPS) {
        this.kodeTPS = kodeTPS;
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
        hash += (kodeTPS != null ? kodeTPS.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbSuaratidaksahtps)) {
            return false;
        }
        TbSuaratidaksahtps other = (TbSuaratidaksahtps) object;
        if ((this.kodeTPS == null && other.kodeTPS != null) || (this.kodeTPS != null && !this.kodeTPS.equals(other.kodeTPS))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.TbSuaratidaksahtps[ kodeTPS=" + kodeTPS + " ]";
    }
    
}
