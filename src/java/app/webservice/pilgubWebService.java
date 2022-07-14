 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.webservice;

import Controller.ControllerGrafik;
import Controller.ControllerKecamatan;
import Controller.ControllerKelurahan;
import Controller.ControllerLoginAdmin;
import Controller.ControllerLoginHelpdesk;
import Controller.ControllerLoginKecamatan;
import Controller.ControllerLoginKelurahan;
import Controller.ControllerLoginTPS;
import Controller.ControllerRekapitulasiKecamatan;
import Controller.ControllerRekapitulasiKelurahan;
import Controller.ControllerRekapitulasiSemua;
import Controller.ControllerSuaraKecamatan;
import Controller.ControllerSuaraKelurahan;
import Controller.ControllerSuaraSemua;
import Controller.ControllerSuaraTPS;
import Controller.ControllerTPS;
import Model.TbSuarasahkecamatan;
import Model.TbSuarasahkelurahan;
import Model.TbSuarasahtps;
import Model.TbSuaratidaksahkecamatan;
import Model.TbSuaratidaksahkelurahan;
import Model.TbSuaratidaksahtps;
import View.Dashboard;
import java.io.IOException;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author user
 */
@WebService(serviceName = "pilgubWebService")
public class pilgubWebService {

    String secretkey = "as-23&3zxcvbnm12345678dfgh";

    @WebMethod(operationName = "LoginUser")
    public String LoginUser(@WebParam(name = "secret") String secret, @WebParam(name = "username") String username, @WebParam(name = "password") String password, @WebParam(name = "hakakses") String hakakses) {
        String tanggapan = "0";
        if (hakakses.equals("2")) {
            if (secret.equals(secretkey)) {
                ControllerLoginKecamatan clk = new ControllerLoginKecamatan();
                tanggapan = clk.cekLoginKecamatan(username, password);
            }
        } else if (hakakses.equals("3")) {
            if (secret.equals(secretkey)) {
                ControllerLoginKelurahan clk = new ControllerLoginKelurahan();
                tanggapan = clk.cekLoginKelurahan(username, password);
            }
        } else if (hakakses.equals("4")) {
            if (secret.equals(secretkey)) {
                ControllerLoginTPS clk = new ControllerLoginTPS();
                tanggapan = clk.cekLoginTPS(username, password);
            }
        } else if (hakakses.equals("5")) {
            if (secret.equals(secretkey)) {
                ControllerLoginHelpdesk clh = new ControllerLoginHelpdesk();
                tanggapan = clh.cekLoginHelpDesk(username, password);
            }
        }
        return tanggapan;
    }

    
    /*BLOK LOGIN KECAMATAN */
    
    @WebMethod(operationName = "getkodekecamatan")
    public String getkodekecamatan(@WebParam(name = "secret") String secret, @WebParam(name = "username") String username, @WebParam(name = "password") String password) {
        String tanggapan = "0";
        if (secret.equals(secretkey)) {
            ControllerLoginKecamatan clk = new ControllerLoginKecamatan();
            clk.cekLoginKecamatan(username, password);
            tanggapan = clk.getKodekecamatan();
        }
        return tanggapan;
    }
    @WebMethod(operationName = "getnamakecamatan")
    public String getnamakecamatan(@WebParam(name = "secret") String secret, @WebParam(name = "kodekecamatan") String kodekecamatan) {
        String tanggapan = "0";
        if (secret.equals(secretkey)) {
            ControllerKecamatan ck = new ControllerKecamatan();
            tanggapan = ck.namaKecamatan(kodekecamatan);
        }
        return tanggapan;
    }

   
        
