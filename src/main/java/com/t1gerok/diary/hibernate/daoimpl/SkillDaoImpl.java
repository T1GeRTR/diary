package com.t1gerok.diary.hibernate.daoimpl;

import com.t1gerok.diary.dao.SkillDao;
import com.t1gerok.diary.exception.DiaryException;
import com.t1gerok.diary.exception.ErrorCode;
import com.t1gerok.diary.model.Skill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SkillDaoImpl extends BaseDaoImpl implements SkillDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(SkillDaoImpl.class);

    @Override
    public Skill insert(Skill skill) throws DiaryException {
        LOGGER.debug("SkillDao insert");
        try {
            skill.setId((Integer) getSession().save(skill));
            getTransaction().commit();
        } catch (RuntimeException e) {
            getTransaction().rollback();
            throw new DiaryException(ErrorCode.CANT_INSERT_SKILL);
        }
        return skill;
    }

    @Override
    public boolean delete(int id) throws DiaryException {
        LOGGER.debug("SkillDao delete");
        try {
            Skill skill = (Skill) getSession().get(Skill.class, id);
            getTransaction().commit();
            getSession().delete(skill);
            getTransaction().commit();
        } catch (RuntimeException e) {
            getTransaction().rollback();
            throw new DiaryException(ErrorCode.CANT_DELETE_SKILL_BY_ID, id);
        }
        return true;
    }

    @Override
    public boolean edit(Skill skill) throws DiaryException {
        LOGGER.debug("SkillDao delete");
        try {
            getSession().update(skill);
            getTransaction().commit();
        } catch (RuntimeException e) {
            getTransaction().rollback();
            throw new DiaryException(ErrorCode.CANT_EDIT_SKILL, skill.getId());
        }
        return true;
    }

    @Override
    public Skill getById(int id) throws DiaryException {
        LOGGER.debug("SkillDao delete");
        try {
            Skill skill = (Skill) getSession().get(Skill.class, id);
            if (skill == null){
                throw new DiaryException(ErrorCode.CANT_FIND_SKILL_BY_ID, id);
            }
            return skill;
        } catch (RuntimeException e) {
            getTransaction().rollback();
            throw new DiaryException(ErrorCode.CANT_FIND_SKILL_BY_ID, id);
        } finally {
            getTransaction().commit();
        }
    }

    @Override
    public List getAll() throws DiaryException {
        LOGGER.debug("SkillDao delete");
        try {
            List<Skill> skills = getSession().createQuery("FROM Skill").list();
            if (skills.size() == 0){
                throw new DiaryException(ErrorCode.CANT_GET_ALL_SKILLS);
            }
            return skills;
        } catch (RuntimeException e) {
            getTransaction().rollback();
            throw new DiaryException(ErrorCode.CANT_GET_ALL_SKILLS);
        } finally {
            getTransaction().commit();
        }
    }
}
