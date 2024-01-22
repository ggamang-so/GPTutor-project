package com.ggamangso.gptutorproject.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@ToString(callSuper = true)
@Entity
public class Chat extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatId;

    @Setter
    @ManyToOne(optional = false)
    @JoinColumn(name = "userId")
    private UserAccount userAccount; // 유저 정보(ID)

    @Setter private String firstMessage;

    @OrderBy("created_at ASC")
    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL)
    @ToString.Exclude // 순환참조에 의한 무한루프를 방지하기 위해 ToString 고리를 끊어줌
    private final Set<Message> messages = new LinkedHashSet<>();

    protected Chat(){}

    private Chat(Long chatId, UserAccount userAccount, String firstMessage) {
        this.chatId = chatId;
        this.userAccount = userAccount;
        this.firstMessage = firstMessage;
    }

    public static Chat of(Long chatId, UserAccount userAccount, String firstMessage) {
        return new Chat(chatId, userAccount, firstMessage);
    }

    public static Chat of(UserAccount userAccount, String firstMessage) {
        return Chat.of(null,userAccount, firstMessage);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chat chat = (Chat) o;
        return Objects.equals(chatId, chat.chatId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chatId);
    }

    public void addMessages(Collection<Message> messages) {
        this.getMessages().addAll(messages);
    }
}
