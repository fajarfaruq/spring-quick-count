/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ControllerSuaraKecamatan;
import Controller.ControllerSuaraSemua;
import java.io.IOException;
import java.math.BigInteger;
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
public class SuaraKecamatan {
    ControllerSuaraKecamatan csk = new ControllerSuaraKecamatan();
    ControllerSuaraSemua css = new ControllerSuaraSemua();
    MenuHandle mh = new MenuHandle();

    @RequestMapping("/perolehansuara/kecamatan")
    public String suara(ModelMap model, HttpSession session, HttpServletRequest request) {
        String Halaman = "redirect";
        try {
            if (session.getAttribute("user").equals("")) {
                Halaman = "redirect";
            } else {
                model.addAttribute("kontenutama", "perolehansuara/suarakecamatan.jsp");
                model.addAttribute("alert", "");
                mh.menuhandle(model, "suara", "suarakecamatan");
                Halaman = "index";
            }
        } catch (Exception e) {
            Halaman = "redirect";
        }
        return Halaman;
    }
    
    @RequestMapping(value = "/perolehansuara/kecamatan/tampil", method = RequestMethod.POST)
    public @ResponseBody
    listTbSuaraKecamatan tampilsuaraKecamatanJSON(@RequestParam(value = "rows", required = false) String rows,
            @RequestParam(value = "page", required = false) String page,@RequestParam(value = "kodekecamatan", required = false) String kodekecamatan) throws IOException {
        listTbSuaraKecamatan tbSuaraKecamatan = new listTbSuaraKecamatan();
        listTbSuaraKecamatan2 tbSuaraKecamatan2 = new listTbSuaraKecamatan2();
        List<listTbSuaraKecamatan1> a = new ArrayList<listTbSuaraKecamatan1>();
        int r = (rows != null) ? Integer.parseInt(rows) : 10;
        int p = (page != null) ? Integer.parseInt(page) : 1;
        kodekecamatan = (kodekecamatan != null) ? kodekecamatan : "";

        int offset = (p - 1) * r;
        int jml = Integer.parseInt(csk.jumlahdata());
        if (r >= jml) {
            r = jml;
        }
        csk.tampilviewsuarakecamatan(tbSuaraKecamatan2, kodekecamatan,offset, r);
        tbSuaraKecamatan.setTotal(csk.jumlahdata());
        for (int i = 0; i < r; i++) {
            a.add(new listTbSuaraKecamatan1(tbSuaraKecamatan2.kodekecamatan[i], tbSuaraKecamatan2.namakecamatan[i], 
                     tbSuaraKecamatan2.jumlahdpt[i], tbSuaraKecamatan2.suarasah[i],
                    tbSuaraKecamatan2.suaratidaksah[i]));
            //System.out.println(i);
        }
        tbSuaraKecamatan.setRows(a);
        return tbSuaraKecamatan;
    }
    
    @RequestMapping("/perolehansuara/kecamatan/tampil/{kodekecamatan}")
    public @ResponseBody
    listTbSuaraKecamatan tampilsuaratiapKecamatanJSON(@PathVariable String kodekecamatan) throws IOException {
        listTbSuaraKecamatan tbSuaraKecamatan = new listTbSuaraKecamatan();
        listTbSuaraKecamatan2 tbSuaraKecamatan2 = new listTbSuaraKecamatan2();
        List<listTbSuaraKecamatan1> a = new ArrayList<listTbSuaraKecamatan1>();

        int jml = Integer.parseInt(csk.jumlahdataterdaftar(kodekecamatan));
        csk.tampilviewsuarakecamatan(tbSuaraKecamatan2, kodekecamatan,0, 20);
        tbSuaraKecamatan.setTotal(csk.jumlahdataterdaftar(kodekecamatan));
        for (int i = 0; i < jml; i++) {
            a.add(new listTbSuaraKecamatan1(tbSuaraKecamatan2.kodekecamatan[i], tbSuaraKecamatan2.namakecamatan[i], 
                     tbSuaraKecamatan2.jumlahdpt[i], tbSuaraKecamatan2.suarasah[i],
                    tbSuaraKecamatan2.suaratidaksah[i]));
            //System.out.println(i);
        }
        tbSuaraKecamatan.setRows(a);
        return tbSuaraKecamatan;
    }
    
    @RequestMapping("/perolehansuara/kecamatan/tampildetail/{kodekecamatan}")
    public @ResponseBody
    List<listTbSuaraDetailKecamatan1> tampilKecamatanDetailJSON(@PathVariable String kodekecamatan) {
        listTbSuaraDetailKecamatan2 detailKecamatan2 = new listTbSuaraDetailKecamatan2();
        List<listTbSuaraDetailKecamatan1> a = new ArrayList<listTbSuaraDetailKecamatan1>();
        int jml = Integer.parseInt(csk.jumlahdatadetail(kodekecamatan));
        csk.tampilviewsuaradetailkecamatan(detailKecamatan2, kodekecamatan);
        for (int i = 0; i < jml; i++) {
            a.add(new listTbSuaraDetailKecamatan1(detailKecamatan2.kodekecamatan[i],detailKecamatan2.namakecamatan[i], detailKecamatan2.kodesuaratps[i],
                    detailKecamatan2.kodetps[i], detailKecamatan2.kodepasangancalon[i], detailKecamatan2.gambar[i],
                    detailKecamatan2.gubernur[i], detailKecamatan2.wakilgubernur[i], detailKecamatan2.suarasah[i]));
            //System.out.println(i);
        }
        return a;
    }
      
