/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.SessionFactoryUtil;
import Model.Tampilkelurahan;
import Model.TbKelurahan;
import View.Kelurahan;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Administrator
 */
public class ControllerKelurahan {

    public String insert(TbKelurahan tk) {
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        String response = "0";
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(tk);
            tx.commit();
            response = "1";
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada insert data kelurahan .... !!\n" + e);
            response = "error";
        }

        return response;
    }

    public String update(TbKelurahan tkec) {
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        String response = "0";
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(tkec);
            tx.commit();
            response = "1";
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada update data kelurahan .... !!\n" + e);
            response = "error";
        }
        return response;
    }

    public String delete(String KodeKelurahan) {
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        String response = "0";
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("delete from TbKelurahan where kodeKelurahan='" + KodeKelurahan + "'");
            query.executeUpdate();
            tx.commit();
            response = "1";
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada hapus data kelurahan .... !! \n" + e);
            response = "error";
        }
        return response;
    }

    public String jumlahdata() {
        String jml = "0";
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            List datalogin = session.createQuery("select count(distinct t.kodeKelurahan) from TbKelurahan as t").list();;
            jml = datalogin.toString().replace("[", "");
            jml = jml.replace("]", "");
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada tampil semua data login \n" + e);
        }
        return jml;
    }
    
    public String jumlahdatasesuaikecamatan(String kodekecamatan) {
        String jml = "0";
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            List datalogin = session.createQuery("select count(distinct t.kodeKelurahan) from TbKelurahan as t "
                    + "WHERE t.kodeKecamatan = '" + kodekecamatan + "'").list();
            jml = datalogin.toString().replace("[", "");
            jml = jml.replace("]", "");
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada tampil semua data login \n" + e);
        }
        return jml;
    }

    public void tampilsemuadata(Kelurahan.listTbKelurahan2 kelurahan2, String skodekelurahan, String skodekecamatan,
            String snamakelurahan, int spage, int srow) {
        String data = "";
        Transaction tx = null;
        String[] kodekelurahan = new String[Integer.valueOf(jumlahdata())];
        String[] kodekecamatan = new String[Integer.valueOf(jumlahdata())];
        String[] namakelurahan = new String[Integer.valueOf(jumlahdata())];

        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("SELECT t FROM TbKelurahan t where  t.kodeKelurahan like '%" + skodekelurahan + "%' "
                    + "AND t.namaKelurahan like '%" + snamakelurahan + "%' AND t.kodeKecamatan like '%" + skodekecamatan + "%'");
            q.setFirstResult(spage);
            q.setMaxResults(srow);
            List datalogin = q.list();
            int i = 0;
            for (Iterator iter = datalogin.iterator(); iter.hasNext();) {
                TbKelurahan element = (TbKelurahan) iter.next();
                kodekelurahan[i] = element.getKodeKelurahan();
                kodekecamatan[i] = element.getKodeKecamatan();
                namakelurahan[i] = element.getNamaKelurahan();
                //System.out.println("wow" + i);
                i++;
            }
            kelurahan2.setKodekelurahan(kodekelurahan);
            kelurahan2.setKodekecamatan(kodekecamatan);
            kelurahan2.setNamakelurahan(namakelurahan);
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada tampil semua data kelurahan \n" + e);
        }
    }
    
    public void tampilsemuaviewkelurahan(Kelurahan.listTbKelurahan2 kelurahan2, String skodekelurahan, String skodekecamatan,
            String snamakelurahan, int spage, int srow) {
        String data = "";
        Transaction tx = null;
        String[] kodekelurahan = new String[Integer.valueOf(jumlahdata())];
        String[] kodekecamatan = new String[Integer.valueOf(jumlahdata())];
        String[] namakelurahan = new String[Integer.valueOf(jumlahdata())];

        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("SELECT t FROM Tampilkelurahan t where  t.kodeKelurahan like '%" + skodekelurahan + "%' "
                    + "AND t.namaKelurahan like '%" + snamakelurahan + "%' AND t.kodeKecamatan like '%" + skodekecamatan + "%'");
            q.setFirstResult(spage);
            q.setMaxResults(srow);
            List datalogin = q.list();
            int i = 0;
            for (Iterator iter = datalogin.iterator(); iter.hasNext();) {
                Tampilkelurahan element = (Tampilkelurahan) iter.next();
                kodekelurahan[i] = element.getKodeKelurahan();
                kodekecamatan[i] = element.getNamaKecamatan();
                namakelurahan[i] = element.getNamaKelurahan();
                //System.out.println("wow" + i);
                i++;
            }
            kelurahan2.setKodekelurahan(kodekelurahan);
            kelurahan2.setKodekecamatan(kodekecamatan);
            kelurahan2.setNamakelurahan(namakelurahan);
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada tampil semua data kelurahan \n" + e);
        }
    }
        
    public String tampilIDakhir() {
        String idkelurahan = "";
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("from TbKelurahan order by kodeKelurahan  DESC");
            query.setMaxResults(1);
            TbKelurahan tbKelurahan = (TbKelurahan) query.uniqueResult();
            idkelurahan = tbKelurahan.getKodeKelurahan();
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada tampil semua data login \n" + e);
        }
        return idkelurahan;
    }

    public String tampilIDselanjutnya() {
        String idkelurahan = "";
        try {
            idkelurahan = tampilIDakhir();
            if (!(idkelurahan.equals("")) || !(idkelurahan.equals(null))) {
                idkelurahan = idkelurahan.substring(2, 5);
                int hasil = Integer.parseInt(idkelurahan) + 1;
                idkelurahan = String.valueOf(hasil);
                if (idkelurahan.length() == 3) {
                    idkelurahan = "KL" + idkelurahan;
                } else if (idkelurahan.length() == 2) {
                    idkelurahan = "KL0" + idkelurahan;
                } else if (idkelurahan.length() == 1) {
                    idkelurahan = "KL00" + idkelurahan;
                }
            } else {
                idkelurahan = "KL001";
            }
        } catch (Exception e) {
            idkelurahan = "KL001";
        }
        return idkelurahan;
    }
    
   public String namaKelurahan(String kodekelurahan){
        String namakelurahan = "0";
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            List datalogin = session.createQuery("SELECT t.namaKelurahan FROM TbKelurahan t where t.kodeKelurahan= '" + kodekelurahan + "'").list();
            namakelurahan = datalogin.toString().replace("[", "");
            namakelurahan = namakelurahan.replace("]", "");
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada tampil namakelurahan \n" + e);
        }
        return namakelurahan;
    }
   
   public String namaKecamatan(String kodekelurahan){
        String namakelurahan = "0";
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            List datalogin = session.createQuery("SELECT t.kodeKecamatan FROM TbKelurahan t where t.kodeKelurahan= '" + kodekelurahan + "'").list();
            namakelurahan = datalogin.toString().replace("[", "");
            namakelurahan = namakelurahan.replace("]", "");
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada tampil namakecamatan \n" + e);
        }
        return namakelurahan;
    }
   
   
    /*  public static void main(String args[]){
     ControllerKelurahan ck = new ControllerKelurahan();
     Kelurahan k = new Kelurahan();
     //System.out.println(ck.jumlahdata());
     //TbKecamatan kecamatan = new TbKecamatan();
     //kecamatan.setKodeKecamatan("asdcd");
     //kecamatan.setNamaKecamatan("123123123");
     //System.out.println(ck.insert(kecamatan));
     //System.out.println(ck.delete("asdcd"));
     //        ck.tampilsemuadata();
        
     }*/
}
