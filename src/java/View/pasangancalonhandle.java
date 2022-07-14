/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 *
 * @author Administrator
 */
public class pasangancalonhandle {

    CommonsMultipartFile fileData;
    String kodepasangancalon;
    String gubernur;
    String wakilgubernur;
    String gambar;

    public String getKodepasangancalon() {
        return kodepasangancalon;
    }

    public void setKodepasangancalon(String kodepasangancalon) {
        this.kodepasangancalon = kodepasangancalon;
    }

    public String getGubernur() {
        return gubernur;
    }

    public void setGubernur(String gubernur) {
        this.gubernur = gubernur;
    }

    public String getWakilgubernur() {
        return wakilgubernur;
    }

    public void setWakilgubernur(String wakilgubernur) {
        this.wakilgubernur = wakilgubernur;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public CommonsMultipartFile getFileData() {
        return fileData;
    }

    public void setFileData(CommonsMultipartFile fileData) {
        this.fileData = fileData;
    }
}
