/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ControllerKelurahan;
import Controller.ControllerLoginAdmin;
import Model.TbKecamatan;
import Model.TbKelurahan;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author user
 */
@Controller
public class Kelurahan {

    ControllerKelurahan ck = new ControllerKelurahan();
    ControllerLoginAdmin cl = new ControllerLoginAdmin();
    MenuHandle mh = new MenuHandle();

    @RequestMapping("/kelurahan")
    public String kelurahan(ModelMap model, HttpSession session, HttpServletRequest request) {
        String Halaman = "redirect";
        try {
            if (session.getAttribute("user").equals("")) {
                Halaman = "redirect";
            } else {
                model.addAttribute("kontenutama", "kelurahan/kelurahan.jsp");
                model.addAttribute("alert", "");
                mh.menuhandle(model, "kelurahan", "");
                Halaman = "index";
            }
        } catch (Exception e) {
            Halaman = "redirect";
        }
        return Halaman;
    }

    @RequestMapping("/kelurahan/managedata")
    public String kelurahanmanagedata(ModelMap model, HttpSession session, HttpServletRequest request) {
        String Halaman = "redirect";
        try {
            if (session.getAttribute("user").equals("")) {
                Halaman = "redirect";
            } else {
                model.addAttribute("kontenutama", "kelurahan/managedata.jsp");
                model.addAttribute("alert", "");
                mh.menuhandle(model, "kelurahan", "kelurahandata");
                Halaman = "index";
            }
        } catch (Exception e) {
            Halaman = "redirect";
        }
        return Halaman;
    }

