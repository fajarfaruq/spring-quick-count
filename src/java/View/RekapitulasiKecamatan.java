/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ControllerRekapitulasiKecamatan;
import Controller.ControllerRekapitulasiKelurahan;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author user
 */
@Controller
public class RekapitulasiKecamatan {
    MenuHandle mh = new MenuHandle();
    ControllerRekapitulasiKecamatan crk = new ControllerRekapitulasiKecamatan();
    @RequestMapping("/rekapitulasi/kecamatan")
    public String suara(ModelMap model, HttpSession session, HttpServletRequest request) {
        String Halaman = "redirect";
        try {
            if (session.getAttribute("user").equals("")) {
                Halaman = "redirect";
            } else {
                model.addAttribute("kontenutama","rekapitulasi/rekapitulasikecamatan.jsp");
                model.addAttribute("alert","");
                mh.menuhandle(model,"rekapitulasi","rekapitulasikecamatan");
                Halaman = "index";
            }
        } catch (Exception e) {
            Halaman = "redirect";
        }
        return Halaman;
    }   
 
    @RequestMapping(value = "/rekapitulasi/kecamatan/tampil", method = RequestMethod.POST)
    public @ResponseBody
    listTbRekapitulasiKecamatan tampilrekapitulasiKecamatanJSON(@RequestParam(value = "rows") String rows,
            @RequestParam(value = "page") String page,@RequestParam(value = "kodekecamatan", required = false) String kodekecamatan ) throws IOException {
        listTbRekapitulasiKecamatan tbRekapitulasiKecamatan = new listTbRekapitulasiKecamatan();
        listTbRekapitulasiKecamatan2 tbRekapitulasiKecamatan2 = new listTbRekapitulasiKecamatan2();
        List<listTbRekapitulasiKecamatan1> a = new ArrayList<listTbRekapitulasiKecamatan1>();
        int r = (rows != null) ? Integer.parseInt(rows) : 10;
        int p = (page != null) ? Integer.parseInt(page) : 1;
        kodekecamatan = (kodekecamatan != null) ? kodekecamatan : "";

        int offset = (p - 1) * r;
        int jml = Integer.parseInt(crk.jumlahdata());
        if (r >= jml) {
            r = jml;
        }
        crk.tampilviewrekapitulasikecamatan(tbRekapitulasiKecamatan2, kodekecamatan, offset, r);
        tbRekapitulasiKecamatan.setTotal(crk.jumlahdata());
        for (int i = 0; i < r; i++) {
            a.add(new listTbRekapitulasiKecamatan1(tbRekapitulasiKecamatan2.kodesuarakecamatan[i], tbRekapitulasiKecamatan2.kodekecamatan[i], tbRekapitulasiKecamatan2.namakecamatan[i]
                    , tbRekapitulasiKecamatan2.jmldpt[i], tbRekapitulasiKecamatan2.suarasah[i], tbRekapitulasiKecamatan2.suaratidaksah[i]));
            //System.out.println(i);
        }
        tbRekapitulasiKecamatan.setRows(a);
        return tbRekapitulasiKecamatan;
    } 
    @RequestMapping("/rekapitulasi/kecamatan/tampildetail/{kodekecamatan}")
    public @ResponseBody
    List<listTbDetailRekapitulasiKecamatan1> tampilDetailKecamatanJSON(@PathVariable String kodekecamatan) {
        listTbDetailRekapitulasiKecamatan2 detailRekapitulasiKecamatan2 = new listTbDetailRekapitulasiKecamatan2();
        List<listTbDetailRekapitulasiKecamatan1> a = new ArrayList<listTbDetailRekapitulasiKecamatan1>();
        int jml = Integer.parseInt(crk.jumlahdatadetail(kodekecamatan));
        crk.tampilviewdetailrekapitulasikecamatan(detailRekapitulasiKecamatan2, kodekecamatan);
        for (int i = 0; i < jml; i++) {
            a.add(new listTbDetailRekapitulasiKecamatan1(detailRekapitulasiKecamatan2.kodesuarakecamatan[i],detailRekapitulasiKecamatan2.kodekecamatan[i], detailRekapitulasiKecamatan2.kodepasangancalon[i],
                    detailRekapitulasiKecamatan2.gambar[i], detailRekapitulasiKecamatan2.gubernur[i], detailRekapitulasiKecamatan2.wakilgubernur[i], detailRekapitulasiKecamatan2.suarasah[i]));
            //System.out.println(i);
        }
        return a;
    }        
    
