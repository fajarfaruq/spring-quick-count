/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ControllerLoginAdmin;
import Model.TbLoginadmin;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.support.HttpRequestHandlerServlet;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Administrator
 */
// LOGIN HANDLER    
@Controller
public class LoginAdmin {

    ControllerLoginAdmin clogin = new ControllerLoginAdmin();
    MenuHandle mh = new MenuHandle();
    
    @RequestMapping("/")
    public String halamanawal(ModelMap model, HttpSession session, HttpServletRequest request) {
        String Halaman = "loginadmin";
        try {
            if (session.getAttribute("user").equals("")) {
                Halaman = "loginadmin";
            } else {
                model.addAttribute("kontenutama","dashboard.jsp");
                model.addAttribute("alert","");
                mh.menuhandle(model,"dashboard","");
                Halaman = "index";
            }
        } catch (Exception e) {
            Halaman = "loginadmin";
        }
        return Halaman;
    }

    @RequestMapping(value = "masuk", method = RequestMethod.POST)
    public String masukadmin(@ModelAttribute("login") TbLoginadmin tbLoginadmin, ModelMap model, HttpSession session, HttpServletRequest request) {
        String alert = "";
        String Halaman = "loginadmin";
        
        
//        ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
//        reCaptcha.setPrivateKey("6LevyN4SAAAAAEg-yZzHL9NekkIWJ-Ks1LmQ_j2N");
//        String remoteAddr = request.getRemoteAddr();
//        String challengeField = request.getParameter("recaptcha_challenge_field");
//        String responseField = request.getParameter("recaptcha_response_field");
//        ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(remoteAddr, challengeField, responseField);

        try {
           // if (reCaptchaResponse.isValid()) {
                String kodeakses = clogin.cekLoginAdmin(tbLoginadmin.getUsername(), tbLoginadmin.getPassword());
                if (kodeakses.equals("1")) {
                    session.setAttribute("user", tbLoginadmin.getUsername());
                    session.setAttribute("akses", kodeakses);
                    alert = "";
                    model.addAttribute("alert", alert);
                    Halaman = "redirect";
                } else {
                    alert = "<div class=\"notification error png_bg\">\n"
                            + "<a href=\"#\" class=\"close\"><img src=" + "\"" + request.getContextPath().toString() + "/resources/images/icons/cross_grey_small.png\" title=\"Close this notification\" alt=\"close\" /></a>\n"
                            + "<div>\n"
                            + "Username atau Password Salah.\n"
                            + "</div>\n"
                            + "</div>   ";
                    model.addAttribute("alert", alert);
                    Halaman = "loginadmin";
                }
           /* } else {
                    alert = "<div class=\"notification error png_bg\">\n"
                            + "<a href=\"#\" class=\"close\"><img src=" + "\"" + request.getContextPath().toString() + "/resources/images/icons/cross_grey_small.png\" title=\"Close this notification\" alt=\"close\" /></a>\n"
                            + "<div>\n"
                            + "Captcha Salah.\n"
                            + "</div>\n"
                            + "</div>   ";
                    model.addAttribute("alert", alert);
                    Halaman = "loginadmin";
            }*/
        } catch (Exception e) {
        }
        return Halaman;
    }

    @RequestMapping("keluar")
    public String keluaradmin(ModelMap model, HttpSession session, HttpServletRequest request) {
        session.removeAttribute("user");
        session.removeAttribute("akses");      
        return "redirect";
    }
    /*Seleksi Hak Akses */
    /*1=Administrator */
}