    @RequestMapping(value = "/kelurahan/managedata/tambah", method = RequestMethod.POST)
    public @ResponseBody
    kelurahanvalidasi tambah(@RequestParam(value = "kodekelurahan", required = false) String kodekelurahan,
            @RequestParam(value = "kodekecamatan", required = false) String kodekecamatan,
            @RequestParam(value = "namakelurahan", required = false) String namakelurahan, HttpSession session) {
        String respon = "0";
        kelurahanvalidasi kel = new kelurahanvalidasi();
        if (session.getAttribute("user").equals("")) {
            kel.setMsg("Penyimpanan gagal");
            kel.setSuccess(false);
        } else {
            respon = ck.insert(new TbKelurahan(kodekelurahan, kodekecamatan, namakelurahan));
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

    @RequestMapping(value = "/kelurahan/managedata/edit", method = RequestMethod.POST)
    public @ResponseBody
    kelurahanvalidasi edit(@RequestParam(value = "kodekelurahan", required = false) String kodekelurahan,
            @RequestParam(value = "kodekecamatan", required = false) String kodekecamatan,
            @RequestParam(value = "namakelurahan", required = false) String namakelurahan, HttpSession session) {
        String respon = "0";
        kelurahanvalidasi kel = new kelurahanvalidasi();
        if (session.getAttribute("user").equals("")) {
            kel.setMsg("Penyimpanan gagal");
            kel.setSuccess(false);
        } else {
            respon = ck.update(new TbKelurahan(kodekelurahan, kodekecamatan, namakelurahan));
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

    @RequestMapping(value = "/kelurahan/managedata/hapus", method = RequestMethod.POST)
    public @ResponseBody
    kelurahanvalidasi hapus(@RequestParam(value = "id", required = false) String kodekelurahan, HttpSession session) {
        String respon = "0";
        kelurahanvalidasi kel = new kelurahanvalidasi();
        if (session.getAttribute("user").equals("")) {
            kel.setMsg("Penyimpanan gagal");
            kel.setSuccess(false);
        } else {
            respon = ck.delete(kodekelurahan);
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
    
    @RequestMapping("/kelurahan/managedata/idselanjutnya")
    public @ResponseBody
    idselanjutnya idselanjutnya() {
        idselanjutnya id = new idselanjutnya();
        String ids = ck.tampilIDselanjutnya();
        id.setIdselanjutnya(ids);
        return id;
    }
    
    @RequestMapping("/kelurahan/manageuser")
    public String kelurahanmanageuser(ModelMap model, HttpSession session, HttpServletRequest request) {
        String Halaman = "redirect";
        try {
            if (session.getAttribute("user").equals("")) {
                Halaman = "redirect";
            } else {
                model.addAttribute("kontenutama", "kelurahan/manageuser.jsp");
                model.addAttribute("alert", "");
                mh.menuhandle(model, "kelurahan", "kelurahanuser");
                Halaman = "index";
            }
        } catch (Exception e) {
            Halaman = "redirect";
        }
        return Halaman;
    }

    @RequestMapping(value = "/kelurahan/tampil", method = RequestMethod.POST)
    public @ResponseBody
    listTbKelurahan tampilKelurahanJSON(@RequestParam(value = "rows") String rows,
            @RequestParam(value = "page") String page, @RequestParam(value = "kodekelurahan", required = false) String kodekelurahan,
            @RequestParam(value = "kodekecamatan", required = false) String kodekecamatan,
            @RequestParam(value = "namakelurahan", required = false) String namakelurahan) throws IOException {
        listTbKelurahan tbKelurahan = new listTbKelurahan();
        listTbKelurahan2 kelurahan2 = new listTbKelurahan2();
        List<listTbKelurahan1> a = new ArrayList<listTbKelurahan1>();
        int r = (rows != null) ? Integer.parseInt(rows) : 10;
        int p = (page != null) ? Integer.parseInt(page) : 1;
        kodekelurahan = (kodekelurahan != null) ? kodekelurahan : "";
        kodekecamatan = (kodekecamatan != null) ? kodekecamatan : "";
        namakelurahan = (namakelurahan != null) ? namakelurahan : "";
        int offset = (p - 1) * r;
        int jml = Integer.parseInt(ck.jumlahdata());
        if (r >= jml) {
            r = jml;
        }
        ck.tampilsemuaviewkelurahan(kelurahan2, kodekelurahan, kodekecamatan, namakelurahan, offset, r);
        tbKelurahan.setTotal(ck.jumlahdata());
        for (int i = 0; i < r; i++) {
            a.add(new listTbKelurahan1(kelurahan2.kodekelurahan[i], kelurahan2.kodekecamatan[i], kelurahan2.namakelurahan[i]));
            System.out.println(i);
        }
        tbKelurahan.setRows(a);
        return tbKelurahan;
    }

    @RequestMapping("/kelurahan/listkelurahan/{kodekecamatan}")
    public @ResponseBody
    List<listTbKelurahan1> tampilKelurahanJSON(@PathVariable String kodekecamatan) {
        listTbKelurahan2 kelurahan2 = new listTbKelurahan2();
        List<listTbKelurahan1> a = new ArrayList<listTbKelurahan1>();
        int jml = Integer.parseInt(ck.jumlahdatasesuaikecamatan(kodekecamatan));
        ck.tampilsemuadata(kelurahan2, "", kodekecamatan, "", 0, jml);
        for (int i = 0; i < jml; i++) {
            a.add(new listTbKelurahan1(kelurahan2.kodekelurahan[i], kelurahan2.kodekecamatan[i], kelurahan2.namakelurahan[i]));
            //System.out.println(i);
        }
        return a;
    }
    @RequestMapping("/kelurahan/listkelurahanclient/{kodekecamatan}")
    public @ResponseBody
    List<listTbKelurahan1> tampilKelurahanJSON1(@PathVariable String kodekecamatan) {
        listTbKelurahan2 kelurahan2 = new listTbKelurahan2();
        List<listTbKelurahan1> a = new ArrayList<listTbKelurahan1>();
        int jml = Integer.parseInt(ck.jumlahdatasesuaikecamatan(kodekecamatan));
        ck.tampilsemuadata(kelurahan2, "", kodekecamatan, "", 0, jml);
        for (int i = 0; i < jml; i++) {
            a.add(new listTbKelurahan1(kelurahan2.kodekelurahan[i], kelurahan2.kodekecamatan[i], kelurahan2.namakelurahan[i]));
            //System.out.println(i);
        }
        return a;
    }
    
    
    @RequestMapping("/kelurahan/tampil/{kodekecamatan}")
    public @ResponseBody
    listTbKelurahan tampilKelurahanJSON2(@PathVariable String kodekecamatan) throws IOException {
        listTbKelurahan tbKelurahan = new listTbKelurahan();
        listTbKelurahan2 kelurahan2 = new listTbKelurahan2();
        List<listTbKelurahan1> a = new ArrayList<listTbKelurahan1>();
        kodekecamatan = (kodekecamatan != null) ? kodekecamatan : "";
        int jml = Integer.parseInt(ck.jumlahdatasesuaikecamatan(kodekecamatan));
        ck.tampilsemuadata(kelurahan2, "", kodekecamatan, "", 0, jml);
        tbKelurahan.setTotal(ck.jumlahdatasesuaikecamatan(kodekecamatan));
        for (int i = 0; i < jml; i++) {
            a.add(new listTbKelurahan1(kelurahan2.kodekelurahan[i], kelurahan2.kodekecamatan[i], kelurahan2.namakelurahan[i]));
            System.out.println(i);
        }
        tbKelurahan.setRows(a);
        return tbKelurahan;
    }
    // Untuk main kelurahan

    private static class listTbKelurahan {

        String Total;
        List<listTbKelurahan1> rows;

        public String getTotal() {
            return Total;
        }

        public void setTotal(String Total) {
            this.Total = Total;
        }

        public List<listTbKelurahan1> getRows() {
            return rows;
        }

        public void setRows(List<listTbKelurahan1> rows) {
            this.rows = rows;
        }
    }

    private static class listTbKelurahan1 {

        String kodekelurahan;
        String kodekecamatan;
        String namakelurahan;

        public listTbKelurahan1(String kodekelurahan, String kodekecamatan, String namakelurahan) {
            setKodekelurahan(kodekelurahan);
            setKodekecamatan(kodekecamatan);
            setNamakelurahan(namakelurahan);
        }

        public String getKodekelurahan() {
            return kodekelurahan;
        }

        public void setKodekelurahan(String kodekelurahan) {
            this.kodekelurahan = kodekelurahan;
        }

        public String getKodekecamatan() {
            return kodekecamatan;
        }

        public void setKodekecamatan(String kodekecamatan) {
            this.kodekecamatan = kodekecamatan;
        }

        public String getNamakelurahan() {
            return namakelurahan;
        }

        public void setNamakelurahan(String namakelurahan) {
            this.namakelurahan = namakelurahan;
        }
    }

    public static class listTbKelurahan2 {

        String[] kodekelurahan;
        String[] kodekecamatan;
        String[] namakelurahan;

        public String[] getKodekelurahan() {
            return kodekelurahan;
        }

        public void setKodekelurahan(String[] kodekelurahan) {
            this.kodekelurahan = kodekelurahan;
        }

        public String[] getKodekecamatan() {
            return kodekecamatan;
        }

        public void setKodekecamatan(String[] kodekecamatan) {
            this.kodekecamatan = kodekecamatan;
        }

        public String[] getNamakelurahan() {
            return namakelurahan;
        }

        public void setNamakelurahan(String[] namakelurahan) {
            this.namakelurahan = namakelurahan;
        }
    }

    private static class idselanjutnya {

        String idselanjutnya;

        public String getIdselanjutnya() {
            return idselanjutnya;
        }

        public void setIdselanjutnya(String idselanjutnya) {
            this.idselanjutnya = idselanjutnya;
        }
    }

    public static class kelurahanvalidasi {

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
