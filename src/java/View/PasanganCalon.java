/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ControllerPasanganCalon;
import Model.TbPasangancalon;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author user
 */
@Controller
public class PasanganCalon {

    ControllerPasanganCalon cpc = new ControllerPasanganCalon();
    MenuHandle mh = new MenuHandle();

    @RequestMapping("/pasangancalon")
    public String pasangancalon(ModelMap model, HttpSession session, HttpServletRequest request) {
        String Halaman = "redirect";
        try {
            if (session.getAttribute("user").equals("")) {
                Halaman = "redirect";
            } else {
                model.addAttribute("kontenutama", "pasangancalon/pasangancalon.jsp");
                model.addAttribute("alert", "");
                mh.menuhandle(model, "pasangancalon", "");
                Halaman = "index";
            }
        } catch (Exception e) {
            Halaman = "redirect";
        }
        return Halaman;
    }

    @RequestMapping(value = "/pasangancalon/managedata", method = RequestMethod.GET)
    public String pasangancalonmanagedata(ModelMap model, HttpSession session, HttpServletRequest request) {
        String Halaman = "redirect";
        try {
            if (session.getAttribute("user").equals("")) {
                Halaman = "redirect";
            } else {
                model.addAttribute("kontenutama", "pasangancalon/managedata.jsp");
                model.addAttribute("alert", "");
                model.addAttribute(new pasangancalonhandle());
                mh.menuhandle(model, "pasangancalon", "pasangancalondata");
                Halaman = "index";
            }
        } catch (Exception e) {
            Halaman = "redirect";
        }
        return Halaman;
    }

