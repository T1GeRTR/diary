package com.t1gerok.diary.hibernate.utils;

import com.t1gerok.diary.model.Link;
import com.t1gerok.diary.model.LinkType;
import com.t1gerok.diary.model.Project;
import com.t1gerok.diary.model.Skill;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {
    private static final SessionFactory sessionFactory
            = configureSessionFactory();
    private static ServiceRegistry serviceRegistry;

    /**
     * Создание фабрики
     * @return {@link SessionFactory}
     * @throws HibernateException
     */
    private static SessionFactory configureSessionFactory()
            throws HibernateException {

        Configuration configuration = new Configuration().configure();
        serviceRegistry = new ServiceRegistryBuilder().applySettings(
                configuration.getProperties()).buildServiceRegistry();
        return configuration.addAnnotatedClass(LinkType.class).addAnnotatedClass(Link.class).addAnnotatedClass(Skill.class).addAnnotatedClass(Project.class).buildSessionFactory(serviceRegistry);
    }

    /**
     * Получить фабрику сессий
     * @return {@link SessionFactory}
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}