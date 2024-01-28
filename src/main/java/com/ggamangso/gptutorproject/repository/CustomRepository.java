package com.ggamangso.gptutorproject.repository;

import com.ggamangso.gptutorproject.domain.Chat;

public interface CustomRepository {
    Long saveAndReturnChatId(Chat entity);
}