    /* BLOK REKAPITULASI KELURAHAN
     * 
     */
    @WebMethod(operationName = "getkodekelurahan")
    public String getkodekelurahan(@WebParam(name = "secret") String secret, @WebParam(name = "username") String username, @WebParam(name = "password") String password) {
        String tanggapan = "0";
        if (secret.equals(secretkey)) {
            ControllerLoginKelurahan clk = new ControllerLoginKelurahan();
            clk.cekLoginKelurahan(username, password);
            tanggapan = clk.getKodekelurahan();
        }
        return tanggapan;
    }    
    @WebMethod(operationName = "getnamakelurahan")
    public String getnamakelurahan(@WebParam(name = "secret") String secret, @WebParam(name = "kodekelurahan") String kodekelurahan) {
        String tanggapan = "0";
        if (secret.equals(secretkey)) {
            ControllerKelurahan ck = new ControllerKelurahan();
            tanggapan = ck.namaKelurahan(kodekelurahan);
        }
        return tanggapan;
    }
    @WebMethod(operationName = "getnamakecamatan_kelurahan")
    public String getnamakecamatan_kelurahan(@WebParam(name = "secret") String secret, @WebParam(name = "kodekelurahan") String kodekelurahan) {
        String tanggapan = "0";
        if (secret.equals(secretkey)) {
            ControllerKelurahan ck = new ControllerKelurahan();
            tanggapan = ck.namaKecamatan(kodekelurahan);
        }
        return tanggapan;
    }
  
    @WebMethod(operationName = "tambahrekapitulasikelurahan")
    public String tambahrekapitulasikelurahan(@WebParam(name = "secret") String secret, @WebParam(name = "kodekelurahan") String kodekelurahan, @WebParam(name = "kodepasangancalon") String kodepasangancalon, @WebParam(name = "suarasah") int suarasah, @WebParam(name = "suaratidaksah") int suaratidaksah,@WebParam(name = "tervalidasi") String tervalidasi) {
        String tanggapan = "0";
        try {
            if (secret.equals(secretkey)) {
                ControllerRekapitulasiKelurahan crk = new ControllerRekapitulasiKelurahan();
                tanggapan = crk.insert(new TbSuarasahkelurahan(null, kodekelurahan, kodepasangancalon, suarasah), new TbSuaratidaksahkelurahan(kodekelurahan, suaratidaksah, tervalidasi));
                System.out.println(tanggapan);
            }
        } catch (Exception e) {
        }
        return tanggapan;
    }   
    @WebMethod(operationName = "tambahrekapitulasikelurahan_suarasah")
    public String tambahrekapitulasikelurahan_suarasah(@WebParam(name = "secret") String secret,
    @WebParam(name = "kodekelurahan") String kodekelurahan, 
    @WebParam(name = "kodepasangancalon") String kodepasangancalon, 
    @WebParam(name = "suarasah") int suarasah) {
        String tanggapan = "0";
        try {
            if (secret.equals(secretkey)) {
                ControllerRekapitulasiKelurahan crk = new ControllerRekapitulasiKelurahan();
                tanggapan = crk.updatesuarasah(new TbSuarasahkelurahan(null, kodekelurahan, kodepasangancalon, suarasah));
                System.out.println(tanggapan);
            }
        } catch (Exception e) {
        }
        return tanggapan;
    }
    
    @WebMethod(operationName = "tambahrekapitulasikelurahan_suaratidaksah")
    public String tambahrekapitulasikelurahan_suaratidaksah(@WebParam(name = "secret") String secret,
    @WebParam(name = "kodekelurahan") String kodekelurahan,
    @WebParam(name = "suaratidaksah") int suaratidaksah,
    @WebParam(name = "tervalidasi") String tervalidasi) {
        String tanggapan = "0";
        try {
            if (secret.equals(secretkey)) {
                ControllerRekapitulasiKelurahan crk = new ControllerRekapitulasiKelurahan();
                tanggapan = crk.updatesuaratidaksah(new TbSuaratidaksahkelurahan(kodekelurahan, suaratidaksah, tervalidasi));
                System.out.println(tanggapan);
            }
        } catch (Exception e) {
        }
        return tanggapan;
    }       
    
