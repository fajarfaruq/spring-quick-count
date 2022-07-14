/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.SessionFactoryUtil;
import Model.TbSuarasahkecamatan;
import Model.TbSuaratidaksahkecamatan;
import View.RekapitulasiKecamatan;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author user
 */
public class ControllerRekapitulasiKecamatan {
    public String insertsuarasah(TbSuarasahkecamatan tbSuarasahkecamatan) {
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        String response = "0";
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(tbSuarasahkecamatan);
            tx.commit();
            response = "1";
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada insert suara kecamatan.... !!\n" + e);
            response = "error";
        }
        return response;
    }     

    public String insertsuaratidaksah(TbSuaratidaksahkecamatan tbSuaratidaksahkecamatan) {
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        String response = "0";
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(tbSuaratidaksahkecamatan);
            tx.commit();
            response = "1";
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada insert suara tidak sah kecamatan.... !!\n" + e);
            response = "error";
        }
        return response;
    }
    
   public String insert(TbSuarasahkecamatan tbSuarasahkecamatan, TbSuaratidaksahkecamatan tbSuaratidaksahkecamatan) {
        String respon = "0";
        try {
            String response1 = insertsuarasah(tbSuarasahkecamatan);
            String response2 = insertsuaratidaksah(tbSuaratidaksahkecamatan);
            respon = "1";
        } catch (Exception e) {
            respon = "0";
        }
        return respon;
    }
   
    public String generatesuarasahinsert(String kodekecamatan) {
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        String response = "0";
        try {
            tx = session.beginTransaction();
            List results = session
                    .createSQLQuery("SELECT tb_kecamatan.Kode_Kecamatan,tb_suarasahkelurahan.Kode_PasanganCalon ,"
                    + " SUM(tb_suarasahkelurahan.Suara_Sah) as Suara_Sah "
                    + " FROM  tb_kecamatan,tb_kelurahan,tb_suarasahkelurahan  Where "
                    + " (tb_kecamatan.Kode_Kecamatan = '" + kodekecamatan + "' AND tb_kelurahan.Kode_Kecamatan = tb_kecamatan.Kode_Kecamatan) "
                    + "AND tb_suarasahkelurahan.Kode_Kelurahan = tb_kelurahan.Kode_Kelurahan GROUP BY tb_suarasahkelurahan.Kode_PasanganCalon")
                    .addScalar("Kode_Kecamatan", Hibernate.STRING)
                    .addScalar("Kode_Pasangancalon", Hibernate.STRING)
                    .addScalar("Suara_Sah", Hibernate.INTEGER)
                    .list();
            int i = 0;
            for (Iterator iter = results.iterator(); iter.hasNext();) {
                Object[] objs = (Object[]) iter.next();
                String col1Value = (String) objs[0];
                String col2Value = (String) objs[1];
                Integer col3Value = (Integer) objs[2];
                session.saveOrUpdate(new TbSuarasahkecamatan(null,col1Value, col2Value, col3Value));
                i++;
            }
            tx.commit();
            response = "1";
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada generate suara sah Kecamatan.... !! \n" + e);
            response = "error";
        }
        return response;
    }

    public String generatesuaratidaksahinsert(String kodekecamatan) {
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        String response = "0";
        try {
            tx = session.beginTransaction();
            List results = session
                    .createSQLQuery("SELECT Kode_Kecamatan , SUM(Suara_Tidak_Sah) as Suara_Tidak_Sah FROM tampilrekapitulasikelurahan "
                    + "WHERE Kode_Kecamatan = '"+ kodekecamatan+ "' GROUP BY Kode_Kecamatan")
                    .addScalar("Kode_Kecamatan", Hibernate.STRING)
                    .addScalar("Suara_Tidak_Sah", Hibernate.INTEGER)
                    .list();
            int i = 0;
            for (Iterator iter = results.iterator(); iter.hasNext();) {
                Object[] objs = (Object[]) iter.next();
                String col1Value = (String) objs[0];
                Integer col2Value = (Integer) objs[1];
                session.saveOrUpdate(new TbSuaratidaksahkecamatan(col1Value, col2Value, "Belum"));
                i++;
            }

            tx.commit();
            response = "1";
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada Generate suara tidak sah Kecamatan.... !! \n" + e);
            response = "error";
        }
        return response;
    }

    public String generate(String kodekecamatan) {
        String respon = "0";
        try {
            String response1 = generatesuaratidaksahinsert(kodekecamatan);
            String response2 = generatesuarasahinsert(kodekecamatan);
            respon = "1";
        } catch (Exception e) {
            respon = "0";
        }
        return respon;
    }
   
    public String updatesuarasah(TbSuarasahkecamatan tbSuarasahkecamatan) {
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        String response = "0";
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(tbSuarasahkecamatan);
            response = "1";
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada update suara sah kecamatan.... !!\n" + e);
            response = "error";
        }
        return response;
    }
    public String updatesuaratidaksah(TbSuaratidaksahkecamatan tbSuaratidaksahkecamatan) {
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        String response = "0";
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(tbSuaratidaksahkecamatan);
            tx.commit();
            response = "1";
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada update suara tidak sah kecamatan.... !!\n" + e);
            response = "error";
        }
        return response;
    }    

   public String update(TbSuarasahkecamatan tbSuarasahkecamatan, TbSuaratidaksahkecamatan tbSuaratidaksahkecamatan) {
        String respon = "0";
        try {
            String response1 = updatesuarasah(tbSuarasahkecamatan);
            String response2 = updatesuaratidaksah(tbSuaratidaksahkecamatan);
            respon = "1";
        } catch (Exception e) {
            respon = "0";
        }
        return respon;
    }
    public String deletesuarasah(String KodeKecamatan) {
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        String response = "0";
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("delete from TbSuarasahkecamatan t where t.kodeKecamatan='" + KodeKecamatan + "'");
            query.executeUpdate();
            tx.commit();
            response = "1";
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada hapus suara sah Kecamatan.... !! \n" + e);
            response = "error";
        }
        return response;
    }   
    public String deletesuaratidaksah(String KodeKecamatan) {
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        String response = "0";
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("delete from TbSuaratidaksahkecamatan t where t.kodeKecamatan='" + KodeKecamatan + "'");
            query.executeUpdate();
            tx.commit();
            response = "1";
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada hapus suara tidak sah Kecamatan.... !! \n" + e);
            response = "error";
        }
        return response;
    }  
    public String delete(String KodeKecamatan) {
        String respon = "0";
        try {
            String response1 = deletesuarasah(KodeKecamatan);
            String response2 = deletesuaratidaksah(KodeKecamatan);
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
            List datalogin = session.createQuery("select count(distinct t.kodeKecamatan) from TbSuarasahkecamatan as t").list();;
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
            List datalogin = session.createQuery("select count(distinct t.kodePasanganCalon) from TbSuarasahkecamatan as t WHERE t.kodeKecamatan= '" + skodekecamatan + "'").list();;
            jml = datalogin.toString().replace("[", "");
            jml = jml.replace("]", "");
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada jumlah data detail Kecamatan \n" + e);
        }
        return jml;
    }  
    
     public void tampilviewrekapitulasikecamatan(RekapitulasiKecamatan.listTbRekapitulasiKecamatan2 tbRekapitulasiKecamatan2, String skodekecamatan, int spage, int srow) {
        String data = "";
        Transaction tx = null;
        int[] kodesuarakecamatan= new int[Integer.valueOf(jumlahdata())];
        String[] kodekecamatan = new String[Integer.valueOf(jumlahdata())];
        String[] namakecamatan=new String[Integer.valueOf(jumlahdata())];
        int[] jmldpt= new int[Integer.valueOf(jumlahdata())];
        int[] suarasah= new int[Integer.valueOf(jumlahdata())];
        int[] suaratidaksah= new int[Integer.valueOf(jumlahdata())];
        String hasil;

        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
           tx = session.beginTransaction();
           List results = session
                    .createSQLQuery("SELECT tb_suarasahkecamatan.Kode_SuaraKecamatan,tb_suarasahkecamatan.Kode_Kecamatan,tb_kecamatan.Nama_Kecamatan,"
                   + "SUM(tb_suarasahkecamatan.Suara_Sah) as Suara_Sah,tb_suaratidaksahkecamatan.Suara_Tidak_Sah FROM "
                   + "tb_suarasahkecamatan,tb_suaratidaksahkecamatan,tb_kecamatan WHERE  "
                   + "(tb_suarasahkecamatan.Kode_Kecamatan LIKE '%"+ skodekecamatan +"%' AND tb_kecamatan.Kode_Kecamatan= tb_suarasahkecamatan.Kode_Kecamatan) "
                   + "AND (tb_suaratidaksahkecamatan.Kode_Kecamatan=tb_suarasahkecamatan.Kode_Kecamatan ) "
                   + "GROUP BY tb_suaratidaksahkecamatan.Kode_Kecamatan ORDER BY tb_suaratidaksahkecamatan.Kode_Kecamatan ASC" )
                   
                    .addScalar("Kode_SuaraKecamatan", Hibernate.INTEGER)
                    .addScalar("Kode_Kecamatan", Hibernate.STRING)
                    .addScalar("Nama_Kecamatan", Hibernate.STRING)
                    .addScalar("Suara_Sah", Hibernate.INTEGER)
                    .addScalar("Suara_Tidak_Sah", Hibernate.INTEGER)

                    .list();
            int i = 0;
            for (Iterator iter = results.iterator(); iter.hasNext();) {
                Object[] objs = (Object[]) iter.next();
                Integer col1Value = (Integer) objs[0];
                String col2Value = (String) objs[1];
                String col3Value = (String) objs[2];
                Integer col4Value = (Integer) objs[3];
                Integer col5Value = (Integer) objs[4];
                
                kodesuarakecamatan[i] = col1Value;
                kodekecamatan[i]= col2Value;
                namakecamatan[i] = col3Value;
                suarasah[i]= col4Value;
                suaratidaksah[i] = col5Value;
                
                List results1 = session
                    .createSQLQuery("SELECT SUM(Jumlah_DPT) as Jumlah_DPT FROM tampiltps Where Kode_Kecamatan ='"+col2Value +"' ")
                    .addScalar("Jumlah_DPT", Hibernate.INTEGER)
                    .list();
                hasil = results1.toString().replace("[", "");
                hasil = hasil.replace("]", "");
                
                jmldpt[i] = Integer.parseInt(hasil);
                i++;
            }
            tbRekapitulasiKecamatan2.setKodesuarakecamatan(kodesuarakecamatan);
            tbRekapitulasiKecamatan2.setKodekecamatan(kodekecamatan);
            tbRekapitulasiKecamatan2.setNamakecamatan(namakecamatan);
            tbRekapitulasiKecamatan2.setJmldpt(jmldpt);
            tbRekapitulasiKecamatan2.setSuarasah(suarasah);
            tbRekapitulasiKecamatan2.setSuaratidaksah(suaratidaksah);
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada tampil semua data suara kecamatan \n" + e);
        }
    } 
    public void tampilviewdetailrekapitulasikecamatan(RekapitulasiKecamatan.listTbDetailRekapitulasiKecamatan2  kecamatan2, String skodekecamatan) {
        String data = "";
        Transaction tx = null;
        int[] kodesuarakecamatan = new int[Integer.valueOf(jumlahdatadetail(skodekecamatan))];
        String[] kodekecamatan = new String[Integer.valueOf(jumlahdatadetail(skodekecamatan))];
        int[] kodepasangancalon = new int[Integer.valueOf(jumlahdatadetail(skodekecamatan))];
        String[] gambar = new String[Integer.valueOf(jumlahdatadetail(skodekecamatan))];
        String[] gubernur = new String[Integer.valueOf(jumlahdatadetail(skodekecamatan))];
        String[] wakilgubernur = new String[Integer.valueOf(jumlahdatadetail(skodekecamatan))];
        int[] suarasah = new int[Integer.valueOf(jumlahdatadetail(skodekecamatan))];

        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
           tx = session.beginTransaction();
           List results = session
                    .createSQLQuery("SELECT tb_suarasahkecamatan.Kode_SuaraKecamatan,tb_suarasahkecamatan.kode_Kecamatan,"
                   + "tb_suarasahkecamatan.Kode_PasanganCalon , tb_pasangancalon.Gubernur , tb_pasangancalon.Wakil_Gubernur , "
                   + "tb_pasangancalon.Gambar,tb_suarasahkecamatan.Suara_Sah FROM tb_suarasahkecamatan,tb_pasangancalon "
                   + "WHERE tb_pasangancalon.Kode_PasanganCalon = tb_suarasahkecamatan.Kode_PasanganCalon AND"
                   + " tb_suarasahkecamatan.kode_Kecamatan = '"+skodekecamatan +"' ORDER BY tb_suarasahkecamatan.Kode_PasanganCalon ASC" )
                   
                    .addScalar("Kode_SuaraKecamatan", Hibernate.INTEGER)
                    .addScalar("kode_Kecamatan", Hibernate.STRING)
                    .addScalar("Kode_PasanganCalon", Hibernate.INTEGER)
                    .addScalar("Gubernur", Hibernate.STRING)
                    .addScalar("Wakil_Gubernur", Hibernate.STRING)
                    .addScalar("Gambar", Hibernate.STRING)
                    .addScalar("Suara_Sah", Hibernate.INTEGER)

                    .list();
            int i = 0;
            for (Iterator iter = results.iterator(); iter.hasNext();) {
                Object[] objs = (Object[]) iter.next();
                Integer col1Value = (Integer) objs[0];
                String col2Value = (String) objs[1];
                Integer col3Value = (Integer) objs[2];
                String col4Value = (String) objs[3];
                String col5Value = (String) objs[4];
                String col6Value = (String) objs[5];
                Integer col7Value = (Integer) objs[6];
                kodesuarakecamatan[i] = col1Value;
                kodekecamatan[i] = col2Value;
                kodepasangancalon[i] = col3Value;
                gubernur[i] = col4Value;
                wakilgubernur[i] = col5Value;
                gambar[i] = col6Value;
                suarasah[i] = col7Value;
                
                i++;
            }
            kecamatan2.setKodesuarakecamatan(kodesuarakecamatan);
            kecamatan2.setKodekecamatan(kodekecamatan);
            kecamatan2.setKodepasangancalon(kodepasangancalon);
            kecamatan2.setGambar(gambar);
            kecamatan2.setGubernur(gubernur);
            kecamatan2.setWakilgubernur(wakilgubernur);
            kecamatan2.setSuarasah(suarasah);
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada tampil semua data suara detail kecamatan \n" + e);
        }
    }  
    
    public String printrekapitulasikecamatan(String kodekecamatan) {
        ObjectMapper objectMapper = new ObjectMapper();
        Data pathq = new Data();
        List<listTbDetailRekapitulasiKecamatan11> a = new ArrayList<listTbDetailRekapitulasiKecamatan11>();
        String json = "";
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            List results = session
                    .createSQLQuery("SELECT tb_suarasahkecamatan.Kode_SuaraKecamatan,tb_suarasahkecamatan.Kode_Kecamatan,"
                    + "tb_suarasahkecamatan.Kode_PasanganCalon , tb_pasangancalon.Gubernur , tb_pasangancalon.Wakil_Gubernur , "
                    + "tb_pasangancalon.Gambar,tb_suarasahkecamatan.Suara_Sah FROM tb_suarasahkecamatan,tb_pasangancalon "
                    + "WHERE tb_pasangancalon.Kode_PasanganCalon = tb_suarasahkecamatan.Kode_PasanganCalon AND"
                    + " tb_suarasahkecamatan.Kode_Kecamatan = '" + kodekecamatan + "' ORDER BY tb_suarasahkecamatan.Suara_Sah DESC")
                    .addScalar("Kode_SuaraKecamatan", Hibernate.INTEGER)
                    .addScalar("Kode_Kecamatan", Hibernate.STRING)
                    .addScalar("Kode_PasanganCalon", Hibernate.INTEGER)
                    .addScalar("Gubernur", Hibernate.STRING)
                    .addScalar("Wakil_Gubernur", Hibernate.STRING)
                    .addScalar("Gambar", Hibernate.STRING)
                    .addScalar("Suara_Sah", Hibernate.INTEGER)
                    .list();
            int i = 0;
            for (Iterator iter = results.iterator(); iter.hasNext();) {
                Object[] objs = (Object[]) iter.next();
                Integer col1Value = (Integer) objs[0];
                String col2Value = (String) objs[1];
                Integer col3Value = (Integer) objs[2];
                String col4Value = (String) objs[3];
                String col5Value = (String) objs[4];
                String col6Value = (String) objs[5];
                Integer col7Value = (Integer) objs[6];
                
                a.add(new listTbDetailRekapitulasiKecamatan11(col1Value, col2Value, col3Value, col4Value, col5Value, col6Value, col7Value));

                i++;
            }
            tx.commit();
            pathq.setData(a);
            json = objectMapper.writeValueAsString(pathq);
        } catch (RuntimeException e) {
            System.out.println("Terjadi error print per kecamatan \n" + e);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }
    
    public String jumlahDPT(String kodekecamatan){
        String jml = "0";
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            List results1 = session
                    .createSQLQuery("SELECT SUM(Jumlah_DPT) as Jumlah_DPT FROM tampiltps Where Kode_Kecamatan ='" + kodekecamatan + "' ")
                    .addScalar("Jumlah_DPT", Hibernate.INTEGER)
                    .list();
            jml = results1.toString().replace("[", "");
            jml = jml.replace("]", "");
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada jumlah DPT Kecamatan \n" + e);
        }
        return jml;
    }
    
    public String getjmlsuaratidaksah(String kodekecamatan) {
        String jml = "0";
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            List results1 = session
                    .createSQLQuery("SELECT Suara_Tidak_Sah FROM tb_suaratidaksahkecamatan t WHERE Kode_Kecamatan = '" + kodekecamatan + "'")
                    .addScalar("Suara_Tidak_Sah", Hibernate.INTEGER)
                    .list();
            jml = results1.toString().replace("[", "");
            jml = jml.replace("]", "");
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada jumlah suara tidak sah kecamatan\n" + e);
        }
        return jml;
    }

    public String getjmlsuarasah(String kodekecamatan) {
        String jml = "0";
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
             List results1 = session
                    .createSQLQuery("SELECT SUM(Suara_Sah) as jml FROM tb_suarasahkecamatan t WHERE Kode_Kecamatan = '" + kodekecamatan + "'")
                    .addScalar("jml", Hibernate.INTEGER)
                    .list();
            jml = results1.toString().replace("[", "");
            jml = jml.replace("]", "");           
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada jumlah suara sah Kecamatan \n" + e);
        }
        return jml;
    }
    
