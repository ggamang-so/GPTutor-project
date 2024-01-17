package com.ggamangso.gptutorproject.domain;

import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@ToString(callSuper = true)
@Table(indexes = {
        @Index(columnList = "userId", unique = true),
        @Index(columnList = "email", unique = true),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@Entity
public class Message extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne(optional = false)
    @JoinColumn(name = "chat_id")
    private Chat chat; // 유저 정보(ID)

    @Setter @Enumerated(EnumType.STRING) @Column(nullable = false) private String role;
    @Setter @Column(nullable = false) private String content;
    @Setter private String corrected_content;
    @Setter @Column(nullable = false, columnDefinition = "default 'false'") private Boolean is_bookmarked;
    @Setter private String message_meno;
}
