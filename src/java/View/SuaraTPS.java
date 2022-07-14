/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ControllerPasanganCalon;
import Controller.ControllerSuaraTPS;
import Model.TbSuarasahtps;
import Model.TbSuaratidaksahtps;
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
public class SuaraTPS {
    MenuHandle mh = new MenuHandle();
    ControllerSuaraTPS cstps =  new ControllerSuaraTPS();
    ControllerPasanganCalon cpc =  new ControllerPasanganCalon();
    
    @RequestMapping("/perolehansuara/tps")
    public String suara(ModelMap model, HttpSession session, HttpServletRequest request) {
        String Halaman = "redirect";
        try {
            if (session.getAttribute("user").equals("")) {
                Halaman = "redirect";
            } else {
                model.addAttribute("kontenutama","perolehansuara/suaratps.jsp");
                model.addAttribute("alert","");
                mh.menuhandle(model,"suara","suaratps");
                Halaman = "index";
            }
        } catch (Exception e) {
            Halaman = "redirect";
        }
        return Halaman;
    }     
    
    @RequestMapping(value = "/perolehansuara/tps/tambah", method = RequestMethod.POST)
    public @ResponseBody
    suaratpsvalidasi tambah(@RequestParam(value = "kodetps", required = false) String kodetps,
            @RequestParam(value = "suaratidaksah", required = false) String suaratidaksah,
            @RequestParam(value = "kodepasangancalon", required = false) String kodepasangancalon,
            @RequestParam(value = "suarasah", required = false) String suarasah,
            @RequestParam(value = "tervalidasi", required = false) String tervalidasi, HttpSession session) {
        
        int Kodetps = Integer.parseInt(kodetps);
        int Suaratidaksah = Integer.parseInt(suaratidaksah);
        int Suarasah = Integer.parseInt(suarasah);
        tervalidasi = "Belum";
        String respon = "0";
        suaratpsvalidasi kel = new suaratpsvalidasi();
        if (session.getAttribute("user").equals("")) {
            kel.setMsg("Penyimpanan gagal");
            kel.setSuccess(false);
        } else {
            respon = cstps.insert(new TbSuarasahtps(null, Kodetps, kodepasangancalon, Suarasah), new TbSuaratidaksahtps(Kodetps, Suaratidaksah, tervalidasi));
            if (respon.equals("1")) {
                kel.setMsg("Penyimpanan berhasil");
                kel.setSuccess(true);
            } else {
                kel.setMsg("Penyimpanan gagal");
                kel.setSuccess(false);
            }
        }
        return kel;
    }
    
    
    @RequestMapping(value = "/perolehansuara/tps/updatesuarasah", method = RequestMethod.POST)
    public @ResponseBody
    suaratpsvalidasi updatesuarasah(@RequestParam(value = "kodesuaratps", required = false) String kodesuaratps,
            @RequestParam(value = "kodetps", required = false) String kodetps,
            @RequestParam(value = "kodepasangancalon", required = false) String kodepasangancalon,
            @RequestParam(value = "suarasah", required = false) String suarasah, HttpSession session) {
        
        int Kodesuaratps = Integer.parseInt(kodesuaratps);
        int Kodetps = Integer.parseInt(kodetps);
        int Suarasah = Integer.parseInt(suarasah);
        String respon = "0";
        suaratpsvalidasi kel = new suaratpsvalidasi();
        if (session.getAttribute("user").equals("")) {
            kel.setMsg("Penyimpanan gagal");
            kel.setSuccess(false);
        } else {
            respon = cstps.updatesuarasah(new TbSuarasahtps(Kodesuaratps, Kodetps, kodepasangancalon, Suarasah));
            if (respon.equals("1")) {
                kel.setMsg("Penyimpanan berhasil");
                kel.setSuccess(true);
            } else {
                kel.setMsg("Penyimpanan gagal");
                kel.setSuccess(false);
            }
        }
        return kel;
    }
    
