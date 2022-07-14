/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

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
public class RekapitulasiKelurahan {
    MenuHandle mh = new MenuHandle();
    ControllerRekapitulasiKelurahan crk = new ControllerRekapitulasiKelurahan();
    @RequestMapping("/rekapitulasi/kelurahan")
    public String suara(ModelMap model, HttpSession session, HttpServletRequest request) {
        String Halaman = "redirect";
        try {
            if (session.getAttribute("user").equals("")) {
                Halaman = "redirect";
            } else {
                model.addAttribute("kontenutama","rekapitulasi/rekapitulasikelurahan.jsp");
                model.addAttribute("alert","");
                mh.menuhandle(model,"rekapitulasi","rekapitulasikelurahan");
                Halaman = "index";
            }
        } catch (Exception e) {
            Halaman = "redirect";
        }
        return Halaman;
    }  

    @RequestMapping(value = "/rekapitulasi/kelurahan/tampil", method = RequestMethod.POST)
    public @ResponseBody
    listTbRekapitulasiKelurahan tampilrekapitulasiKelurahanJSON(@RequestParam(value = "rows") String rows,
            @RequestParam(value = "page") String page,@RequestParam(value = "kodekecamatan", required = false) String kodekecamatan,
            @RequestParam(value = "kodekelurahan", required = false) String kodekelurahan) throws IOException {
        listTbRekapitulasiKelurahan tbRekapitulasiKelurahan = new listTbRekapitulasiKelurahan();
        listTbRekapitulasiKelurahan2 tbRekapitulasiKelurahan2 = new listTbRekapitulasiKelurahan2();
        List<listTbRekapitulasiKelurahan1> a = new ArrayList<listTbRekapitulasiKelurahan1>();
        int r = (rows != null) ? Integer.parseInt(rows) : 10;
        int p = (page != null) ? Integer.parseInt(page) : 1;
        kodekecamatan = (kodekecamatan != null) ? kodekecamatan : "";
        kodekelurahan = (kodekelurahan != null) ? kodekelurahan : "";

        int offset = (p - 1) * r;
        int jml = Integer.parseInt(crk.jumlahdata());
        if (r >= jml) {
            r = jml;
        }
        crk.tampilviewrekapitulasikelurahan(tbRekapitulasiKelurahan2, kodekelurahan, offset, r);
        tbRekapitulasiKelurahan.setTotal(crk.jumlahdata());
        for (int i = 0; i < r; i++) {
            a.add(new listTbRekapitulasiKelurahan1(tbRekapitulasiKelurahan2.kodekecamatan[i], tbRekapitulasiKelurahan2.namakecamatan[i],
                    tbRekapitulasiKelurahan2.kodesuarakelurahan[i], tbRekapitulasiKelurahan2.kodekelurahan[i], tbRekapitulasiKelurahan2.namakelurahan[i]
                    , tbRekapitulasiKelurahan2.jmldpt[i], tbRekapitulasiKelurahan2.suarasah[i], tbRekapitulasiKelurahan2.suaratidaksah[i],tbRekapitulasiKelurahan2.tervalidasi[i]));
            //System.out.println(i);
        }
        tbRekapitulasiKelurahan.setRows(a);
        return tbRekapitulasiKelurahan;
    }    
    
