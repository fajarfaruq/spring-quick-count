/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.SessionFactoryUtil;
import View.RekapitulasiSemua;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author user
 */
public class ControllerRekapitulasiSemua {
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
                    .createSQLQuery("SELECT SUM(Suara_Sah) as Suara_Sah FROM tb_suarasahkecamatan")
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
                    .createSQLQuery("SELECT SUM(Suara_Tidak_Sah) as Suara_Tidak_Sah FROM tb_suaratidaksahkecamatan")
                    .addScalar("Suara_Tidak_Sah", Hibernate.STRING)
                    .list();
            hasil = results.toString().replace("[", "");
            hasil = hasil.replace("]", "");
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada suara tidak sah kecamatan \n" + e);
        }
        return hasil;
    } 
    
    public String jumlahdata(){
        String jml = "0" ;
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            List datalogin = session.createQuery("select count(distinct t.kodePasanganCalon) as jml from TbSuarasahkecamatan as t").list();;
            jml = datalogin.toString().replace("[", "");
            jml= jml.replace("]", "");
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada tampil jumlah Rekapitulasi Semua \n" + e);
        }
        return jml;
    }
    
     public void tampilsemuadata(RekapitulasiSemua.listTbRekapitulasiSemua2 suaraSemua2) {
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
                    .createSQLQuery("SELECT tb_pasangancalon.Kode_Pasangancalon,tb_pasangancalon.Gubernur,tb_pasangancalon.Wakil_Gubernur, "
                   + "tb_pasangancalon.Gambar, SUM(tb_suarasahkecamatan.Suara_Sah) as Suara_Sah FROM tb_pasangancalon,tb_suarasahkecamatan "
                   + "Where tb_suarasahkecamatan.Kode_PasanganCalon = tb_pasangancalon.Kode_PasanganCalon GROUP BY tb_pasangancalon.Kode_Pasangancalon")
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
     
   public String printrekapitulasikecamatan() throws IOException {
        String hasil = "";
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            List resultkecamatan = session
                    .createSQLQuery("SELECT tb_suarasahkecamatan.Kode_Kecamatan,tb_kecamatan.Nama_Kecamatan, "
                    + "SUM(tb_suarasahkecamatan.Suara_Sah) as Suara_Sah,"
                    + "tb_suaratidaksahkecamatan.Suara_Tidak_Sah "
                    + "FROM tb_suarasahkecamatan,tb_suaratidaksahkecamatan,tb_kecamatan "
                    + "WHERE tb_kecamatan.Kode_Kecamatan= tb_suarasahkecamatan.Kode_Kecamatan "
                    + "AND (tb_suaratidaksahkecamatan.Kode_Kecamatan=tb_suarasahkecamatan.Kode_Kecamatan ) "
                    + "GROUP BY tb_suaratidaksahkecamatan.Kode_Kecamatan "
                    + "ORDER BY tb_kecamatan.Nama_Kecamatan ASC")
                    .addScalar("Kode_Kecamatan", Hibernate.STRING)
                    .addScalar("Nama_Kecamatan", Hibernate.STRING)
                    .addScalar("Suara_Sah", Hibernate.INTEGER)
                    .addScalar("Suara_Tidak_Sah", Hibernate.INTEGER)
                    .list();
            int i = 1;
            for (Iterator iter = resultkecamatan.iterator(); iter.hasNext();) {
                Object[] obj = (Object[]) iter.next();
                String KodeKecamatan = (String) obj[0];
                String NamaKecamatan = (String) obj[1];
                Integer SuaraSah = (Integer) obj[2];
                Integer SuaraTidakSah = (Integer) obj[3];
                //PERULANGAN HANDEL detail suara sah kecamatan
                List results1 = session
                    .createSQLQuery("SELECT SUM(Jumlah_DPT) as Jumlah_DPT FROM tampiltps Where Kode_Kecamatan ='"+KodeKecamatan +"' ")
                    .addScalar("Jumlah_DPT", Hibernate.INTEGER)
                    .list();
                String JumlahDPT = results1.toString().replace("[", "");
                JumlahDPT = JumlahDPT.replace("]", ""); 
                
                hasil = hasil + "{'No.':'"+ String.valueOf(i) + "', 'Kecamatan' : '"+ NamaKecamatan +"','Jumlah DPT' : '" + JumlahDPT + "' ";
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
                            + " FROM tb_suarasahkecamatan WHERE Kode_Kecamatan = '" + KodeKecamatan + "' AND "
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
                        hasil = hasil + ",'" + Gubernur +" - " + WakilGubernur+"' : '" + String.valueOf(SuaraSah1) +"'";
                    }
                }
                hasil = hasil + ",'Suara Sah ':'" + String.valueOf(SuaraSah) +"','Suara Tidak Sah':'" + String.valueOf(SuaraTidakSah) + "'},";
                i++;
            }
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada tampil semua data detail suara kecamatan \n" + e);
        }
        return hasil;
    }
//     public static void main(String args[]) throws IOException{
//
//         System.out.println(new ControllerRekapitulasiSemua().printrekapitulasikecamatan());
//     }
}
