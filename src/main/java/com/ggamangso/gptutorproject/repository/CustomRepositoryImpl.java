package com.ggamangso.gptutorproject.repository;

import com.ggamangso.gptutorproject.domain.Chat;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CustomRepositoryImpl implements CustomRepository{
    @PersistenceContext
    private EntityManager entityManager;

    public CustomRepositoryImpl(EntityManager entityManager){
        this.entityManager=entityManager;
    }

    @Override
    public Long saveAndReturnChatId(Chat entity) {
        entityManager.getTransaction().begin();
        try{
            entityManager.persist(entity);
            entityManager.flush();
            entityManager.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            entityManager.close();
        }

        return entity.getChatId();
    }
}
