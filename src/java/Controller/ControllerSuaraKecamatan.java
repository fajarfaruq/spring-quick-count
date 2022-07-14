/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.SessionFactoryUtil;
import Model.Tampilsuarakecamatan;
import View.SuaraKecamatan;
import View.SuaraKecamatan.listTbSuaraDetailKecamatan2;
import View.SuaraKecamatan.listTbSuaraKecamatan2;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Administrator
 */
public class ControllerSuaraKecamatan {

    public String jumlahdata() {
        String jml = "0";
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            List datalogin = session.createQuery("select count(distinct t.kodeKecamatan) from Tampilsuarakecamatan as t").list();;
            jml = datalogin.toString().replace("[", "");
            jml = jml.replace("]", "");
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada jumlah data Kecamatan \n" + e);
        }
        return jml;
    }

    public String jumlahdatadetail(String skodekecamatan) {
        String jml = "0";
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            List datalogin = session.createQuery("select count(distinct t.kodePasangancalon) from Tampilsuarakecamatandetail as t "
                    + "WHERE t.kodeKecamatan= '" + skodekecamatan + "'").list();;
            jml = datalogin.toString().replace("[", "");
            jml = jml.replace("]", "");
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada jumlah data detail Kecamatan \n" + e);
        }
        return jml;
    }

    public String jumlahdataterdaftar(String kodekecamatan) {
        String jml = "0";
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            List datalogin = session.createQuery("select count(distinct t.kodeKecamatan) from Tampilsuarakecamatan as t where t.kodeKecamatan= '" + kodekecamatan + "'").list();;
            jml = datalogin.toString().replace("[", "");
            jml = jml.replace("]", "");
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada jumlah data Kecamatan \n" + e);
        }
        return jml;
    }

    public void tampilviewsuarakecamatan(listTbSuaraKecamatan2 tbSuaraKecamatan2, String skodekecamatan, int spage, int srow) {
        String data = "";
        Transaction tx = null;
        String[] kodekecamatan = new String[Integer.valueOf(jumlahdata())];
        String[] namakecamatan = new String[Integer.valueOf(jumlahdata())];
        Integer[] jumlahdpt = new Integer[Integer.valueOf(jumlahdata())];
        BigInteger[] suarasah = new BigInteger[Integer.valueOf(jumlahdata())];
        BigInteger[] suaratidaksah = new BigInteger[Integer.valueOf(jumlahdata())];


        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("SELECT t FROM Tampilsuarakecamatan t where t.kodeKecamatan like '%" + skodekecamatan + "%'");
            q.setFirstResult(spage);
            q.setMaxResults(srow);
            List datalogin = q.list();
            int i = 0;
            for (Iterator iter = datalogin.iterator(); iter.hasNext();) {
                Tampilsuarakecamatan element = (Tampilsuarakecamatan) iter.next();
                kodekecamatan[i] = element.getKodeKecamatan();
                namakecamatan[i] = element.getNamaKecamatan();

                List results1 = session
                        .createSQLQuery("SELECT SUM(Jumlah_DPT) as Jumlah_DPT FROM tampiltps Where Kode_Kecamatan ='" + element.getKodeKecamatan() + "' ")
                        .addScalar("Jumlah_DPT", Hibernate.INTEGER)
                        .list();
                String jml = results1.toString().replace("[", "");
                jml = jml.replace("]", "");
                jumlahdpt[i] = Integer.parseInt(jml);
                suarasah[i] = element.getSuarasah();
                suaratidaksah[i] = element.getSuaraTidakSah();

                //System.out.println("wow" + i);
                i++;
            }
            tbSuaraKecamatan2.setKodekecamatan(kodekecamatan);
            tbSuaraKecamatan2.setNamakecamatan(namakecamatan);
            tbSuaraKecamatan2.setJumlahdpt(jumlahdpt);
            tbSuaraKecamatan2.setSuarasah(suarasah);
            tbSuaraKecamatan2.setSuaratidaksah(suaratidaksah);
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada tampil semua data suara kecamatan \n" + e);
        }
    }

    public void tampilviewsuaradetailkecamatan(listTbSuaraDetailKecamatan2 detailKecamatan2, String skodekecamatan) {
        String data = "";
        Transaction tx = null;
        String[] kodekecamatan = new String[Integer.valueOf(jumlahdatadetail(skodekecamatan))];
        String[] namakecamatan = new String[Integer.valueOf(jumlahdatadetail(skodekecamatan))];
        int[] kodesuaratps = new int[Integer.valueOf(jumlahdatadetail(skodekecamatan))];
        int[] kodetps = new int[Integer.valueOf(jumlahdatadetail(skodekecamatan))];
        String[] kodepasangancalon = new String[Integer.valueOf(jumlahdatadetail(skodekecamatan))];
        String[] gambar = new String[Integer.valueOf(jumlahdatadetail(skodekecamatan))];
        String[] gubernur = new String[Integer.valueOf(jumlahdatadetail(skodekecamatan))];
        String[] wakilgubernur = new String[Integer.valueOf(jumlahdatadetail(skodekecamatan))];
        int[] suarasah = new int[Integer.valueOf(jumlahdatadetail(skodekecamatan))];
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            List results = session
                    .createSQLQuery("SELECT Kode_Kecamatan, Nama_Kecamatan , Kode_SuaraTPS , Kode_TPS, "
                    + " Kode_Pasangancalon,Gambar,Gubernur,Wakil_Gubernur, SUM(Suara_Sah) as Suara_Sah"
                    + " FROM tampilsuarakecamatandetail WHERE Kode_Kecamatan = '" + skodekecamatan + "' GROUP BY Kode_Pasangancalon")
                    .addScalar("Kode_Kecamatan", Hibernate.STRING)
                    .addScalar("Nama_Kecamatan", Hibernate.STRING)
                    .addScalar("Kode_SuaraTPS", Hibernate.INTEGER)
                    .addScalar("Kode_TPS", Hibernate.INTEGER)
                    .addScalar("Kode_Pasangancalon", Hibernate.STRING)
                    .addScalar("Gambar", Hibernate.STRING)
                    .addScalar("Gubernur", Hibernate.STRING)
                    .addScalar("Wakil_Gubernur", Hibernate.STRING)
                    .addScalar("Suara_Sah", Hibernate.INTEGER)
                    .list();
            int i = 0;
            for (Iterator iter = results.iterator(); iter.hasNext();) {
                Object[] objs = (Object[]) iter.next();
                String col1Value = (String) objs[0];
                String col2Value = (String) objs[1];
                Integer col3Value = (Integer) objs[2];
                Integer col4Value = (Integer) objs[3];
                String col5Value = (String) objs[4];
                String col6Value = (String) objs[5];
                String col7Value = (String) objs[6];
                String col8Value = (String) objs[7];
                Integer col9Value = (Integer) objs[8];
                kodekecamatan[i] = col1Value;
                namakecamatan[i] = col2Value;
                kodesuaratps[i] = col3Value;
                kodetps[i] = col4Value;
                kodepasangancalon[i] = col5Value;
                gambar[i] = col6Value;
                gubernur[i] = col7Value;
                wakilgubernur[i] = col8Value;
                suarasah[i] = col9Value;
                i++;
            }
            detailKecamatan2.setKodekecamatan(kodekecamatan);
            detailKecamatan2.setNamakecamatan(namakecamatan);
            detailKecamatan2.setKodesuaratps(kodesuaratps);
            detailKecamatan2.setKodetps(kodetps);
            detailKecamatan2.setKodepasangancalon(kodepasangancalon);
            detailKecamatan2.setGambar(gambar);
            detailKecamatan2.setGubernur(gubernur);
            detailKecamatan2.setWakilgubernur(wakilgubernur);
            detailKecamatan2.setSuarasah(suarasah);
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada tampil semua data detail suara kecamatan \n" + e);
        }
    }

    public String printsuarakecamatan() throws IOException {
        String hasil = "";
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            List resultkecamatan = session
                    .createSQLQuery("SELECT Kode_Kecamatan,Nama_Kecamatan,"
                    + " suarasah , Suara_Tidak_Sah FROM tampilsuarakecamatan ORDER BY Nama_Kecamatan ASC")
                    .addScalar("Kode_Kecamatan", Hibernate.STRING)
                    .addScalar("Nama_Kecamatan", Hibernate.STRING)
                    .addScalar("suarasah", Hibernate.INTEGER)
                    .addScalar("Suara_Tidak_Sah", Hibernate.INTEGER)
                    .list();
            int i = 1;
            for (Iterator iter = resultkecamatan.iterator(); iter.hasNext();) {
                Object[] obj = (Object[]) iter.next();
                String KodeKecamatan = (String) obj[0];
                String NamaKecamatan = (String) obj[1];
                List results1 = session
                        .createSQLQuery("SELECT SUM(Jumlah_DPT) as Jumlah_DPT FROM tampiltps Where Kode_Kecamatan ='" + KodeKecamatan + "' ")
                        .addScalar("Jumlah_DPT", Hibernate.INTEGER)
                        .list();
                String jml = results1.toString().replace("[", "");
                jml = jml.replace("]", "");
                Integer JumlahDPT = Integer.parseInt(jml);
                Integer SuaraSah = (Integer) obj[2];
                Integer SuaraTidakSah = (Integer) obj[3];
                //PERULANGAN HANDEL detail suara sah kecamatan
                hasil = hasil + "{'No.':'" + String.valueOf(i) + "', 'Kecamatan' : '" + NamaKecamatan + "','Jumlah DPT' : '" + String.valueOf(JumlahDPT) + "' ";
                List results = session
                        .createSQLQuery("SELECT Kode_Pasangancalon, Gubernur , Wakil_Gubernur FROM tb_pasangancalon ORDER BY Kode_Pasangancalon ASC")
                        .addScalar("Kode_Pasangancalon", Hibernate.STRING)
                        .addScalar("Gubernur", Hibernate.STRING)
                        .addScalar("Wakil_Gubernur", Hibernate.STRING)
                        .list();
                for (Iterator iter2 = results.iterator(); iter2.hasNext();) {
                    Object[] objs2 = (Object[]) iter2.next();
                    String KodePasanganCalon = (String) objs2[0];
                    String Gubernur = (String) objs2[1];
                    String WakilGubernur = (String) objs2[2];
                    List resultdetail = session
                            .createSQLQuery("SELECT Kode_Kecamatan  ,  "
                            + " Kode_Pasangancalon,SUM(Suara_Sah) as Suara_Sah"
                            + " FROM tampilsuarakecamatandetail WHERE Kode_Kecamatan = '" + KodeKecamatan + "' AND "
                            + " Kode_Pasangancalon = '" + KodePasanganCalon + "' GROUP BY Kode_Pasangancalon")
                            .addScalar("Kode_Kecamatan", Hibernate.STRING)
                            .addScalar("Kode_Pasangancalon", Hibernate.STRING)
                            .addScalar("Suara_Sah", Hibernate.INTEGER)
                            .list();
                    for (Iterator iter3 = resultdetail.iterator(); iter3.hasNext();) {
                        Object[] obj3 = (Object[]) iter3.next();
                        String KodeKecamatan1 = (String) obj3[0];
                        String KodePasanganCalon1 = (String) obj3[1];
                        Integer SuaraSah1 = (Integer) obj3[2];
                        hasil = hasil + ",'" + Gubernur + " - " + WakilGubernur + "' : '" + String.valueOf(SuaraSah1) + "'";
                    }
                }
                hasil = hasil + ",'Suara Sah ':'" + String.valueOf(SuaraSah) + "','Suara Tidak Sah':'" + String.valueOf(SuaraTidakSah) + "'},";
                i++;
            }
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada tampil semua data detail suara kecamatan \n" + e);
        }
        return hasil;
    }

    public String jumlahDPT(String kodekecamatan) {
        String jml = "0";
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            List datalogin = session.createQuery("select t.jumlahDPT from Tampilsuarakecamatan t where t.kodeKecamatan = '" + kodekecamatan + "'").list();;
            jml = datalogin.toString().replace("[", "");
            jml = jml.replace("]", "");
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada jumlah data Kecamatan \n" + e);
        }
        return jml;
    }
//    public static void main(String args[]) throws IOException{
//        System.out.println(new ControllerSuaraKecamatan().jumlahDPT("KC001"));
//    }
}
