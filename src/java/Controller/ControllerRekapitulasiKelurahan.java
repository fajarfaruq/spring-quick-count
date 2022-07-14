/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.SessionFactoryUtil;
import Model.TbSuarasahkelurahan;
import Model.TbSuaratidaksahkelurahan;
import View.RekapitulasiKecamatan;
import View.RekapitulasiKelurahan;
import java.text.DecimalFormat;
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
public class ControllerRekapitulasiKelurahan {

    public String insertsuarasah(TbSuarasahkelurahan tbSuarasahkelurahan) {
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        String response = "0";
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(tbSuarasahkelurahan);
            tx.commit();
            response = "1";
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada insert suara kelurahan.... !!\n" + e);
            response = "error";
        }
        return response;
    }

    public String insertsuaratidaksah(TbSuaratidaksahkelurahan tbSuaratidaksahkelurahan) {
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        String response = "0";
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(tbSuaratidaksahkelurahan);
            tx.commit();
            response = "1";
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada insert suara tidak sah kelurahan.... !!\n" + e);
            response = "error";
        }
        return response;
    }

    public String insert(TbSuarasahkelurahan tbSuarasahkelurahan, TbSuaratidaksahkelurahan tbSuaratidaksahkelurahan) {
        String respon = "0";
        try {
            String response1 = insertsuarasah(tbSuarasahkelurahan);
            String response2 = insertsuaratidaksah(tbSuaratidaksahkelurahan);
            respon = "1";
        } catch (Exception e) {
            respon = "0";
        }
        return respon;
    }

    public String generatesuarasahinsert(String kodekelurahan) {
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        String response = "0";
        try {
            tx = session.beginTransaction();
            List results = session
                    .createSQLQuery("SELECT t.Kode_Kelurahan,t.Kode_Pasangancalon,SUM(t.Suara_Sah) as Suara_Sah FROM tampilsuarakelurahandetail t "
                    + "where t.Kode_Kelurahan = '" + kodekelurahan + "' GROUP BY t.Kode_Pasangancalon")
                    .addScalar("Kode_Kelurahan", Hibernate.STRING)
                    .addScalar("Kode_Pasangancalon", Hibernate.STRING)
                    .addScalar("Suara_Sah", Hibernate.INTEGER)
                    .list();
            int i = 0;
            for (Iterator iter = results.iterator(); iter.hasNext();) {
                Object[] objs = (Object[]) iter.next();
                String col1Value = (String) objs[0];
                String col2Value = (String) objs[1];
                Integer col3Value = (Integer) objs[2];
                session.saveOrUpdate(new TbSuarasahkelurahan(null, col1Value, col2Value, col3Value));
                i++;
            }
            tx.commit();
            response = "1";
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada hapus suara sah Kelurahan.... !! \n" + e);
            response = "error";
        }
        return response;
    }

    public String generatesuaratidaksahinsert(String kodekelurahan) {
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        String response = "0";
        try {
            tx = session.beginTransaction();
            List results = session
                    .createSQLQuery("SELECT tampilsuarakelurahan.Kode_Kelurahan , tampilsuarakelurahan.Suara_Tidak_Sah FROM tampilsuarakelurahan Where "
                    + " tampilsuarakelurahan.Kode_Kelurahan= '" + kodekelurahan + "'")
                    .addScalar("Kode_Kelurahan", Hibernate.STRING)
                    .addScalar("Suara_Tidak_Sah", Hibernate.INTEGER)
                    .list();
            int i = 0;
            for (Iterator iter = results.iterator(); iter.hasNext();) {
                Object[] objs = (Object[]) iter.next();
                String col1Value = (String) objs[0];
                Integer col2Value = (Integer) objs[1];
                session.saveOrUpdate(new TbSuaratidaksahkelurahan(col1Value, col2Value, "Belum"));
                i++;
            }

            tx.commit();
            response = "1";
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada hapus suara sah Kelurahan.... !! \n" + e);
            response = "error";
        }
        return response;
    }

    public String generate(String kodekelurahan) {
        String respon = "0";
        try {
            String response1 = generatesuaratidaksahinsert(kodekelurahan);
            String response2 = generatesuarasahinsert(kodekelurahan);
            respon = "1";
        } catch (Exception e) {
            respon = "0";
        }
        return respon;
    }

    public String updatesuarasah(TbSuarasahkelurahan tbSuarasahkelurahan) {
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        String response = "0";
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(tbSuarasahkelurahan);
            response = "1";
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada update suara sah kelurahan .... !!\n" + e);
            response = "error";
        }
        return response;
    }

    public String updatesuaratidaksah(TbSuaratidaksahkelurahan tbSuaratidaksahkelurahan) {
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        String response = "0";
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(tbSuaratidaksahkelurahan);
            tx.commit();
            response = "1";
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada update suara tidak sah kelurahan .... !!\n" + e);
            response = "error";
        }
        return response;
    }

    public String update(TbSuarasahkelurahan tbSuarasahkelurahan, TbSuaratidaksahkelurahan tbSuaratidaksahkelurahan) {
        String respon = "0";
        try {
            String response1 = updatesuarasah(tbSuarasahkelurahan);
            String response2 = updatesuaratidaksah(tbSuaratidaksahkelurahan);
            respon = "1";
        } catch (Exception e) {
            respon = "0";
        }
        return respon;
    }

    public String deletesuarasah(String KodeKelurahan) {
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        String response = "0";
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("delete from TbSuarasahkelurahan t where t.kodeKelurahan ='" + KodeKelurahan + "'");
            query.executeUpdate();
            tx.commit();
            response = "1";
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada hapus suara sah Kelurahan.... !! \n" + e);
            response = "error";
        }
        return response;
    }

    public String deletesuaratidaksah(String KodeKelurahan) {
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        String response = "0";
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("delete from TbSuaratidaksahkelurahan t where t.kodeKelurahan ='" + KodeKelurahan + "'");
            query.executeUpdate();
            tx.commit();
            response = "1";
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada hapus suara tidak sah Kelurahan.... !! \n" + e);
            response = "error";
        }
        return response;
    }

    public String delete(String KodeKelurahan) {
        String respon = "0";
        try {
            String response1 = deletesuarasah(KodeKelurahan);
            String response2 = deletesuaratidaksah(KodeKelurahan);
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
            List datalogin = session.createQuery("select count(distinct t.kodeKelurahan) from TbSuarasahkelurahan as t").list();;
            jml = datalogin.toString().replace("[", "");
            jml = jml.replace("]", "");
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada jumlah data Kelurahan \n" + e);
        }
        return jml;
    }

    public String jumlahdatakecamatan(String kodekecamatan) {
        String hasil = "";
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            List results = session
                    .createSQLQuery("SELECT COUNT(tampilrekapitulasikelurahan.Kode_Kecamatan) as jml "
                    + "FROM tampilrekapitulasikelurahan WHERE tampilrekapitulasikelurahan.Kode_Kecamatan = '" + kodekecamatan + "' ")
                    .addScalar("jml", Hibernate.INTEGER)
                    .list();
            hasil = results.toString().replace("[", "");
            hasil = hasil.replace("]", "");
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada total suara sah \n" + e);
        }
        return hasil;
    }

    public String jumlahdatadetail(String skodekelurahan) {
        String jml = "0";
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            List datalogin = session.createQuery("select count(distinct t.kodePasanganCalon) from TbSuarasahkelurahan as t WHERE t.kodeKelurahan = '" + skodekelurahan + "'").list();;
            jml = datalogin.toString().replace("[", "");
            jml = jml.replace("]", "");
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada jumlah data detail Kelurahan \n" + e);
        }
        return jml;
    }

    public void tampilviewrekapitulasikelurahan(RekapitulasiKelurahan.listTbRekapitulasiKelurahan2 tbRekapitulasiKelurahan2, String skodekelurahan, int spage, int srow) {
        String data = "";
        Transaction tx = null;
        String[] kodekecamatan = new String[Integer.valueOf(jumlahdata())];
        String[] namakecamatan = new String[Integer.valueOf(jumlahdata())];
        int[] kodesuarakelurahan = new int[Integer.valueOf(jumlahdata())];
        String[] kodekelurahan = new String[Integer.valueOf(jumlahdata())];
        String[] namakelurahan = new String[Integer.valueOf(jumlahdata())];
        int[] jmldpt = new int[Integer.valueOf(jumlahdata())];
        int[] suarasah = new int[Integer.valueOf(jumlahdata())];
        int[] suaratidaksah = new int[Integer.valueOf(jumlahdata())];
        String[] tervalidasi = new String[Integer.valueOf(jumlahdata())];
        String hasil;

        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            List results = session
                    .createSQLQuery("SELECT tb_kecamatan.Kode_Kecamatan,tb_kecamatan.Nama_Kecamatan, "
                    + "tb_suarasahkelurahan.Kode_SuaraKelurahan,tb_suarasahkelurahan.kode_Kelurahan,tb_kelurahan.Nama_Kelurahan,"
                    + "SUM(tb_suarasahkelurahan.Suara_Sah) as Suara_Sah,tb_suaratidaksahkelurahan.Suara_Tidak_Sah ,tb_suaratidaksahkelurahan.Tervalidasi FROM "
                    + "tb_suarasahkelurahan,tb_suaratidaksahkelurahan,tb_kelurahan,tb_kecamatan WHERE  "
                    + "(tb_suarasahkelurahan.kode_Kelurahan LIKE '%" + skodekelurahan + "%' AND tb_kelurahan.Kode_Kelurahan= tb_suarasahkelurahan.kode_Kelurahan) "
                    + "AND (tb_suaratidaksahkelurahan.Kode_Kelurahan=tb_suarasahkelurahan.kode_Kelurahan "
                    + "AND tb_kecamatan.Kode_Kecamatan = tb_kelurahan.Kode_Kecamatan) "
                    + "GROUP BY tb_suaratidaksahkelurahan.Kode_Kelurahan ORDER BY tb_suaratidaksahkelurahan.Kode_Kelurahan ASC")
                    .addScalar("Kode_Kecamatan", Hibernate.STRING)
                    .addScalar("Nama_Kecamatan", Hibernate.STRING)
                    .addScalar("Kode_SuaraKelurahan", Hibernate.INTEGER)
                    .addScalar("kode_Kelurahan", Hibernate.STRING)
                    .addScalar("Nama_Kelurahan", Hibernate.STRING)
                    .addScalar("Suara_Sah", Hibernate.INTEGER)
                    .addScalar("Suara_Tidak_Sah", Hibernate.INTEGER)
                    .addScalar("Tervalidasi", Hibernate.STRING)
                    .list();
            int i = 0;
            for (Iterator iter = results.iterator(); iter.hasNext();) {
                Object[] objs = (Object[]) iter.next();
                String col1Value = (String) objs[0];
                String col2Value = (String) objs[1];
                Integer col3Value = (Integer) objs[2];
                String col4Value = (String) objs[3];
                String col5Value = (String) objs[4];
                Integer col6Value = (Integer) objs[5];
                Integer col7Value = (Integer) objs[6];
                String col8Value = (String) objs[7];
                kodekecamatan[i] = col1Value;
                namakecamatan[i] = col2Value;
                kodesuarakelurahan[i] = col3Value;
                kodekelurahan[i] = col4Value;
                namakelurahan[i] = col5Value;
                suarasah[i] = col6Value;
                suaratidaksah[i] = col7Value;
                tervalidasi[i] = col8Value;
                List results1 = session
                        .createSQLQuery("SELECT SUM(Jumlah_DPT) as Jumlah_DPT FROM tampiltps Where Kode_Kelurahan ='" + col4Value + "' ")
                        .addScalar("Jumlah_DPT", Hibernate.INTEGER)
                        .list();
                hasil = results1.toString().replace("[", "");
                hasil = hasil.replace("]", "");

                jmldpt[i] = Integer.parseInt(hasil);
                i++;
            }
            tbRekapitulasiKelurahan2.setKodekecamatan(kodekecamatan);
            tbRekapitulasiKelurahan2.setNamakecamatan(namakecamatan);
            tbRekapitulasiKelurahan2.setKodesuarakelurahan(kodesuarakelurahan);
            tbRekapitulasiKelurahan2.setKodekelurahan(kodekelurahan);
            tbRekapitulasiKelurahan2.setNamakelurahan(namakelurahan);
            tbRekapitulasiKelurahan2.setJmldpt(jmldpt);
            tbRekapitulasiKelurahan2.setSuarasah(suarasah);
            tbRekapitulasiKelurahan2.setSuaratidaksah(suaratidaksah);
            tbRekapitulasiKelurahan2.setTervalidasi(tervalidasi);
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada tampil semua data suara kelurahan utama \n" + e);
        }
    }

    public void tampilviewrekapitulasikelurahankecamatan(RekapitulasiKelurahan.listTbRekapitulasiKelurahan2 tbRekapitulasiKelurahan2, String skodekecamatan) {
        String data = "";
        Transaction tx = null;
        String[] kodekecamatan = new String[Integer.valueOf(jumlahdatakecamatan(skodekecamatan))];
        String[] namakecamatan = new String[Integer.valueOf(jumlahdatakecamatan(skodekecamatan))];
        int[] kodesuarakelurahan = new int[Integer.valueOf(jumlahdatakecamatan(skodekecamatan))];
        String[] kodekelurahan = new String[Integer.valueOf(jumlahdatakecamatan(skodekecamatan))];
        String[] namakelurahan = new String[Integer.valueOf(jumlahdatakecamatan(skodekecamatan))];
        int[] jmldpt = new int[Integer.valueOf(jumlahdatakecamatan(skodekecamatan))];
        int[] suarasah = new int[Integer.valueOf(jumlahdatakecamatan(skodekecamatan))];
        int[] suaratidaksah = new int[Integer.valueOf(jumlahdatakecamatan(skodekecamatan))];
        String[] tervalidasi = new String[Integer.valueOf(jumlahdata())];
        String hasil;

        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            List results = session
                    .createSQLQuery("SELECT tb_kecamatan.Kode_Kecamatan, tb_kecamatan.Nama_Kecamatan, tb_suarasahkelurahan.Kode_SuaraKelurahan, "
                    + "tb_suarasahkelurahan.kode_Kelurahan, tb_kelurahan.Nama_Kelurahan, SUM( tb_suarasahkelurahan.Suara_Sah ) AS Suara_Sah, "
                    + "tb_suaratidaksahkelurahan.Suara_Tidak_Sah, tb_suaratidaksahkelurahan.Tervalidasi "
                    + "FROM tb_kecamatan, tb_kelurahan, tb_suarasahkelurahan, tb_suaratidaksahkelurahan "
                    + "WHERE (tb_kecamatan.Kode_Kecamatan =  '" + skodekecamatan + "' AND tb_kelurahan.Kode_Kecamatan = tb_kecamatan.Kode_Kecamatan ) "
                    + "AND (tb_suarasahkelurahan.kode_Kelurahan = tb_kelurahan.Kode_Kelurahan AND tb_suaratidaksahkelurahan.Kode_Kelurahan = tb_kelurahan.Kode_Kelurahan) "
                    + "GROUP BY tb_suarasahkelurahan.kode_Kelurahan")
                    .addScalar("Kode_Kecamatan", Hibernate.STRING)
                    .addScalar("Nama_Kecamatan", Hibernate.STRING)
                    .addScalar("Kode_SuaraKelurahan", Hibernate.INTEGER)
                    .addScalar("kode_Kelurahan", Hibernate.STRING)
                    .addScalar("Nama_Kelurahan", Hibernate.STRING)
                    .addScalar("Suara_Sah", Hibernate.INTEGER)
                    .addScalar("Suara_Tidak_Sah", Hibernate.INTEGER)
                    .addScalar("Tervalidasi", Hibernate.STRING)
                    .list();
            int i = 0;
            for (Iterator iter = results.iterator(); iter.hasNext();) {
                Object[] objs = (Object[]) iter.next();
                String col1Value = (String) objs[0];
                String col2Value = (String) objs[1];
                Integer col3Value = (Integer) objs[2];
                String col4Value = (String) objs[3];
                String col5Value = (String) objs[4];
                Integer col6Value = (Integer) objs[5];
                Integer col7Value = (Integer) objs[6];
                String col8Value = (String) objs[7];
                kodekecamatan[i] = col1Value;
                namakecamatan[i] = col2Value;
                kodesuarakelurahan[i] = col3Value;
                kodekelurahan[i] = col4Value;
                namakelurahan[i] = col5Value;
                suarasah[i] = col6Value;
                suaratidaksah[i] = col7Value;
                tervalidasi[i] = col8Value;

                List results1 = session
                        .createSQLQuery("SELECT SUM(Jumlah_DPT) as Jumlah_DPT FROM tampiltps Where Kode_Kelurahan ='" + col4Value + "' ")
                        .addScalar("Jumlah_DPT", Hibernate.INTEGER)
                        .list();
                hasil = results1.toString().replace("[", "");
                hasil = hasil.replace("]", "");

                jmldpt[i] = Integer.parseInt(hasil);
                i++;
            }
            tbRekapitulasiKelurahan2.setKodekecamatan(kodekecamatan);
            tbRekapitulasiKelurahan2.setNamakecamatan(namakecamatan);
            tbRekapitulasiKelurahan2.setKodesuarakelurahan(kodesuarakelurahan);
            tbRekapitulasiKelurahan2.setKodekelurahan(kodekelurahan);
            tbRekapitulasiKelurahan2.setNamakelurahan(namakelurahan);
            tbRekapitulasiKelurahan2.setJmldpt(jmldpt);
            tbRekapitulasiKelurahan2.setSuarasah(suarasah);
            tbRekapitulasiKelurahan2.setSuaratidaksah(suaratidaksah);
            tbRekapitulasiKelurahan2.setTervalidasi(tervalidasi);
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada tampil semua data suara kelurahan kecamatan \n" + e);
        }
    }

    public void tampilviewrekapitulasihasilsementarakecamatan(RekapitulasiKelurahan.listTbRekapitulasiKelurahan2 tbRekapitulasiKelurahan2, String skodekecamatan) {
        String data = "";
        Transaction tx = null;
        String[] kodekecamatan = new String[Integer.valueOf(jumlahdatakecamatan(skodekecamatan))];
        String[] namakecamatan = new String[Integer.valueOf(jumlahdatakecamatan(skodekecamatan))];
        int[] kodesuarakelurahan = new int[Integer.valueOf(jumlahdatakecamatan(skodekecamatan))];
        String[] kodekelurahan = new String[Integer.valueOf(jumlahdatakecamatan(skodekecamatan))];
        String[] namakelurahan = new String[Integer.valueOf(jumlahdatakecamatan(skodekecamatan))];
        int[] jmldpt = new int[Integer.valueOf(jumlahdatakecamatan(skodekecamatan))];
        int[] suarasah = new int[Integer.valueOf(jumlahdatakecamatan(skodekecamatan))];
        int[] suaratidaksah = new int[Integer.valueOf(jumlahdatakecamatan(skodekecamatan))];
        String[] tervalidasi = new String[Integer.valueOf(jumlahdata())];
        String hasil;

        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            List results = session
                    .createSQLQuery("SELECT tampilrekapitulasikelurahan.Kode_Kecamatan,tampilrekapitulasikelurahan.Nama_Kecamatan,"
                    + " tampilrekapitulasikelurahan.kode_Kelurahan, tampilrekapitulasikelurahan.Nama_Kelurahan , "
                    + " SUM(tampilrekapitulasikelurahan.Suara_Sah) as Suara_Sah , SUM(tampilrekapitulasikelurahan.Suara_Tidak_Sah) as Suara_Tidak_Sah "
                    + "FROM tampilrekapitulasikelurahan WHERE tampilrekapitulasikelurahan.Kode_Kecamatan = '" + skodekecamatan + "' "
                    + "GROUP BY tampilrekapitulasikelurahan.Kode_Kecamatan")
                    .addScalar("Kode_Kecamatan", Hibernate.STRING)
                    .addScalar("Nama_Kecamatan", Hibernate.STRING)
                    .addScalar("kode_Kelurahan", Hibernate.STRING)
                    .addScalar("Nama_Kelurahan", Hibernate.STRING)
                    .addScalar("Suara_Sah", Hibernate.INTEGER)
                    .addScalar("Suara_Tidak_Sah", Hibernate.INTEGER)
                    .list();
            int i = 0;
            for (Iterator iter = results.iterator(); iter.hasNext();) {
                Object[] objs = (Object[]) iter.next();
                String col1Value = (String) objs[0];
                String col2Value = (String) objs[1];
                String col3Value = (String) objs[2];
                String col4Value = (String) objs[3];
                Integer col5Value = (Integer) objs[4];
                Integer col6Value = (Integer) objs[5];

                kodekecamatan[i] = col1Value;
                namakecamatan[i] = col2Value;
                kodesuarakelurahan[i] = 0;
                kodekelurahan[i] = col3Value;
                namakelurahan[i] = col4Value;
                suarasah[i] = col5Value;
                suaratidaksah[i] = col6Value;
                tervalidasi[i] = "";

                List results1 = session
                        .createSQLQuery("SELECT SUM(Jumlah_DPT) as Jumlah_DPT FROM tampiltps Where Kode_Kecamatan ='" + col1Value + "' ")
                        .addScalar("Jumlah_DPT", Hibernate.INTEGER)
                        .list();
                hasil = results1.toString().replace("[", "");
                hasil = hasil.replace("]", "");

                jmldpt[i] = Integer.parseInt(hasil);
                i++;
            }
            tbRekapitulasiKelurahan2.setKodekecamatan(kodekecamatan);
            tbRekapitulasiKelurahan2.setNamakecamatan(namakecamatan);
            tbRekapitulasiKelurahan2.setKodesuarakelurahan(kodesuarakelurahan);
            tbRekapitulasiKelurahan2.setKodekelurahan(kodekelurahan);
            tbRekapitulasiKelurahan2.setNamakelurahan(namakelurahan);
            tbRekapitulasiKelurahan2.setJmldpt(jmldpt);
            tbRekapitulasiKelurahan2.setSuarasah(suarasah);
            tbRekapitulasiKelurahan2.setSuaratidaksah(suaratidaksah);
            tbRekapitulasiKelurahan2.setTervalidasi(tervalidasi);
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada tampil semua data suara kelurahan kecamatan \n" + e);
        }
    }

    public void tampilviewdetailrekapitulasikelurahan(RekapitulasiKelurahan.listTbDetailRekapitulasiKelurahan2 kelurahan2, String skodekelurahan) {
        String data = "";
        Transaction tx = null;
        int[] kodesuarakelurahan = new int[Integer.valueOf(jumlahdatadetail(skodekelurahan))];
        String[] kodekelurahan = new String[Integer.valueOf(jumlahdatadetail(skodekelurahan))];
        int[] kodepasangancalon = new int[Integer.valueOf(jumlahdatadetail(skodekelurahan))];
        String[] gambar = new String[Integer.valueOf(jumlahdatadetail(skodekelurahan))];
        String[] gubernur = new String[Integer.valueOf(jumlahdatadetail(skodekelurahan))];
        String[] wakilgubernur = new String[Integer.valueOf(jumlahdatadetail(skodekelurahan))];
        int[] suarasah = new int[Integer.valueOf(jumlahdatadetail(skodekelurahan))];

        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            List results = session
                    .createSQLQuery("SELECT tb_suarasahkelurahan.Kode_SuaraKelurahan,tb_suarasahkelurahan.kode_Kelurahan,"
                    + "tb_suarasahkelurahan.Kode_PasanganCalon , tb_pasangancalon.Gubernur , tb_pasangancalon.Wakil_Gubernur , "
                    + "tb_pasangancalon.Gambar,tb_suarasahkelurahan.Suara_Sah FROM tb_suarasahkelurahan,tb_pasangancalon "
                    + "WHERE tb_pasangancalon.Kode_PasanganCalon = tb_suarasahkelurahan.Kode_PasanganCalon AND"
                    + " tb_suarasahkelurahan.kode_Kelurahan = '" + skodekelurahan + "' ORDER BY tb_suarasahkelurahan.Kode_PasanganCalon ASC")
                    .addScalar("Kode_SuaraKelurahan", Hibernate.INTEGER)
                    .addScalar("kode_Kelurahan", Hibernate.STRING)
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
                kodesuarakelurahan[i] = col1Value;
                kodekelurahan[i] = col2Value;
                kodepasangancalon[i] = col3Value;
                gubernur[i] = col4Value;
                wakilgubernur[i] = col5Value;
                gambar[i] = col6Value;
                suarasah[i] = col7Value;

                i++;
            }
            kelurahan2.setKodesuarakelurahan(kodesuarakelurahan);
            kelurahan2.setKodekelurahan(kodekelurahan);
            kelurahan2.setKodepasangancalon(kodepasangancalon);
            kelurahan2.setGambar(gambar);
            kelurahan2.setGubernur(gubernur);
            kelurahan2.setWakilgubernur(wakilgubernur);
            kelurahan2.setSuarasah(suarasah);
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada tampil semua data suara detail kelurahan \n" + e);
        }
    }

    public void tampilviewdetailrekapitulasikelurahankecamatansementara(RekapitulasiKecamatan.listTbDetailRekapitulasiKecamatan2 kecamatan2, String skodekecamatan) {
        String data = "";
        Transaction tx = null;
        int[] kodesuarakelurahan = new int[20];
        String[] kodekecamatan = new String[20];
        int[] kodepasangancalon = new int[20];
        String[] gambar = new String[20];
        String[] gubernur = new String[20];
        String[] wakilgubernur = new String[20];
        int[] suarasah = new int[20];

        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            List results = session
                    .createSQLQuery("SELECT tb_suarasahkelurahan.Kode_SuaraKelurahan,tb_kecamatan.Kode_Kecamatan, "
                    + "tb_suarasahkelurahan.Kode_PasanganCalon , tb_pasangancalon.Gubernur , tb_pasangancalon.Wakil_Gubernur ,  "
                    + "tb_pasangancalon.Gambar,SUM(tb_suarasahkelurahan.Suara_Sah) as Suara_Sah FROM "
                    + "tb_kecamatan,tb_kelurahan,tb_suarasahkelurahan,tb_pasangancalon  "
                    + "WHERE (tb_kecamatan.Kode_Kecamatan = '" + skodekecamatan + "' AND tb_kelurahan.Kode_Kecamatan = tb_kecamatan.Kode_kecamatan) "
                    + "AND (tb_pasangancalon.Kode_PasanganCalon = tb_suarasahkelurahan.Kode_PasanganCalon "
                    + "AND tb_suarasahkelurahan.kode_Kelurahan = tb_kelurahan.Kode_Kelurahan ) "
                    + "GROUP BY tb_suarasahkelurahan.Kode_PasanganCalon ORDER BY Suara_Sah DESC")
                    .addScalar("Kode_SuaraKelurahan", Hibernate.INTEGER)
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
                kodesuarakelurahan[i] = col1Value;
                kodekecamatan[i] = col2Value;
                kodepasangancalon[i] = col3Value;
                gubernur[i] = col4Value;
                wakilgubernur[i] = col5Value;
                gambar[i] = col6Value;
                suarasah[i] = col7Value;

                i++;
            }
            kecamatan2.setKodesuarakecamatan(kodesuarakelurahan);
            kecamatan2.setKodekecamatan(kodekecamatan);
            kecamatan2.setKodepasangancalon(kodepasangancalon);
            kecamatan2.setGambar(gambar);
            kecamatan2.setGubernur(gubernur);
            kecamatan2.setWakilgubernur(wakilgubernur);
            kecamatan2.setSuarasah(suarasah);
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada tampil semua data suara detail kelurahan \n" + e);
        }
    }

    public String printrekapitulasikelurahan(String kodekelurahan) {
        ObjectMapper objectMapper = new ObjectMapper();
        Data pathq = new Data();
        List<listTbDetailRekapitulasiKelurahan11> a = new ArrayList<listTbDetailRekapitulasiKelurahan11>();
        String json = "";
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            List results = session
                    .createSQLQuery("SELECT tb_suarasahkelurahan.Kode_SuaraKelurahan,tb_suarasahkelurahan.kode_Kelurahan,"
                    + "tb_suarasahkelurahan.Kode_PasanganCalon , tb_pasangancalon.Gubernur , tb_pasangancalon.Wakil_Gubernur , "
                    + "tb_pasangancalon.Gambar,tb_suarasahkelurahan.Suara_Sah FROM tb_suarasahkelurahan,tb_pasangancalon "
                    + "WHERE tb_pasangancalon.Kode_PasanganCalon = tb_suarasahkelurahan.Kode_PasanganCalon AND"
                    + " tb_suarasahkelurahan.kode_Kelurahan = '" + kodekelurahan + "' ORDER BY tb_suarasahkelurahan.Suara_Sah DESC")
                    .addScalar("Kode_SuaraKelurahan", Hibernate.INTEGER)
                    .addScalar("kode_Kelurahan", Hibernate.STRING)
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
                a.add(new listTbDetailRekapitulasiKelurahan11(col1Value, col2Value, col3Value, col4Value, col5Value, col6Value, col7Value));

                i++;
            }
            tx.commit();
            pathq.setData(a);
            json = objectMapper.writeValueAsString(pathq);
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada tampil semua data detail suara kelurahan \n" + e);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    public String getNomorTPS(String KodeKelurahan) {
        String data = "";
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            List results = session
                    .createSQLQuery("SELECT Kode_Kelurahan,Nomor_TPS FROM tampiltps WHERE Kode_Kelurahan = '" + KodeKelurahan + "'")
                    .addScalar("Kode_Kelurahan", Hibernate.STRING)
                    .addScalar("Nomor_TPS", Hibernate.INTEGER)
                    .list();
            int i = 0 ;
            for (Iterator iter = results.iterator(); iter.hasNext();) {
                Object[] objs = (Object[]) iter.next();
                String col1Value = (String) objs[0];
                Integer col2Value = (Integer) objs[1];
                List results2 = session
                        .createSQLQuery("SELECT COUNT(Kode_SuaraTPS) as jml FROM tampilsuaratps WHERE Kode_Kelurahan = '" + KodeKelurahan + "' AND Nomor_TPS = " +col2Value +" ")
                        .addScalar("jml", Hibernate.INTEGER)
                        .list();
                String hasil = results2.toString().replace("[", "");
                hasil = hasil.replace("]", "");
                if(hasil.equals("0")){
                    i++;
                    data = data + "- Nomor TPS " + col2Value + " Belum Ada <br />";
                }else{
                    data = data + "";
                }
                 
            }
            if(i>0){
                data = data + "<br />-------------------------<br />";
                data = data + "Terdapat " + i + " Error";
            }else{
                data = data + "<div style='color:green;'>Data valid</div>";
            }

            
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada tampil cekere nomor tps \n" + e);
        }
        return data;
    }
