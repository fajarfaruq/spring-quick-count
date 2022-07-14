/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.SessionFactoryUtil;
import Model.Tampilkelurahan;
import Model.Tampilsuarakelurahan;
import Model.Tampilsuarakelurahandetail;

import Model.Tampilsuaratpsdetail;
import View.SuaraKelurahan.*;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Administrator
 */
public class ControllerSuaraKelurahan {

    public String jumlahdata() {
        String jml = "0";
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            List datalogin = session.createQuery("select count(distinct t.kodeKelurahan) from Tampilsuarakelurahan as t").list();;
            jml = datalogin.toString().replace("[", "");
            jml = jml.replace("]", "");
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada jumlah data Kelurahan \n" + e);
        }
        return jml;
    }
    public String jumlahdataterdaftar(String kodekelurahan) {
        String jml = "0";
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            List datalogin = session.createQuery("select count(distinct t.kodeKelurahan) from Tampilsuarakelurahan as t where t.kodeKelurahan = '" + kodekelurahan+"'").list();;
            jml = datalogin.toString().replace("[", "");
            jml = jml.replace("]", "");
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada jumlah data Kelurahan \n" + e);
        }
        return jml;
    }

    public String jumlahdatadetail(String skodekelurahan) {
        String jml = "0";
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            List datalogin = session.createQuery("select count(distinct t.kodePasangancalon) from Tampilsuarakelurahandetail as t "
                    + "WHERE t.kodeKelurahan = '" + skodekelurahan + "'").list();;
            jml = datalogin.toString().replace("[", "");
            jml = jml.replace("]", "");
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada jumlah data detail Kelurahan \n" + e);
        }
        return jml;
    }
    
    public String jumlahDPT(String kodekelurahan){
        String jml = "0";
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            List datalogin = session.createQuery("select t.jumlahDPT from Tampilsuarakelurahan t where t.kodeKelurahan= '" + kodekelurahan+ "'").list();;
            jml = datalogin.toString().replace("[", "");
            jml = jml.replace("]", "");
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada jumlah data Kecamatan \n" + e);
        }
        return jml;
    }
    public String getjmlsuaratidaksah(String kodekelurahan) {
        String jml = "0";
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            List datalogin = session.createQuery("SELECT t.suaraTidakSah FROM Tampilsuarakelurahan t WHERE t.kodeKelurahan = '" + kodekelurahan + "'").list();
            jml = datalogin.toString().replace("[", "");
            jml = jml.replace("]", "");
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada jumlah data kelurahan \n" + e);
        }
        return jml;
    }

    public String getjmlsuarasah(String kodekelurahan) {
        String jml = "0";
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            List datalogin = session.createQuery("SELECT SUM(t.suaraSah) FROM Tampilsuarakelurahandetail t WHERE t.kodeKelurahan = '" + kodekelurahan + "'").list();
            jml = datalogin.toString().replace("[", "");
            jml = jml.replace("]", "");
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada jumlah data Kelurahan \n" + e);
        }
        return jml;
    }
    public void tampilviewsuarakelurahan(listTbSuaraKelurahan2 tbSuaraKelurahan2, String skodekecamatan, String skodekelurahan, int spage, int srow) {
        String data = "";
        Transaction tx = null;
        String[] kodekecamatan = new String[Integer.valueOf(jumlahdata())];
        String[] namakecamatan = new String[Integer.valueOf(jumlahdata())];
        String[] kodekelurahan = new String[Integer.valueOf(jumlahdata())];
        String[] namakelurahan = new String[Integer.valueOf(jumlahdata())];
        BigInteger[] jumlahdpt = new BigInteger[Integer.valueOf(jumlahdata())];
        BigInteger[] suarasah = new BigInteger[Integer.valueOf(jumlahdata())];
        BigInteger[] suaratidaksah = new BigInteger[Integer.valueOf(jumlahdata())];


        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("SELECT t FROM Tampilsuarakelurahan t where t.kodeKecamatan like '%" + skodekecamatan + "%' "
                    + "AND t.kodeKelurahan like '%" + skodekelurahan + "%'");
            q.setFirstResult(spage);
            q.setMaxResults(srow);
            List datalogin = q.list();
            int i = 0;
            for (Iterator iter = datalogin.iterator(); iter.hasNext();) {
                Tampilsuarakelurahan element = (Tampilsuarakelurahan) iter.next();
                kodekecamatan[i] = element.getKodeKecamatan();
                namakecamatan[i] = element.getNamaKecamatan();
                kodekelurahan[i] = element.getKodeKelurahan();
                namakelurahan[i] = element.getNamaKelurahan();
                jumlahdpt[i] = element.getJumlahDPT();
                suarasah[i] = element.getSuarasah();
                suaratidaksah[i] = element.getSuaraTidakSah();

                //System.out.println("wow" + i);
                i++;
            }
            tbSuaraKelurahan2.setKodekecamatan(kodekecamatan);
            tbSuaraKelurahan2.setNamakecamatan(namakecamatan);
            tbSuaraKelurahan2.setKodekelurahan(kodekelurahan);
            tbSuaraKelurahan2.setNamakelurahan(namakelurahan);
            tbSuaraKelurahan2.setJumlahdpt(jumlahdpt);
            tbSuaraKelurahan2.setSuarasah(suarasah);
            tbSuaraKelurahan2.setSuaratidaksah(suaratidaksah);
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada tampil semua data suara kelurahan \n" + e);
        }
    }
    public void tampilviewsuaratiapkelurahan(listTbSuaraKelurahan2 tbSuaraKelurahan2, String skodekecamatan, String skodekelurahan) {
        String data = "";
        Transaction tx = null;
        String[] kodekecamatan = new String[Integer.valueOf(jumlahdataterdaftar(skodekelurahan))];
        String[] namakecamatan = new String[Integer.valueOf(jumlahdataterdaftar(skodekelurahan))];
        String[] kodekelurahan = new String[Integer.valueOf(jumlahdataterdaftar(skodekelurahan))];
        String[] namakelurahan = new String[Integer.valueOf(jumlahdataterdaftar(skodekelurahan))];
        BigInteger[] jumlahdpt = new BigInteger[Integer.valueOf(jumlahdataterdaftar(skodekelurahan))];
        BigInteger[] suarasah = new BigInteger[Integer.valueOf(jumlahdataterdaftar(skodekelurahan))];
        BigInteger[] suaratidaksah = new BigInteger[Integer.valueOf(jumlahdataterdaftar(skodekelurahan))];


        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("SELECT t FROM Tampilsuarakelurahan t where t.kodeKelurahan = '" + skodekelurahan + "'");
            List datalogin = q.list();
            int i = 0;
            for (Iterator iter = datalogin.iterator(); iter.hasNext();) {
                Tampilsuarakelurahan element = (Tampilsuarakelurahan) iter.next();
                kodekecamatan[i] = element.getKodeKecamatan();
                namakecamatan[i] = element.getNamaKecamatan();
                kodekelurahan[i] = element.getKodeKelurahan();
                namakelurahan[i] = element.getNamaKelurahan();
                jumlahdpt[i] = element.getJumlahDPT();
                suarasah[i] = element.getSuarasah();
                suaratidaksah[i] = element.getSuaraTidakSah();

                //System.out.println("wow" + i);
                i++;
            }
            tbSuaraKelurahan2.setKodekecamatan(kodekecamatan);
            tbSuaraKelurahan2.setNamakecamatan(namakecamatan);
            tbSuaraKelurahan2.setKodekelurahan(kodekelurahan);
            tbSuaraKelurahan2.setNamakelurahan(namakelurahan);
            tbSuaraKelurahan2.setJumlahdpt(jumlahdpt);
            tbSuaraKelurahan2.setSuarasah(suarasah);
            tbSuaraKelurahan2.setSuaratidaksah(suaratidaksah);
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada tampil semua data suara tiap kelurahan \n" + e);
        }
    }

    public void tampilviewsuaradetailkelurahan(listTbSuaraDetailKelurahan2 detailKelurahan2, String skodekelurahan) {
        String data = "";
        Transaction tx = null;
        String[] kodekelurahan = new String[Integer.valueOf(jumlahdatadetail(skodekelurahan))];
        String[] namakelurahan = new String[Integer.valueOf(jumlahdatadetail(skodekelurahan))];
        int[] kodesuaratps = new int[Integer.valueOf(jumlahdatadetail(skodekelurahan))];
        int[] kodetps = new int[Integer.valueOf(jumlahdatadetail(skodekelurahan))];
        String[] kodepasangancalon = new String[Integer.valueOf(jumlahdatadetail(skodekelurahan))];
        String[] gambar = new String[Integer.valueOf(jumlahdatadetail(skodekelurahan))];
        String[] gubernur = new String[Integer.valueOf(jumlahdatadetail(skodekelurahan))];
        String[] wakilgubernur = new String[Integer.valueOf(jumlahdatadetail(skodekelurahan))];
        int[] suarasah = new int[Integer.valueOf(jumlahdatadetail(skodekelurahan))];
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            List results = session
                    .createSQLQuery("SELECT Kode_Kelurahan, Nama_Kelurahan , Kode_SuaraTPS , Kode_TPS, "
                    + " Kode_Pasangancalon,Gambar,Gubernur,Wakil_Gubernur, SUM(Suara_Sah) as Suara_Sah"
                    + " FROM tampilsuarakelurahandetail WHERE Kode_Kelurahan = '" + skodekelurahan + "' GROUP BY Kode_Pasangancalon")
                    .addScalar("Kode_Kelurahan", Hibernate.STRING)
                    .addScalar("Nama_Kelurahan", Hibernate.STRING)
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
                kodekelurahan[i] = col1Value;
                namakelurahan[i] = col2Value;
                kodesuaratps[i] = col3Value;
                kodetps[i] = col4Value;
                kodepasangancalon[i] = col5Value;
                gambar[i] = col6Value;
                gubernur[i] = col7Value;
                wakilgubernur[i] = col8Value;
                suarasah[i] = col9Value;
                i++;
            }
            detailKelurahan2.setKodekelurahan(kodekelurahan);
            detailKelurahan2.setKodesuaratps(kodesuaratps);
            detailKelurahan2.setKodetps(kodetps);
            detailKelurahan2.setKodepasangancalon(kodepasangancalon);
            detailKelurahan2.setGambar(gambar);
            detailKelurahan2.setGubernur(gubernur);
            detailKelurahan2.setWakilgubernur(wakilgubernur);
            detailKelurahan2.setSuarasah(suarasah);
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada tampil semua data detail suara tps \n" + e);
        }


    }
//    public static void main(String [] args){
//        ControllerSuaraKelurahan csk = new ControllerSuaraKelurahan();
//        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
//        Transaction tx = null;
//        tx = session.beginTransaction();
////       SQLQuery q = session.createSQLQuery
////                       ( "SELECT Kode_Kelurahan FROM Tampilsuarakelurahandetail"); 
////       q.addScalar( "Kode_Kelurahan", Hibernate.STRING); 
////       System.out.println(q);
//
//        List results = session
//                .createSQLQuery("SELECT Kode_Kelurahan, Suara_Sah FROM tampilsuarakelurahandetail")
//                .addScalar("Kode_Kelurahan", Hibernate.STRING)
//                .addScalar("Suara_Sah", Hibernate.INTEGER)
//                .list();
//
//        for (Iterator iter = results.iterator(); iter.hasNext();) {
//            Object[] objs = (Object[]) iter.next();
//            String col1Value = (String) objs[0];
//            Integer col2Value = (Integer) objs[1];
//            System.out.println(col1Value);
//        }
//        tx.commit();
//    }
}