    @RequestMapping(value = "/perolehansuara/tps/updatesuaratidaksah", method = RequestMethod.POST)
    public @ResponseBody
    suaratpsvalidasi updatesuaratidaksah(@RequestParam(value = "kodetps", required = false) String kodetps,
            @RequestParam(value = "suaratidaksah", required = false) String suaratidaksah,
            @RequestParam(value = "tervalidasi", required = false) String tervalidasi, HttpSession session) {        
        int Kodetps = Integer.parseInt(kodetps);
        int Suaratidaksah = Integer.parseInt(suaratidaksah);
        String respon = "0";
        suaratpsvalidasi kel = new suaratpsvalidasi();
        if (session.getAttribute("user").equals("")) {
            kel.setMsg("Penyimpanan gagal");
            kel.setSuccess(false);
        } else {
            respon = cstps.updatesuaratidaksah(new TbSuaratidaksahtps(Kodetps, Suaratidaksah, tervalidasi));
            if (respon.equals("1")) {
                kel.setMsg("Penyimpanan berhasil");
                kel.setSuccess(true);
            } else {
                kel.setMsg("Penyimpanan gagal");
                kel.setSuccess(false);
            }
        }
        return kel;
    }
    
    @RequestMapping(value = "/perolehansuara/tps/hapus", method = RequestMethod.POST)
    public @ResponseBody
    suaratpsvalidasi hapus(@RequestParam(value = "id", required = false) String kodeTPS, HttpSession session) {
        String respon = "0";
        suaratpsvalidasi kel = new suaratpsvalidasi();
        if (session.getAttribute("user").equals("")) {
            kel.setMsg("Penyimpanan gagal");
            kel.setSuccess(false);
        } else {
            respon = cstps.delete(kodeTPS);
            if (respon.equals("1")) {
                kel.setMsg("Penyimpanan berhasil");
                kel.setSuccess(true);
            } else {
                kel.setMsg("Penyimpanan gagal");
                kel.setSuccess(false);
            }
        }
        return kel;
    }
    

    @RequestMapping(value = "/perolehansuara/tps/tampil", method = RequestMethod.POST)
    public @ResponseBody
    listTbSuaraTPS tampilsuaraTPSJSON(@RequestParam(value = "rows") String rows,
            @RequestParam(value = "page") String page, @RequestParam(value = "kodetps", required = false) String kodetps,
            @RequestParam(value = "kodekecamatan", required = false) String kodekecamatan, @RequestParam(value = "kodekelurahan", required = false) String kodekelurahan) throws IOException {
        listTbSuaraTPS tbSuaraTPS = new listTbSuaraTPS();
        listTbSuaraTPS2 tbSuaraTPS2 = new listTbSuaraTPS2();
        List<listTbSuaraTPS1> a = new ArrayList<listTbSuaraTPS1>();
        int r = (rows != null) ? Integer.parseInt(rows) : 10;
        int p = (page != null) ? Integer.parseInt(page) : 1;
        kodetps = (kodetps != null) ? kodetps : "";
        kodekecamatan = (kodekecamatan != null) ? kodekecamatan : "";
        kodekelurahan = (kodekelurahan != null) ? kodekelurahan : "";
        
        int offset = (p - 1) * r;
        int jml = Integer.parseInt(cstps.jumlahdata());
        if (r >= jml) {
            r = jml;
        }
        cstps.tampilviewsuaratps(tbSuaraTPS2, kodetps, kodekecamatan, kodekelurahan, offset, r);
        tbSuaraTPS.setTotal(cstps.jumlahdata());
        for (int i = 0; i < r; i++) {
            a.add(new listTbSuaraTPS1(tbSuaraTPS2.kodesuaratps[i] , tbSuaraTPS2.kodekecamatan[i], tbSuaraTPS2.namakecamatan[i], tbSuaraTPS2.kodekelurahan[i], 
            tbSuaraTPS2.namakelurahan[i], tbSuaraTPS2.kodetps[i], tbSuaraTPS2.nomortps[i], tbSuaraTPS2.jmldpt[i], tbSuaraTPS2.suarasah[i], 
                    tbSuaraTPS2.suaratidaksah[i],tbSuaraTPS2.tervalidasi[i]));
            //System.out.println(i);
        }
        tbSuaraTPS.setRows(a);
        return tbSuaraTPS;
    }
    @RequestMapping("/perolehansuara/tps/tampil/{kodekelurahan}")
    public @ResponseBody
    listTbSuaraTPS tampilsuaraTPSJSONClient(@PathVariable String kodekelurahan) throws IOException {
        listTbSuaraTPS tbSuaraTPS = new listTbSuaraTPS();
        listTbSuaraTPS2 tbSuaraTPS2 = new listTbSuaraTPS2();
        List<listTbSuaraTPS1> a = new ArrayList<listTbSuaraTPS1>();
        int jml = Integer.parseInt(cstps.jumlahdatakelurahanterdaftar(kodekelurahan));
        cstps.tampilviewsuaratps(tbSuaraTPS2, "", "", kodekelurahan, 0, 10);
        tbSuaraTPS.setTotal(cstps.jumlahdatakelurahanterdaftar(kodekelurahan));
        for (int i = 0; i < jml; i++) {
            a.add(new listTbSuaraTPS1(tbSuaraTPS2.kodesuaratps[i] , tbSuaraTPS2.kodekecamatan[i], tbSuaraTPS2.namakecamatan[i], tbSuaraTPS2.kodekelurahan[i], 
            tbSuaraTPS2.namakelurahan[i], tbSuaraTPS2.kodetps[i], tbSuaraTPS2.nomortps[i], tbSuaraTPS2.jmldpt[i], tbSuaraTPS2.suarasah[i], 
                    tbSuaraTPS2.suaratidaksah[i],tbSuaraTPS2.tervalidasi[i]));
            //System.out.println(i);
        }
        tbSuaraTPS.setRows(a);
        return tbSuaraTPS;
    }
    @RequestMapping("/perolehansuara/tps/tampilkecamatan/{kodekecamatan}")
    public @ResponseBody
    listTbSuaraTPS tampilsuaraTPSJSONClientkecamatan(@PathVariable String kodekecamatan) throws IOException {
        listTbSuaraTPS tbSuaraTPS = new listTbSuaraTPS();
        listTbSuaraTPS2 tbSuaraTPS2 = new listTbSuaraTPS2();
        List<listTbSuaraTPS1> a = new ArrayList<listTbSuaraTPS1>();
        int jml = Integer.parseInt(cstps.jumlahdatakecamatanterdaftar(kodekecamatan));
        cstps.tampilviewsuaratps(tbSuaraTPS2, "", kodekecamatan, "", 0, 10);
        tbSuaraTPS.setTotal(cstps.jumlahdatakecamatanterdaftar(kodekecamatan));
        for (int i = 0; i < jml; i++) {
            a.add(new listTbSuaraTPS1(tbSuaraTPS2.kodesuaratps[i] , tbSuaraTPS2.kodekecamatan[i], tbSuaraTPS2.namakecamatan[i], tbSuaraTPS2.kodekelurahan[i], 
            tbSuaraTPS2.namakelurahan[i], tbSuaraTPS2.kodetps[i], tbSuaraTPS2.nomortps[i], tbSuaraTPS2.jmldpt[i], tbSuaraTPS2.suarasah[i], 
                    tbSuaraTPS2.suaratidaksah[i],tbSuaraTPS2.tervalidasi[i]));
            //System.out.println(i);
        }
        tbSuaraTPS.setRows(a);
        return tbSuaraTPS;
    }
    
