/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ControllerSuaraKelurahan;
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
public class SuaraKelurahan {
     MenuHandle mh = new MenuHandle();
     ControllerSuaraKelurahan csk = new ControllerSuaraKelurahan();
     
    @RequestMapping("/perolehansuara/kelurahan")
    public String suara(ModelMap model, HttpSession session, HttpServletRequest request) {
        String Halaman = "redirect";
        try {
            if (session.getAttribute("user").equals("")) {
                Halaman = "redirect";
            } else {
                model.addAttribute("kontenutama","perolehansuara/suarakelurahan.jsp");
                model.addAttribute("alert","");
                mh.menuhandle(model,"suara","suarakelurahan");
                Halaman = "index";
            }
        } catch (Exception e) {
            Halaman = "redirect";
        }
        return Halaman;
    }     
    
    
    @RequestMapping(value = "/perolehansuara/kelurahan/tampil", method = RequestMethod.POST)
    public @ResponseBody
    listTbSuaraKelurahan tampilsuaraKelurahanJSON(@RequestParam(value = "rows") String rows,
            @RequestParam(value = "page") String page,@RequestParam(value = "kodekecamatan", required = false) String kodekecamatan,
            @RequestParam(value = "kodekelurahan", required = false) String kodekelurahan) throws IOException {
        listTbSuaraKelurahan tbSuaraKelurahan = new listTbSuaraKelurahan();
        listTbSuaraKelurahan2 tbSuaraKelurahan2 = new listTbSuaraKelurahan2();
        List<listTbSuaraKelurahan1> a = new ArrayList<listTbSuaraKelurahan1>();
        int r = (rows != null) ? Integer.parseInt(rows) : 10;
        int p = (page != null) ? Integer.parseInt(page) : 1;
        kodekecamatan = (kodekecamatan != null) ? kodekecamatan : "";
        kodekelurahan = (kodekelurahan != null) ? kodekelurahan : "";

        int offset = (p - 1) * r;
        int jml = Integer.parseInt(csk.jumlahdata());
        if (r >= jml) {
            r = jml;
        }
        csk.tampilviewsuarakelurahan(tbSuaraKelurahan2, kodekecamatan, kodekelurahan, offset, r);
        tbSuaraKelurahan.setTotal(csk.jumlahdata());
        for (int i = 0; i < r; i++) {
            a.add(new listTbSuaraKelurahan1(tbSuaraKelurahan2.kodekecamatan[i], tbSuaraKelurahan2.namakecamatan[i], 
                    tbSuaraKelurahan2.kodekelurahan[i], tbSuaraKelurahan2.namakelurahan[i], tbSuaraKelurahan2.jumlahdpt[i], tbSuaraKelurahan2.suarasah[i],
                    tbSuaraKelurahan2.suaratidaksah[i]));
            //System.out.println(i);
        }
        tbSuaraKelurahan.setRows(a);
        return tbSuaraKelurahan;
    }

  @RequestMapping("/perolehansuara/kelurahan/tampil/{kodekelurahan}")
    public @ResponseBody
    listTbSuaraKelurahan tampilsuaratiapKelurahanJSON(@PathVariable String kodekelurahan) throws IOException {
        listTbSuaraKelurahan tbSuaraKelurahan = new listTbSuaraKelurahan();
        listTbSuaraKelurahan2 tbSuaraKelurahan2 = new listTbSuaraKelurahan2();
        List<listTbSuaraKelurahan1> a = new ArrayList<listTbSuaraKelurahan1>();
        kodekelurahan = (kodekelurahan != null) ? kodekelurahan : "";


        int jml = Integer.parseInt(csk.jumlahdataterdaftar(kodekelurahan));

        csk.tampilviewsuarakelurahan(tbSuaraKelurahan2, "", kodekelurahan, 0, 10);
        tbSuaraKelurahan.setTotal(csk.jumlahdataterdaftar(kodekelurahan));
        for (int i = 0; i < jml; i++) {
            a.add(new listTbSuaraKelurahan1(tbSuaraKelurahan2.kodekecamatan[i], tbSuaraKelurahan2.namakecamatan[i], 
                    tbSuaraKelurahan2.kodekelurahan[i], tbSuaraKelurahan2.namakelurahan[i], tbSuaraKelurahan2.jumlahdpt[i], tbSuaraKelurahan2.suarasah[i],
                    tbSuaraKelurahan2.suaratidaksah[i]));
            //System.out.println(i);
        }
        tbSuaraKelurahan.setRows(a);
        return tbSuaraKelurahan;
    }

