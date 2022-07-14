/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.SessionFactoryUtil;
import Model.TbKecamatan;
import View.Kecamatan;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author user
 */
public class ControllerKecamatan {

    public String insert(TbKecamatan tkec) {
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        String response = "0";
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(tkec);
            tx.commit();
            response = "1";
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada insert data login .... !!\n" + e);
            response = "error";
        }

        return response;
    }

    public String update(TbKecamatan tkec) {
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        String response = "0";
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(tkec);
            tx.commit();
            response = "1";
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada update data login .... !!\n" + e);
            response = "error";
        }
        return response;
    }

    public String delete(String KodeKecamatan) {
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        String response = "0";
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("delete from TbKecamatan where Kode_Kecamatan='" + KodeKecamatan + "'");
            query.executeUpdate();
            tx.commit();
            response = "1";
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada hapus data login .... !! \n" + e);
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
            List datalogin = session.createQuery("select count(distinct t.kodeKecamatan) from TbKecamatan as t").list();;
            jml = datalogin.toString().replace("[", "");
            jml = jml.replace("]", "");
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada tampil semua data login \n" + e);
        }
        return jml;

    }

    public void tampilsemuadata(Kecamatan.listTbKecamatan3 kecamatan3, String skodekecamatan, String snamakecamatan, int spage, int srow) {
        String data = "";
        Transaction tx = null;
        String[] kodekecamatan = new String[Integer.valueOf(jumlahdata())];
        String[] namakecamatan = new String[Integer.valueOf(jumlahdata())];
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("SELECT t FROM TbKecamatan t where t.kodeKecamatan like '%" + skodekecamatan + "%' "
                    + "AND t.namaKecamatan like '%" + snamakecamatan + "%'  ");
            q.setFirstResult(spage);
            q.setMaxResults(srow);
            List datalogin = q.list();
            int i = 0;
            for (Iterator iter = datalogin.iterator(); iter.hasNext();) {
                TbKecamatan element = (TbKecamatan) iter.next();
                kodekecamatan[i] = element.getKodeKecamatan();
                namakecamatan[i] = element.getNamaKecamatan();
                //System.out.println("wow" + i);
                i++;
            }
            kecamatan3.setKodekecamatan(kodekecamatan);
            kecamatan3.setNamakecamatan(namakecamatan);

            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada tampil semua data login \n" + e);
        }
    }

    public String tampilIDakhir() {
        String idkecamatan = "";
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("from TbKecamatan order by kodeKecamatan  DESC");
            query.setMaxResults(1);
            TbKecamatan tbKecamatan = (TbKecamatan) query.uniqueResult();
            idkecamatan = tbKecamatan.getKodeKecamatan();
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada tampil semua data login \n" + e);
        }
        return idkecamatan;
    }

    public String tampilIDselanjutnya() {
        String idkecamatan = "";
        try {
            idkecamatan = tampilIDakhir();
            if (!(idkecamatan.equals("")) || !(idkecamatan.equals(null))) {
                idkecamatan = idkecamatan.substring(2, 5);
                int hasil = Integer.parseInt(idkecamatan) + 1;
                idkecamatan = String.valueOf(hasil);
                if (idkecamatan.length() == 3) {
                    idkecamatan = "KC" + idkecamatan;
                } else if (idkecamatan.length() == 2) {
                    idkecamatan = "KC0" + idkecamatan;
                } else if (idkecamatan.length() == 1) {
                    idkecamatan = "KC00" + idkecamatan;
                }
            } else {
                idkecamatan = "KC001";
            }
        } catch (Exception e) {
            idkecamatan = "KC001";
        }
        return idkecamatan;
    }

    public String namaKecamatan(String kodekecamatan){
        String namakecamatan = "0";
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            List datalogin = session.createQuery("SELECT t.namaKecamatan FROM TbKecamatan t where t.kodeKecamatan = '" + kodekecamatan + "'").list();
            namakecamatan = datalogin.toString().replace("[", "");
            namakecamatan = namakecamatan.replace("]", "");
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println("Terjadi error pada tampil semua data login \n" + e);
        }
        return namakecamatan;
    }
    /* public static void main(String args[]){
     ControllerKecamatan ck = new ControllerKecamatan();
     //System.out.println(ck.jumlahdata());
     //TbKecamatan kecamatan = new TbKecamatan();
     //kecamatan.setKodeKecamatan("asdcd");
     //kecamatan.setNamaKecamatan("123123123");
     //System.out.println(ck.insert(kecamatan));
     //System.out.println(ck.delete("asdcd"));
     System.out.println(ck.tampilIDakhir());
     System.out.println(ck.tampilIDselanjutnya());
        
     }
     */
//    public static void main(String args[]){
//             ControllerKecamatan ck = new ControllerKecamatan();
//             System.out.println(ck.namaKecamatan("KC001"));
//    }
}
