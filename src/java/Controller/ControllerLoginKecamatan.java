/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.SessionFactoryUtil;
import Model.TbLoginkecamatan;
import View.Kecamatan;
import View.UserKecamatan;
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
public class ControllerLoginKecamatan {
    /* hak akses 2 = kecamatan */
    String kodekecamatan;

    public String getKodekecamatan() {
        return kodekecamatan;
    }

    public void setKodekecamatan(String kodekecamatan) {
        this.kodekecamatan = kodekecamatan;
    }
     public String cekLoginKecamatan(String Username, String Password) {
        String kodeakses = "0";
        Transaction tx = null;
        EnkripsiData en = new EnkripsiData();
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            Password = en.MD5(Password);
            List datalogin = session.createQuery("SELECT t FROM TbLoginkecamatan t Where "
                    + "t.username='" + Username + "' AND t.password='" + Password + "'").list();
            for (Iterator iter = datalogin.iterator(); iter.hasNext();) {
                TbLoginkecamatan element = (TbLoginkecamatan) iter.next();
                kodeakses = element.getKodeHakAkses();
                setKodekecamatan(element.getKodeKecamatan());
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

    //USER UNTUK Kecamatan ////////////////////////////////////////////////////////
    public String insertuserkecamatan(TbLoginkecamatan tbLoginkecamatan) {
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        String response = "0";
        EnkripsiData en = new EnkripsiData();
        try {
            tx = session.beginTransaction();
            tbLoginkecamatan.setKodeHakAkses("2");
            tbLoginkecamatan.setPassword(en.MD5(tbLoginkecamatan.getPassword()));
            session.saveOrUpdate(tbLoginkecamatan);
            tx.commit();
            response = "1";
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada insert data login kecamatan.... !!\n" + e);
            response = "error";
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ControllerLoginAdmin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ControllerLoginAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }

        return response;
    }

    public String updateuserkecamatan(TbLoginkecamatan tbLoginkecamatan) {
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        String response = "0";
        EnkripsiData en = new EnkripsiData();
        try {
            tx = session.beginTransaction();
            tbLoginkecamatan.setKodeHakAkses("2");
            tbLoginkecamatan.setPassword(en.MD5(tbLoginkecamatan.getPassword()));
            session.saveOrUpdate(tbLoginkecamatan);
            tx.commit();
            response = "1";
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada update data login kecamatan.... !!\n" + e);
            response = "error";
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ControllerLoginAdmin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ControllerLoginAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }

    public String deleteuserkecamatan(String username) {
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        String response = "0";
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("delete from TbLoginkecamatan where username ='" + username + "' AND "
                    + "kodeHakAkses ='2'");
            query.executeUpdate();
            tx.commit();
            response = "1";
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada hapus data login kecamatan.... !! \n" + e);
            response = "error";
        }
        return response;
    }

    public String jumlahuserkecamatan() {
        String jml = "0";
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            List datalogin = session.createQuery("select count(distinct t.username) from TbLoginkecamatan as t ").list();;
            jml = datalogin.toString().replace("[", "");
            jml = jml.replace("]", "");
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada jumlah user data login kecamatan\n" + e);
        }
        return jml;

    }
    public void tampilsemuadatauserkecamatan(UserKecamatan.userTbKecamatan2 tbKecamatan2, String Username, String namakecamatan, int spage, int srow) {
        String data = "";
        Transaction tx = null;
        String[] username = new String[Integer.valueOf(jumlahuserkecamatan())];
        String[] password = new String[Integer.valueOf(jumlahuserkecamatan())];
        String[] kodekecamatan = new String[Integer.valueOf(jumlahuserkecamatan())];

        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("SELECT t FROM TbLoginkecamatan t where t.username  like '%" + Username + "%' AND "
                    + "t.kodeKecamatan like '%" + namakecamatan + "%'");
            q.setFirstResult(spage);
            q.setMaxResults(srow);
            List datalogin = q.list();
            int i = 0;
            for (Iterator iter = datalogin.iterator(); iter.hasNext();) {
                TbLoginkecamatan element = (TbLoginkecamatan) iter.next();
                username[i] = element.getUsername();
                System.out.println(element.getUsername());

                password[i] = element.getPassword();
                System.out.println(element.getPassword());

                kodekecamatan[i] = element.getKodeKecamatan();
                System.out.println(element.getKodeKecamatan());

                i++;
            }
            tbKecamatan2.setUsername(username);
            tbKecamatan2.setPassword(password);
            tbKecamatan2.setKodekecamatan(kodekecamatan);
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada tampil semua data login kecamatan \n" + e);
        }
    }
}
