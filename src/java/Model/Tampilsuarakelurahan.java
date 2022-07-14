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
 * @author user
 */
@Entity
@Table(name = "tampilsuarakelurahan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tampilsuarakelurahan.findAll", query = "SELECT t FROM Tampilsuarakelurahan t"),
    @NamedQuery(name = "Tampilsuarakelurahan.findByKodeKecamatan", query = "SELECT t FROM Tampilsuarakelurahan t WHERE t.kodeKecamatan = :kodeKecamatan"),
    @NamedQuery(name = "Tampilsuarakelurahan.findByNamaKecamatan", query = "SELECT t FROM Tampilsuarakelurahan t WHERE t.namaKecamatan = :namaKecamatan"),
    @NamedQuery(name = "Tampilsuarakelurahan.findByKodeKelurahan", query = "SELECT t FROM Tampilsuarakelurahan t WHERE t.kodeKelurahan = :kodeKelurahan"),
    @NamedQuery(name = "Tampilsuarakelurahan.findByNamaKelurahan", query = "SELECT t FROM Tampilsuarakelurahan t WHERE t.namaKelurahan = :namaKelurahan"),
    @NamedQuery(name = "Tampilsuarakelurahan.findByJumlahDPT", query = "SELECT t FROM Tampilsuarakelurahan t WHERE t.jumlahDPT = :jumlahDPT"),
    @NamedQuery(name = "Tampilsuarakelurahan.findBySuarasah", query = "SELECT t FROM Tampilsuarakelurahan t WHERE t.suarasah = :suarasah"),
    @NamedQuery(name = "Tampilsuarakelurahan.findBySuaraTidakSah", query = "SELECT t FROM Tampilsuarakelurahan t WHERE t.suaraTidakSah = :suaraTidakSah")})
public class Tampilsuarakelurahan implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "Kode_Kecamatan")
    private String kodeKecamatan;
    @Column(name = "Nama_Kecamatan")
    private String namaKecamatan;
    @Basic(optional = false)
    @Column(name = "Kode_Kelurahan")
    @Id
    private String kodeKelurahan;
    @Basic(optional = false)
    @Column(name = "Nama_Kelurahan")
    private String namaKelurahan;
    @Column(name = "Jumlah_DPT")
    private BigInteger jumlahDPT;
    @Column(name = "suarasah")
    private BigInteger suarasah;
    @Column(name = "Suara_Tidak_Sah")
    private BigInteger suaraTidakSah;

    public Tampilsuarakelurahan() {
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

    public BigInteger getJumlahDPT() {
        return jumlahDPT;
    }

    public void setJumlahDPT(BigInteger jumlahDPT) {
        this.jumlahDPT = jumlahDPT;
    }

    public BigInteger getSuarasah() {
        return suarasah;
    }

    public void setSuarasah(BigInteger suarasah) {
        this.suarasah = suarasah;
    }

    public BigInteger getSuaraTidakSah() {
        return suaraTidakSah;
    }

    public void setSuaraTidakSah(BigInteger suaraTidakSah) {
        this.suaraTidakSah = suaraTidakSah;
    }
    
}
