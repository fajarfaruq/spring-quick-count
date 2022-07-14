/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ControllerTPS;
import Model.TbTps;
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
public class TPS {

    ControllerTPS ctps = new ControllerTPS();
    MenuHandle mh = new MenuHandle();

    @RequestMapping("/tps")
    public String tps(ModelMap model, HttpSession session, HttpServletRequest request) {
        String Halaman = "redirect";
        try {
            if (session.getAttribute("user").equals("")) {
                Halaman = "redirect";
            } else {
                model.addAttribute("kontenutama", "tps/tps.jsp");
                model.addAttribute("alert", "");
                mh.menuhandle(model, "tps", "");
                Halaman = "index";
            }
        } catch (Exception e) {
            Halaman = "redirect";
        }
        return Halaman;
    }

    @RequestMapping("/tps/managedata")
    public String tpsmanagedata(ModelMap model, HttpSession session, HttpServletRequest request) {
        String Halaman = "redirect";
        try {
            if (session.getAttribute("user").equals("")) {
                Halaman = "redirect";
            } else {
                model.addAttribute("kontenutama", "tps/managedata.jsp");
                model.addAttribute("alert", "");
                mh.menuhandle(model, "tps", "tpsdata");
                Halaman = "index";
            }
        } catch (Exception e) {
            Halaman = "redirect";
        }
        return Halaman;
    }