    //Dari Rekapitulasi Kelurahan
    @RequestMapping("/rekapitulasi/kelurahan/tampildetailkecamatansementara/{kodekecamatan}")
    public @ResponseBody
    List<listTbDetailRekapitulasiKecamatan1> tampilDetailKelurahanKecamatanSementaraJSON(@PathVariable String kodekecamatan) {
        listTbDetailRekapitulasiKecamatan2 detailRekapitulasiKecamatan2 = new listTbDetailRekapitulasiKecamatan2();
        List<listTbDetailRekapitulasiKecamatan1> a = new ArrayList<listTbDetailRekapitulasiKecamatan1>();
        ControllerRekapitulasiKelurahan crkel = new ControllerRekapitulasiKelurahan();
        int jml = Integer.parseInt(crkel.jumlahdatadetail(kodekecamatan));
        crkel.tampilviewdetailrekapitulasikelurahankecamatansementara(detailRekapitulasiKecamatan2, kodekecamatan);
        for (int i = 0; i < 3; i++) {
            a.add(new listTbDetailRekapitulasiKecamatan1(detailRekapitulasiKecamatan2.kodesuarakecamatan[i],detailRekapitulasiKecamatan2.kodekecamatan[i], detailRekapitulasiKecamatan2.kodepasangancalon[i],
                    detailRekapitulasiKecamatan2.gambar[i], detailRekapitulasiKecamatan2.gubernur[i], detailRekapitulasiKecamatan2.wakilgubernur[i], detailRekapitulasiKecamatan2.suarasah[i]));
        }
        return a;
    }
    public static class listTbRekapitulasiKecamatan {

        String Total;
        List<listTbRekapitulasiKecamatan1> rows;

        public List<listTbRekapitulasiKecamatan1> getRows() {
            return rows;
        }

        public void setRows(List<listTbRekapitulasiKecamatan1> rows) {
            this.rows = rows;
        }

        public String getTotal() {
            return Total;
        }

        public void setTotal(String Total) {
            this.Total = Total;
        }

    }        
   private static class listTbRekapitulasiKecamatan1 {
        int kodesuarakecamatan;
        String kodekecamatan;
        String namakecamatan;
        int jmldpt;
        int suarasah;
        int suaratidaksah;

        public listTbRekapitulasiKecamatan1(int KodeSuaraKecamatan , String KodeKecamatan, String NamaKecamatan , int JmlDpt , int SuaraSah , int SuaraTidakSah) {
            setKodesuarakecamatan(KodeSuaraKecamatan);
            setKodekecamatan(KodeKecamatan);
            setNamakecamatan(NamaKecamatan);
            setJmldpt(JmlDpt);
            setSuarasah(SuaraSah);
            setSuaratidaksah(SuaraTidakSah);
        }

        public int getKodesuarakecamatan() {
            return kodesuarakecamatan;
        }

        public void setKodesuarakecamatan(int kodesuarakecamatan) {
            this.kodesuarakecamatan = kodesuarakecamatan;
        }

        public String getKodekecamatan() {
            return kodekecamatan;
        }

        public void setKodekecamatan(String kodekecamatan) {
            this.kodekecamatan = kodekecamatan;
        }

        public String getNamakecamatan() {
            return namakecamatan;
        }

        public void setNamakecamatan(String namakecamatan) {
            this.namakecamatan = namakecamatan;
        }

        public int getJmldpt() {
            return jmldpt;
        }

        public void setJmldpt(int jmldpt) {
            this.jmldpt = jmldpt;
        }

        public int getSuarasah() {
            return suarasah;
        }

        public void setSuarasah(int suarasah) {
            this.suarasah = suarasah;
        }

        public int getSuaratidaksah() {
            return suaratidaksah;
        }