    @RequestMapping(value = "/pasangancalon/managedata", method = RequestMethod.POST)
    public String tambah(pasangancalonhandle pshandel, BindingResult result,
            HttpServletRequest request, HttpServletResponse response,
            HttpSession session, ModelMap model) {
        
        if (session.getAttribute("user").equals("")) {
            return "redirect";
        }
        
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.err.println("Error: " + error.getCode() + " - "
                        + error.getDefaultMessage());
            }
            String alert = "$.messager.show({title: 'Error',msg: 'Terjadi Kesalahan Penyimpanan Data'});";
            model.addAttribute("kontenutama", "pasangancalon/managedata.jsp");
            model.addAttribute("alert", alert);
            model.addAttribute(new pasangancalonhandle());
            mh.menuhandle(model, "pasangancalon", "pasangancalondata");
            return "index";
        }

        // Some type of file processing...
        System.err.println("-------------------------------------------");
        try {
            MultipartFile file = pshandel.getFileData();
            String fileName = null;
            InputStream inputStream = null;
            OutputStream outputStream = null;
            if (file.getSize() > 0) {
                inputStream = file.getInputStream();
                if (file.getSize() > 70000) {
                    System.out.println("File Size:::" + file.getSize());
                    String alert = "$.messager.show({title: 'Error',msg: 'Ukuran file harus < 70kb'});";
                    model.addAttribute("kontenutama", "pasangancalon/managedata.jsp");
                    model.addAttribute("alert", alert);
                    model.addAttribute(new pasangancalonhandle());
                    mh.menuhandle(model, "pasangancalon", "pasangancalondata");
                    return "index";
                }
                System.out.println("size::" + file.getSize());
                fileName = request.getRealPath("") + "\\upload\\pasangancalon\\"
                        + file.getOriginalFilename();
                outputStream = new FileOutputStream(fileName);
                System.out.println("fileName:" + file.getOriginalFilename());
                System.out.println("lokasi:" + fileName);
                cpc.insert(new TbPasangancalon(pshandel.getKodepasangancalon(), pshandel.getGubernur(), pshandel.getWakilgubernur(), file.getOriginalFilename()));
                int readBytes = 0;
                byte[] buffer = new byte[70000];
                while ((readBytes = inputStream.read(buffer, 0, 70000)) != -1) {
                    outputStream.write(buffer, 0, readBytes);
                }
                outputStream.close();
                inputStream.close();
            }
            // ..........................................
            session.setAttribute("uploadFile", file.getOriginalFilename());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:managedata/";
    }

    @RequestMapping(value = "/pasangancalon/managedata/edit", method = RequestMethod.POST)
    public @ResponseBody
    pasangancalonvalidasi edit(pasangancalonhandle pshandel, BindingResult result,
            HttpServletRequest request, HttpServletResponse response,
            HttpSession session, ModelMap model) {
        pasangancalonvalidasi psval = new pasangancalonvalidasi();
        if (session.getAttribute("user").equals("")) {
            psval.setMsg("Terjadi Kegagalan Edit");
            psval.setSuccess(false);
            return psval;
        }
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.err.println("Error: " + error.getCode() + " - "
                        + error.getDefaultMessage());
            }
            psval.setMsg("Terjadi Kegagalan Edit");
            psval.setSuccess(false);
            //return "index";
        }

        // Some type of file processing...
        System.err.println("-------------------------------------------");
        try {
            MultipartFile file = pshandel.getFileData();
            String fileName = null;
            InputStream inputStream = null;
            OutputStream outputStream = null;
            if (file.getSize() > 0) {
                inputStream = file.getInputStream();
                if (file.getSize() > 70000) {
                    System.out.println("File Size:::" + file.getSize());
                    psval.setMsg("Ukuran Gambar Harus <70 kb .. !!");
                    psval.setSuccess(false);
                    //return "index";
                }
                System.out.println("size::" + file.getSize());
                fileName = request.getRealPath("") + "\\upload\\pasangancalon\\"
                        + file.getOriginalFilename();
                outputStream = new FileOutputStream(fileName);
                System.out.println("fileName:" + file.getOriginalFilename());
                System.out.println("lokasi:" + fileName);
                String respon = cpc.update(new TbPasangancalon(pshandel.getKodepasangancalon(), pshandel.getGubernur(), pshandel.getWakilgubernur(), file.getOriginalFilename()));
                if (respon.equals("1")) {
                    psval.setMsg("Penyimpanan berhasil");
                    psval.setSuccess(true);
                } else {
                    psval.setMsg("Penyimpanan gagal");
                    psval.setSuccess(false);
                }
                int readBytes = 0;
                byte[] buffer = new byte[70000];
                while ((readBytes = inputStream.read(buffer, 0, 70000)) != -1) {
                    outputStream.write(buffer, 0, readBytes);
                }
                outputStream.close();
                inputStream.close();
            }
            // ..........................................
            session.setAttribute("uploadFile", file.getOriginalFilename());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return psval;
    }

    @RequestMapping(value = "/pasangancalon/managedata/hapus", method = RequestMethod.POST)
    public @ResponseBody
    pasangancalonvalidasi hapuspasangancalon(@RequestParam(value = "id", required = false) String kodepasangancalon, HttpSession session) {
        String respon = "0";
        pasangancalonvalidasi psval = new pasangancalonvalidasi();
        if (session.getAttribute("user").equals("")) {
            psval.setMsg("Penyimpanan gagal");
            psval.setSuccess(false);
        } else {
            respon = cpc.delete(kodepasangancalon);
            if (respon.equals("1")) {
                psval.setMsg("Penyimpanan berhasil");
                psval.setSuccess(true);
            } else {
                psval.setMsg("Penyimpanan gagal");
                psval.setSuccess(false);
            }
        }
        return psval;
    }

    @RequestMapping(value = "/pasangancalon/tampil", method = RequestMethod.POST)
    public @ResponseBody
    listTbPasanganCalon tampilPasanganCalonJSON(@RequestParam(value = "rows") String rows,
            @RequestParam(value = "page") String page) throws IOException {
        listTbPasanganCalon tbPasanganCalon = new listTbPasanganCalon();
        listTbPasanganCalon2 pasanganCalon2 = new listTbPasanganCalon2();
        List<listTbPasanganCalon1> a = new ArrayList<listTbPasanganCalon1>();
        int r = (rows != null) ? Integer.parseInt(rows) : 10;
        int p = (page != null) ? Integer.parseInt(page) : 1;
        int offset = (p - 1) * r;
        int jml = Integer.parseInt(cpc.jumlahdata());
        if (r >= jml) {
            r = jml;
        }
        cpc.tampilsemuadata(pasanganCalon2, offset, r);
        tbPasanganCalon.setTotal(cpc.jumlahdata());
        for (int i = 0; i < r; i++) {
            a.add(new listTbPasanganCalon1(pasanganCalon2.kodepasangancalon[i], pasanganCalon2.gubernur[i], pasanganCalon2.wakilgubernur[i], pasanganCalon2.gambar[i]));
            //System.out.println(i);
        }
        tbPasanganCalon.setRows(a);
        return tbPasanganCalon;
    }

    @RequestMapping("/pasangancalon/listpasangancalon")
    public @ResponseBody
    List<listTbPasanganCalon1> tampilPasangancaloncomboJSON() {
        listTbPasanganCalon2 pasanganCalon2 = new listTbPasanganCalon2();
        List<listTbPasanganCalon1> a = new ArrayList<listTbPasanganCalon1>();
        int jml = Integer.parseInt(cpc.jumlahdata());
        cpc.tampilsemuadata(pasanganCalon2, 0, jml);
        for (int i = 0; i < jml; i++) {
            a.add(new listTbPasanganCalon1(pasanganCalon2.kodepasangancalon[i], pasanganCalon2.gubernur[i], pasanganCalon2.wakilgubernur[i], pasanganCalon2.gambar[i]));
            System.out.println(i);
        }
        return a;
    }
    
    @RequestMapping("/pasangancalon/listpasangancalon/{}")
    public @ResponseBody
    List<listTbPasanganCalon1> tampilPasangancalondetailcomboJSON() {
        listTbPasanganCalon2 pasanganCalon2 = new listTbPasanganCalon2();
        List<listTbPasanganCalon1> a = new ArrayList<listTbPasanganCalon1>();
        int jml = Integer.parseInt(cpc.jumlahdata());
        cpc.tampilsemuadata(pasanganCalon2, 0, jml);
        for (int i = 0; i < jml; i++) {
            a.add(new listTbPasanganCalon1(pasanganCalon2.kodepasangancalon[i], pasanganCalon2.gubernur[i], pasanganCalon2.wakilgubernur[i], pasanganCalon2.gambar[i]));
            System.out.println(i);
        }
        return a;
    }

    private static class listTbPasanganCalon {

        String Total;
        List<listTbPasanganCalon1> rows;

        public String getTotal() {
            return Total;
        }

        public void setTotal(String Total) {
            this.Total = Total;
        }

        public List<listTbPasanganCalon1> getRows() {
            return rows;
        }

        public void setRows(List<listTbPasanganCalon1> rows) {
            this.rows = rows;
        }
    }

    private static class listTbPasanganCalon1 {

        String kodepasangancalon;
        String gubernur;
        String wakilgubernur;
        String gambar;

        public listTbPasanganCalon1(String Kodepasangacalon, String Gubernur, String Wakilgubernur, String Gambar) {
            setKodepasangancalon(Kodepasangacalon);
            setGubernur(Gubernur);
            setWakilgubernur(Wakilgubernur);
            setGambar(Gambar);
        }

        public String getKodepasangancalon() {
            return kodepasangancalon;
        }

        public void setKodepasangancalon(String kodepasangancalon) {
            this.kodepasangancalon = kodepasangancalon;
        }

        public String getGubernur() {
            return gubernur;
        }

        public void setGubernur(String gubernur) {
            this.gubernur = gubernur;
        }

        public String getWakilgubernur() {
            return wakilgubernur;
        }

        public void setWakilgubernur(String wakilgubernur) {
            this.wakilgubernur = wakilgubernur;
        }

        public String getGambar() {
            return gambar;
        }

        public void setGambar(String gambar) {
            this.gambar = gambar;
        }
    }

    public static class listTbPasanganCalon2 {

        String[] kodepasangancalon;
        String[] gubernur;
        String[] wakilgubernur;
        String[] gambar;

        public String[] getKodepasangancalon() {
            return kodepasangancalon;
        }

        public void setKodepasangancalon(String[] kodepasangancalon) {
            this.kodepasangancalon = kodepasangancalon;
        }

        public String[] getGubernur() {
            return gubernur;
        }

        public void setGubernur(String[] gubernur) {
            this.gubernur = gubernur;
        }

        public String[] getWakilgubernur() {
            return wakilgubernur;
        }

        public void setWakilgubernur(String[] wakilgubernur) {
            this.wakilgubernur = wakilgubernur;
        }

        public String[] getGambar() {
            return gambar;
        }

        public void setGambar(String[] gambar) {
            this.gambar = gambar;
        }
    }

    public static class pasangancalonvalidasi {

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