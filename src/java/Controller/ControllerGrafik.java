/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.SessionFactoryUtil;
import View.Dashboard;
import View.Dashboard.listhasil;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Administrator
 */
public class ControllerGrafik {

    public String jumlahlistkecamatan() {
        String jml = "0";
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            List datalogin = session.createQuery("select count(distinct t.namaKecamatan) from Tampilsuarakecamatan t").list();;
            jml = datalogin.toString().replace("[", "");
            jml = jml.replace("]", "");
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada jumlah data detail Kecamatan \n" + e);
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
    public void realtimepasangancalon1(listhasil listHasil) throws IOException {
        String totalsuarasah = "";
        totalsuarasah = totalSuaraSah();
        String data = "";
        Transaction tx = null;
        String[] kodepasangancalon = new String[Integer.valueOf(jumlahdata())];
        String[] gubernur = new String[Integer.valueOf(jumlahdata())];
        String[] wakilgubernur = new String[Integer.valueOf(jumlahdata())];
        String[] gambar = new String[Integer.valueOf(jumlahdata())];
        String[] persentase = new String[Integer.valueOf(jumlahdata())];
        int[] suarasah = new int[Integer.valueOf(jumlahdata())];
        Double prosespersentase = 0.0;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
           tx = session.beginTransaction();
           List results = session
                    .createSQLQuery("SELECT Kode_Pasangancalon,Gubernur,Wakil_Gubernur,Gambar, SUM(Suara_Sah) as Suara_Sah"
                    + " FROM tampilsuarakecamatandetail GROUP BY Kode_Pasangancalon ORDER BY Kode_Pasangancalon ASC ")
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
                prosespersentase = (Double.valueOf(col5Value)/Double.valueOf(totalsuarasah)*100);
                DecimalFormat decimalFormat = new DecimalFormat("#.##");
                persentase[i] = String.valueOf(decimalFormat.format(prosespersentase));
                i++;
            }
            listHasil.setGubernur(gubernur);
            listHasil.setWakilgubernur(wakilgubernur);
            listHasil.setGambar(gambar);
            listHasil.setPersentase(persentase);
            listHasil.setSuarasah(suarasah);
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada realtime pasangan calon \n" + e);
        }
    }

    public String realtimelistkecamatan() throws IOException {
        String[] result = new String[Integer.valueOf(jumlahlistkecamatan())];
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            List results = session
                    .createSQLQuery("SELECT Kode_Kecamatan, Nama_Kecamatan FROM tampilsuarakecamatan ORDER BY Nama_Kecamatan ASC")
                    .addScalar("Kode_Kecamatan", Hibernate.STRING)
                    .addScalar("Nama_Kecamatan", Hibernate.STRING)
                    .list();
            int i = 0;
            for (Iterator iter = results.iterator(); iter.hasNext();) {
                Object[] objs = (Object[]) iter.next();
                String col1Value = (String) objs[0];
                String col2Value = (String) objs[1];
                result[i] = col2Value;
                i++;
            }
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada tampil semua data detail suara kecamatan \n" + e);
        }
        ObjectMapper mapper = new ObjectMapper();
        String hasil = mapper.writeValueAsString(result);
        return hasil;
    }

    public String detailrealtimekecamatan() throws IOException {
        List<tampilgrafik> tg = new ArrayList<tampilgrafik>();
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            List results = session
                    .createSQLQuery("SELECT Kode_Pasangancalon, Gubernur , Wakil_Gubernur FROM tb_pasangancalon ORDER BY Kode_Pasangancalon ASC")
                    .addScalar("Kode_Pasangancalon", Hibernate.STRING)
                    .addScalar("Gubernur", Hibernate.STRING)
                    .addScalar("Wakil_Gubernur", Hibernate.STRING)
                    .list();
            for (Iterator iter = results.iterator(); iter.hasNext();) {
                Object[] objs = (Object[]) iter.next();
                String KodePasanganCalon = (String) objs[0];
                String Gubernur = (String) objs[1];
                String WakilGubernur = (String) objs[2];
                String jml = "0";
                List datalogin = session.createQuery("select count(distinct t.kodeKecamatan) from Tampilsuarakecamatan as t").list();;
                jml = datalogin.toString().replace("[", "");
                jml = jml.replace("]", "");
                int[] data = new int[Integer.parseInt(jml)];
                //PERULANGAN HANDLE KECAMATAN
                List resultkecamatan = session
                        .createSQLQuery("SELECT Kode_Kecamatan,Nama_Kecamatan FROM tampilsuarakecamatan ORDER BY Nama_Kecamatan ASC")
                        .addScalar("Kode_Kecamatan", Hibernate.STRING)
                        .addScalar("Nama_Kecamatan", Hibernate.STRING)
                        .list();
                int i = 0;
                for (Iterator iter2 = resultkecamatan.iterator(); iter2.hasNext();) {
                    Object[] obj2 = (Object[]) iter2.next();
                    String KodeKecamatan = (String) obj2[0];
                    //PERULANGAN HANDEL detail suara sah kecamatan
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
                        data[i] = SuaraSah1;
                    }
                    i++;
                }
                tg.add(new tampilgrafik(Gubernur + "-" + WakilGubernur, data));
            }
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada tampil semua data detail suara kecamatan \n" + e);
        }
        ObjectMapper mapper = new ObjectMapper();
        String hasil = mapper.writeValueAsString(tg);
        return hasil;
    }

    public static class tampilgrafik {

        String name;
        int[] data;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int[] getData() {
            return data;
        }

        public void setData(int[] data) {
            this.data = data;
        }

        public tampilgrafik(String Name, int[] Data) {
            setName(Name);
            setData(Data);
        }
    }
//    public static void main(String[] args) throws IOException {
//        System.out.println(new ControllerGrafik().realtimelistkecamatan());
//    }
}
