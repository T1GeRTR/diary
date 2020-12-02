package com.t1gerok.diary.hibernate.daoimpl;

import com.t1gerok.diary.hibernate.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class BaseDaoImpl {
    protected  Session session;
    protected Session getSession(){
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        return session;
    }

    protected Transaction getTransaction(){
        return session.getTransaction();
    }
}
