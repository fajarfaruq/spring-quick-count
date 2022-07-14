/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.SessionFactoryUtil;
import Model.TbLogintps;
import View.UserTPS;
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
public class ControllerLoginTPS {
    /* hak akses 4 = TPS*/
    int kodetps;

    public int getKodetps() {
        return kodetps;
    }

    public void setKodetps(int kodetps) {
        this.kodetps = kodetps;
    }
    
    public String cekLoginTPS(String Username, String Password) {
        String kodeakses = "0";
        Transaction tx = null;
        EnkripsiData en = new EnkripsiData();
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            Password = en.MD5(Password);
            List datalogin = session.createQuery("SELECT t FROM TbLogintps t Where "
                    + "t.username='" + Username + "' AND t.password='" + Password + "'").list();
            for (Iterator iter = datalogin.iterator(); iter.hasNext();) {
                TbLogintps element = (TbLogintps) iter.next();
                kodeakses = element.getKodeHakAkses();
                setKodetps(element.getKodeTPS());
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

    public String insertusertps(TbLogintps tbLogintps) {
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        String response = "0";
        EnkripsiData en = new EnkripsiData();
        try {
            tx = session.beginTransaction();
            tbLogintps.setKodeHakAkses("4");
            tbLogintps.setPassword(en.MD5(tbLogintps.getPassword()));
            session.saveOrUpdate(tbLogintps);
            tx.commit();
            response = "1";
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada insert data login tps.... !!\n" + e);
            response = "error";
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ControllerLoginAdmin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ControllerLoginAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }

        return response;
    }
    
    public String updateusertps(TbLogintps tbLogintps) {
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        String response = "0";
        EnkripsiData en = new EnkripsiData();
        try {
            tx = session.beginTransaction();
            tbLogintps.setKodeHakAkses("4");
            tbLogintps.setPassword(en.MD5(tbLogintps.getPassword()));
            session.saveOrUpdate(tbLogintps);
            tx.commit();
            response = "1";
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada update data login tps.... !!\n" + e);
            response = "error";
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ControllerLoginAdmin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ControllerLoginAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }    
    
   public String deleteusertps(String username) {
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        String response = "0";
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("delete from TbLogintps p Where p.username ='" + username + "' AND "
                    + "p.kodeHakAkses ='4'");
            query.executeUpdate();
            tx.commit();
            response = "1";
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada hapus data login tps.... !! \n" + e);
            response = "error";
        }
        return response;
    }
    public String jumlahusertps() {
        String jml = "0";
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            List datalogin = session.createQuery("select count(distinct t.username) from TbLogintps as t ").list();;
            jml = datalogin.toString().replace("[", "");
            jml = jml.replace("]", "");
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada jumlah user data login TPS \n" + e);
        }
        return jml;
    }

   public void tampilsemuadatausertps(UserTPS.userTbTPS2 tbTPS2, String Username, int spage, int srow) {
        String data = "";
        Transaction tx = null;
        String[] username = new String[Integer.valueOf(jumlahusertps())];
        String[] password = new String[Integer.valueOf(jumlahusertps())];
        int[] kodetps = new int[Integer.valueOf(jumlahusertps())];
        
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("SELECT t FROM TbLogintps t where t.username  like '%" + Username + "%'");
            q.setFirstResult(spage);
            q.setMaxResults(srow);
            List datalogin = q.list();
            int i = 0;
            for (Iterator iter = datalogin.iterator(); iter.hasNext();) {
                TbLogintps element = (TbLogintps) iter.next();
                username[i] = element.getUsername();
                System.out.println(element.getUsername());

                password[i] = element.getPassword();
                System.out.println(element.getPassword());
                
                kodetps[i] = element.getKodeTPS();
                i++;
            }
            tbTPS2.setUsername(username);
            tbTPS2.setPassword(password);
            tbTPS2.setKodetps(kodetps);
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada tampil semua data login TPS \n" + e);
        }
    }   

}