   @RequestMapping("/perolehansuara/kecamatan/print")
    public String suarakecamatanprint(ModelMap model, HttpSession session, HttpServletRequest request) {
        String Halaman = "redirect";
        try {
            if (session.getAttribute("user").equals("")) {
                Halaman = "redirect";
            } else {
                String data = csk.printsuarakecamatan();
                model.addAttribute("data", data);
                model.addAttribute("totaldpt",css.totalDPT());
                model.addAttribute("totalsuarasah", css.totalSuaraSah());
                model.addAttribute("totalsuaratidaksah", css.totalSuaraTidakSah());
                Halaman = "perolehansuara/laporan/index";
            }
        } catch (Exception e) {
            Halaman = "redirect";
        }
        return Halaman;
    }
        
    public static class listTbSuaraKecamatan {

        String Total;
        List<listTbSuaraKecamatan1> rows;

        public List<listTbSuaraKecamatan1> getRows() {
            return rows;
        }

        public void setRows(List<listTbSuaraKecamatan1> rows) {
            this.rows = rows;
        }

        public String getTotal() {
            return Total;
        }

        public void setTotal(String Total) {
            this.Total = Total;
        }
    }

    public static class listTbSuaraKecamatan1 {

        String kodekecamatan;
        String namakecamatan;
        Integer jumlahdpt;
        BigInteger suarasah;
        BigInteger suaratidaksah;
        
        public listTbSuaraKecamatan1(String KodeKecamatan , String NamaKecamatan , Integer JumlahDPT , BigInteger SuaraSah , BigInteger SuaraTidakSah){
            setKodekecamatan(KodeKecamatan);
            setNamakecamatan(NamaKecamatan);
            setJumlahdpt(JumlahDPT);
            setSuarasah(SuaraSah);
            setSuaratidaksah(SuaraTidakSah);
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
        
        public Integer getJumlahdpt() {
            return jumlahdpt;
        }

        public void setJumlahdpt(Integer jumlahdpt) {
            this.jumlahdpt = jumlahdpt;
        }

        public BigInteger getSuarasah() {
            return suarasah;
        }

        public void setSuarasah(BigInteger suarasah) {
            this.suarasah = suarasah;
        }

        public BigInteger getSuaratidaksah() {
            return suaratidaksah;
        }

        public void setSuaratidaksah(BigInteger suaratidaksah) {
            this.suaratidaksah = suaratidaksah;
        }
    }

    public class listTbSuaraKecamatan2 {
        String[] kodekecamatan;
        String[] namakecamatan;
        Integer[] jumlahdpt;
        BigInteger[] suarasah;
        BigInteger[] suaratidaksah;

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

        public Integer[] getJumlahdpt() {
            return jumlahdpt;
        }

        public void setJumlahdpt(Integer[] jumlahdpt) {
            this.jumlahdpt = jumlahdpt;
        }

        public BigInteger[] getSuarasah() {
            return suarasah;
        }

        public void setSuarasah(BigInteger[] suarasah) {
            this.suarasah = suarasah;
        }

        public BigInteger[] getSuaratidaksah() {
            return suaratidaksah;
        }

        public void setSuaratidaksah(BigInteger[] suaratidaksah) {
            this.suaratidaksah = suaratidaksah;
        }
    }

    private static class listTbSuaraDetailKecamatan1 {
        String kodekecamatan;
        String namakecamatan;
        int kodesuaratps;
        int kodetps;
        String kodepasangancalon;
        String gambar;
        String gubernur;
        String wakilgubernur;
        int suarasah;

        public listTbSuaraDetailKecamatan1(String KodeKecamatan,String NamaKecamatan, int kodeSuaratps, int kodeTPS, String kodePasanganCalon, String Gambar, String Gubernur, String wakilGubernur, int suaraSah) {
            setKodekecamatan(KodeKecamatan);
            setNamakecamatan(NamaKecamatan);
            setKodesuaratps(kodeSuaratps);
            setKodetps(kodeTPS);
            setKodepasangancalon(kodePasanganCalon);
            setGambar(Gambar);
            setGubernur(Gubernur);
            setWakilgubernur(wakilGubernur);
            setSuarasah(suaraSah);
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

        public int getKodesuaratps() {
            return kodesuaratps;
        }

        public void setKodesuaratps(int kodesuaratps) {
            this.kodesuaratps = kodesuaratps;
        }

        public int getKodetps() {
            return kodetps;
        }

        public void setKodetps(int kodetps) {
            this.kodetps = kodetps;
        }

        public String getKodepasangancalon() {
            return kodepasangancalon;
        }

        public void setKodepasangancalon(String kodepasangancalon) {
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

    public class listTbSuaraDetailKecamatan2 {

        String[] kodekecamatan;
        String[] namakecamatan;
        int[] kodesuaratps;
        int[] kodetps;
        String[] kodepasangancalon;
        String[] gambar;
        String[] gubernur;
        String[] wakilgubernur;
        int[] suarasah;
        
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

        public int[] getKodesuaratps() {
            return kodesuaratps;
        }

        public void setKodesuaratps(int[] kodesuaratps) {
            this.kodesuaratps = kodesuaratps;
        }

        public int[] getKodetps() {
            return kodetps;
        }

        public void setKodetps(int[] kodetps) {
            this.kodetps = kodetps;
        }

        public String[] getKodepasangancalon() {
            return kodepasangancalon;
        }

        public void setKodepasangancalon(String[] kodepasangancalon) {
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

    public static class suarakecamatanvalidasi {

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
