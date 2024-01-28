package com.ggamangso.gptutorproject.domain.dto;

import com.ggamangso.gptutorproject.constant.AuthorityType;
import com.ggamangso.gptutorproject.domain.UserAccount;

import java.time.LocalDateTime;

/**
 * DTO for {@link UserAccount}
 */
public record UserAccountDto(

        String userId,
        String userPassword,
        AuthorityType authority,
        String email,
        String nickname,
        String memo,
        LocalDateTime created_at,
        LocalDateTime updated_at
) {

    public static UserAccountDto of(String userId, String userPassword, AuthorityType authority, String email, String nickname, String memo, LocalDateTime created_at, LocalDateTime updated_at) {
        return new UserAccountDto(userId, userPassword, authority, email, nickname, memo, created_at, updated_at);
    }

    public static UserAccountDto of(String userId, String userPassword, AuthorityType authority, String email, String nickname, String memo )
    {
        return new UserAccountDto(userId, userPassword, authority, email, nickname, memo, null,null);
    }

    public static UserAccountDto from(UserAccount entity){
        return UserAccountDto.of(
                entity.getUserId(),
                entity.getUserPassword(),
                entity.getAuthority(),
                entity.getEmail(),
                entity.getNickname(),
                entity.getMemo(),
                entity.getCreated_at(),
                entity.getUpdated_at()
        );
    }

    public UserAccount toEntity(){
        return UserAccount.of(
                userId,
                userPassword,
                authority,
                email,
                nickname,
                memo
        );
    }

}