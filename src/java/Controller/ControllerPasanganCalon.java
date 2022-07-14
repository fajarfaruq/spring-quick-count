/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.SessionFactoryUtil;
import Model.TbPasangancalon;
import View.PasanganCalon;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author user
 */
public class ControllerPasanganCalon {
    public String insert(TbPasangancalon tp) {
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        String response = "0";
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(tp);
            tx.commit();
            response = "1";
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada insert data pasangan calon .... !!\n" + e);
            response = "error";
        }
        
        return response;
    }

    public String update(TbPasangancalon tp) {
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        String response = "0";        
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(tp);
            tx.commit();
            response="1";
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada update data pasangan calon .... !!\n" + e);
            response = "error";
        }
        return response;
    }

    public String delete(String KodePasanganCalon) {
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        String response = "0";
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("delete from TbPasangancalon where kodePasangancalon=" + KodePasanganCalon + "");
            query.executeUpdate();
            tx.commit();
            response = "1";
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada hapus data Pasangagan calon .... !! \n" + e);
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
            List datalogin = session.createQuery("select count(distinct t.kodePasangancalon) from TbPasangancalon as t").list();;
            jml = datalogin.toString().replace("[", "");
            jml= jml.replace("]", "");
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada tampil semua data login \n" + e);
        }
        return jml;
    }
    
      public void tampilsemuadata(PasanganCalon.listTbPasanganCalon2 pasanganCalon2,int spage , int srow) {
        String data="";
        Transaction tx = null;
        String[] kodepasangancalon = new String[Integer.valueOf(jumlahdata())];
        String[] gubernur = new String[Integer.valueOf(jumlahdata())];
        String[] wakilgubernur = new String[Integer.valueOf(jumlahdata())];
        String[] gambar = new String[Integer.valueOf(jumlahdata())];
        
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("SELECT t FROM TbPasangancalon t ");
            q.setFirstResult(spage);
            q.setMaxResults(srow);
            List datalogin = q.list();
            int i =0;
            for (Iterator iter = datalogin.iterator(); iter.hasNext();) {
                TbPasangancalon element = (TbPasangancalon) iter.next(); 
                kodepasangancalon[i] = element.getKodePasangancalon();
                gubernur[i] = element.getGubernur();
                wakilgubernur[i] = element.getWakilGubernur();
                gambar[i] = element.getGambar();
                //System.out.println("wow" + i);
                i++;
            }
            pasanganCalon2.setKodepasangancalon(kodepasangancalon);
            pasanganCalon2.setGubernur(gubernur);
            pasanganCalon2.setWakilgubernur(wakilgubernur);
            pasanganCalon2.setGambar(gambar);
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada tampil semua data pasangancalon \n" + e);
        }
    }  
    
 
}
