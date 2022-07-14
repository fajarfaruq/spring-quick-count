/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.TbLoginadmin;

import Model.SessionFactoryUtil;
import View.UserAdmin;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Administrator
 */
public class ControllerLoginAdmin {

    /* hak akses 1 = administrator */
    public String cekLoginAdmin(String Username, String Password) {
        String kodeakses = "0";
        Transaction tx = null;
        EnkripsiData en = new EnkripsiData();
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            Password = en.MD5(Password);
            List datalogin = session.createQuery("SELECT t FROM TbLoginadmin t Where "
                    + "t.username='" + Username + "' AND t.password='" + Password + "'").list();
            for (Iterator iter = datalogin.iterator(); iter.hasNext();) {
                TbLoginadmin element = (TbLoginadmin) iter.next();
                kodeakses = element.getKodeHakAkses();
            }
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada tampil semua data login \n" + e);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ControllerLoginAdmin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ControllerLoginAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kodeakses;
    }

    //USER UNTUK ADMIN ////////////////////////////////////////////////////////
    public String insertuseradmin(TbLoginadmin tbLoginadmin) {
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        String response = "0";
        EnkripsiData en = new EnkripsiData();
        try {
            tx = session.beginTransaction();
            tbLoginadmin.setKodeHakAkses("1");
            tbLoginadmin.setPassword(en.MD5(tbLoginadmin.getPassword()));
            session.saveOrUpdate(tbLoginadmin);
            tx.commit();
            response = "1";
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada insert data login admin.... !!\n" + e);
            response = "error";
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ControllerLoginAdmin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ControllerLoginAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }

        return response;
    }

    public String updateuseradmin(TbLoginadmin tbLoginadmin) {
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        String response = "0";
        EnkripsiData en = new EnkripsiData();
        try {
            tx = session.beginTransaction();
            tbLoginadmin.setKodeHakAkses("1");
            tbLoginadmin.setPassword(en.MD5(tbLoginadmin.getPassword()));
            session.saveOrUpdate(tbLoginadmin);
            tx.commit();
            response = "1";
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada update data login admin.... !!\n" + e);
            response = "error";
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ControllerLoginAdmin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ControllerLoginAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }

    public String deleteuseradmin(String username) {
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        String response = "0";
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("delete from TbLoginadmin where username ='" + username + "' AND "
                    + "kodeHakAkses ='1'");
            query.executeUpdate();
            tx.commit();
            response = "1";
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada hapus data login admin.... !! \n" + e);
            response = "error";
        }
        return response;
    }

    public String jumlahuseradmin() {
        String jml = "0";
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            List datalogin = session.createQuery("select count(distinct t.username) from TbLoginadmin as t where t.kodeHakAkses ='1'").list();;
            jml = datalogin.toString().replace("[", "");
            jml = jml.replace("]", "");
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada jumlah user data login admin\n" + e);
        }
        return jml;

    }
    
    public void tampilsemuadatauseradmin(UserAdmin.userTbAdmin2 tbAdmin2, String Username, int spage, int srow) {
        String data = "";
        Transaction tx = null;
        String[] username = new String[Integer.valueOf(jumlahuseradmin())];
        String[] password = new String[Integer.valueOf(jumlahuseradmin())];

        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("SELECT t FROM TbLoginadmin t where t.username  like '%" + Username + "%'");
            q.setFirstResult(spage);
            q.setMaxResults(srow);
            List datalogin = q.list();
            int i = 0;
            for (Iterator iter = datalogin.iterator(); iter.hasNext();) {
                TbLoginadmin element = (TbLoginadmin) iter.next();
                username[i] = element.getUsername();
                System.out.println(element.getUsername());

                password[i] = element.getPassword();
                System.out.println(element.getPassword());

                i++;
            }
            tbAdmin2.setUsername(username);
            tbAdmin2.setPassword(password);
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada tampil semua data login kecamatan \n" + e);
        }
    }



    // Cek Hibernate 
    /*public static void main(String args[]) {
     ControllerLogin w = new ControllerLogin();
     //Cek Login
     //JOptionPane.showMessageDialog(null, w.cekLogin("test", "0"));
     //cek tampil semua data
     //JOptionPane.showMessageDialog(null, w.tampilsemuadata());        
        
     /*TbLogin tl = new TbLogin();
        
     tl.setUsername("a");
     tl.setPassword("bcbc");
     tl.setKodeHakAkses("0");
     tl.setKodeKecamatan("0");
     tl.setKodeKelurahan("0");
        
     // w.insert(tl);
     //w.update(tl);
     w.delete("a");*/
    //System.out.println(w.jumlahdata());
    //}
}
