/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ControllerLoginKelurahan;
import Model.TbLoginkelurahan;
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
 * @author Administrator
 */
@Controller
public class UserKelurahan {
    ControllerLoginKelurahan cl = new ControllerLoginKelurahan();
    MenuHandle mh = new MenuHandle();
    
    @RequestMapping("/user/userkelurahan")
    public String userhelpdesk(ModelMap model, HttpSession session, HttpServletRequest request) {
        String Halaman = "redirect";
        try {
            if (session.getAttribute("user").equals("")) {
                Halaman = "redirect";
            } else {
                model.addAttribute("kontenutama", "user/userkelurahan.jsp");
                model.addAttribute("alert", "");
                mh.menuhandle(model, "user", "userkelurahan");
                Halaman = "index";
            }
        } catch (Exception e) {
            Halaman = "redirect";
        }
        return Halaman;
    }    
    
    @RequestMapping(value = "/user/userkelurahan/tambah", method = RequestMethod.POST)
    public @ResponseBody
    uservalidasi tambahuser(@RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "password", required = false) String password, 
            @RequestParam(value = "kodekelurahan", required = false) String kodekelurahan,HttpSession session) {
        TbLoginkelurahan tbLoginkelurahan = new TbLoginkelurahan();
        tbLoginkelurahan.setUsername(username);
        tbLoginkelurahan.setPassword(password);
        tbLoginkelurahan.setKodeKelurahan(kodekelurahan);
        tbLoginkelurahan.setKodeHakAkses("3");
        String respon = "0";
        uservalidasi kec = new uservalidasi();
        if (session.getAttribute("user").equals("")) {
            kec.setMsg("Penyimpanan gagal");
            kec.setSuccess(false);
        } else {
            respon = cl.insertuserkelurahan(tbLoginkelurahan);
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
    
    @RequestMapping(value = "/user/userkelurahan/edit", method = RequestMethod.POST)
    public @ResponseBody
    uservalidasi edituser(@RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "password", required = false) String password, 
            @RequestParam(value = "kodekelurahan", required = false) String kodekelurahan,HttpSession session) {
        TbLoginkelurahan tbLoginkelurahan = new TbLoginkelurahan();
        tbLoginkelurahan.setUsername(username);
        tbLoginkelurahan.setPassword(password);
        tbLoginkelurahan.setKodeKelurahan(kodekelurahan);
        tbLoginkelurahan.setKodeHakAkses("5");
        String respon = "0";
        uservalidasi kec = new uservalidasi();
        if (session.getAttribute("user").equals("")) {
            kec.setMsg("Penyimpanan gagal");
            kec.setSuccess(false);
        } else {
            respon = cl.updateuserkelurahan(tbLoginkelurahan);
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
    
    
    @RequestMapping(value = "/user/userkelurahan/tampil", method = RequestMethod.POST)
    public @ResponseBody
    userTbKelurahan tampiluserHelpDeskJSON(@RequestParam(value = "rows") String rows,
            @RequestParam(value = "page") String page, @RequestParam(value = "username", required = false) String username) {
        userTbKelurahan tbKelurahan = new userTbKelurahan();
        userTbKelurahan2 tbKelurahan2 = new userTbKelurahan2();
        List<userTbKelurahan1> a = new ArrayList<userTbKelurahan1>();
        int r = (rows != null) ? Integer.parseInt(rows) : 10;
        int p = (page != null) ? Integer.parseInt(page) : 1;
        username = (username != null) ? username : "";
        int offset = (p - 1) * r;
        int jml = Integer.parseInt(cl.jumlahuserkelurahan());
        if (r >= jml) {
            r = jml;
        }
        cl.tampilsemuadatauserkelurahan(tbKelurahan2, username, offset, r);
        tbKelurahan.setTotal(cl.jumlahuserkelurahan());
        for (int i = 0; i < r; i++) {
            a.add(new userTbKelurahan1(tbKelurahan2.username[i], "**********" , tbKelurahan2.kodekelurahan[i]));
            //System.out.println(i);
        }
        tbKelurahan.setRows(a);
        return tbKelurahan;
    }
    
    @RequestMapping(value = "/user/userkelurahan/hapus", method = RequestMethod.POST)
    public @ResponseBody
    uservalidasi hapususer(@RequestParam(value = "id", required = false) String username, HttpSession session) {
        String respon = "0";
        uservalidasi kec = new uservalidasi();
        if (session.getAttribute("user").equals("")) {
            kec.setMsg("Penyimpanan gagal");
            kec.setSuccess(false);
        } else {
            respon = cl.deleteuserkelurahan(username);
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
    
    public static class userTbKelurahan {

        String Total;
        List<userTbKelurahan1> rows;

        public String getTotal() {
            return Total;
        }

        public void setTotal(String Total) {
            this.Total = Total;
        }

        public List<userTbKelurahan1> getRows() {
            return rows;
        }

        public void setRows(List<userTbKelurahan1> rows) {
            this.rows = rows;
        }

        public userTbKelurahan() {
        }
    }

    public static class userTbKelurahan1 {

        String username;
        String password;
        String kodekelurahan;

        public userTbKelurahan1(String username, String password , String kodekelurahan) {
            setUsername(username);
            setPassword(password);
            setKodekelurahan(kodekelurahan);
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

        public String getKodekelurahan() {
            return kodekelurahan;
        }

        public void setKodekelurahan(String kodekelurahan) {
            this.kodekelurahan = kodekelurahan;
        }
        
    }

    public static class userTbKelurahan2 {

        String[] username;
        String[] password;
        String[] kodekelurahan;

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

        public String[] getKodekelurahan() {
            return kodekelurahan;
        }

        public void setKodekelurahan(String[] kodekelurahan) {
            this.kodekelurahan = kodekelurahan;
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