    @WebMethod(operationName = "generatesuarakelurahan")
    public String generatesuarakelurahan(@WebParam(name = "secret") String secret,
    @WebParam(name = "kodekelurahan") String kodekelurahan) {
        String tanggapan = "0";
        try {
            if (secret.equals(secretkey)) {
                ControllerRekapitulasiKelurahan crk = new ControllerRekapitulasiKelurahan();
                tanggapan = crk.generate(kodekelurahan);
                System.out.println(tanggapan);
            }
        } catch (Exception e) {
        }
        return tanggapan;
    }       
    
    
    @WebMethod(operationName = "updaterekapitulasikelurahan")
    public String updaterekapitulasikelurahan(@WebParam(name = "secret") String secret, @WebParam(name = "kodesuarakelurahan") int kodesuarakelurahan, @WebParam(name = "kodekelurahan") String kodekelurahan, @WebParam(name = "kodepasangancalon") String kodepasangancalon, @WebParam(name = "suarasah") int suarasah, @WebParam(name = "suaratidaksah") int suaratidaksah) {
        String tanggapan = "0";
        try {
            if (secret.equals(secretkey)) {
                ControllerRekapitulasiKelurahan crk = new ControllerRekapitulasiKelurahan();
                tanggapan = crk.update(new TbSuarasahkelurahan(kodesuarakelurahan, kodekelurahan, kodepasangancalon, suarasah), new TbSuaratidaksahkelurahan(kodekelurahan, suaratidaksah, "Belum"));
                System.out.println(tanggapan);
            }
        } catch (Exception e) {
        }
        return tanggapan;
    }   
    
    @WebMethod(operationName = "updaterekapitulasikelurahan_suarasah")
    public String updaterekapitulasikelurahan_suarasah(@WebParam(name = "secret") String secret,
    @WebParam(name = "kodesuarakelurahan") int kodesuarakelurahan, 
    @WebParam(name = "kodekelurahan") String kodekelurahan, 
    @WebParam(name = "kodepasangancalon") String kodepasangancalon, 
    @WebParam(name = "suarasah") int suarasah) {
        String tanggapan = "0";
        try {
            if (secret.equals(secretkey)) {
                ControllerRekapitulasiKelurahan crk = new ControllerRekapitulasiKelurahan();
                tanggapan = crk.updatesuarasah(new TbSuarasahkelurahan(kodesuarakelurahan, kodekelurahan, kodepasangancalon, suarasah));
                System.out.println(tanggapan);
            }
        } catch (Exception e) {
        }
        return tanggapan;
    }
    
    @WebMethod(operationName = "updaterekapitulasikelurahan_suaratidaksah")
    public String updaterekapitulasikelurahan_suaratidaksah(@WebParam(name = "secret") String secret,
    @WebParam(name = "kodekelurahan") String kodekelurahan,
    @WebParam(name = "suaratidaksah") int suaratidaksah,
    @WebParam(name = "tervalidasi") String tervalidasi) {
        String tanggapan = "0";
        try {
            if (secret.equals(secretkey)) {
                ControllerRekapitulasiKelurahan crk = new ControllerRekapitulasiKelurahan();
                tanggapan = crk.updatesuaratidaksah(new TbSuaratidaksahkelurahan(kodekelurahan, suaratidaksah, tervalidasi));
                System.out.println(tanggapan);
            }
        } catch (Exception e) {
        }
        return tanggapan;
    }   
    
    
   @WebMethod(operationName = "hapusrekapitulasikelurahan")
    public String hapusrekapitulasikelurahan(@WebParam(name = "secret") String secret, @WebParam(name = "kodekelurahan") String kodekelurahan) {
        String tanggapan = "0";
        try {
            if (secret.equals(secretkey)) {
                ControllerRekapitulasiKelurahan crk = new ControllerRekapitulasiKelurahan();
                tanggapan = crk.delete(kodekelurahan);
                System.out.println(tanggapan);
            }
        } catch (Exception e) {
        }
        return tanggapan;
    }
    
