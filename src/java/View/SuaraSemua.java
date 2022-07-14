/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ControllerSuaraSemua;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Administrator
 */
@Controller
public class SuaraSemua {

    MenuHandle mh = new MenuHandle();
    ControllerSuaraSemua csl = new ControllerSuaraSemua();
    @RequestMapping("/perolehansuara/semua")
    public String suara(ModelMap model, HttpSession session, HttpServletRequest request) {
        String Halaman = "redirect";
        try {
            if (session.getAttribute("user").equals("")) {
                Halaman = "redirect";
            } else {
                model.addAttribute("TotalDPT", csl.totalDPT());
                model.addAttribute("TotalSuaraSah", csl.totalSuaraSah());
                model.addAttribute("TotalSuaraTidakSah", csl.totalSuaraTidakSah());
                model.addAttribute("kontenutama", "perolehansuara/suarasemua.jsp");
                model.addAttribute("alert", "");
                mh.menuhandle(model, "suara", "suarasemua");
                Halaman = "index";
            }
        } catch (Exception e) {
            Halaman = "redirect";
        }
        return Halaman;
    }

    @RequestMapping("/perolehansuara/semua/tampil ")
    public @ResponseBody
    listTbSuaraSemua tampilSuaraSemuaJSON() throws IOException {
        listTbSuaraSemua tbSuaraSemua = new listTbSuaraSemua();
        listTbSuaraSemua2 suaraSemua2 = new listTbSuaraSemua2();
        List<listTbSuaraSemua1> a = new ArrayList<listTbSuaraSemua1>();
        int jml = Integer.parseInt(csl.jumlahdata());

        csl.tampilsemuadata(suaraSemua2);
        tbSuaraSemua.setTotal(csl.jumlahdata());
        for (int i = 0; i < jml; i++) {
            a.add(new listTbSuaraSemua1(suaraSemua2.kodepasangancalon[i], suaraSemua2.gubernur[i], suaraSemua2.wakilgubernur[i], suaraSemua2.gambar[i],suaraSemua2.suarasah[i]));
            //System.out.println(i);
        }
        tbSuaraSemua.setRows(a);
        return tbSuaraSemua;
    }
    
    public static class listTbSuaraSemua {

        String Total;
        List<listTbSuaraSemua1> rows;

        public String getTotal() {
            return Total;
        }

        public void setTotal(String Total) {
            this.Total = Total;
        }

        public List<listTbSuaraSemua1> getRows() {
            return rows;
        }

        public void setRows(List<listTbSuaraSemua1> rows) {
            this.rows = rows;
        }
    }

    public static class listTbSuaraSemua1 {

        String kodepasangancalon;
        String gubernur;
        String wakilgubernur;
        String gambar;
        int suarasah;

        public int getSuarasah() {
            return suarasah;
        }

        public void setSuarasah(int suarasah) {
            this.suarasah = suarasah;
        }

        public String getGambar() {
            return gambar;
        }

        public void setGambar(String gambar) {
            this.gambar = gambar;
        }

        public listTbSuaraSemua1(String Kodepasangacalon, String Gubernur, String Wakilgubernur,String Gambar, int SuaraSah) {
            setKodepasangancalon(Kodepasangacalon);
            setGubernur(Gubernur);
            setWakilgubernur(Wakilgubernur);
            setGambar(Gambar);
            setSuarasah(SuaraSah);
        }

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
    }

    public static class listTbSuaraSemua2 {

        String[] kodepasangancalon;
        String[] gubernur;
        String[] wakilgubernur;
        String[] gambar;
        int[] suarasah;

        public String[] getGambar() {
            return gambar;
        }

        public void setGambar(String[] gambar) {
            this.gambar = gambar;
        }

        public int[] getSuarasah() {
            return suarasah;
        }

        public void setSuarasah(int[] suarasah) {
            this.suarasah = suarasah;
        }

        public String[] getKodepasangancalon() {
            return kodepasangancalon;
        }

        public void setKodepasangancalon(String[] kodepasangancalon) {
            this.kodepasangancalon = kodepasangancalon;
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
    }
}
