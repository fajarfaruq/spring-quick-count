/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import Model.SessionFactoryUtil;
import View.SuaraSemua;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
/**
 *
 * @author Administrator
 */
public class ControllerSuaraSemua {
   
    public String totalDPT(){
        String hasil = "";
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            List results = session
                    .createSQLQuery("SELECT SUM(Jumlah_DPT) as Jumlah_DPT FROM tb_tps")
                    .addScalar("Jumlah_DPT", Hibernate.STRING)
                    .list();
            hasil = results.toString().replace("[", "");
            hasil = hasil.replace("]", "");
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada total suara sah \n" + e);
        }
        return hasil;
    }
    
    public String totalSuaraSah()throws IOException {
        String hasil = "";
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            List results = session
                    .createSQLQuery("SELECT SUM(suarasah) as Suara_Sah FROM tampilsuarakecamatan")
                    .addScalar("Suara_Sah", Hibernate.STRING)
                    .list();
            hasil = results.toString().replace("[", "");
            hasil = hasil.replace("]", "");
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada total suara sah \n" + e);
        }
        return hasil;
    }
    
    public String totalSuaraTidakSah(){
        String hasil = "";
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            List results = session
                    .createSQLQuery("SELECT SUM(Suara_Tidak_Sah) as Suara_Tidak_Sah FROM tampilsuarakecamatan")
                    .addScalar("Suara_Tidak_Sah", Hibernate.STRING)
                    .list();
            hasil = results.toString().replace("[", "");
            hasil = hasil.replace("]", "");
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada total suara sah \n" + e);
        }
        return hasil;
    }
    
    public String jumlahdata(){
        String jml = "0" ;
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            List datalogin = session.createQuery("select count(distinct t.kodePasangancalon) from TbPasangancalon as t").list();;
            jml = datalogin.toString().replace("[", "");
            jml= jml.replace("]", "");
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada tampil semua data login \n" + e);
        }
        return jml;
    }
    
    public void tampilsemuadata(SuaraSemua.listTbSuaraSemua2 suaraSemua2) {
        String data = "";
        Transaction tx = null;
        String[] kodepasangancalon = new String[Integer.valueOf(jumlahdata())];
        String[] gubernur = new String[Integer.valueOf(jumlahdata())];
        String[] wakilgubernur = new String[Integer.valueOf(jumlahdata())];
        String[] gambar = new String[Integer.valueOf(jumlahdata())];
        int[] suarasah = new int[Integer.valueOf(jumlahdata())];

        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
           List results = session
                    .createSQLQuery("SELECT Kode_Pasangancalon,Gubernur,Wakil_Gubernur,Gambar, SUM(Suara_Sah) as Suara_Sah"
                    + " FROM tampilsuarakecamatandetail GROUP BY Kode_Pasangancalon")
                    .addScalar("Kode_Pasangancalon", Hibernate.STRING)
                    .addScalar("Gubernur", Hibernate.STRING)
                    .addScalar("Wakil_Gubernur", Hibernate.STRING)
                    .addScalar("Gambar", Hibernate.STRING)
                    .addScalar("Suara_Sah", Hibernate.INTEGER)
                    .list();
            int i = 0;
            for (Iterator iter = results.iterator(); iter.hasNext();) {
                Object[] objs = (Object[]) iter.next();
                String col1Value = (String) objs[0];
                String col2Value = (String) objs[1];
                String col3Value = (String) objs[2];
                String col4Value = (String) objs[3];
                Integer col5Value = (Integer) objs[4];
                kodepasangancalon[i] = col1Value;
                gubernur[i] = col2Value;
                wakilgubernur[i] = col3Value;
                gambar[i] = col4Value;
                suarasah[i] = col5Value;
                i++;
            }
            suaraSemua2.setKodepasangancalon(kodepasangancalon);
            suaraSemua2.setGubernur(gubernur);
            suaraSemua2.setWakilgubernur(wakilgubernur);
            suaraSemua2.setGambar(gambar);
            suaraSemua2.setSuarasah(suarasah);
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada tampil semua data pasangancalon \n" + e);
        }
    }
    
}
