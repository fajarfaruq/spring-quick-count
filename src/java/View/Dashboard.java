/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ControllerGrafik;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Administrator
 */
@Controller
public class Dashboard {

    ControllerGrafik cg = new ControllerGrafik();

    @RequestMapping("/dashboard/konten1")
    public String konten1(ModelMap model, HttpSession session, HttpServletRequest request) {
        String Halaman = "redirect";
        try {
                model.addAttribute("listhasil", cetaklisthasil(request));
                model.addAttribute("listhasilgrafik", getListhasilgrafik());
                Halaman = "dashboard1";
        } catch (Exception e) {
            Halaman = "redirect";
        }
        return Halaman;
    }
    String listhasilgrafik;

    public String getListhasilgrafik() {
        return listhasilgrafik;
    }

    public void setListhasilgrafik(String listhasilgrafik) {
        this.listhasilgrafik = listhasilgrafik;
    }

    public String cetaklisthasil(HttpServletRequest request) throws IOException {
        String[] warna = {"#4897F1", "#263C53", "#A4D53A", "#AA1919", "#1AADCE", "#492970"};
        String hasil = "";
        setListhasilgrafik("");
        listhasil lh = new listhasil();
        cg.realtimepasangancalon1(lh);
        int jml = Integer.parseInt(cg.jumlahdata());
        for (int i = 0; i < jml; i++) {
            hasil = hasil + "<div id='itemcalon'>"
                    + "<div id='gambar'>"
                    + "<img src='" + request.getContextPath().toString() + "/upload/pasangancalon/" + lh.gambar[i] + "' style='width:100px;float:left'>"
                    + "</div>"
                    + "<div id='namacalon'>" + lh.gubernur[i] + " - " + lh.wakilgubernur[i] + "</div>"
                    + "<div id='persentase'><span style='color: " + warna[i] + ";'> " + lh.persentase[i] + "%</span></div>"
                    + "<div id='jmlsuara'>" + lh.suarasah[i] + " Suara</div>"
                    + "</div><br/>";

            setListhasilgrafik(getListhasilgrafik() + "['" + lh.gubernur[i] + " - " + lh.wakilgubernur[i] + "'," + lh.persentase[i].replace(",", ".") + "],");

        }
        return hasil;
    }
    
    public String cetakdatagrafikcalon() throws IOException{
        String hasil = "";
        setListhasilgrafik("");
        listhasil lh = new listhasil();
        cg.realtimepasangancalon1(lh);
        int jml = Integer.parseInt(cg.jumlahdata());
        for (int i = 0; i < jml; i++) {
            hasil = hasil + "['" + lh.gubernur[i] + " - " + lh.wakilgubernur[i] + "'," + lh.persentase[i].replace(",", ".") + "],";

        }
        return hasil;
    }

    @RequestMapping("/dashboard/konten2")
    public String konten2(ModelMap model, HttpSession session, HttpServletRequest request) {
        String Halaman = "redirect";
        String hasil = "";
        String datahasil = "";
        try {
                hasil = cg.realtimelistkecamatan();
                datahasil = cg.detailrealtimekecamatan();
                model.addAttribute("listkecamatan", hasil);
                model.addAttribute("datahasil", datahasil);
                Halaman = "dashboard2";
        } catch (Exception e) {
            Halaman = "redirect";
        }
        return Halaman;
    }

    public static class listhasil {

        public int[] kodepasangancalon;
        public String[] gubernur;
        public String[] wakilgubernur;
        public String[] gambar;
        public String[] persentase;
        public int[] suarasah;
        
        
        public int[] getSuarasah() {
            return suarasah;
        }

        public void setSuarasah(int[] suarasah) {
            this.suarasah = suarasah;
        }

        public String[] getGambar() {
            return gambar;
        }

        public void setGambar(String[] gambar) {
            this.gambar = gambar;
        }

        public String[] getGubernur() {
            return gubernur;
        }

        public void setGubernur(String[] gubernur) {
            this.gubernur = gubernur;
        }

        public String[] getWakilgubernur() {
            return wakilgubernur;
        }

        public void setWakilgubernur(String[] wakilgubernur) {
            this.wakilgubernur = wakilgubernur;
        }

        public String[] getPersentase() {
            return persentase;
        }

        public void setPersentase(String[] persentase) {
            this.persentase = persentase;
        }
    }
}