//    public static void main(String args[]){
//        ControllerRekapitulasiKecamatan crk = new ControllerRekapitulasiKecamatan();
//         System.out.println(crk.getjmlsuarasah("KC001"));
//    }

    class Data {

        List<listTbDetailRekapitulasiKecamatan11> data;

        public List<listTbDetailRekapitulasiKecamatan11> getData() {
            return data;
        }

        public void setData(List<listTbDetailRekapitulasiKecamatan11> data) {
            this.data = data;
        }
    }

    class listTbDetailRekapitulasiKecamatan11 {

        int kodesuarakecamatan;
        String kodekecamatan;
        int kodepasangancalon;
        String gambar;
        String gubernur;
        String wakilgubernur;
        int suarasah;

        public listTbDetailRekapitulasiKecamatan11(int kodeSuarakecamatan, String kodeKecamatan, int kodePasanganCalon,  String Gubernur, String wakilGubernur,String Gambar, int suaraSah) {
            setKodesuarakecamatan(kodeSuarakecamatan);
            setKodekecamatan(kodeKecamatan);
            setKodepasangancalon(kodePasanganCalon);
            setGambar(Gambar);
            setGubernur(Gubernur);
            setWakilgubernur(wakilGubernur);
            setSuarasah(suaraSah);
        }

        public int getKodesuarakecamatan() {
            return kodesuarakecamatan;
        }

        public void setKodesuarakecamatan(int kodesuarakecamatan) {
            this.kodesuarakecamatan = kodesuarakecamatan;
        }

        public String getKodekecamatan() {
            return kodekecamatan;
        }

        public void setKodekecamatan(String kodekecamatan) {
            this.kodekecamatan = kodekecamatan;
        }



        public int getKodepasangancalon() {
            return kodepasangancalon;
        }

        public void setKodepasangancalon(int kodepasangancalon) {
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