   @WebMethod(operationName = "printrekapitulasikelurahan")
    public String printrekapitulasikelurahan(@WebParam(name = "secret") String secret,@WebParam(name = "kodekelurahan") String kodekelurahan) {
        String tanggapan = "0";
        try {
            if (secret.equals(secretkey)) {
                ControllerRekapitulasiKelurahan ctps = new ControllerRekapitulasiKelurahan();
                tanggapan = ctps.printrekapitulasikelurahan(kodekelurahan);
            }
        } catch (Exception e) {
        }
        return tanggapan;
    }   
   
   @WebMethod(operationName = "jumlahDPTkelurahan")
    public String jumlahdptkelurahan(@WebParam(name = "secret") String secret, @WebParam(name = "kodekelurahan") String kodekelurahan) {
        String tanggapan = "0";
        if (secret.equals(secretkey)) {
            ControllerSuaraKelurahan csk = new ControllerSuaraKelurahan();
            tanggapan = csk.jumlahDPT(kodekelurahan);
        }
        return tanggapan;
    }
   
   @WebMethod(operationName = "jumlahSuarasahkelurahan")
    public String jumlahsuarasahkelurahan(@WebParam(name = "secret") String secret, @WebParam(name = "kodekelurahan") String kodekelurahan) {
        String tanggapan = "0";
        if (secret.equals(secretkey)) {
            ControllerSuaraKelurahan csk = new ControllerSuaraKelurahan();
            tanggapan = csk.getjmlsuarasah(kodekelurahan);
        }
        return tanggapan;
    }
   
   @WebMethod(operationName = "jumlahSuaratidaksahkelurahan")
    public String jumlahsuaratidaksahkelurahan(@WebParam(name = "secret") String secret, @WebParam(name = "kodekelurahan") String kodekelurahan) {
        String tanggapan = "0";
        if (secret.equals(secretkey)) {
            ControllerSuaraKelurahan csk = new ControllerSuaraKelurahan();
            tanggapan = csk.getjmlsuaratidaksah(kodekelurahan);
        }
        return tanggapan;
    }
   
    @WebMethod(operationName = "cekkevalidtandata")
    public String cekkevalidtandata(@WebParam(name = "secret") String secret, @WebParam(name = "kodekelurahan") String kodekelurahan) {
        String tanggapan = "0";
        if (secret.equals(secretkey)) {
            ControllerRekapitulasiKelurahan csk = new ControllerRekapitulasiKelurahan();
            tanggapan = csk.getNomorTPS(kodekelurahan);
        }
        return tanggapan;
    }  
   
   /*BLOK REKAPITULASI KECAMATAN */
    @WebMethod(operationName = "tambahrekapitulasikecamatan")
    public String tambahrekapitulasikecamatan(@WebParam(name = "secret") String secret, @WebParam(name = "kodekecamatan") String kodekecamatan, @WebParam(name = "kodepasangancalon") String kodepasangancalon, @WebParam(name = "suarasah") int suarasah, @WebParam(name = "suaratidaksah") int suaratidaksah
            , @WebParam(name = "tervalidasi") String tervalidasi) {
        String tanggapan = "0";
        try {
            if (secret.equals(secretkey)) {
                ControllerRekapitulasiKecamatan crk = new ControllerRekapitulasiKecamatan();
                tanggapan = crk.insert(new TbSuarasahkecamatan(null, kodekecamatan, kodepasangancalon, suarasah), new TbSuaratidaksahkecamatan(kodekecamatan, suaratidaksah, tervalidasi));
                System.out.println(tanggapan);
            }
        } catch (Exception e) {
        }
        return tanggapan;
    }   
    @WebMethod(operationName = "updaterekapitulasikecamatan")
    public String updaterekapitulasikecamatan(@WebParam(name = "secret") String secret, @WebParam(name = "kodekecamatan") String kodekecamatan, @WebParam(name = "kodepasangancalon") String kodepasangancalon, @WebParam(name = "suarasah") int suarasah, @WebParam(name = "suaratidaksah") int suaratidaksah,
    @WebParam(name = "tervalidasi") String tervalidasi) {
        String tanggapan = "0";
        try {
            if (secret.equals(secretkey)) {
                ControllerRekapitulasiKecamatan crk = new ControllerRekapitulasiKecamatan();
                tanggapan = crk.update(new TbSuarasahkecamatan(null, kodekecamatan, kodepasangancalon, suarasah), new TbSuaratidaksahkecamatan(kodekecamatan, suaratidaksah, tervalidasi));
                System.out.println(tanggapan);
            }
        } catch (Exception e) {
        }
        return tanggapan;
    }   
    
