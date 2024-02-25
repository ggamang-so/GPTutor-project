package com.ggamangso.gptutorproject.repository;

import com.ggamangso.gptutorproject.domain.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RepositoryRestResource
public interface ChatRepository extends JpaRepository<Chat, Long>, CustomRepository {
    List<Chat> findByUserAccount_UserIdOrderByChatIdDesc(String userId);

    void deleteByChatIdAndUserAccount_UserId(Long chatId, String userId);

    Chat getReferenceByChatId(Long chatId);

    Optional<Chat> findByChatId(Long chatId);

    void deleteByChatId(Long chatId);
}