//    public static void main(String args[]){
//        ControllerRekapitulasiKelurahan crk = new ControllerRekapitulasiKelurahan();
//         System.out.println(crk.getNomorTPS("KL014"));
//    }

    class Data {

        List<listTbDetailRekapitulasiKelurahan11> data;

        public List<listTbDetailRekapitulasiKelurahan11> getData() {
            return data;
        }

        public void setData(List<listTbDetailRekapitulasiKelurahan11> data) {
            this.data = data;
        }
    }

    class listTbDetailRekapitulasiKelurahan11 {

        int kodesuarakelurahan;
        String kodekelurahan;
        int kodepasangancalon;
        String gambar;
        String gubernur;
        String wakilgubernur;
        int suarasah;

        public listTbDetailRekapitulasiKelurahan11(int kodeSuarakelurahan, String kodeKelurahan, int kodePasanganCalon, String Gubernur, String wakilGubernur, String Gambar, int suaraSah) {
            setKodesuarakelurahan(kodeSuarakelurahan);
            setKodekelurahan(kodeKelurahan);
            setKodepasangancalon(kodePasanganCalon);
            setGambar(Gambar);
            setGubernur(Gubernur);
            setWakilgubernur(wakilGubernur);
            setSuarasah(suaraSah);
        }

        public int getKodesuarakelurahan() {
            return kodesuarakelurahan;
        }

        public void setKodesuarakelurahan(int kodesuarakelurahan) {
            this.kodesuarakelurahan = kodesuarakelurahan;
        }

        public String getKodekelurahan() {
            return kodekelurahan;
        }

        public void setKodekelurahan(String kodekelurahan) {
            this.kodekelurahan = kodekelurahan;
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
