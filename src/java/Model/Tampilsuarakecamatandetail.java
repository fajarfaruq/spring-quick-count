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
@Table(name = "tampilsuarakecamatandetail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tampilsuarakecamatandetail.findAll", query = "SELECT t FROM Tampilsuarakecamatandetail t"),
    @NamedQuery(name = "Tampilsuarakecamatandetail.findByKodeKecamatan", query = "SELECT t FROM Tampilsuarakecamatandetail t WHERE t.kodeKecamatan = :kodeKecamatan"),
    @NamedQuery(name = "Tampilsuarakecamatandetail.findByNamaKecamatan", query = "SELECT t FROM Tampilsuarakecamatandetail t WHERE t.namaKecamatan = :namaKecamatan"),
    @NamedQuery(name = "Tampilsuarakecamatandetail.findByKodeKelurahan", query = "SELECT t FROM Tampilsuarakecamatandetail t WHERE t.kodeKelurahan = :kodeKelurahan"),
    @NamedQuery(name = "Tampilsuarakecamatandetail.findByNamaKelurahan", query = "SELECT t FROM Tampilsuarakecamatandetail t WHERE t.namaKelurahan = :namaKelurahan"),
    @NamedQuery(name = "Tampilsuarakecamatandetail.findByKodeSuaraTPS", query = "SELECT t FROM Tampilsuarakecamatandetail t WHERE t.kodeSuaraTPS = :kodeSuaraTPS"),
    @NamedQuery(name = "Tampilsuarakecamatandetail.findByKodeTPS", query = "SELECT t FROM Tampilsuarakecamatandetail t WHERE t.kodeTPS = :kodeTPS"),
    @NamedQuery(name = "Tampilsuarakecamatandetail.findByKodePasangancalon", query = "SELECT t FROM Tampilsuarakecamatandetail t WHERE t.kodePasangancalon = :kodePasangancalon"),
    @NamedQuery(name = "Tampilsuarakecamatandetail.findByGambar", query = "SELECT t FROM Tampilsuarakecamatandetail t WHERE t.gambar = :gambar"),
    @NamedQuery(name = "Tampilsuarakecamatandetail.findByGubernur", query = "SELECT t FROM Tampilsuarakecamatandetail t WHERE t.gubernur = :gubernur"),
    @NamedQuery(name = "Tampilsuarakecamatandetail.findByWakilGubernur", query = "SELECT t FROM Tampilsuarakecamatandetail t WHERE t.wakilGubernur = :wakilGubernur"),
    @NamedQuery(name = "Tampilsuarakecamatandetail.findBySuaraSah", query = "SELECT t FROM Tampilsuarakecamatandetail t WHERE t.suaraSah = :suaraSah")})
public class Tampilsuarakecamatandetail implements Serializable {
    private static final long serialVersionUID = 1L;
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
    @Column(name = "Kode_SuaraTPS")
    @Id
    private int kodeSuaraTPS;
    @Basic(optional = false)
    @Column(name = "Kode_TPS")
    private int kodeTPS;
    @Basic(optional = false)
    @Column(name = "Kode_Pasangancalon")
    private String kodePasangancalon;
    @Basic(optional = false)
    @Column(name = "Gambar")
    private String gambar;
    @Basic(optional = false)
    @Column(name = "Gubernur")
    private String gubernur;
    @Basic(optional = false)
    @Column(name = "Wakil_Gubernur")
    private String wakilGubernur;
    @Basic(optional = false)
    @Column(name = "Suara_Sah")
    private int suaraSah;

    public Tampilsuarakecamatandetail() {
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

    public int getKodeSuaraTPS() {
        return kodeSuaraTPS;
    }

    public void setKodeSuaraTPS(int kodeSuaraTPS) {
        this.kodeSuaraTPS = kodeSuaraTPS;
    }

    public int getKodeTPS() {
        return kodeTPS;
    }

    public void setKodeTPS(int kodeTPS) {
        this.kodeTPS = kodeTPS;
    }

    public String getKodePasangancalon() {
        return kodePasangancalon;
    }

    public void setKodePasangancalon(String kodePasangancalon) {
        this.kodePasangancalon = kodePasangancalon;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getGubernur() {
        return gubernur;
    }

    public void setGubernur(String gubernur) {
        this.gubernur = gubernur;
    }

    public String getWakilGubernur() {
        return wakilGubernur;
    }

    public void setWakilGubernur(String wakilGubernur) {
        this.wakilGubernur = wakilGubernur;
    }

    public int getSuaraSah() {
        return suaraSah;
    }

    public void setSuaraSah(int suaraSah) {
        this.suaraSah = suaraSah;
    }
    
}
