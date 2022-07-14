/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ControllerLoginKecamatan;
import Model.TbLoginkecamatan;
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
 * @author user
 */
@Controller
public class UserKecamatan {
    ControllerLoginKecamatan cl = new ControllerLoginKecamatan();
    MenuHandle mh = new MenuHandle();
    
    @RequestMapping("/user")
    public String user(ModelMap model, HttpSession session, HttpServletRequest request) {
        String Halaman = "redirect";
        try {
            if (session.getAttribute("user").equals("")) {
                Halaman = "redirect";
            } else {
                model.addAttribute("kontenutama", "user/user.jsp");
                model.addAttribute("alert", "");
                mh.menuhandle(model, "user", "");
                Halaman = "index";
            }
        } catch (Exception e) {
            Halaman = "redirect";
        }
        return Halaman;
    }
    
    @RequestMapping("/user/userkecamatan")
    public String userkecamatan(ModelMap model, HttpSession session, HttpServletRequest request) {
        String Halaman = "redirect";
        try {
            if (session.getAttribute("user").equals("")) {
                Halaman = "redirect";
            } else {
                model.addAttribute("kontenutama", "user/userkecamatan.jsp");
                model.addAttribute("alert", "");
                mh.menuhandle(model, "user", "userkecamatan");
                Halaman = "index";
            }
        } catch (Exception e) {
            Halaman = "redirect";
        }
        return Halaman;
    }
    
    

    @RequestMapping(value = "/user/userkecamatan/tambah", method = RequestMethod.POST)
    public @ResponseBody
    uservalidasi tambahuser(@RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "password", required = false) String password,
            @RequestParam(value = "kodekecamatan", required = false) String kodekecamatan, HttpSession session) {
        TbLoginkecamatan tbLoginkecamatan = new TbLoginkecamatan();
        tbLoginkecamatan.setUsername(username);
        tbLoginkecamatan.setPassword(password);
        tbLoginkecamatan.setKodeHakAkses("2");
        tbLoginkecamatan.setKodeKecamatan(kodekecamatan);
        String respon = "0";
        uservalidasi kec = new uservalidasi();
        if (session.getAttribute("user").equals("")) {
            kec.setMsg("Penyimpanan gagal");
            kec.setSuccess(false);
        } else {
            respon = cl.insertuserkecamatan(tbLoginkecamatan);
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

    @RequestMapping(value = "/user/userkecamatan/edit", method = RequestMethod.POST)
    public @ResponseBody
    uservalidasi edituser(@RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "password", required = false) String password,
            @RequestParam(value = "kodekecamatan", required = false) String kodekecamatan, HttpSession session) {
        TbLoginkecamatan tbLoginkecamatan = new TbLoginkecamatan();
        tbLoginkecamatan.setUsername(username);
        tbLoginkecamatan.setPassword(password);
        tbLoginkecamatan.setKodeHakAkses("2");
        tbLoginkecamatan.setKodeKecamatan(kodekecamatan);
        String respon = "0";
        uservalidasi kec = new uservalidasi();
        if (session.getAttribute("user").equals("")) {
            kec.setMsg("Penyimpanan gagal");
            kec.setSuccess(false);
        } else {
            respon = cl.updateuserkecamatan(tbLoginkecamatan);
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

   @RequestMapping(value = "/user/userkecamatan/tampil", method = RequestMethod.POST)
    public @ResponseBody
    userTbKecamatan tampiluserKecamatanJSON(@RequestParam(value = "rows") String rows,
            @RequestParam(value = "page") String page, @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "kodekecamatan", required = false) String kodekecamatan) {
        userTbKecamatan tbKecamatan = new userTbKecamatan();
        userTbKecamatan2 tbKecamatan2 = new userTbKecamatan2();
        List<userTbKecamatan1> a = new ArrayList<userTbKecamatan1>();
        int r = (rows != null) ? Integer.parseInt(rows) : 10;
        int p = (page != null) ? Integer.parseInt(page) : 1;
        username = (username != null) ? username : "";
        kodekecamatan = (kodekecamatan != null) ? kodekecamatan : "";
        int offset = (p - 1) * r;
        int jml = Integer.parseInt(cl.jumlahuserkecamatan());
        if (r >= jml) {
            r = jml;
        }
        cl.tampilsemuadatauserkecamatan(tbKecamatan2, username, kodekecamatan, offset, r);
        tbKecamatan.setTotal(cl.jumlahuserkecamatan());
        for (int i = 0; i < r; i++) {
            a.add(new userTbKecamatan1(tbKecamatan2.username[i], "**********", tbKecamatan2.kodekecamatan[i]));
            //System.out.println(i);
        }

        tbKecamatan.setRows(a);
        return tbKecamatan;
    }
        
    @RequestMapping(value = "/user/userkecamatan/hapus", method = RequestMethod.POST)
    public @ResponseBody
    uservalidasi hapususer(@RequestParam(value = "id", required = false) String username, HttpSession session) {
        String respon = "0";
        uservalidasi kec = new uservalidasi();
        if (session.getAttribute("user").equals("")) {
            kec.setMsg("Penyimpanan gagal");
            kec.setSuccess(false);
        } else {
            respon = cl.deleteuserkecamatan(username);
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

   public static class userTbKecamatan {

        String Total;
        List<userTbKecamatan1> rows;

        public String getTotal() {
            return Total;
        }

        public void setTotal(String Total) {
            this.Total = Total;
        }

        public List<userTbKecamatan1> getRows() {
            return rows;
        }

        public void setRows(List<userTbKecamatan1> rows) {
            this.rows = rows;
        }

        public userTbKecamatan() {
        }
    }

    public static class userTbKecamatan1 {

        String username;
        String password;
        String kodekecamatan;

        public userTbKecamatan1(String username, String password, String kodekecamatan) {
            setUsername(username);
            setPassword(password);
            setKodekecamatan(kodekecamatan);

        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getKodekecamatan() {
            return kodekecamatan;
        }

        public void setKodekecamatan(String kodekecamatan) {
            this.kodekecamatan = kodekecamatan;
        }
    }

    public static class userTbKecamatan2 {

        String[] username;
        String[] password;
        String[] kodekecamatan;

        public String[] getUsername() {
            return username;
        }

        public void setUsername(String[] username) {
            this.username = username;
        }

        public String[] getPassword() {
            return password;
        }

        public void setPassword(String[] password) {
            this.password = password;
        }

        public String[] getKodekecamatan() {
            return kodekecamatan;
        }

        public void setKodekecamatan(String[] kodekecamatan) {
            this.kodekecamatan = kodekecamatan;
        }
    }
    
    public static class uservalidasi {

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
