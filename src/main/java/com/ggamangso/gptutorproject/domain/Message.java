package com.ggamangso.gptutorproject.domain;
;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@ToString(callSuper = true)
@Entity
public class Message extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne(optional = false)
    @JoinColumn(name = "chat_id")
    private Chat chat; // 유저 정보(ID)

    @Setter @Column(nullable = false) private String role;
    @Setter @Column(nullable = false) private String content;
    @Setter private String corrected_content;
    @Setter @Column(nullable = false) private Boolean is_bookmarked;
    @Setter private String message_memo;



    protected Message(){

    }
    private Message(Chat chat, String role, String content, String corrected_content, Boolean is_bookmarked, String message_memo) {
        this.chat = chat;
        this.role = role;
        this.content = content;
        this.corrected_content = corrected_content;
        this.is_bookmarked = is_bookmarked;
        this.message_memo = message_memo;
    }

    public static Message of(Chat chat, String role, String content, String corrected_content, Boolean is_bookmarked, String message_memo) {
        return new Message(chat, role, content, corrected_content, is_bookmarked, message_memo);
    }
}