    @RequestMapping("/rekapitulasi/kelurahan/tampilkecamatan/{kodekecamatan}")
    public @ResponseBody
    listTbRekapitulasiKelurahan tampilrekapitulasiKelurahanKecamatanJSON(@PathVariable String kodekecamatan) throws IOException {
        listTbRekapitulasiKelurahan tbRekapitulasiKelurahan = new listTbRekapitulasiKelurahan();
        listTbRekapitulasiKelurahan2 tbRekapitulasiKelurahan2 = new listTbRekapitulasiKelurahan2();
        List<listTbRekapitulasiKelurahan1> a = new ArrayList<listTbRekapitulasiKelurahan1>();


        int jml = Integer.parseInt(crk.jumlahdatakecamatan(kodekecamatan));

        crk.tampilviewrekapitulasikelurahankecamatan(tbRekapitulasiKelurahan2, kodekecamatan);
        tbRekapitulasiKelurahan.setTotal(crk.jumlahdatakecamatan(kodekecamatan));
        for (int i = 0; i < jml; i++) {
            a.add(new listTbRekapitulasiKelurahan1(tbRekapitulasiKelurahan2.kodekecamatan[i], tbRekapitulasiKelurahan2.namakecamatan[i],
                    tbRekapitulasiKelurahan2.kodesuarakelurahan[i], tbRekapitulasiKelurahan2.kodekelurahan[i], tbRekapitulasiKelurahan2.namakelurahan[i]
                    , tbRekapitulasiKelurahan2.jmldpt[i], tbRekapitulasiKelurahan2.suarasah[i], tbRekapitulasiKelurahan2.suaratidaksah[i],tbRekapitulasiKelurahan2.tervalidasi[i]));
            //System.out.println(i);
        }
        tbRekapitulasiKelurahan.setRows(a);
        return tbRekapitulasiKelurahan;
    }    
    @RequestMapping("/rekapitulasi/kelurahan/tampilkecamatansementara/{kodekecamatan}")
    public @ResponseBody
    listTbRekapitulasiKelurahan tampilrekapitulasiKelurahanKecamatanSementaraJSON(@PathVariable String kodekecamatan) throws IOException {
        listTbRekapitulasiKelurahan tbRekapitulasiKelurahan = new listTbRekapitulasiKelurahan();
        listTbRekapitulasiKelurahan2 tbRekapitulasiKelurahan2 = new listTbRekapitulasiKelurahan2();
        List<listTbRekapitulasiKelurahan1> a = new ArrayList<listTbRekapitulasiKelurahan1>();
        
        crk.tampilviewrekapitulasihasilsementarakecamatan(tbRekapitulasiKelurahan2, kodekecamatan);
        tbRekapitulasiKelurahan.setTotal("1");
        for (int i = 0; i < 1; i++) {
            a.add(new listTbRekapitulasiKelurahan1(tbRekapitulasiKelurahan2.kodekecamatan[i], tbRekapitulasiKelurahan2.namakecamatan[i],
                    tbRekapitulasiKelurahan2.kodesuarakelurahan[i], tbRekapitulasiKelurahan2.kodekelurahan[i], tbRekapitulasiKelurahan2.namakelurahan[i]
                    , tbRekapitulasiKelurahan2.jmldpt[i], tbRekapitulasiKelurahan2.suarasah[i], tbRekapitulasiKelurahan2.suaratidaksah[i],tbRekapitulasiKelurahan2.tervalidasi[i]));
            //System.out.println(i);
        }
        tbRekapitulasiKelurahan.setRows(a);
        return tbRekapitulasiKelurahan;
    }    
    
    @RequestMapping("/rekapitulasi/kelurahan/tampildetail/{kodekelurahan}")
    public @ResponseBody
    List<listTbDetailRekapitulasiKelurahan1> tampilDetailKelurahanJSON(@PathVariable String kodekelurahan) {
        listTbDetailRekapitulasiKelurahan2 detailRekapitulasiKelurahan2 = new listTbDetailRekapitulasiKelurahan2();
        List<listTbDetailRekapitulasiKelurahan1> a = new ArrayList<listTbDetailRekapitulasiKelurahan1>();
        int jml = Integer.parseInt(crk.jumlahdatadetail(kodekelurahan));
        crk.tampilviewdetailrekapitulasikelurahan(detailRekapitulasiKelurahan2, kodekelurahan);
        for (int i = 0; i < jml; i++) {
            a.add(new listTbDetailRekapitulasiKelurahan1(detailRekapitulasiKelurahan2.kodesuarakelurahan[i],detailRekapitulasiKelurahan2.kodekelurahan[i], detailRekapitulasiKelurahan2.kodepasangancalon[i],
                    detailRekapitulasiKelurahan2.gambar[i], detailRekapitulasiKelurahan2.gubernur[i], detailRekapitulasiKelurahan2.wakilgubernur[i], detailRekapitulasiKelurahan2.suarasah[i]));
            //System.out.println(i);
        }
        return a;
    }
    
    //Detailnya di kecamatan
    
    
    public static class listTbRekapitulasiKelurahan {

        String Total;
        List<listTbRekapitulasiKelurahan1> rows;

        public List<listTbRekapitulasiKelurahan1> getRows() {
            return rows;
        }

        public void setRows(List<listTbRekapitulasiKelurahan1> rows) {
            this.rows = rows;
        }

