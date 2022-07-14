/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.SessionFactoryUtil;
import Model.TbLoginhelpdesk;
import View.UserHelpDesk;
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
 * @author user
 */
public class ControllerLoginHelpdesk {
    /* hak akses 5 = Helpdesk */
    public String cekLoginHelpDesk(String Username, String Password) {
        String kodeakses = "0";
        Transaction tx = null;
        EnkripsiData en = new EnkripsiData();
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            Password = en.MD5(Password);
            List datalogin = session.createQuery("SELECT t FROM TbLoginhelpdesk t Where "
                    + "t.username='" + Username + "' AND t.password='" + Password + "'").list();
            for (Iterator iter = datalogin.iterator(); iter.hasNext();) {
                TbLoginhelpdesk element = (TbLoginhelpdesk) iter.next();
                kodeakses = element.getKodeHakAkses();
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

    //USER UNTUK Help Desk ////////////////////////////////////////////////////////
    public String insertuserhelpdesk(TbLoginhelpdesk tbLoginhelpdesk) {
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        String response = "0";
        EnkripsiData en = new EnkripsiData();
        try {
            tx = session.beginTransaction();
            tbLoginhelpdesk.setKodeHakAkses("5");
            tbLoginhelpdesk.setPassword(en.MD5(tbLoginhelpdesk.getPassword()));
            session.saveOrUpdate(tbLoginhelpdesk);
            tx.commit();
            response = "1";
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada insert data login help desk.... !!\n" + e);
            response = "error";
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ControllerLoginAdmin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ControllerLoginAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }

        return response;
    }

    public String updateuserhelpdesk(TbLoginhelpdesk tbLoginhelpdesk) {
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        String response = "0";
        EnkripsiData en = new EnkripsiData();
        try {
            tx = session.beginTransaction();
            tbLoginhelpdesk.setKodeHakAkses("5");
            tbLoginhelpdesk.setPassword(en.MD5(tbLoginhelpdesk.getPassword()));
            session.saveOrUpdate(tbLoginhelpdesk);
            tx.commit();
            response = "1";
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada update data login help desk.... !!\n" + e);
            response = "error";
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ControllerLoginAdmin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ControllerLoginAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }

    public String deleteuserhelpdesk(String username) {
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        String response = "0";
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("delete from TbLoginhelpdesk where username ='" + username + "' AND "
                    + "kodeHakAkses ='5'");
            query.executeUpdate();
            tx.commit();
            response = "1";
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada hapus data login admin.... !! \n" + e);
            response = "error";
        }
        return response;
    }

    public String jumlahuserhelpdesk() {
        String jml = "0";
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            List datalogin = session.createQuery("select count(distinct t.username) from TbLoginhelpdesk as t ").list();;
            jml = datalogin.toString().replace("[", "");
            jml = jml.replace("]", "");
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada jumlah user data login Help Desk \n" + e);
        }
        return jml;

    }
    
    public void tampilsemuadatauserhelpdesk(UserHelpDesk.userTbHelpDesk2 tbHelpDesk2, String Username, int spage, int srow) {
        String data = "";
        Transaction tx = null;
        String[] username = new String[Integer.valueOf(jumlahuserhelpdesk())];
        String[] password = new String[Integer.valueOf(jumlahuserhelpdesk())];

        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("SELECT t FROM TbLoginhelpdesk t where t.username  like '%" + Username + "%'");
            q.setFirstResult(spage);
            q.setMaxResults(srow);
            List datalogin = q.list();
            int i = 0;
            for (Iterator iter = datalogin.iterator(); iter.hasNext();) {
                TbLoginhelpdesk element = (TbLoginhelpdesk) iter.next();
                username[i] = element.getUsername();
                System.out.println(element.getUsername());

                password[i] = element.getPassword();
                System.out.println(element.getPassword());

                i++;
            }
            tbHelpDesk2.setUsername(username);
            tbHelpDesk2.setPassword(password);
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada tampil semua data login helpdesk \n" + e);
        }
}    
}