        public void setSuaratidaksah(int suaratidaksah) {
            this.suaratidaksah = suaratidaksah;
        }


    }
    
    public class listTbRekapitulasiKecamatan2 {    
        int[] kodesuarakecamatan;
        String[] kodekecamatan;
        String[] namakecamatan;
        int[] jmldpt;
        int[] suarasah;
        int[] suaratidaksah;

        public int[] getKodesuarakecamatan() {
            return kodesuarakecamatan;
        }

        public void setKodesuarakecamatan(int[] kodesuarakecamatan) {
            this.kodesuarakecamatan = kodesuarakecamatan;
        }

        public String[] getKodekecamatan() {
            return kodekecamatan;
        }

        public void setKodekecamatan(String[] kodekecamatan) {
            this.kodekecamatan = kodekecamatan;
        }

        public String[] getNamakecamatan() {
            return namakecamatan;
        }

        public void setNamakecamatan(String[] namakecamatan) {
            this.namakecamatan = namakecamatan;
        }

        public int[] getJmldpt() {
            return jmldpt;
        }

        public void setJmldpt(int[] jmldpt) {
            this.jmldpt = jmldpt;
        }

        public int[] getSuarasah() {
            return suarasah;
        }

        public void setSuarasah(int[] suarasah) {
            this.suarasah = suarasah;
        }

        public int[] getSuaratidaksah() {
            return suaratidaksah;
        }

        public void setSuaratidaksah(int[] suaratidaksah) {
            this.suaratidaksah = suaratidaksah;
        }


    }
    
    
    public class listTbDetailRekapitulasiKecamatan1 {
        int kodesuarakecamatan;
        String kodekecamatan;
        int kodepasangancalon;
        String gambar;
        String gubernur;
        String wakilgubernur;
        int suarasah;
        
        public listTbDetailRekapitulasiKecamatan1(int kodeSuarakecamatan , String kodeKecamatan ,int kodePasanganCalon, String Gambar , String Gubernur , String wakilGubernur , int suaraSah){
            setKodesuarakecamatan(kodeSuarakecamatan);
            setKodekecamatan(kodeKecamatan);
            setKodepasangancalon(kodePasanganCalon);
            setGambar(Gambar);
            setGubernur(Gubernur);
            setWakilgubernur(wakilGubernur);
            setSuarasah(suaraSah);
        }

        public int getKodesuarakecamatan() {
            return kodesuarakecamatan;
        }

        public void setKodesuarakecamatan(int kodesuarakecamatan) {
            this.kodesuarakecamatan = kodesuarakecamatan;
        }

        public String getKodekecamatan() {
            return kodekecamatan;
        }

        public void setKodekecamatan(String kodekecamatan) {
            this.kodekecamatan = kodekecamatan;
        }

        public int getKodepasangancalon() {
            return kodepasangancalon;
        }

        public void setKodepasangancalon(int kodepasangancalon) {
            this.kodepasangancalon = kodepasangancalon;
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
    public class listTbDetailRekapitulasiKecamatan2 {
        int[] kodesuarakecamatan;
        String[] kodekecamatan;
        int[] kodepasangancalon;
        String[] gambar;
        String[] gubernur;
        String[] wakilgubernur;
        int[] suarasah;        

        public int[] getKodesuarakecamatan() {
            return kodesuarakecamatan;
        }

        public void setKodesuarakecamatan(int[] kodesuarakecamatan) {
            this.kodesuarakecamatan = kodesuarakecamatan;
        }

        public String[] getKodekecamatan() {
            return kodekecamatan;
        }

        public void setKodekecamatan(String[] kodekecamatan) {
            this.kodekecamatan = kodekecamatan;
        }

       
        public int[] getKodepasangancalon() {
            return kodepasangancalon;
        }

        public void setKodepasangancalon(int[] kodepasangancalon) {
            this.kodepasangancalon = kodepasangancalon;
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

        public int[] getSuarasah() {
            return suarasah;
        }

        public void setSuarasah(int[] suarasah) {
            this.suarasah = suarasah;
        }
    }
    public static class suaratpsvalidasi {

        boolean success;
        String msg;

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }    
}
