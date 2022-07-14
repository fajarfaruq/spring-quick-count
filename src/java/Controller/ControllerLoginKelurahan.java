/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.SessionFactoryUtil;
import Model.TbLoginkelurahan;
import View.UserKelurahan;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Administrator
 */
public class ControllerLoginKelurahan {
    String kodekelurahan ;

    public String getKodekelurahan() {
        return kodekelurahan;
    }

    public void setKodekelurahan(String kodekelurahan) {
        this.kodekelurahan = kodekelurahan;
    }
    
    /* hak akses 3 = Kelurahan */
    public String cekLoginKelurahan(String Username, String Password) {
        String kodeakses = "0";
        Transaction tx = null;
        EnkripsiData en = new EnkripsiData();
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            Password = en.MD5(Password);
            List datalogin = session.createQuery("SELECT t FROM TbLoginkelurahan t Where "
                    + "t.username='" + Username + "' AND t.password='" + Password + "'").list();
            for (Iterator iter = datalogin.iterator(); iter.hasNext();) {
                TbLoginkelurahan element = (TbLoginkelurahan) iter.next();
                kodeakses = element.getKodeHakAkses();
                setKodekelurahan(element.getKodeKelurahan());
            }
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada cek data Login Help Desk \n" + e);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ControllerLoginAdmin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ControllerLoginAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kodeakses;
    }
    
    public String insertuserkelurahan(TbLoginkelurahan tbLoginkelurahan) {
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        String response = "0";
        EnkripsiData en = new EnkripsiData();
        try {
            tx = session.beginTransaction();
            tbLoginkelurahan.setKodeHakAkses("3");
            tbLoginkelurahan.setPassword(en.MD5(tbLoginkelurahan.getPassword()));
            session.saveOrUpdate(tbLoginkelurahan);
            tx.commit();
            response = "1";
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada insert data login kelurahan.... !!\n" + e);
            response = "error";
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ControllerLoginAdmin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ControllerLoginAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }

        return response;
    }
    public String updateuserkelurahan(TbLoginkelurahan tbLoginkelurahan) {
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        String response = "0";
        EnkripsiData en = new EnkripsiData();
        try {
            tx = session.beginTransaction();
            tbLoginkelurahan.setKodeHakAkses("3");
            tbLoginkelurahan.setPassword(en.MD5(tbLoginkelurahan.getPassword()));
            session.saveOrUpdate(tbLoginkelurahan);
            tx.commit();
            response = "1";
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada update data login kelurahan.... !!\n" + e);
            response = "error";
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ControllerLoginAdmin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ControllerLoginAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }
    
   public String deleteuserkelurahan(String username) {
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        String response = "0";
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("delete from TbLoginkelurahan where username ='" + username + "' AND "
                    + "kodeHakAkses ='3'");
            query.executeUpdate();
            tx.commit();
            response = "1";
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada hapus data login kelurahan.... !! \n" + e);
            response = "error";
        }
        return response;
    }
   
    public String jumlahuserkelurahan() {
        String jml = "0";
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            List datalogin = session.createQuery("select count(distinct t.username) from TbLoginkelurahan as t ").list();;
            jml = datalogin.toString().replace("[", "");
            jml = jml.replace("]", "");
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada jumlah user data login Kelurahan \n" + e);
        }
        return jml;
    }
     public void tampilsemuadatauserkelurahan(UserKelurahan.userTbKelurahan2 tbKelurahan2, String Username, int spage, int srow) {
        String data = "";
        Transaction tx = null;
        String[] username = new String[Integer.valueOf(jumlahuserkelurahan())];
        String[] password = new String[Integer.valueOf(jumlahuserkelurahan())];
        String[] kodekelurahan = new String[Integer.valueOf(jumlahuserkelurahan())];
        
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("SELECT t FROM TbLoginkelurahan t where t.username  like '%" + Username + "%'");
            q.setFirstResult(spage);
            q.setMaxResults(srow);
            List datalogin = q.list();
            int i = 0;
            for (Iterator iter = datalogin.iterator(); iter.hasNext();) {
                TbLoginkelurahan element = (TbLoginkelurahan) iter.next();
                username[i] = element.getUsername();
                System.out.println(element.getUsername());

                password[i] = element.getPassword();
                System.out.println(element.getPassword());
                
                kodekelurahan[i] = element.getKodeKelurahan();
                i++;
            }
            tbKelurahan2.setUsername(username);
            tbKelurahan2.setPassword(password);
            tbKelurahan2.setKodekelurahan(kodekelurahan);
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada tampil semua data login kelurahan \n" + e);
        }
    }       
}
