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
public class Suara {
    MenuHandle mh = new MenuHandle();
    
    @RequestMapping("/perolehansuara")
    public String suara(ModelMap model, HttpSession session, HttpServletRequest request) {
        String Halaman = "redirect";
        try {
            if (session.getAttribute("user").equals("")) {
                Halaman = "redirect";
            } else {
                model.addAttribute("kontenutama","perolehansuara/suara.jsp");
                model.addAttribute("alert","");
                mh.menuhandle(model,"suara","");
                Halaman = "index";
            }
        } catch (Exception e) {
            Halaman = "redirect";
        }
        return Halaman;
    }    
}