   @WebMethod(operationName = "hapusrekapitulasikecamatan")
    public String hapusrekapitulasikecamatan(@WebParam(name = "secret") String secret, @WebParam(name = "kodekecamatan") String kodekecamatan) {
        String tanggapan = "0";
        try {
            if (secret.equals(secretkey)) {
                ControllerRekapitulasiKecamatan crk = new ControllerRekapitulasiKecamatan();
                tanggapan = crk.delete(kodekecamatan);
                System.out.println(tanggapan);
            }
        } catch (Exception e) {
        }
        return tanggapan;
    }   
   @WebMethod(operationName = "totalsuarasahrekapitulasi")
    public String totalsuarasahrekapitulasi(@WebParam(name = "secret") String secret) {
        String tanggapan = "0";
        try {
            if (secret.equals(secretkey)) {
                ControllerRekapitulasiSemua crs = new ControllerRekapitulasiSemua();
                tanggapan = crs.totalSuaraSah();
                System.out.println(tanggapan);
            }
        } catch (Exception e) {
        }
        return tanggapan;
    }   
   @WebMethod(operationName = "totalsuaratidaksahrekapitulasi")
    public String totalsuaratidaksahrekapitulasi(@WebParam(name = "secret") String secret) {
        String tanggapan = "0";
        try {
            if (secret.equals(secretkey)) {
                ControllerRekapitulasiSemua crs = new ControllerRekapitulasiSemua();
                tanggapan = crs.totalSuaraTidakSah();
                System.out.println(tanggapan);
            }
        } catch (Exception e) {
        }
        return tanggapan;
    }   
   
    @WebMethod(operationName = "generatesuarakecamatan")
    public String generatesuarakecamatan(@WebParam(name = "secret") String secret,
            @WebParam(name = "kodekecamatan") String kodekecamatan) {
        String tanggapan = "0";
        try {
            if (secret.equals(secretkey)) {
                ControllerRekapitulasiKecamatan crk = new ControllerRekapitulasiKecamatan();
                tanggapan = crk.generate(kodekecamatan);
                System.out.println(tanggapan);
            }
        } catch (Exception e) {
        }
        return tanggapan;
    }
 
    @WebMethod(operationName = "jumlahDPTkecamatan")
    public String jumlahdptkecamatan(@WebParam(name = "secret") String secret, @WebParam(name = "kodekecamatan") String kodekecamatan) {
        String tanggapan = "0";
        if (secret.equals(secretkey)) {
            ControllerRekapitulasiKecamatan csk = new ControllerRekapitulasiKecamatan();
            tanggapan = csk.jumlahDPT(kodekecamatan);
        }
        return tanggapan;
    }
   
   @WebMethod(operationName = "jumlahSuarasahkecamatan")
    public String jumlahsuarasahkecamatan(@WebParam(name = "secret") String secret, @WebParam(name = "kodekecamatan") String kodekecamatan) {
        String tanggapan = "0";
        if (secret.equals(secretkey)) {
            ControllerRekapitulasiKecamatan csk = new ControllerRekapitulasiKecamatan();
            tanggapan = csk.getjmlsuarasah(kodekecamatan);
        }
        return tanggapan;
    }
   
