/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.data.manager;

import cbvmp.admin.data.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author tanbir
 *
 *
 */
public abstract class ModelManager {

    protected Object getEntity(Class clazz, Long id) {

        Session session = HibernateUtil.getSession("CBVMP_MASTER");
        session.beginTransaction();
        Object obj = null;
        obj = session.get(clazz, id);
        session.getTransaction().commit();
        return obj;
    }
    protected Object getEntity(Class clazz, Integer id) {

        Session session = HibernateUtil.getSession("CBVMP_MASTER");
        session.beginTransaction();
        Object obj = null;
        obj = session.get(clazz, id);
        session.getTransaction().commit();
        return obj;
    }

    protected String saveEntity(Object o) {
        Transaction tx = null;
        Session session = HibernateUtil.getSession("CBVMP_MASTER");
        try {
            tx = session.getTransaction();
            tx.begin();
            session.save(o);
            tx.commit();
            return null;
            
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return e.getMessage();
        }
//        finally {
//            session.close();
//        }
    }

    protected void updateEntity(Object o) {
        Transaction tx = null;
        Session session = HibernateUtil.getSession("CBVMP_MASTER");
        try {
            tx = session.getTransaction();
            tx.begin();
            session.update(o);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
//        finally {
//            session.close();
//        }
    }

    protected void saveOrUpdateEntity(Object o) {
        Transaction tx = null;
        Session session = HibernateUtil.getSession("CBVMP_MASTER");
        try {
            tx = session.getTransaction();
            tx.begin();
            session.saveOrUpdate(o);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
//        finally {
//            session.close();
//        }

    }

    protected void deleteEntity(Class clazz, Long id) {
        Transaction tx = null;
        Session session = HibernateUtil.getSession("CBVMP_MASTER");
        try {
            tx = session.getTransaction();
            tx.begin();
            session.createQuery("delete " + clazz.getName() + " where id=" + 13);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
//        finally {
//            session.close();
//        }

    }

    protected void deleteEntity(Object o) {
        Transaction tx = null;
        Session session = HibernateUtil.getSession("CBVMP_MASTER");
        try {
            tx = session.getTransaction();
            tx.begin();
            session.delete(o);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
//        finally {
//            session.close();
//        }
    }

    protected List listAll(Class clazz) {

        Session session = HibernateUtil.getSession("CBVMP_MASTER");
        List list = null;
        session.beginTransaction();
        list = session.createQuery("from " + clazz.getName() + " c order by c.id desc").list();
        session.getTransaction().commit();
        //logger.warn("Hibernate Close: closing session after listing all");
        return list;

    }
}
