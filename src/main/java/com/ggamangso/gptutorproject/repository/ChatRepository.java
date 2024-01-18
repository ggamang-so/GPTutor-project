package com.ggamangso.gptutorproject.repository;

import com.ggamangso.gptutorproject.domain.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ChatRepository extends JpaRepository<Chat, Long> {
}