   @WebMethod(operationName = "jumlahSuaratidaksahkecamatan")
    public String jumlahsuaratidaksahkecamatan(@WebParam(name = "secret") String secret, @WebParam(name = "kodekecamatan") String kodekecamatan) {
        String tanggapan = "0";
        if (secret.equals(secretkey)) {
            ControllerRekapitulasiKecamatan csk = new ControllerRekapitulasiKecamatan();
            tanggapan = csk.getjmlsuaratidaksah(kodekecamatan);
        }
        return tanggapan;
    }
       
    
    @WebMethod(operationName = "printrekapitulasikecamatan")
    public String printrekapitulasikecamatan(@WebParam(name = "secret") String secret,@WebParam(name = "kodekecamatan") String kodekecamatan) {
        String tanggapan = "0";
        try {
            if (secret.equals(secretkey)) {
                ControllerRekapitulasiKecamatan ctps = new ControllerRekapitulasiKecamatan();
                tanggapan = ctps.printrekapitulasikecamatan(kodekecamatan);
            }
        } catch (Exception e) {
        }
        return tanggapan;
    }      
       
   /*BLOK DATA REKAPITULASI TPS */
     @WebMethod(operationName = "printrekapitulasitps")
    public String printrekapitulasitps(@WebParam(name = "secret") String secret,@WebParam(name = "kodetps") String kodetps) {
        String tanggapan = "0";
        try {
            if (secret.equals(secretkey)) {
                ControllerSuaraTPS ctps = new ControllerSuaraTPS();
                tanggapan = ctps.printTPS(kodetps);
            }
        } catch (Exception e) {
        }
        return tanggapan;
    }    
   
    /**
     * BLOK OLAH SUARA REAL COUNT
     */
    @WebMethod(operationName = "tambahsuaratps")
    public String tambahsuaratps(@WebParam(name = "secret") String secret, @WebParam(name = "kodetps") int kodetps, @WebParam(name = "suaratidaksah") int suaratidaksah, @WebParam(name = "kodepasangancalon") String kodepasangancalon, @WebParam(name = "suarasah") int suarasah, @WebParam(name = "tervalidasi") String tervalidasi) {
        String tanggapan = "0";
        try {
            if (secret.equals(secretkey)) {
                ControllerSuaraTPS cstps = new ControllerSuaraTPS();
                tanggapan = cstps.insert(new TbSuarasahtps(null, kodetps, kodepasangancalon, suarasah), new TbSuaratidaksahtps(kodetps, suaratidaksah, tervalidasi));
                System.out.println(tanggapan);
            }
        } catch (Exception e) {
        }
        return tanggapan;
    }
    @WebMethod(operationName = "tambahsuarasah")
    public String tambahsuarasah(@WebParam(name = "secret") String secret, @WebParam(name = "kodetps") int kodetps, @WebParam(name = "kodepasangancalon") String kodepasangancalon, @WebParam(name = "suarasah") int suarasah) {
        String tanggapan = "0";
        try {
            if (secret.equals(secretkey)) {
                ControllerSuaraTPS cstps = new ControllerSuaraTPS();
                tanggapan = cstps.updatesuarasah(new TbSuarasahtps(null, kodetps, kodepasangancalon, suarasah));
            }
        } catch (Exception e) {
        }
        return tanggapan;
    }
    
    @WebMethod(operationName = "tambahsuaratidaksah")
    public String tambahsuaratidaksah(@WebParam(name = "secret") String secret, @WebParam(name = "kodetps") int kodetps, @WebParam(name = "suaratidaksah") int suaratidaksah, @WebParam(name = "tervalidasi") String tervalidasi) {
        String tanggapan = "0";
        try {
            if (secret.equals(secretkey)) {
                ControllerSuaraTPS cstps = new ControllerSuaraTPS();
                tanggapan = cstps.updatesuaratidaksah(new TbSuaratidaksahtps(kodetps, suaratidaksah, tervalidasi));
            }
        } catch (Exception e) {
        }
        return tanggapan;
    }
    @WebMethod(operationName = "updatesuarasah")
    public String updatesuarasah(@WebParam(name = "secret") String secret, @WebParam(name = "kodesuaratps") int kodesuaratps, @WebParam(name = "kodetps") int kodetps, @WebParam(name = "kodepasangancalon") String kodepasangancalon, @WebParam(name = "suarasah") int suarasah) {
        String tanggapan = "0";
        try {
            if (secret.equals(secretkey)) {
                ControllerSuaraTPS cstps = new ControllerSuaraTPS();
                tanggapan = cstps.updatesuarasah(new TbSuarasahtps(kodesuaratps, kodetps, kodepasangancalon, suarasah));
            }
        } catch (Exception e) {
        }
        return tanggapan;
    }

