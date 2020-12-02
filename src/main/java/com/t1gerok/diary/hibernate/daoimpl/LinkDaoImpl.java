package com.t1gerok.diary.hibernate.daoimpl;

import com.t1gerok.diary.dao.LinkDao;
import com.t1gerok.diary.exception.DiaryException;
import com.t1gerok.diary.exception.ErrorCode;
import com.t1gerok.diary.model.Link;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LinkDaoImpl extends BaseDaoImpl implements LinkDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(LinkDaoImpl.class);

    @Override
    public Link insert(Link link) throws DiaryException {
        LOGGER.debug("DAO insert");
        try {
            link.setId((Integer) getSession().save(link));
            getTransaction().commit();
        } catch (RuntimeException e) {
            getTransaction().rollback();
            throw new DiaryException(ErrorCode.CANT_INSERT_LINK);
        }
        return link;
    }

    @Override
    public boolean delete(int id) throws DiaryException {
        LOGGER.debug("DAO delete");
        try {
            Link link = (Link) getSession().get(Link.class, id);
            getTransaction().commit();
            getSession().delete(link);
            getTransaction().commit();
        } catch (RuntimeException e) {
            getTransaction().rollback();
            throw new DiaryException(ErrorCode.CANT_DELETE_LINK_BY_ID, id);
        }
        return true;
    }

    @Override
    public boolean edit(Link link) throws DiaryException {
        LOGGER.debug("DAO delete");
        try {
            getSession().saveOrUpdate(link);
            getTransaction().commit();
        } catch (RuntimeException e) {
            getTransaction().rollback();
            throw new DiaryException(ErrorCode.CANT_EDIT_LINK, link.getId());
        }
        return true;
    }

    @Override
    public Link getById(int id) throws DiaryException {
        LOGGER.debug("DAO delete");
        try {
            Link link = (Link) getSession().get(Link.class, id);
            if (link == null){
                throw new DiaryException(ErrorCode.CANT_FIND_LINK_BY_ID, id);
            }
            return link;
        } catch (RuntimeException e) {
            getTransaction().rollback();
            throw new DiaryException(ErrorCode.CANT_FIND_LINK_BY_ID, id);
        } finally {
            getTransaction().commit();
        }
    }

    @Override
    public List getAll() throws DiaryException {
        LOGGER.debug("DAO delete");
        try {
            List<Link> links = getSession().createQuery("FROM Link").list();
            if (links.size() == 0){
                throw new DiaryException(ErrorCode.CANT_GET_ALL_LINKS);
            }
            return links;
        } catch (RuntimeException e) {
            getTransaction().rollback();
            throw new DiaryException(ErrorCode.CANT_GET_ALL_LINKS);
        } finally {
            getTransaction().commit();
        }
    }
}
