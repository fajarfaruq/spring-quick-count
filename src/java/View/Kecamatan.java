/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ControllerKecamatan;
import Controller.ControllerLoginKecamatan;
import Model.TbKecamatan;
import Model.TbLoginkecamatan;
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
public class Kecamatan {

    ControllerKecamatan ck = new ControllerKecamatan();
    MenuHandle mh = new MenuHandle();

    @RequestMapping("/kecamatan")
    public String kecamatan(ModelMap model, HttpSession session, HttpServletRequest request) {
        String Halaman = "redirect";
        try {
            if (session.getAttribute("user").equals("")) {
                Halaman = "redirect";
            } else {
                model.addAttribute("kontenutama", "kecamatan/kecamatan.jsp");
                model.addAttribute("alert", "");
                mh.menuhandle(model, "kecamatan", "");
                Halaman = "index";
            }
        } catch (Exception e) {
            Halaman = "redirect";
        }
        return Halaman;
    }

    @RequestMapping("/kecamatan/managedata")
    public String kecamatanmanagedata(ModelMap model, HttpSession session, HttpServletRequest request) {
        String Halaman = "redirect";
        try {
            if (session.getAttribute("user").equals("")) {
                Halaman = "redirect";
            } else {
                model.addAttribute("kontenutama", "kecamatan/managedata.jsp");
                model.addAttribute("alert", "");
                mh.menuhandle(model, "kecamatan", "kecamatandata");
                Halaman = "index";
            }
        } catch (Exception e) {
            Halaman = "redirect";
        }
        return Halaman;
    }

    @RequestMapping(value = "/kecamatan/managedata/tambah", method = RequestMethod.POST)
    public @ResponseBody
    kecamatanvalidasi tambah(@RequestParam(value = "kodekecamatan", required = false) String kodekecamatan,
            @RequestParam(value = "namakecamatan", required = false) String namakecamatan, HttpSession session) {
        String respon = "0";
        kecamatanvalidasi kec = new kecamatanvalidasi();
        if (session.getAttribute("user").equals("")) {
            kec.setMsg("Penyimpanan gagal");
            kec.setSuccess(false);
        } else {
            respon = ck.insert(new TbKecamatan(kodekecamatan, namakecamatan));
            if (respon.equals("1")) {
                kec.setMsg("Penyimpanan berhasil");
                kec.setSuccess(true);
            } else {
                kec.setMsg("Penyimpanan gagal");
                kec.setSuccess(false);
            }
        }
        return kec;
    }

    @RequestMapping(value = "/kecamatan/managedata/edit", method = RequestMethod.POST)
    public @ResponseBody
    kecamatanvalidasi edit(@RequestParam(value = "kodekecamatan", required = false) String kodekecamatan,
            @RequestParam(value = "namakecamatan", required = false) String namakecamatan, HttpSession session) {

        String respon = "0";
        kecamatanvalidasi kec = new kecamatanvalidasi();
        if (session.getAttribute("user").equals("")) {
            kec.setMsg("Penyimpanan gagal");
            kec.setSuccess(false);
        } else {
            respon = ck.update(new TbKecamatan(kodekecamatan, namakecamatan));
            if (respon.equals("1")) {
                kec.setMsg("Penyimpanan berhasil");
                kec.setSuccess(true);
            } else {
                kec.setMsg("Penyimpanan gagal");
                kec.setSuccess(false);
            }
        }
        return kec;
    }

    @RequestMapping(value = "/kecamatan/managedata/hapus", method = RequestMethod.POST)
    public @ResponseBody
    kecamatanvalidasi hapus(@RequestParam(value = "id", required = false) String kodekecamatan, HttpSession session) {
        String respon = "0";
        kecamatanvalidasi kec = new kecamatanvalidasi();
        if (session.getAttribute("user").equals("")) {
            kec.setMsg("Penyimpanan gagal");
            kec.setSuccess(false);
        } else {
            respon = ck.delete(kodekecamatan);
            if (respon.equals("1")) {
                kec.setMsg("Penyimpanan berhasil");
                kec.setSuccess(true);
            } else {
                kec.setMsg("Penyimpanan gagal");
                kec.setSuccess(false);
            }
        }
        return kec;
    }