    @RequestMapping("/perolehansuara/tps/tampildetail/{kodetps}")
    public @ResponseBody
    List<listTbSuaraDetailTPS1> tampilKelurahanJSON(@PathVariable String kodetps) {
        listTbSuaraDetailTPS2 detailTPS2 = new listTbSuaraDetailTPS2();
        List<listTbSuaraDetailTPS1> a = new ArrayList<listTbSuaraDetailTPS1>();
        int jml = Integer.parseInt(cstps.jumlahdatadetail(kodetps));
        cstps.tampilviewsuaradetailtps(detailTPS2, kodetps);
        for (int i = 0; i < jml; i++) {
            a.add(new listTbSuaraDetailTPS1(detailTPS2.kodesuaratps[i],detailTPS2.kodetps[i], detailTPS2.kodepasangancalon[i],
                    detailTPS2.gambar[i], detailTPS2.gubernur[i], detailTPS2.wakilgubernur[i], detailTPS2.suarasah[i]));
            //System.out.println(i);
        }
        return a;
    }
        
        
     public static class listTbSuaraTPS {

        String Total;
        List<listTbSuaraTPS1> rows;

        public List<listTbSuaraTPS1> getRows() {
            return rows;
        }

        public void setRows(List<listTbSuaraTPS1> rows) {
            this.rows = rows;
        }

        public String getTotal() {
            return Total;
        }

        public void setTotal(String Total) {
            this.Total = Total;
        }

    }
     
    private static class listTbSuaraTPS1 {

        int kodesuaratps;
        String kodekecamatan;
        String namakecamatan;
        String kodekelurahan;
        String namakelurahan;
        int kodetps;
        int nomortps;
        int jmldpt;
        BigInteger suarasah;
        int suaratidaksah;
        String tervalidasi;

