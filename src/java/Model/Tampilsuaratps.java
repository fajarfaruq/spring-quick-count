/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "tampilsuaratps")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tampilsuaratps.findAll", query = "SELECT t FROM Tampilsuaratps t"),
    @NamedQuery(name = "Tampilsuaratps.findByKodeSuaraTPS", query = "SELECT t FROM Tampilsuaratps t WHERE t.kodeSuaraTPS = :kodeSuaraTPS"),
    @NamedQuery(name = "Tampilsuaratps.findByKodeKecamatan", query = "SELECT t FROM Tampilsuaratps t WHERE t.kodeKecamatan = :kodeKecamatan"),
    @NamedQuery(name = "Tampilsuaratps.findByNamaKecamatan", query = "SELECT t FROM Tampilsuaratps t WHERE t.namaKecamatan = :namaKecamatan"),
    @NamedQuery(name = "Tampilsuaratps.findByKodeKelurahan", query = "SELECT t FROM Tampilsuaratps t WHERE t.kodeKelurahan = :kodeKelurahan"),
    @NamedQuery(name = "Tampilsuaratps.findByNamaKelurahan", query = "SELECT t FROM Tampilsuaratps t WHERE t.namaKelurahan = :namaKelurahan"),
    @NamedQuery(name = "Tampilsuaratps.findByKodeTPS", query = "SELECT t FROM Tampilsuaratps t WHERE t.kodeTPS = :kodeTPS"),
    @NamedQuery(name = "Tampilsuaratps.findByNomorTPS", query = "SELECT t FROM Tampilsuaratps t WHERE t.nomorTPS = :nomorTPS"),
    @NamedQuery(name = "Tampilsuaratps.findByJumlahDPT", query = "SELECT t FROM Tampilsuaratps t WHERE t.jumlahDPT = :jumlahDPT"),
    @NamedQuery(name = "Tampilsuaratps.findBySuarasah", query = "SELECT t FROM Tampilsuaratps t WHERE t.suarasah = :suarasah"),
    @NamedQuery(name = "Tampilsuaratps.findBySuaraTidakSah", query = "SELECT t FROM Tampilsuaratps t WHERE t.suaraTidakSah = :suaraTidakSah"),
    @NamedQuery(name = "Tampilsuaratps.findByTervalidasi", query = "SELECT t FROM Tampilsuaratps t WHERE t.tervalidasi = :tervalidasi")})
public class Tampilsuaratps implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "Kode_SuaraTPS")
    @Id
    private int kodeSuaraTPS;
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
    @Column(name = "Kode_TPS")
    private int kodeTPS;
    @Basic(optional = false)
    @Column(name = "Nomor_TPS")
    private int nomorTPS;
    @Basic(optional = false)
    @Column(name = "Jumlah_DPT")
    private int jumlahDPT;
    @Column(name = "suarasah")
    private BigInteger suarasah;
    @Basic(optional = false)
    @Column(name = "Suara_Tidak_Sah")
    private int suaraTidakSah;
    @Basic(optional = false)
    @Column(name = "Tervalidasi")
    private String tervalidasi;

    public Tampilsuaratps() {
    }

    public int getKodeSuaraTPS() {
        return kodeSuaraTPS;
    }

    public void setKodeSuaraTPS(int kodeSuaraTPS) {
        this.kodeSuaraTPS = kodeSuaraTPS;
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

    public int getKodeTPS() {
        return kodeTPS;
    }

    public void setKodeTPS(int kodeTPS) {
        this.kodeTPS = kodeTPS;
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

    public BigInteger getSuarasah() {
        return suarasah;
    }

    public void setSuarasah(BigInteger suarasah) {
        this.suarasah = suarasah;
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
    
}
