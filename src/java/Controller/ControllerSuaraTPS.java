/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.SessionFactoryUtil;
import Model.Tampilsuaratps;
import Model.Tampilsuaratpsdetail;
import Model.TbSuarasahtps;
import Model.TbSuaratidaksahtps;
import View.SuaraTPS;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Administrator
 */
public class ControllerSuaraTPS {

    public String insertsuarasah(TbSuarasahtps tbSuarasahtps) {
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        String response = "0";
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(tbSuarasahtps);
            tx.commit();
            response = "1";
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada insert suara tps.... !!\n" + e);
            response = "error";
        }
        return response;
    }

    public String insertsuaratidaksah(TbSuaratidaksahtps tbSuaratidaksahtps) {
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        String response = "0";
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(tbSuaratidaksahtps);
            tx.commit();
            response = "1";
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada insert suara tidak sah tps.... !!\n" + e);
            response = "error";
        }
        return response;
    }

    public String insert(TbSuarasahtps tbSuarasahtps, TbSuaratidaksahtps tbSuaratidaksahtps) {
        String respon = "0";
        try {
            String response1 = insertsuarasah(tbSuarasahtps);
            String response2 = insertsuaratidaksah(tbSuaratidaksahtps);
            respon = "1";
        } catch (Exception e) {
            respon = "0";
        }
        return respon;
    }

    public String updatesuarasah(TbSuarasahtps tbSuarasahtps) {
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        String response = "0";
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(tbSuarasahtps);
            response = "1";
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada update suara sah tps .... !!\n" + e);
            response = "error";
        }
        return response;
    }

    public String updatesuaratidaksah(TbSuaratidaksahtps tbSuaratidaksahtps) {
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        String response = "0";
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(tbSuaratidaksahtps);
            tx.commit();
            response = "1";
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada update suara tidak sah tps .... !!\n" + e);
            response = "error";
        }
        return response;
    }

    public String update(TbSuarasahtps tbSuarasahtps, TbSuaratidaksahtps tbSuaratidaksahtps) {
        String respon = "0";
        try {
            String response1 = updatesuarasah(tbSuarasahtps);
            String response2 = updatesuaratidaksah(tbSuaratidaksahtps);
            respon = "1";
        } catch (Exception e) {
            respon = "0";
        }
        return respon;
    }

    public String deletesuarasahtps(String KodeTPS) {
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        String response = "0";
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("delete from TbSuarasahtps where kodeTPS=" + KodeTPS + "");
            query.executeUpdate();
            tx.commit();
            response = "1";
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada hapus suara sah TPS.... !! \n" + e);
            response = "error";
        }
        return response;
    }

    public String deletesuarasahtps1(int SuaraSahTPS) {
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        String response = "0";
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("delete from TbSuarasahtps as t where t.kodeSuaraTPS =" + SuaraSahTPS + "");
            query.executeUpdate();
            tx.commit();
            response = "1";
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada hapus suara sah TPS.... !! \n" + e);
            response = "error";
        }
        return response;
    }

    public String deletesuaratidaksahtps(String KodeTPS) {
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        String response = "0";
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("delete from TbSuaratidaksahtps where kodeTPS=" + KodeTPS + "");
            query.executeUpdate();
            tx.commit();
            response = "1";
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada hapus suara tidak sah TPS .... !! \n" + e);
            response = "error";
        }
        return response;
    }

    public String delete(String KodeTPS) {
        String respon = "0";
        try {
            String response1 = deletesuarasahtps(KodeTPS);
            String response2 = deletesuaratidaksahtps(KodeTPS);
            respon = "1";
        } catch (Exception e) {
            respon = "0";
        }
        return respon;
    }

    public String jumlahdata() {
        String jml = "0";
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            List datalogin = session.createQuery("select count(distinct t.kodeSuaraTPS) from Tampilsuaratps as t").list();;
            jml = datalogin.toString().replace("[", "");
            jml = jml.replace("]", "");
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada jumlah data TPS \n" + e);
        }
        return jml;
    }

    public String jumlahdatadetail(String skodetps) {
        String jml = "0";
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            List datalogin = session.createQuery("select count(distinct t.kodeSuaraTPS) from Tampilsuaratpsdetail as t WHERE t.kodeTPS = " + skodetps + "").list();;
            jml = datalogin.toString().replace("[", "");
            jml = jml.replace("]", "");
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada jumlah data detail TPS \n" + e);
        }
        return jml;
    }

    public void tampilviewsuaratps(SuaraTPS.listTbSuaraTPS2 tbSuaraTPS2, String skodetps, String skodekecamatan, String skodekelurahan, int spage, int srow) {
        String data = "";
        Transaction tx = null;

        int[] kodesuaratps = new int[Integer.valueOf(jumlahdata())];
        String[] kodekecamatan = new String[Integer.valueOf(jumlahdata())];
        String[] namakecamatan = new String[Integer.valueOf(jumlahdata())];
        String[] kodekelurahan = new String[Integer.valueOf(jumlahdata())];
        String[] namakelurahan = new String[Integer.valueOf(jumlahdata())];
        int[] kodetps = new int[Integer.valueOf(jumlahdata())];
        int[] nomortps = new int[Integer.valueOf(jumlahdata())];
        int[] jmldpt = new int[Integer.valueOf(jumlahdata())];
        BigInteger[] suarasah = new BigInteger[Integer.valueOf(jumlahdata())];
        int[] suaratidaksah = new int[Integer.valueOf(jumlahdata())];
        String[] tervalidasi = new String[Integer.valueOf(jumlahdata())];

        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("SELECT t FROM Tampilsuaratps t where t.kodeTPS "
                    + " like '%" + skodetps + "%' "
                    + "AND t.kodeKecamatan like '%" + skodekecamatan + "%' "
                    + "AND t.kodeKelurahan like '%" + skodekelurahan + "%'");
            q.setFirstResult(spage);
            q.setMaxResults(srow);
            List datalogin = q.list();
            int i = 0;
            for (Iterator iter = datalogin.iterator(); iter.hasNext();) {
                Tampilsuaratps element = (Tampilsuaratps) iter.next();
                kodesuaratps[i] = element.getKodeSuaraTPS();
                kodekecamatan[i] = element.getKodeKecamatan();
                namakecamatan[i] = element.getNamaKecamatan();
                kodekelurahan[i] = element.getKodeKelurahan();
                namakelurahan[i] = element.getNamaKelurahan();
                kodetps[i] = element.getKodeTPS();
                nomortps[i] = element.getNomorTPS();
                jmldpt[i] = element.getJumlahDPT();
                suarasah[i] = element.getSuarasah();
                suaratidaksah[i] = element.getSuaraTidakSah();
                tervalidasi[i] = element.getTervalidasi();
                //System.out.println("wow" + i);
                i++;
            }
            tbSuaraTPS2.setKodesuaratps(kodesuaratps);
            tbSuaraTPS2.setKodekecamatan(kodekecamatan);
            tbSuaraTPS2.setNamakecamatan(namakecamatan);
            tbSuaraTPS2.setKodekelurahan(kodekelurahan);
            tbSuaraTPS2.setNamakelurahan(namakelurahan);
            tbSuaraTPS2.setKodetps(kodetps);
            tbSuaraTPS2.setNomortps(nomortps);
            tbSuaraTPS2.setJmldpt(jmldpt);
            tbSuaraTPS2.setSuarasah(suarasah);
            tbSuaraTPS2.setSuaratidaksah(suaratidaksah);
            tbSuaraTPS2.setTervalidasi(tervalidasi);

            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada tampil semua data suara tps \n" + e);
        }
    }

    public void tampilviewsuaradetailtps(SuaraTPS.listTbSuaraDetailTPS2 detailTPS2, String skodetps) {
        String data = "";
        Transaction tx = null;
        int[] kodesuaratps = new int[Integer.valueOf(jumlahdatadetail(skodetps))];
        int[] kodetps = new int[Integer.valueOf(jumlahdatadetail(skodetps))];
        String[] kodepasangancalon = new String[Integer.valueOf(jumlahdatadetail(skodetps))];
        String[] gambar = new String[Integer.valueOf(jumlahdatadetail(skodetps))];
        String[] gubernur = new String[Integer.valueOf(jumlahdatadetail(skodetps))];
        String[] wakilgubernur = new String[Integer.valueOf(jumlahdatadetail(skodetps))];
        int[] suarasah = new int[Integer.valueOf(jumlahdatadetail(skodetps))];

        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("SELECT t FROM Tampilsuaratpsdetail t WHERE t.kodeTPS = " + skodetps + "  ORDER BY t.suaraSah DESC");
            List datalogin = q.list();
            int i = 0;
            for (Iterator iter = datalogin.iterator(); iter.hasNext();) {
                Tampilsuaratpsdetail element = (Tampilsuaratpsdetail) iter.next();
                kodesuaratps[i] = element.getKodeSuaraTPS();
                kodetps[i] = element.getKodeTPS();
                kodepasangancalon[i] = element.getKodePasangancalon();
                gambar[i] = element.getGambar();
                gubernur[i] = element.getGubernur();
                wakilgubernur[i] = element.getWakilGubernur();
                suarasah[i] = element.getSuaraSah();
                //System.out.println("wow" + i);
                i++;
            }
            detailTPS2.setKodesuaratps(kodesuaratps);
            detailTPS2.setKodetps(kodetps);
            detailTPS2.setKodepasangancalon(kodepasangancalon);
            detailTPS2.setGambar(gambar);
            detailTPS2.setGubernur(gubernur);
            detailTPS2.setWakilgubernur(wakilgubernur);
            detailTPS2.setSuarasah(suarasah);
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada tampil semua data detail suara tps \n" + e);
        }
    }

    public String jumlahdatakelurahanterdaftar(String kodekelurahan) {
        String jml = "0";
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            List datalogin = session.createQuery("select count(distinct t.kodeSuaraTPS) from Tampilsuaratps as t WHERE t.kodeKelurahan = '" + kodekelurahan + "'").list();;
            jml = datalogin.toString().replace("[", "");
            jml = jml.replace("]", "");
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada jumlah data TPS \n" + e);
        }
        return jml;
    }

    public String jumlahdatakecamatanterdaftar(String kodekecamatan) {
        String jml = "0";
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            List datalogin = session.createQuery("select count(distinct t.kodeSuaraTPS) from Tampilsuaratps as t WHERE t.kodeKecamatan = '" + kodekecamatan + "'").list();;
            jml = datalogin.toString().replace("[", "");
            jml = jml.replace("]", "");
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada jumlah data TPS \n" + e);
        }
        return jml;
    }

    public String getsuaratidaksah(String kodetps) {
        String jml = "0";
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            List datalogin = session.createQuery("SELECT t.suaraTidakSah FROM Tampilsuaratps t WHERE t.kodeTPS = " + kodetps + "").list();
            jml = datalogin.toString().replace("[", "");
            jml = jml.replace("]", "");
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada jumlah data TPS \n" + e);
        }
        return jml;
    }

    public String getjmlsuarasah(String kodetps) {
        String jml = "0";
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            List datalogin = session.createQuery("SELECT SUM(t.suaraSah) FROM Tampilsuaratpsdetail t WHERE t.kodeTPS = " + kodetps + "").list();
            jml = datalogin.toString().replace("[", "");
            jml = jml.replace("]", "");
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada jumlah data TPS \n" + e);
        }
        return jml;
    }

    public String printTPS(String skodetps) {
        ObjectMapper objectMapper = new ObjectMapper();
        Data pathq = new Data();
        List<listTbSuaraDetailTPS1> a = new ArrayList<listTbSuaraDetailTPS1>();
        String json = "";
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("SELECT t FROM Tampilsuaratpsdetail t WHERE  t.kodeTPS = " + skodetps + " ORDER BY t.suaraSah DESC");
            List datalogin = q.list();
            int i = 0;
            for (Iterator iter = datalogin.iterator(); iter.hasNext();) {
                Tampilsuaratpsdetail element = (Tampilsuaratpsdetail) iter.next();
                //System.out.println("wow" + i);
                a.add(new listTbSuaraDetailTPS1(element.getKodeSuaraTPS(), element.getKodeTPS(),
                        element.getKodePasangancalon(), element.getGambar(), element.getGubernur(),
                        element.getWakilGubernur(), element.getSuaraSah()));
                i++;
            }

            tx.commit();
            pathq.setData(a);
            json = objectMapper.writeValueAsString(pathq);
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada tampil semua data detail suara tps \n" + e);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }
//   public static void main (String args[]){
//       ControllerSuaraTPS controllerSuaraTPS =  new ControllerSuaraTPS();
//       System.out.println(controllerSuaraTPS.printTPS("7"));
//   }
    class Data {

    List<listTbSuaraDetailTPS1> data;

    public List<listTbSuaraDetailTPS1> getData() {
        return data;
    }

    public void setData(List<listTbSuaraDetailTPS1> data) {
        this.data = data;
    }
}

class listTbSuaraDetailTPS1 {

    int kodesuaratps;
    int kodetps;
    String kodepasangancalon;
    String gambar;
    String gubernur;
    String wakilgubernur;
    int suarasah;

    public listTbSuaraDetailTPS1(int kodeSuaratps, int kodeTPS, String kodePasanganCalon, String Gambar, String Gubernur, String wakilGubernur, int suaraSah) {
        setKodesuaratps(kodeSuaratps);
        setKodetps(kodeTPS);
        setKodepasangancalon(kodePasanganCalon);
        setGambar(Gambar);
        setGubernur(Gubernur);
        setWakilgubernur(wakilGubernur);
        setSuarasah(suaraSah);
    }

    public int getKodesuaratps() {
        return kodesuaratps;
    }

    public void setKodesuaratps(int kodesuaratps) {
        this.kodesuaratps = kodesuaratps;
    }

    public int getKodetps() {
        return kodetps;
    }

    public void setKodetps(int kodetps) {
        this.kodetps = kodetps;
    }

    public String getKodepasangancalon() {
        return kodepasangancalon;
    }

    public void setKodepasangancalon(String kodepasangancalon) {
        this.kodepasangancalon = kodepasangancalon;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
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

    public int getSuarasah() {
        return suarasah;
    }

    public void setSuarasah(int suarasah) {
        this.suarasah = suarasah;
    }
}

}

