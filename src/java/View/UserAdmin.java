/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ControllerLoginAdmin;
import Model.TbLoginadmin;
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
public class UserAdmin {
    ControllerLoginAdmin cl = new ControllerLoginAdmin();
    MenuHandle mh = new MenuHandle();
        
    @RequestMapping("/user/useradmin")
    public String userkecamatan(ModelMap model, HttpSession session, HttpServletRequest request) {
        String Halaman = "redirect";
        try {
            if (session.getAttribute("user").equals("")) {
                Halaman = "redirect";
            } else {
                model.addAttribute("kontenutama", "user/useradmin.jsp");
                model.addAttribute("alert", "");
                mh.menuhandle(model, "user", "useradmin");
                Halaman = "index";
            }
        } catch (Exception e) {
            Halaman = "redirect";
        }
        return Halaman;
    }
    
    

    @RequestMapping(value = "/user/useradmin/tambah", method = RequestMethod.POST)
    public @ResponseBody
    uservalidasi tambahuser(@RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "password", required = false) String password, HttpSession session) {
        TbLoginadmin tbLoginadmin = new TbLoginadmin();
        tbLoginadmin.setUsername(username);
        tbLoginadmin.setPassword(password);
        tbLoginadmin.setKodeHakAkses("1");
        String respon = "0";
        uservalidasi kec = new uservalidasi();
        if (session.getAttribute("user").equals("")) {
            kec.setMsg("Penyimpanan gagal");
            kec.setSuccess(false);
        } else {
            respon = cl.insertuseradmin(tbLoginadmin);
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

    @RequestMapping(value = "/user/useradmin/edit", method = RequestMethod.POST)
    public @ResponseBody
    uservalidasi edituser(@RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "password", required = false) String password, HttpSession session) {
        TbLoginadmin tbLoginadmin = new TbLoginadmin();
        tbLoginadmin.setUsername(username);
        tbLoginadmin.setPassword(password);
        tbLoginadmin.setKodeHakAkses("1");
        String respon = "0";
        uservalidasi kec = new uservalidasi();
        if (session.getAttribute("user").equals("")) {
            kec.setMsg("Penyimpanan gagal");
            kec.setSuccess(false);
        } else {
            respon =cl.updateuseradmin(tbLoginadmin);
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

   @RequestMapping(value = "/user/useradmin/tampil", method = RequestMethod.POST)
    public @ResponseBody
    userTbAdmin tampiluserAdminJSON(@RequestParam(value = "rows") String rows,
            @RequestParam(value = "page") String page, @RequestParam(value = "username", required = false) String username) {
        userTbAdmin tbAdmin = new userTbAdmin();
        userTbAdmin2 tbAdmin2 = new userTbAdmin2();
        List<userTbAdmin1> a = new ArrayList<userTbAdmin1>();
        int r = (rows != null) ? Integer.parseInt(rows) : 10;
        int p = (page != null) ? Integer.parseInt(page) : 1;
        username = (username != null) ? username : "";
        int offset = (p - 1) * r;
        int jml = Integer.parseInt(cl.jumlahuseradmin());
        if (r >= jml) {
            r = jml;
        }
        cl.tampilsemuadatauseradmin(tbAdmin2, username, offset, r);
        tbAdmin.setTotal(cl.jumlahuseradmin());
        for (int i = 0; i < r; i++) {
            a.add(new userTbAdmin1(tbAdmin2.username[i], "**********"));
            //System.out.println(i);
        }

        tbAdmin.setRows(a);
        return tbAdmin;
    }
        
    @RequestMapping(value = "/user/useradmin/hapus", method = RequestMethod.POST)
    public @ResponseBody
    uservalidasi hapususer(@RequestParam(value = "id", required = false) String username, HttpSession session) {
        uservalidasi kec = new uservalidasi();
        String respon = "0";
        if (session.getAttribute("user").equals("")) {
            kec.setMsg("Penyimpanan gagal");
            kec.setSuccess(false);
        } else {
            if (username.equals("admin")) {
                kec.setMsg("User Admin Tidak Bisa Dihapus !!!");
                kec.setSuccess(false);
            } else {
                respon = cl.deleteuseradmin(username);
                if (respon.equals("1")) {
                    kec.setMsg("Penyimpanan berhasil");
                    kec.setSuccess(true);
                } else {
                    kec.setMsg("Penyimpanan gagal");
                    kec.setSuccess(false);
                }
            }
        }
        return kec;
    }
    
    public static class userTbAdmin {

        String Total;
        List<userTbAdmin1> rows;

        public String getTotal() {
            return Total;
        }

        public void setTotal(String Total) {
            this.Total = Total;
        }

        public List<userTbAdmin1> getRows() {
            return rows;
        }

        public void setRows(List<userTbAdmin1> rows) {
            this.rows = rows;
        }

        public userTbAdmin() {
        }
    }

    public static class userTbAdmin1 {

        String username;
        String password;

        public userTbAdmin1(String username, String password) {
            setUsername(username);
            setPassword(password);

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

    }

    public static class userTbAdmin2 {

        String[] username;
        String[] password;

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