    @RequestMapping(value = "/tps/managedata/tambah", method = RequestMethod.POST)
    public @ResponseBody
    tpsvalidasi tambah(@RequestParam(value = "kodetps", required = false) String kodetps,
            @RequestParam(value = "kodekelurahan", required = false) String kodekelurahan,
            @RequestParam(value = "nomortps", required = false) int nomortps,
            @RequestParam(value = "jmldpt", required = false) int jmldpt, HttpSession session) {
        String respon = "0";
        tpsvalidasi kel = new tpsvalidasi();
        if (session.getAttribute("user").equals("")) {
            kel.setMsg("Penyimpanan gagal");
            kel.setSuccess(false);
        } else {
            respon = ctps.insert(new TbTps(null, kodekelurahan, nomortps, jmldpt));
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

    @RequestMapping(value = "/tps/managedata/edit", method = RequestMethod.POST)
    public @ResponseBody
    tpsvalidasi edit(@RequestParam(value = "kodetps", required = false) int kodetps,
            @RequestParam(value = "kodekelurahan", required = false) String kodekelurahan,
            @RequestParam(value = "nomortps", required = false) int nomortps,
            @RequestParam(value = "jmldpt", required = false) int jmldpt, HttpSession session) {
        String respon = "0";
        tpsvalidasi kel = new tpsvalidasi();
        if (session.getAttribute("user").equals("")) {
            kel.setMsg("Penyimpanan gagal");
            kel.setSuccess(false);
        } else {
            respon = ctps.update(new TbTps(kodetps, kodekelurahan, nomortps, jmldpt));
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

    @RequestMapping(value = "/tps/managedata/hapus", method = RequestMethod.POST)
    public @ResponseBody
    tpsvalidasi hapus(@RequestParam(value = "id", required = false) String kodetps, HttpSession session) {
        String respon = "0";
        tpsvalidasi kel = new tpsvalidasi();
        if (session.getAttribute("user").equals("")) {
            kel.setMsg("Penyimpanan gagal");
            kel.setSuccess(false);
        } else {
            respon = ctps.delete(kodetps);
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

    @RequestMapping(value = "/tps/tampil", method = RequestMethod.POST)
    public @ResponseBody
    listTbTPS tampilTPSJSON(@RequestParam(value = "rows") String rows,
            @RequestParam(value = "page") String page, @RequestParam(value = "kodetps", required = false) String kodetps,
            @RequestParam(value = "kodekecamatan", required = false) String kodekecamatan, @RequestParam(value = "kodekelurahan", required = false) String kodekelurahan) throws IOException {
        listTbTPS tbTPS = new listTbTPS();
        listTbTPS2 tbTPS2 = new listTbTPS2();
        List<listTbTPS1> a = new ArrayList<listTbTPS1>();
        int r = (rows != null) ? Integer.parseInt(rows) : 10;
        int p = (page != null) ? Integer.parseInt(page) : 1;
        kodetps = (kodetps != null) ? kodetps : "";
        kodekecamatan = (kodekecamatan != null) ? kodekecamatan : "";
        kodekelurahan = (kodekelurahan != null) ? kodekelurahan : "";
        
        int offset = (p - 1) * r;
        int jml = Integer.parseInt(ctps.jumlahdata());
        if (r >= jml) {
            r = jml;
        }
        ctps.tampilviewtps(tbTPS2, kodetps, kodekecamatan, kodekelurahan, offset, r);
        tbTPS.setTotal(ctps.jumlahdata());
        for (int i = 0; i < r; i++) {
            a.add(new listTbTPS1(tbTPS2.kodetps[i], tbTPS2.kodekecamatan[i] , tbTPS2.namakecamatan[i], 
                    tbTPS2.kodekelurahan[i], tbTPS2.namakelurahan[i], tbTPS2.nomortps[i], tbTPS2.jmldpt[i]));
            //System.out.println(i);
        }
        tbTPS.setRows(a);
        return tbTPS;
    }
    @RequestMapping("/tps/listtps/{kodekelurahan}")
    public @ResponseBody
    List<listTbTPS1> tampilKelurahanJSON(@PathVariable String kodekelurahan) {
        listTbTPS2 tbTPS2 = new listTbTPS2();
        List<listTbTPS1> a = new ArrayList<listTbTPS1>();
        int jml = Integer.parseInt(ctps.jumlahdatacombo(kodekelurahan));
        ctps.tampilviewtpscombo(tbTPS2, kodekelurahan);
        for (int i = 0; i < jml; i++) {
            a.add(new listTbTPS1(tbTPS2.kodetps[i], tbTPS2.kodekecamatan[i] , tbTPS2.namakecamatan[i], 
                    tbTPS2.kodekelurahan[i], tbTPS2.namakelurahan[i], tbTPS2.nomortps[i], tbTPS2.jmldpt[i]));
            //System.out.println(i);
        }
        return a;
    }
    // Untuk main TPS
    private static class listTbTPS {

        String Total;
        List<listTbTPS1> rows;

        public String getTotal() {
            return Total;
        }

        public void setTotal(String Total) {
            this.Total = Total;
        }

        public List<listTbTPS1> getRows() {
            return rows;
        }

        public void setRows(List<listTbTPS1> rows) {
            this.rows = rows;
        }
    }

    private static class listTbTPS1 {

        int kodetps;
        String kodekecamatan;
        String namakecamatan;
        String kodekelurahan;
        String namakelurahan;
        int nomortps;
        int jmldpt;

        public listTbTPS1(int kodeTPS, String kodeKecamatan, String namaKecamatan, String kodeKelurahan
        ,String namaKelurahan , int nomorTPS, int jmlDPT

        
            ){
            setKodetps(kodeTPS);
            setKodekecamatan(kodeKecamatan);
            setNamakecamatan(namaKecamatan);
            setKodekelurahan(kodeKelurahan);
            setNamakelurahan(namaKelurahan);
            setNomortps(nomorTPS);
            setJmldpt(jmlDPT);
        }

        public int getKodetps() {
            return kodetps;
        }

        public void setKodetps(int kodetps) {
            this.kodetps = kodetps;
        }

        public String getKodekecamatan() {
            return kodekecamatan;
        }

        public void setKodekecamatan(String kodekecamatan) {
            this.kodekecamatan = kodekecamatan;
        }

        public String getKodekelurahan() {
            return kodekelurahan;
        }

        public void setKodekelurahan(String kodekelurahan) {
            this.kodekelurahan = kodekelurahan;
        }

        public String getNamakecamatan() {
            return namakecamatan;
        }

        public void setNamakecamatan(String namakecamatan) {
            this.namakecamatan = namakecamatan;
        }

        public String getNamakelurahan() {
            return namakelurahan;
        }

        public void setNamakelurahan(String namakelurahan) {
            this.namakelurahan = namakelurahan;
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
    }

    public static class listTbTPS2 {

        int[] kodetps;
        String[] kodekecamatan;
        String[] namakecamatan;
        String[] kodekelurahan;
        String[] namakelurahan;
        int[] nomortps;
        int[] jmldpt;

        public int[] getKodetps() {
            return kodetps;
        }

        public void setKodetps(int[] kodetps) {
            this.kodetps = kodetps;
        }

        public String[] getNamakecamatan() {
            return namakecamatan;
        }

        public void setNamakecamatan(String[] namakecamatan) {
            this.namakecamatan = namakecamatan;
        }

        public String[] getKodekecamatan() {
            return kodekecamatan;
        }

        public void setKodekecamatan(String[] kodekecamatan) {
            this.kodekecamatan = kodekecamatan;
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
    }

    public static class tpsvalidasi {

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
