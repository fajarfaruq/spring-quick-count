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
@Table(name = "tampilsuaratpsdetail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tampilsuaratpsdetail.findAll", query = "SELECT t FROM Tampilsuaratpsdetail t"),
    @NamedQuery(name = "Tampilsuaratpsdetail.findByKodeSuaraTPS", query = "SELECT t FROM Tampilsuaratpsdetail t WHERE t.kodeSuaraTPS = :kodeSuaraTPS"),
    @NamedQuery(name = "Tampilsuaratpsdetail.findByKodeTPS", query = "SELECT t FROM Tampilsuaratpsdetail t WHERE t.kodeTPS = :kodeTPS"),
    @NamedQuery(name = "Tampilsuaratpsdetail.findByKodePasangancalon", query = "SELECT t FROM Tampilsuaratpsdetail t WHERE t.kodePasangancalon = :kodePasangancalon"),
    @NamedQuery(name = "Tampilsuaratpsdetail.findByGambar", query = "SELECT t FROM Tampilsuaratpsdetail t WHERE t.gambar = :gambar"),
    @NamedQuery(name = "Tampilsuaratpsdetail.findByGubernur", query = "SELECT t FROM Tampilsuaratpsdetail t WHERE t.gubernur = :gubernur"),
    @NamedQuery(name = "Tampilsuaratpsdetail.findByWakilGubernur", query = "SELECT t FROM Tampilsuaratpsdetail t WHERE t.wakilGubernur = :wakilGubernur"),
    @NamedQuery(name = "Tampilsuaratpsdetail.findBySuaraSah", query = "SELECT t FROM Tampilsuaratpsdetail t WHERE t.suaraSah = :suaraSah")})
public class Tampilsuaratpsdetail implements Serializable {
    private static final long serialVersionUID = 1L;
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

    public Tampilsuaratpsdetail() {
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