    @WebMethod(operationName = "updatesuaratidaksah")
    public String updatesuaratidaksah(@WebParam(name = "secret") String secret, @WebParam(name = "kodetps") int kodetps, @WebParam(name = "suaratidaksah") int suaratidaksah, @WebParam(name = "tervalidasi") String tervalidasi) {
        String tanggapan = "0";
        try {
            if (secret.equals(secretkey)) {
                ControllerSuaraTPS cstps = new ControllerSuaraTPS();
                tanggapan = cstps.updatesuaratidaksah(new TbSuaratidaksahtps(kodetps, suaratidaksah, tervalidasi));
            }
        } catch (Exception e) {
        }
        return tanggapan;
    }

    
    @WebMethod(operationName = "hapussuaratps")
    public String hapussuaratps(@WebParam(name = "secret") String secret, @WebParam(name = "kodetps") String kodetps) {
        String tanggapan = "0";
        try {
            if (secret.equals(secretkey)) {
                ControllerSuaraTPS cstps = new ControllerSuaraTPS();
                tanggapan = cstps.delete(kodetps);
                System.out.println(tanggapan);
            }
        } catch (Exception e) {
        }
        return tanggapan;
    }
    @WebMethod(operationName = "hapussuarasahtps")
    public String hapussuarasahtps(@WebParam(name = "secret") String secret, @WebParam(name = "kodetps") String kodetps) {
        String tanggapan = "0";
        try {
            if (secret.equals(secretkey)) {
                ControllerSuaraTPS cstps = new ControllerSuaraTPS();
                tanggapan = cstps.deletesuarasahtps(kodetps);
                System.out.println(tanggapan);
            }
        } catch (Exception e) {
        }
        return tanggapan;
    }
    @WebMethod(operationName = "hapussuarasahtps1")
    public String hapussuarasahtps1(@WebParam(name = "secret") String secret, @WebParam(name = "kodesuaratps") int kodesuaratps) {
        String tanggapan = "0";
        try {
            if (secret.equals(secretkey)) {
                ControllerSuaraTPS cstps = new ControllerSuaraTPS();
                tanggapan = cstps.deletesuarasahtps1(kodesuaratps);
                System.out.println(tanggapan);
            }
        } catch (Exception e) {
        }
        return tanggapan;
    }
    @WebMethod(operationName = "hapussuaratidaksahtps")
    public String hapussuaratidaksahtps(@WebParam(name = "secret") String secret, @WebParam(name = "kodetps") String kodetps) {
        String tanggapan = "0";
        try {
            if (secret.equals(secretkey)) {
                ControllerSuaraTPS cstps = new ControllerSuaraTPS();
                tanggapan = cstps.deletesuaratidaksahtps(kodetps);
                System.out.println(tanggapan);
            }
        } catch (Exception e) {
        }
        return tanggapan;
    }

    @WebMethod(operationName = "getsuaratidaksah")
    public String getsuaratidaksah(@WebParam(name = "secret") String secret, @WebParam(name = "kodetps") String kodetps) {
        String tanggapan = "0";
        try {
            if (secret.equals(secretkey)) {
                ControllerSuaraTPS cstps = new ControllerSuaraTPS();
                tanggapan = cstps.getsuaratidaksah(kodetps);
                System.out.println(tanggapan);
            }
        } catch (Exception e) {
        }
        return tanggapan;
    }
    @WebMethod(operationName = "getjmlsuarasah")
    public String getjmlsuarasah(@WebParam(name = "secret") String secret, @WebParam(name = "kodetps") String kodetps) {
        String tanggapan = "0";
        try {
            if (secret.equals(secretkey)) {
                ControllerSuaraTPS cstps = new ControllerSuaraTPS();
                tanggapan = cstps.getjmlsuarasah(kodetps);
                System.out.println(tanggapan);
            }
        } catch (Exception e) {
        }
        return tanggapan;
    }
    @WebMethod(operationName = "getjmldpttps")
    public String getjmldpttps(@WebParam(name = "secret") String secret, @WebParam(name = "kodetps") String kodetps) {
        String tanggapan = "0";
        try {
            if (secret.equals(secretkey)) {
                ControllerTPS ctps = new ControllerTPS();
                tanggapan = ctps.getJmlDPT(Integer.parseInt(kodetps));
                System.out.println(tanggapan);
            }
        } catch (Exception e) {
        }
        return tanggapan;
    }