    @RequestMapping("/perolehansuara/kelurahan/tampildetail/{kodekelurahan}")
    public @ResponseBody
    List<listTbSuaraDetailKelurahan1> tampilKelurahanJSON(@PathVariable String kodekelurahan) {
        listTbSuaraDetailKelurahan2 detailKelurahan2 = new listTbSuaraDetailKelurahan2();
        List<listTbSuaraDetailKelurahan1> a = new ArrayList<listTbSuaraDetailKelurahan1>();
        int jml = Integer.parseInt(csk.jumlahdatadetail(kodekelurahan));
        csk.tampilviewsuaradetailkelurahan(detailKelurahan2, kodekelurahan);
        for (int i = 0; i < jml; i++) {
            a.add(new listTbSuaraDetailKelurahan1(detailKelurahan2.kodekelurahan[i], detailKelurahan2.kodesuaratps[i],
                    detailKelurahan2.kodetps[i], detailKelurahan2.kodepasangancalon[i], detailKelurahan2.gambar[i],
                    detailKelurahan2.gubernur[i], detailKelurahan2.wakilgubernur[i], detailKelurahan2.suarasah[i]));
            //System.out.println(i);
        }
        return a;
    }

    public static class listTbSuaraKelurahan {

        String Total;
        List<listTbSuaraKelurahan1> rows;

        public List<listTbSuaraKelurahan1> getRows() {
            return rows;
        }

        public void setRows(List<listTbSuaraKelurahan1> rows) {
            this.rows = rows;
        }

        public String getTotal() {
            return Total;
        }

        public void setTotal(String Total) {
            this.Total = Total;
        }
    }

    private static class listTbSuaraKelurahan1 {

        String kodekecamatan;
        String namakecamatan;
        String kodekelurahan;
        String namakelurahan;
        BigInteger jumlahdpt;
        BigInteger suarasah;
        BigInteger suaratidaksah;

        public listTbSuaraKelurahan1(String KodeKecamatan, String NamaKecamatan, String KodeKelurahan, String NamaKelurahan, BigInteger JumlahDPT,
                BigInteger SuaraSah, BigInteger SuaraTidakSah) {

            setKodekecamatan(KodeKecamatan);
            setNamakecamatan(NamaKecamatan);
            setKodekelurahan(KodeKelurahan);
            setNamakelurahan(NamaKelurahan);
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

        public String getKodekelurahan() {
            return kodekelurahan;
        }

        public void setKodekelurahan(String kodekelurahan) {
            this.kodekelurahan = kodekelurahan;
        }

        public String getNamakelurahan() {
            return namakelurahan;
        }

        public void setNamakelurahan(String namakelurahan) {
            this.namakelurahan = namakelurahan;
        }

        public BigInteger getJumlahdpt() {
            return jumlahdpt;
        }

        public void setJumlahdpt(BigInteger jumlahdpt) {
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

    public class listTbSuaraKelurahan2 {

        String[] kodekecamatan;
        String[] namakecamatan;
        String[] kodekelurahan;
        String[] namakelurahan;
        BigInteger[] jumlahdpt;
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

        public String[] getKodekelurahan() {
            return kodekelurahan;
        }

        public void setKodekelurahan(String[] kodekelurahan) {
            this.kodekelurahan = kodekelurahan;
        }

        public String[] getNamakelurahan() {
            return namakelurahan;
        }

        public void setNamakelurahan(String[] namakelurahan) {
            this.namakelurahan = namakelurahan;
        }

        public BigInteger[] getJumlahdpt() {
            return jumlahdpt;
        }

        public void setJumlahdpt(BigInteger[] jumlahdpt) {
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

    private static class listTbSuaraDetailKelurahan1 {

        String kodekelurahan;
        int kodesuaratps;
        int kodetps;
        String kodepasangancalon;
        String gambar;
        String gubernur;
        String wakilgubernur;
        int suarasah;

        public listTbSuaraDetailKelurahan1(String kodeKelurahan, int kodeSuaratps, int kodeTPS, String kodePasanganCalon, String Gambar, String Gubernur, String wakilGubernur, int suaraSah) {
            setKodekelurahan(kodeKelurahan);
            setKodesuaratps(kodeSuaratps);
            setKodetps(kodeTPS);
            setKodepasangancalon(kodePasanganCalon);
            setGambar(Gambar);
            setGubernur(Gubernur);
            setWakilgubernur(wakilGubernur);
            setSuarasah(suaraSah);
        }

        public String getKodekelurahan() {
            return kodekelurahan;
        }

        public void setKodekelurahan(String kodekelurahan) {
            this.kodekelurahan = kodekelurahan;
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

    public class listTbSuaraDetailKelurahan2 {

        String[] kodekelurahan;
        int[] kodesuaratps;
        int[] kodetps;
        String[] kodepasangancalon;
        String[] gambar;
        String[] gubernur;
        String[] wakilgubernur;
        int[] suarasah;

        public String[] getKodekelurahan() {
            return kodekelurahan;
        }

        public void setKodekelurahan(String[] kodekelurahan) {
            this.kodekelurahan = kodekelurahan;
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

    public static class suarakelurahanvalidasi {

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
