package com.ggamangso.gptutorproject.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Getter
@ToString
@Entity
public class Chat extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chat_id;

    @Setter
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private UserAccount userAccount; // 유저 정보(ID)

    protected Chat(){

    }
    private Chat(Long chat_id, UserAccount userAccount) {
        this.chat_id = chat_id;
        this.userAccount = userAccount;
    }

    public static Chat of(Long chat_id, UserAccount userAccount) {
        return new Chat(chat_id, userAccount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chat chat = (Chat) o;
        return Objects.equals(chat_id, chat.chat_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chat_id);
    }
}