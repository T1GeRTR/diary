package com.t1gerok.diary.hibernate.daoimpl;

import com.t1gerok.diary.dao.LinkTypeDao;
import com.t1gerok.diary.exception.DiaryException;
import com.t1gerok.diary.exception.ErrorCode;
import com.t1gerok.diary.model.LinkType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LinkTypeDaoImpl extends BaseDaoImpl implements LinkTypeDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(LinkTypeDaoImpl.class);

    @Override
    public LinkType insert(LinkType linkType) throws DiaryException {
        LOGGER.debug("LinkTypeDao insert");
        try {
            linkType.setId((Integer) getSession().save(linkType));
            getTransaction().commit();
        } catch (RuntimeException e) {
            getTransaction().rollback();
            throw new DiaryException(ErrorCode.CANT_INSERT_LINK_TYPE);
        }
        return linkType;
    }

    @Override
    public boolean delete(int id) throws DiaryException {
        LOGGER.debug("LinkTypeDao delete");
        try {
            LinkType linkType = (LinkType) getSession().get(LinkType.class, id);
            getTransaction().commit();
            getSession().delete(linkType);
            getTransaction().commit();
        } catch (RuntimeException e) {
            getTransaction().rollback();
            throw new DiaryException(ErrorCode.CANT_DELETE_LINK_TYPE_BY_ID, id);
        }
        return true;
    }

    @Override
    public boolean edit(LinkType linkType) throws DiaryException {
        LOGGER.debug("LinkTypeDao delete");
        try {
            getSession().update(linkType);
            getTransaction().commit();
        } catch (RuntimeException e) {
            getTransaction().rollback();
            throw new DiaryException(ErrorCode.CANT_EDIT_LINK_TYPE, linkType.getId());
        }
        return true;
    }

    @Override
    public LinkType getById(int id) throws DiaryException {
        LOGGER.debug("LinkTypeDao delete");
        try {
            LinkType linkType = (LinkType) getSession().get(LinkType.class, id);
            if (linkType == null){
                throw new DiaryException(ErrorCode.CANT_FIND_LINK_TYPE_BY_ID, id);
            }
            return linkType;
        } catch (RuntimeException e) {
            getTransaction().rollback();
            throw new DiaryException(ErrorCode.CANT_FIND_LINK_TYPE_BY_ID, id);
        } finally {
            getTransaction().commit();
        }
    }

    @Override
    public List getAll() throws DiaryException {
        LOGGER.debug("LinkTypeDao delete");
        try {
            List<LinkType> linkTypes = getSession().createQuery("FROM LinkType").list();
            if (linkTypes.size() == 0){
                throw new DiaryException(ErrorCode.CANT_GET_ALL_LINK_TYPES);
            }
            return linkTypes;
        } catch (RuntimeException e) {
            getTransaction().rollback();
            throw new DiaryException(ErrorCode.CANT_GET_ALL_LINK_TYPES);
        } finally {
            getTransaction().commit();
        }
    }
}