    @WebMethod(operationName = "cetakdatagrafikcalon")
    public String cetakdatagrafikcalon(@WebParam(name = "secret") String secret) {
        String hasil = "";
        try {
            if (secret.equals(secretkey)) {
                Dashboard ds = new Dashboard();
                hasil = ds.cetakdatagrafikcalon();
            }
        } catch (Exception e) {
        }
        return hasil;
    }

    @WebMethod(operationName = "datacetak")
    public String datacetak(@WebParam(name = "secret") String secret) {
        String hasil = "";
        try {
            if (secret.equals(secretkey)) {
                ControllerSuaraKecamatan csk = new ControllerSuaraKecamatan();
                hasil = csk.printsuarakecamatan();
            }
        } catch (Exception e) {
        }
        return hasil;
    }

    @WebMethod(operationName = "totaldpt")
    public String totaldpt(@WebParam(name = "secret") String secret) {
        String hasil = "";
        try {
            if (secret.equals(secretkey)) {
                ControllerSuaraSemua css = new ControllerSuaraSemua();
                hasil = css.totalDPT();
            }
        } catch (Exception e) {
        }
        return hasil;
    }

    @WebMethod(operationName = "totalsuarasah")
    public String totalsuarasah(@WebParam(name = "secret") String secret) {
        String hasil = "";
        try {
            if (secret.equals(secretkey)) {
                ControllerSuaraSemua css = new ControllerSuaraSemua();
                hasil = css.totalSuaraSah();
            }
        } catch (Exception e) {
        }
        return hasil;
    }

    @WebMethod(operationName = "totalsuaratidaksah")
    public String totalsuaratidaksah(@WebParam(name = "secret") String secret) {
        String hasil = "";
        try {
            if (secret.equals(secretkey)) {
                ControllerSuaraSemua css = new ControllerSuaraSemua();
                hasil = css.totalSuaraTidakSah();
            }
        } catch (Exception e) {
        }
        return hasil;
    }
    
    @WebMethod(operationName = "getkodetps")
    public String getkodetps(@WebParam(name = "secret") String secret, @WebParam(name = "username") String username, @WebParam(name = "password") String password) {
        String tanggapan = "0";
        if (secret.equals(secretkey)) {
            ControllerLoginTPS clk = new ControllerLoginTPS();
            clk.cekLoginTPS(username, password);
            tanggapan = String.valueOf(clk.getKodetps());
        }
        return tanggapan;
    }
    @WebMethod(operationName = "getkodetps_kodekelurahan")
    public String getkodetps_kodekelurahan(@WebParam(name = "secret") String secret, @WebParam(name = "kodetps") String kodetps) {
        String tanggapan = "0";
        if (secret.equals(secretkey)) {
            ControllerTPS ct = new ControllerTPS();
            tanggapan = String.valueOf(ct.kodeKelurahan(Integer.parseInt(kodetps)));
        }
        return tanggapan;
    }    
    @WebMethod(operationName = "getkodetps_nomortps")
    public String getkodetps_nomortps(@WebParam(name = "secret") String secret, @WebParam(name = "kodetps") String kodetps) {
        String tanggapan = "0";
        if (secret.equals(secretkey)) {
            ControllerTPS ct = new ControllerTPS();
            tanggapan = String.valueOf(ct.nomorTPS(Integer.parseInt(kodetps)));
        }
        return tanggapan;
    }    
    
}