        public String getTotal() {
            return Total;
        }

        public void setTotal(String Total) {
            this.Total = Total;
        }

    }
     
    private static class listTbRekapitulasiKelurahan1 {
        String kodekecamatan;
        String namakecamatan;
        int kodesuarakelurahan;
        String kodekelurahan;
        String namakelurahan;
        int jmldpt;
        int suarasah;
        int suaratidaksah;
        String tervalidasi;

        public listTbRekapitulasiKelurahan1(String KodeKecamatan, String NamaKecamatan, int KodeSuaraKelurahan , String KodeKelurahan, String NamaKelurahan , int JmlDpt , int SuaraSah , int SuaraTidakSah,String Tervalidasi) {
            setKodekecamatan(KodeKecamatan);
            setNamakecamatan(NamaKecamatan);
            setKodesuarakelurahan(KodeSuaraKelurahan);
            setKodekelurahan(KodeKelurahan);
            setNamakelurahan(NamaKelurahan);
            setJmldpt(JmlDpt);
            setSuarasah(SuaraSah);
            setSuaratidaksah(SuaraTidakSah);
            setTervalidasi(Tervalidasi);
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

        
        public int getKodesuarakelurahan() {
            return kodesuarakelurahan;
        }

        public void setKodesuarakelurahan(int kodesuarakelurahan) {
            this.kodesuarakelurahan = kodesuarakelurahan;
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

        public String getTervalidasi() {
            return tervalidasi;
        }

        public void setTervalidasi(String tervalidasi) {
            this.tervalidasi = tervalidasi;
        }


    }
    
    public class listTbRekapitulasiKelurahan2 {
        String[] kodekecamatan;
        String[] namakecamatan;        
        int[] kodesuarakelurahan;
        String[] kodekelurahan;
        String[] namakelurahan;
        int[] jmldpt;
        int[] suarasah;
        int[] suaratidaksah;
        String[] tervalidasi;

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

        public int[] getKodesuarakelurahan() {
            return kodesuarakelurahan;
        }

        public void setKodesuarakelurahan(int[] kodesuarakelurahan) {
            this.kodesuarakelurahan = kodesuarakelurahan;
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

        public String[] getTervalidasi() {
            return tervalidasi;
        }

        public void setTervalidasi(String[] tervalidasi) {
            this.tervalidasi = tervalidasi;
        }


    }
    
    
    private static class listTbDetailRekapitulasiKelurahan1 {
        int kodesuarakelurahan;
        String kodekelurahan;
        int kodepasangancalon;
        String gambar;
        String gubernur;
        String wakilgubernur;
        int suarasah;
        
        public listTbDetailRekapitulasiKelurahan1(int kodeSuarakelurahan , String kodeKelurahan ,int kodePasanganCalon, String Gambar , String Gubernur , String wakilGubernur , int suaraSah){
            setKodesuarakelurahan(kodeSuarakelurahan);
            setKodekelurahan(kodeKelurahan);
            setKodepasangancalon(kodePasanganCalon);
            setGambar(Gambar);
            setGubernur(Gubernur);
            setWakilgubernur(wakilGubernur);
            setSuarasah(suaraSah);
        }

        public int getKodesuarakelurahan() {
            return kodesuarakelurahan;
        }

        public void setKodesuarakelurahan(int kodesuarakelurahan) {
            this.kodesuarakelurahan = kodesuarakelurahan;
        }

        public String getKodekelurahan() {
            return kodekelurahan;
        }

        public void setKodekelurahan(String kodekelurahan) {
            this.kodekelurahan = kodekelurahan;
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
    public class listTbDetailRekapitulasiKelurahan2 {
        int[] kodesuarakelurahan;
        String[] kodekelurahan;
        int[] kodepasangancalon;
        String[] gambar;
        String[] gubernur;
        String[] wakilgubernur;
        int[] suarasah;        

        public int[] getKodesuarakelurahan() {
            return kodesuarakelurahan;
        }

        public void setKodesuarakelurahan(int[] kodesuarakelurahan) {
            this.kodesuarakelurahan = kodesuarakelurahan;
        }

        public String[] getKodekelurahan() {
            return kodekelurahan;
        }

        public void setKodekelurahan(String[] kodekelurahan) {
            this.kodekelurahan = kodekelurahan;
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
