package com.ggamangso.gptutorproject.repository;

import com.ggamangso.gptutorproject.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface MessageRepository extends JpaRepository<Message,Long > {
    List<Message> findByChat_ChatId(Long chatId);


    void deleteByChat_ChatId(Long chatId);
}


