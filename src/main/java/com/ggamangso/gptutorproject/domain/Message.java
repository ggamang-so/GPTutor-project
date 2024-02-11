package com.ggamangso.gptutorproject.domain;

import com.ggamangso.gptutorproject.constant.MessageType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.*;

@Getter
@ToString(callSuper = true)
@Entity
public class Message extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne(optional = false)
    @JoinColumn(name = "chatId")
    private Chat chat; // 유저 정보(ID)

    @Setter @Enumerated(EnumType.STRING) @Column(nullable = false) private MessageType role;
    @Setter @Column(nullable = false) private String content;
    @Setter @Column(name="corrected_Content") private String correctedContent;
    @Setter @Column(name="is_bookmarked",nullable = false) private Boolean isBookmarked;
    @Setter @Column(name="message_memo") private String messageMemo;



    protected Message(){

    }
    private Message(Chat chat, MessageType role, String content, String correctedContent, Boolean isBookmarked, String messageMemo) {
        this.chat = chat;
        this.role = role;
        this.content = content;
        this.correctedContent = correctedContent;
        this.isBookmarked = isBookmarked;
        this.messageMemo = messageMemo;
    }

    public static Message of(Chat chat, MessageType role, String content, String corrected_content, Boolean isBookmarked, String messageMemo) {
        return new Message(chat, role, content, corrected_content, isBookmarked, messageMemo);
    }
}
