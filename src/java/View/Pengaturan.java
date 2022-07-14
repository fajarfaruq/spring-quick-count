/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author user
 */
@Controller
public class Pengaturan {
    MenuHandle mh = new MenuHandle();
    
    @RequestMapping("/pengaturan")
    public String pengaturan(ModelMap model, HttpSession session, HttpServletRequest request) {
        String Halaman = "redirect";
        try {
            if (session.getAttribute("user").equals("")) {
                Halaman = "redirect";
            } else {
                model.addAttribute("kontenutama","pengaturan/pengaturan.jsp");
                model.addAttribute("alert","");
                mh.menuhandle(model,"pengaturan","");
                Halaman = "index";
            }
        } catch (Exception e) {
            Halaman = "redirect";
        }
        return Halaman;
    }

    @RequestMapping("/pengaturan/akun")
    public String pengaturanakun(ModelMap model, HttpSession session, HttpServletRequest request) {
        String Halaman = "redirect";
        try {
            if (session.getAttribute("user").equals("")) {
                Halaman = "redirect";
            } else {
                model.addAttribute("kontenutama","pengaturan/akun.jsp");
                model.addAttribute("alert","");
                mh.menuhandle(model,"pengaturan","pengaturanakun");
                Halaman = "index";
            }
        } catch (Exception e) {
            Halaman = "redirect";
        }
        return Halaman;
    }
    
    @RequestMapping("/pengaturan/database")
    public String pengaturandatabase(ModelMap model, HttpSession session, HttpServletRequest request) {
        String Halaman = "redirect";
        try {
            if (session.getAttribute("user").equals("")) {
                Halaman = "redirect";
            } else {
                model.addAttribute("kontenutama","pengaturan/database.jsp");
                model.addAttribute("alert","");
                mh.menuhandle(model,"pengaturan","pengaturandatabase");
                Halaman = "index";
            }
        } catch (Exception e) {
            Halaman = "redirect";
        }
        return Halaman;
    }
    

}
