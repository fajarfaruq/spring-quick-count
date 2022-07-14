/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import org.springframework.ui.ModelMap;

/**
 *
 * @author user
 */
public class MenuHandle {
    public void menuhandle(ModelMap model,String menumain,String menusub){
        String menu[] = {"dashboard",
                         "pasangancalon","pasangancalondata",
                         "kecamatan","kecamatandata",
                         "kelurahan","kelurahandata",
                         "tps","tpsdata",
                         "suara","suaratps","suarakelurahan","suarakecamatan","suarasemua",
                         "rekapitulasi","rekapitulasitps","rekapitulasikelurahan","rekapitulasikecamatan","rekapitulasisemua",
                         "user","userkecamatan","userkelurahan","usertps","userhelpdesk","useradmin",
                         "pengaturan","pengaturanakun","pengaturandatabase"};
        for(int i=0;i<menu.length;i++){
            if(menumain.equals(menu[i]) || menusub.equals(menu[i])){
               model.addAttribute("menu" + String.valueOf(i), "current");
            }else{
               model.addAttribute("menu" + String.valueOf(i), " ");
            }
        }
    }
}
