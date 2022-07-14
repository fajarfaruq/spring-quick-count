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
@Table(name = "tampiltps")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tampiltps.findAll", query = "SELECT t FROM Tampiltps t"),
    @NamedQuery(name = "Tampiltps.findByKodeTPS", query = "SELECT t FROM Tampiltps t WHERE t.kodeTPS = :kodeTPS"),
    @NamedQuery(name = "Tampiltps.findByKodeKecamatan", query = "SELECT t FROM Tampiltps t WHERE t.kodeKecamatan = :kodeKecamatan"),
    @NamedQuery(name = "Tampiltps.findByNamaKecamatan", query = "SELECT t FROM Tampiltps t WHERE t.namaKecamatan = :namaKecamatan"),
    @NamedQuery(name = "Tampiltps.findByKodeKelurahan", query = "SELECT t FROM Tampiltps t WHERE t.kodeKelurahan = :kodeKelurahan"),
    @NamedQuery(name = "Tampiltps.findByNamaKelurahan", query = "SELECT t FROM Tampiltps t WHERE t.namaKelurahan = :namaKelurahan"),
    @NamedQuery(name = "Tampiltps.findByNomorTPS", query = "SELECT t FROM Tampiltps t WHERE t.nomorTPS = :nomorTPS"),
    @NamedQuery(name = "Tampiltps.findByJumlahDPT", query = "SELECT t FROM Tampiltps t WHERE t.jumlahDPT = :jumlahDPT")})
public class Tampiltps implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "Kode_TPS")
    @Id
    private int kodeTPS;
    @Basic(optional = false)
    @Column(name = "Kode_Kecamatan")
    private String kodeKecamatan;
    @Column(name = "Nama_Kecamatan")
    private String namaKecamatan;
    @Basic(optional = false)
    @Column(name = "Kode_Kelurahan")
    private String kodeKelurahan;
    @Basic(optional = false)
    @Column(name = "Nama_Kelurahan")
    private String namaKelurahan;
    @Basic(optional = false)
    @Column(name = "Nomor_TPS")
    private int nomorTPS;
    @Basic(optional = false)
    @Column(name = "Jumlah_DPT")
    private int jumlahDPT;

    public Tampiltps() {
    }

    public int getKodeTPS() {
        return kodeTPS;
    }

    public void setKodeTPS(int kodeTPS) {
        this.kodeTPS = kodeTPS;
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

    public String getKodeKelurahan() {
        return kodeKelurahan;
    }

    public void setKodeKelurahan(String kodeKelurahan) {
        this.kodeKelurahan = kodeKelurahan;
    }

    public String getNamaKelurahan() {
        return namaKelurahan;
    }

    public void setNamaKelurahan(String namaKelurahan) {
        this.namaKelurahan = namaKelurahan;
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
    
}
