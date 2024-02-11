package com.ggamangso.gptutorproject.repository;

import com.ggamangso.gptutorproject.domain.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryRestResource
public interface ChatRepository extends JpaRepository<Chat, Long>, CustomRepository {
    List<Chat> findByUserAccount_UserIdOrderByChatIdDesc(String userId);

    void deleteByChatIdAndUserAccount_UserId(Long chatId, String userId);

    Chat getReferenceByChatId(Long chatId);

    Chat findByChatId(Long chatId);

    Chat findByFirstMessage(String firstMessage);


}