        public listTbSuaraTPS1(int kodeSuaratps, String kodeKecamatan, String namaKecamatan,String kodeKelurahan
                , String namaKelurahan , int kodeTPS , int nomorTPS , int jmlDPT , BigInteger suaraSah , int suaraTidakSah , String Tervalidasi) {
            setKodesuaratps(kodeSuaratps);
            setKodekecamatan(kodeKecamatan);
            setNamakecamatan(namaKecamatan);
            setKodekelurahan(kodeKelurahan);
            setNamakelurahan(namaKelurahan);
            setKodetps(kodeTPS);
            setNomortps(nomorTPS);
            setJmldpt(jmlDPT);
            setSuarasah(suaraSah);
            setSuaratidaksah(suaraTidakSah);
            setTervalidasi(Tervalidasi);
        }
        public int getKodesuaratps() {
            return kodesuaratps;
        }

        public void setKodesuaratps(int kodesuaratps) {
            this.kodesuaratps = kodesuaratps;
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

        public int getKodetps() {
            return kodetps;
        }

        public void setKodetps(int kodetps) {
            this.kodetps = kodetps;
        }

        public int getNomortps() {
            return nomortps;
        }

        public void setNomortps(int nomortps) {
            this.nomortps = nomortps;
        }

        public int getJmldpt() {
            return jmldpt;
        }

        public void setJmldpt(int jmldpt) {
            this.jmldpt = jmldpt;
        }

        public BigInteger getSuarasah() {
            return suarasah;
        }

        public void setSuarasah(BigInteger suarasah) {
            this.suarasah = suarasah;
        }

        public int getSuaratidaksah() {
            return suaratidaksah;
        }

        public void setSuaratidaksah(int suaratidaksah) {
            this.suaratidaksah = suaratidaksah;
        }

        public String getTervalidasi() {
            return tervalidasi;
        }

        public void setTervalidasi(String tervalidasi) {
            this.tervalidasi = tervalidasi;
        }

    }
    
    public class listTbSuaraTPS2 {
        int[] kodesuaratps;
        String[] kodekecamatan;
        String[] namakecamatan;
        String[] kodekelurahan;
        String[] namakelurahan;
        int[] kodetps;
        int[] nomortps;
        int[] jmldpt;
        BigInteger[] suarasah;
        int[] suaratidaksah;
        String[] tervalidasi;

        public int[] getKodesuaratps() {
            return kodesuaratps;
        }

        public void setKodesuaratps(int[] kodesuaratps) {
            this.kodesuaratps = kodesuaratps;
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

        public int[] getKodetps() {
            return kodetps;
        }

        public void setKodetps(int[] kodetps) {
            this.kodetps = kodetps;
        }

        public int[] getNomortps() {
            return nomortps;
        }

        public void setNomortps(int[] nomortps) {
            this.nomortps = nomortps;
        }

        public int[] getJmldpt() {
            return jmldpt;
        }

        public void setJmldpt(int[] jmldpt) {
            this.jmldpt = jmldpt;
        }

        public BigInteger[] getSuarasah() {
            return suarasah;
        }

        public void setSuarasah(BigInteger[] suarasah) {
            this.suarasah = suarasah;
        }

        public int[] getSuaratidaksah() {
            return suaratidaksah;
        }

        public void setSuaratidaksah(int[] suaratidaksah) {
            this.suaratidaksah = suaratidaksah;
        }

        public String[] getTervalidasi() {
            return tervalidasi;
        }

        public void setTervalidasi(String[] tervalidasi) {
            this.tervalidasi = tervalidasi;
        }
    }
    
    
    private static class listTbSuaraDetailTPS1 {
        int kodesuaratps;
        int kodetps;
        String kodepasangancalon;
        String gambar;
        String gubernur;
        String wakilgubernur;
        int suarasah;
        
        public listTbSuaraDetailTPS1(int kodeSuaratps , int kodeTPS ,String kodePasanganCalon, String Gambar , String Gubernur , String wakilGubernur , int suaraSah){
            setKodesuaratps(kodeSuaratps);
            setKodetps(kodeTPS);
            setKodepasangancalon(kodePasanganCalon);
            setGambar(Gambar);
            setGubernur(Gubernur);
            setWakilgubernur(wakilGubernur);
            setSuarasah(suaraSah);
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
    public class listTbSuaraDetailTPS2 {
        int[] kodesuaratps;
        int[] kodetps;
        String[] kodepasangancalon;
        String[] gambar;
        String[] gubernur;
        String[] wakilgubernur;
        int[] suarasah;        

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




