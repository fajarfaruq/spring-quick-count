/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ControllerLoginHelpdesk;
import Model.TbLoginhelpdesk;
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
public class UserHelpDesk {
    
    ControllerLoginHelpdesk cl = new ControllerLoginHelpdesk();
    MenuHandle mh = new MenuHandle();
        
    @RequestMapping("/user/userhelpdesk")
    public String userhelpdesk(ModelMap model, HttpSession session, HttpServletRequest request) {
        String Halaman = "redirect";
        try {
            if (session.getAttribute("user").equals("")) {
                Halaman = "redirect";
            } else {
                model.addAttribute("kontenutama", "user/userhelpdesk.jsp");
                model.addAttribute("alert", "");
                mh.menuhandle(model, "user", "userhelpdesk");
                Halaman = "index";
            }
        } catch (Exception e) {
            Halaman = "redirect";
        }
        return Halaman;
    }
    
    

    @RequestMapping(value = "/user/userhelpdesk/tambah", method = RequestMethod.POST)
    public @ResponseBody
    uservalidasi tambahuser(@RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "password", required = false) String password, HttpSession session) {
        TbLoginhelpdesk tbLoginhelpdesk = new TbLoginhelpdesk();
        tbLoginhelpdesk.setUsername(username);
        tbLoginhelpdesk.setPassword(password);
        tbLoginhelpdesk.setKodeHakAkses("5");
        String respon = "0";
        uservalidasi kec = new uservalidasi();
        if (session.getAttribute("user").equals("")) {
            kec.setMsg("Penyimpanan gagal");
            kec.setSuccess(false);
        } else {
            respon = cl.insertuserhelpdesk(tbLoginhelpdesk);
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

    @RequestMapping(value = "/user/userhelpdesk/edit", method = RequestMethod.POST)
    public @ResponseBody
    uservalidasi edituser(@RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "password", required = false) String password, HttpSession session) {
        TbLoginhelpdesk tbLoginhelpdesk = new TbLoginhelpdesk();
        tbLoginhelpdesk.setUsername(username);
        tbLoginhelpdesk.setPassword(password);
        tbLoginhelpdesk.setKodeHakAkses("5");
        String respon = "0";
        uservalidasi kec = new uservalidasi();
        if (session.getAttribute("user").equals("")) {
            kec.setMsg("Penyimpanan gagal");
            kec.setSuccess(false);
        } else {
            respon = cl.updateuserhelpdesk(tbLoginhelpdesk);
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

   @RequestMapping(value = "/user/userhelpdesk/tampil", method = RequestMethod.POST)
    public @ResponseBody
    userTbHelpDesk tampiluserHelpDeskJSON(@RequestParam(value = "rows") String rows,
            @RequestParam(value = "page") String page, @RequestParam(value = "username", required = false) String username) {
        userTbHelpDesk tbHelpDesk = new userTbHelpDesk();
        userTbHelpDesk2 tbHelpDesk2 = new userTbHelpDesk2();
        List<userTbHelpDesk1> a = new ArrayList<userTbHelpDesk1>();
        int r = (rows != null) ? Integer.parseInt(rows) : 10;
        int p = (page != null) ? Integer.parseInt(page) : 1;
        username = (username != null) ? username : "";
        int offset = (p - 1) * r;
        int jml = Integer.parseInt(cl.jumlahuserhelpdesk());
        if (r >= jml) {
            r = jml;
        }
        cl.tampilsemuadatauserhelpdesk(tbHelpDesk2, username, offset, r);
        tbHelpDesk.setTotal(cl.jumlahuserhelpdesk());
        for (int i = 0; i < r; i++) {
            a.add(new userTbHelpDesk1(tbHelpDesk2.username[i], "**********"));
            //System.out.println(i);
        }

        tbHelpDesk.setRows(a);
        return tbHelpDesk;
    }
        
    @RequestMapping(value = "/user/userhelpdesk/hapus", method = RequestMethod.POST)
    public @ResponseBody
    uservalidasi hapususer(@RequestParam(value = "id", required = false) String username, HttpSession session) {
        String respon = "0";
        uservalidasi kec = new uservalidasi();
        if (session.getAttribute("user").equals("")) {
            kec.setMsg("Penyimpanan gagal");
            kec.setSuccess(false);
        } else {
            respon = cl.deleteuserhelpdesk(username);
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
    
    
    public static class userTbHelpDesk {

        String Total;
        List<userTbHelpDesk1> rows;

        public String getTotal() {
            return Total;
        }

        public void setTotal(String Total) {
            this.Total = Total;
        }

        public List<userTbHelpDesk1> getRows() {
            return rows;
        }

        public void setRows(List<userTbHelpDesk1> rows) {
            this.rows = rows;
        }

        public userTbHelpDesk() {
        }
    }

    public static class userTbHelpDesk1 {

        String username;
        String password;

        public userTbHelpDesk1(String username, String password) {
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

    public static class userTbHelpDesk2 {

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
