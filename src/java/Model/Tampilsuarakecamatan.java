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
@Table(name = "tampilsuarakecamatan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tampilsuarakecamatan.findAll", query = "SELECT t FROM Tampilsuarakecamatan t"),
    @NamedQuery(name = "Tampilsuarakecamatan.findByKodeKecamatan", query = "SELECT t FROM Tampilsuarakecamatan t WHERE t.kodeKecamatan = :kodeKecamatan"),
    @NamedQuery(name = "Tampilsuarakecamatan.findByNamaKecamatan", query = "SELECT t FROM Tampilsuarakecamatan t WHERE t.namaKecamatan = :namaKecamatan"),
    @NamedQuery(name = "Tampilsuarakecamatan.findByJumlahDPT", query = "SELECT t FROM Tampilsuarakecamatan t WHERE t.jumlahDPT = :jumlahDPT"),
    @NamedQuery(name = "Tampilsuarakecamatan.findBySuarasah", query = "SELECT t FROM Tampilsuarakecamatan t WHERE t.suarasah = :suarasah"),
    @NamedQuery(name = "Tampilsuarakecamatan.findBySuaraTidakSah", query = "SELECT t FROM Tampilsuarakecamatan t WHERE t.suaraTidakSah = :suaraTidakSah")})
public class Tampilsuarakecamatan implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "Kode_Kecamatan")
    @Id
    private String kodeKecamatan;
    @Column(name = "Nama_Kecamatan")
    private String namaKecamatan;
    @Column(name = "Jumlah_DPT")
    private BigInteger jumlahDPT;
    @Column(name = "suarasah")
    private BigInteger suarasah;
    @Column(name = "Suara_Tidak_Sah")
    private BigInteger suaraTidakSah;

    public Tampilsuarakecamatan() {
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
