package com.ggamangso.gptutorproject.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;


@Getter
@ToString(callSuper = true)
@Table(indexes = {
        @Index(columnList = "userId", unique = true),
        @Index(columnList = "email", unique = true),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@Entity
public class UserAccount extends AuditingFields{

    @Id
    @Column(length = 50)
    private String user_id;

    @Setter @Column(nullable = false)private String user_password;
    @Setter @Column(nullable = false) private String user_role;
    @Setter @Column(length = 100) private String email;
    @Setter @Column(length = 100) private String nickname;
    @Setter @Column(length = 1000)private String memo;

    protected UserAccount() {
    }

    private UserAccount(String user_id, String user_password, String user_role, String email, String nickname, String memo) {
        this.user_id = user_id;
        this.user_password = user_password;
        this.user_role = user_role;
        this.email = email;
        this.nickname = nickname;
        this.memo = memo;
    }

    public static UserAccount of(String user_id, String user_password, String user_role, String email, String nickname, String memo){
        return new UserAccount(user_id, user_password, user_role, email, nickname, memo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAccount that = (UserAccount) o;
        return Objects.equals(user_id, that.user_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id);
    }
}