    @RequestMapping("/kecamatan/managedata/idselanjutnya")
    public @ResponseBody
    idselanjutnya idselanjutnya() {
        idselanjutnya id = new idselanjutnya();
        String ids = ck.tampilIDselanjutnya();
        id.setIdselanjutnya(ids);
        return id;
    }

    @RequestMapping(value = "/kecamatan/tampil", method = RequestMethod.POST)
    public @ResponseBody
    listTbKecamatan tampilKecamatanJSON(@RequestParam(value = "rows") String rows,
            @RequestParam(value = "page") String page, @RequestParam(value = "kodekecamatan", required = false) String kodekecamatan,
            @RequestParam(value = "namakecamatan", required = false) String namakecamatan) throws IOException {
        listTbKecamatan tbKecamatan = new listTbKecamatan();
        listTbKecamatan3 kecamatan3 = new listTbKecamatan3();
        List<listTbKecamatan2> a = new ArrayList<listTbKecamatan2>();
        int r = (rows != null) ? Integer.parseInt(rows) : 10;
        int p = (page != null) ? Integer.parseInt(page) : 1;
        kodekecamatan = (kodekecamatan != null) ? kodekecamatan : "";
        namakecamatan = (namakecamatan != null) ? namakecamatan : "";
        int offset = (p - 1) * r;
        int jml = Integer.parseInt(ck.jumlahdata());
        if (r >= jml) {
            r = jml;
        }
        ck.tampilsemuadata(kecamatan3, kodekecamatan, namakecamatan, offset, r);
        tbKecamatan.setTotal(ck.jumlahdata());
        for (int i = 0; i < r; i++) {
            a.add(new listTbKecamatan2(kecamatan3.kodekecamatan[i], kecamatan3.namakecamatan[i]));
            //System.out.println(i);
        }
        tbKecamatan.setRows(a);
        return tbKecamatan;
    }

    @RequestMapping("/kecamatan/listkecamatan")
    public @ResponseBody
    List<listTbKecamatan2> tampilKecamatanJSON() {
        listTbKecamatan3 kecamatan3 = new listTbKecamatan3();
        List<listTbKecamatan2> a = new ArrayList<listTbKecamatan2>();
        int jml = Integer.parseInt(ck.jumlahdata());
        ck.tampilsemuadata(kecamatan3, "", "", 0, jml);
        for (int i = 0; i < jml; i++) {
            a.add(new listTbKecamatan2(kecamatan3.kodekecamatan[i], kecamatan3.namakecamatan[i]));
            //System.out.println(i);
        }
        return a;
    }

    // Untuk main kecamatan 
    public static class listTbKecamatan {

        String Total;
        List<listTbKecamatan2> rows;

        public String getTotal() {
            return Total;
        }

        public void setTotal(String Total) {
            this.Total = Total;
        }

        public List<listTbKecamatan2> getRows() {
            return rows;
        }

        public void setRows(List<listTbKecamatan2> rows) {
            this.rows = rows;
        }

        public listTbKecamatan() {
        }
    }

    public static class listTbKecamatan2 {

        public String kodekecamatan;
        public String namakecamatan;

        public listTbKecamatan2(String kodekecamatan, String namakecamatan) {
            setKodekecamatan(kodekecamatan);
            setNamakecamatan(namakecamatan);
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
    }

    public static class listTbKecamatan3 {

        public String[] kodekecamatan;
        public String[] namakecamatan;

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
    }

    public static class idselanjutnya {

        String idselanjutnya;

        public String getIdselanjutnya() {
            return idselanjutnya;
        }

        public void setIdselanjutnya(String idselanjutnya) {
            this.idselanjutnya = idselanjutnya;
        }
    }

    public static class kecamatanvalidasi {

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