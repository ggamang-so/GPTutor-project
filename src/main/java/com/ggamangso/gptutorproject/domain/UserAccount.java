package com.ggamangso.gptutorproject.domain;


import com.ggamangso.gptutorproject.constant.AuthorityType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.*;
import java.util.Objects;


@Getter
@ToString(callSuper = true)
@Entity
public class UserAccount extends AuditingFields{

    @Id
    @Column(length = 50)
    private String userId;

    @Setter @Column(nullable = false)private String userPassword;
    @Setter @Enumerated(EnumType.STRING) @Column(nullable = false) private AuthorityType authority;
    @Setter @Column(length = 100) private String email;
    @Setter @Column(length = 100) private String nickname;
    @Setter @Column(length = 1000)private String memo;

    protected UserAccount() {
    }

    private UserAccount(String userId, String userPassword, AuthorityType authority, String email, String nickname, String memo) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.authority = authority;
        this.email = email;
        this.nickname = nickname;
        this.memo = memo;
    }

    public static UserAccount of(String userId, String userPassword, AuthorityType authority, String email, String nickname, String memo){
        return new UserAccount(userId, userPassword, authority, email, nickname, memo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAccount that = (UserAccount) o;
        return Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}
