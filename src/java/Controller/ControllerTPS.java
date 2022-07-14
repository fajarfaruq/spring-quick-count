/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.SessionFactoryUtil;
import Model.Tampiltps;
import Model.TbTps;
import View.TPS;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Administrator
 */

public class ControllerTPS {
   public String insert(TbTps tbTps) {
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        String response = "0";
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(tbTps);
            tx.commit();
            response = "1";
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada insert data tps.... !!\n" + e);
            response = "error";
        }
        
        return response;
    }

    public String update(TbTps tbTps) {
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        String response = "0";        
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(tbTps);
            tx.commit();
            response="1";
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada update data tps .... !!\n" + e);
            response = "error";
        }
        return response;
    }

    public String delete(String KodeTPS) {
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        String response = "0";
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("delete from TbTps where kodeTPS='" + KodeTPS + "'");
            query.executeUpdate();
            tx.commit();
            response = "1";
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada hapus data TPS .... !! \n" + e);
            response= "error";
        }
        return response;
    }
    public String jumlahdata(){
        String jml = "0" ;
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            List datalogin = session.createQuery("select count(distinct t.kodeTPS) from TbTps as t").list();;
            jml = datalogin.toString().replace("[", "");
            jml= jml.replace("]", "");
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada tampil semua data TPS \n" + e);
        }
        return jml;
        
    }
    public String jumlahdatacombo(String skodekelurahan){
        String jml = "0" ;
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            List datalogin = session.createQuery("select count(distinct t.kodeTPS) from TbTps as t where t.kodeKelurahan = '" + skodekelurahan + "'").list();;
            jml = datalogin.toString().replace("[", "");
            jml= jml.replace("]", "");
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada tampil semua data TPS \n" + e);
        }
        return jml;
        
    }
  public void tampilsemuadata(TPS.listTbTPS2 tbTPS2,String skodetps ,String skodekelurahan,int spage , int srow) {
        String data="";
        Transaction tx = null;
        int[] kodetps = new int[Integer.valueOf(jumlahdata())];
        String[] kodekelurahan = new String[Integer.valueOf(jumlahdata())];
        int[] nomortps = new int[Integer.valueOf(jumlahdata())];
        int[] jmldpt = new int[Integer.valueOf(jumlahdata())];
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("SELECT t FROM TbTps t where t.kodeTPS "
                    + " like '%" + skodetps + "%' "
                    + "AND t.kodeKelurahan like '%" + skodekelurahan + "%'");
            q.setFirstResult(spage);
            q.setMaxResults(srow);
            List datalogin = q.list();
            int i =0;
            for (Iterator iter = datalogin.iterator(); iter.hasNext();) {
                TbTps element = (TbTps) iter.next();
                kodetps[i] = element.getKodeTPS();
                kodekelurahan[i] = element.getKodeKelurahan();
                nomortps[i] = element.getNomorTPS();
                jmldpt[i] = element.getJumlahDPT();
                //System.out.println("wow" + i);
                i++;
            }
            /*tbTPS2.setKodetps(kodetps);
            tbTPS2.setKodekelurahan(kodekelurahan);
            tbTPS2.setNomortps(nomortps);
            tbTPS2.setJmldpt(jmldpt);*/
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada tampil semua data kelurahan \n" + e);
        }
    }    
   public void tampilviewtps(TPS.listTbTPS2 tbTPS2,String skodetps ,String skodekecamatan ,String skodekelurahan,int spage , int srow) {
        String data="";
        Transaction tx = null;
        int[] kodetps = new int[Integer.valueOf(jumlahdata())];
        String[] kodekecamatan = new String[Integer.valueOf(jumlahdata())];
        String[] namakecamatan = new String[Integer.valueOf(jumlahdata())];
        String[] kodekelurahan = new String[Integer.valueOf(jumlahdata())];
        String[] namakelurahan = new String[Integer.valueOf(jumlahdata())];
        int[] nomortps = new int[Integer.valueOf(jumlahdata())];
        int[] jmldpt = new int[Integer.valueOf(jumlahdata())];
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("SELECT t FROM Tampiltps t where t.kodeTPS "
                    + " like '%" + skodetps + "%' "
                    + "AND t.kodeKecamatan like '%" + skodekecamatan + "%' "
                    + "AND t.kodeKelurahan like '%" + skodekelurahan + "%' ORDER BY t.kodeKecamatan,t.nomorTPS ASC");
            q.setFirstResult(spage);
            q.setMaxResults(srow);
            List datalogin = q.list();
            int i =0;
            for (Iterator iter = datalogin.iterator(); iter.hasNext();) {
                Tampiltps element = (Tampiltps) iter.next();
                kodetps[i] = element.getKodeTPS();
                kodekecamatan[i] = element.getKodeKecamatan();
                namakecamatan[i] = element.getNamaKecamatan();
                kodekelurahan[i] = element.getKodeKelurahan();
                namakelurahan[i] = element.getNamaKelurahan();
                nomortps[i] = element.getNomorTPS();
                jmldpt[i] = element.getJumlahDPT();
                //System.out.println("wow" + i);
                i++;
            }
            tbTPS2.setKodetps(kodetps);
            tbTPS2.setKodekecamatan(kodekecamatan);
            tbTPS2.setNamakecamatan(namakecamatan);
            tbTPS2.setKodekelurahan(kodekelurahan);
            tbTPS2.setNamakelurahan(namakelurahan);
            tbTPS2.setNomortps(nomortps);
            tbTPS2.setJmldpt(jmldpt);
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada tampil semua data kelurahan \n" + e);
        }
    }    
   
      public void tampilviewtpscombo(TPS.listTbTPS2 tbTPS2,String skodekelurahan) {
        String data="";
        Transaction tx = null;
        int[] kodetps = new int[Integer.valueOf(jumlahdata())];
        String[] kodekecamatan = new String[Integer.valueOf(jumlahdata())];
        String[] namakecamatan = new String[Integer.valueOf(jumlahdata())];
        String[] kodekelurahan = new String[Integer.valueOf(jumlahdata())];
        String[] namakelurahan = new String[Integer.valueOf(jumlahdata())];
        int[] nomortps = new int[Integer.valueOf(jumlahdata())];
        int[] jmldpt = new int[Integer.valueOf(jumlahdata())];
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("SELECT t FROM Tampiltps t where t.kodeKelurahan = '" + skodekelurahan + "'");
            List datalogin = q.list();
            int i =0;
            for (Iterator iter = datalogin.iterator(); iter.hasNext();) {
                Tampiltps element = (Tampiltps) iter.next();
                kodetps[i] = element.getKodeTPS();
                kodekecamatan[i] = element.getKodeKecamatan();
                namakecamatan[i] = element.getNamaKecamatan();
                kodekelurahan[i] = element.getKodeKelurahan();
                namakelurahan[i] = element.getNamaKelurahan();
                nomortps[i] = element.getNomorTPS();
                jmldpt[i] = element.getJumlahDPT();
                //System.out.println("wow" + i);
                i++;
            }
            tbTPS2.setKodetps(kodetps);
            tbTPS2.setKodekecamatan(kodekecamatan);
            tbTPS2.setNamakecamatan(namakecamatan);
            tbTPS2.setKodekelurahan(kodekelurahan);
            tbTPS2.setNamakelurahan(namakelurahan);
            tbTPS2.setNomortps(nomortps);
            tbTPS2.setJmldpt(jmldpt);
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada tampil semua data kelurahan \n" + e);
        }
    } 
    public String kodeKelurahan(int kodetps){
        String namakelurahan = "0";
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            List datalogin = session.createQuery("SELECT t.kodeKelurahan FROM TbTps t where t.kodeTPS= " + kodetps + "").list();
            namakelurahan = datalogin.toString().replace("[", "");
            namakelurahan = namakelurahan.replace("]", "");
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada tampil kodekelurahan \n" + e);
        }
        return namakelurahan;
    }
    public String getJmlDPT(int kodetps){
        String jmldpt = "0";
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            List datalogin = session.createQuery("SELECT t.jumlahDPT FROM TbTps t where t.kodeTPS= " + kodetps + "").list();
            jmldpt = datalogin.toString().replace("[", "");
            jmldpt = jmldpt.replace("]", "");
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada tampil kodekelurahan \n" + e);
        }
        return jmldpt;
    }
    public String nomorTPS(int kodetps){
        String namakelurahan = "0";
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            List datalogin = session.createQuery("SELECT t.nomorTPS FROM TbTps t where t.kodeTPS= " + kodetps + "").list();
            namakelurahan = datalogin.toString().replace("[", "");
            namakelurahan = namakelurahan.replace("]", "");
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada tampil nomortps \n" + e);
        }
        return namakelurahan;
    }
}
