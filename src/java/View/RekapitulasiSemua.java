/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ControllerRekapitulasiSemua;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author user
 */
@Controller
public class RekapitulasiSemua {
    MenuHandle mh = new MenuHandle();
    ControllerRekapitulasiSemua crs = new ControllerRekapitulasiSemua();
    @RequestMapping("/rekapitulasi/semua")
    public String suara(ModelMap model, HttpSession session, HttpServletRequest request) {
        String Halaman = "redirect";
        try {
            if (session.getAttribute("user").equals("")) {
                Halaman = "redirect";
            } else {
                model.addAttribute("TotalDPT", crs.totalDPT());
                model.addAttribute("TotalSuaraSah", crs.totalSuaraSah());
                model.addAttribute("TotalSuaraTidakSah", crs.totalSuaraTidakSah());
                model.addAttribute("kontenutama","rekapitulasi/rekapitulasisemua.jsp");
                model.addAttribute("alert","");
                mh.menuhandle(model,"rekapitulasi","rekapitulasisemua");
                Halaman = "index";
            }
        } catch (Exception e) {
            Halaman = "redirect";
        }
        return Halaman;
    }
    
    @RequestMapping("/rekapitulasi/semua/tampil ")
    public @ResponseBody
    listTbRekapitulasiSemua tampilRekapitulasiSemuaJSON() throws IOException {
        listTbRekapitulasiSemua tbRekapitulasiSemua = new listTbRekapitulasiSemua();
        listTbRekapitulasiSemua2 rekapitulasiSemua2 = new listTbRekapitulasiSemua2();
        List<listTbRekapitulasiSemua1> a = new ArrayList<listTbRekapitulasiSemua1>();
        int jml = Integer.parseInt(crs.jumlahdata());

        crs.tampilsemuadata(rekapitulasiSemua2);
        tbRekapitulasiSemua.setTotal(crs.jumlahdata());
        for (int i = 0; i < jml; i++) {
            a.add(new listTbRekapitulasiSemua1(rekapitulasiSemua2.kodepasangancalon[i], rekapitulasiSemua2.gubernur[i], rekapitulasiSemua2.wakilgubernur[i], rekapitulasiSemua2.gambar[i],rekapitulasiSemua2.suarasah[i]));
            //System.out.println(i);
        }
        tbRekapitulasiSemua.setRows(a);
        return tbRekapitulasiSemua;
    }   
    
    @RequestMapping("/rekapitulasi/semua/print")
    public String suarakecamatanprint(ModelMap model, HttpSession session, HttpServletRequest request) {
        String Halaman = "redirect";
        try {
            if (session.getAttribute("user").equals("")) {
                Halaman = "redirect";
            } else {
                String data = crs.printrekapitulasikecamatan();
                model.addAttribute("data", data);
                model.addAttribute("totaldpt",crs.totalDPT());
                model.addAttribute("totalsuarasah", crs.totalSuaraSah());
                model.addAttribute("totalsuaratidaksah", crs.totalSuaraTidakSah());
                Halaman = "rekapitulasi/laporan/index";
            }
        } catch (Exception e) {
            Halaman = "redirect";
        }
        return Halaman;
    }
    public static class listTbRekapitulasiSemua {

        String Total;
        List<listTbRekapitulasiSemua1> rows;

        public String getTotal() {
            return Total;
        }

        public void setTotal(String Total) {
            this.Total = Total;
        }

        public List<listTbRekapitulasiSemua1> getRows() {
            return rows;
        }

        public void setRows(List<listTbRekapitulasiSemua1> rows) {
            this.rows = rows;
        }
    }

    public static class listTbRekapitulasiSemua1 {

        String kodepasangancalon;
        String gubernur;
        String wakilgubernur;
        String gambar;
        int suarasah;

        public String getGambar() {
            return gambar;
        }

        public void setGambar(String gambar) {
            this.gambar = gambar;
        }

        public listTbRekapitulasiSemua1(String Kodepasangacalon, String Gubernur, String Wakilgubernur,String Gambar, int SuaraSah) {
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

        public int getSuarasah() {
            return suarasah;
        }

        public void setSuarasah(int suarasah) {
            this.suarasah = suarasah;
        }
        
    }

    public static class listTbRekapitulasiSemua2 {

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

        public int[] getSuarasah() {
            return suarasah;
        }

        public void setSuarasah(int[] suarasah) {
            this.suarasah = suarasah;
        }
        
    }    
}
