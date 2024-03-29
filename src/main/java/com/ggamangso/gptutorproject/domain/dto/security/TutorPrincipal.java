package com.ggamangso.gptutorproject.domain.dto.security;

import com.ggamangso.gptutorproject.constant.AuthorityType;
import com.ggamangso.gptutorproject.domain.dto.UserAccountDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public record TutorPrincipal(
        String username,
        String userPassword,
        String authority,
        Collection<? extends GrantedAuthority> authorities,
        String email,
        String nickname,
        String memo,
        Map<String, Object> oAuth2Attributes
) implements UserDetails, OAuth2User {

    public static TutorPrincipal of(String username, String userPassword, String authority, String email, String nickname, String memo){
        return TutorPrincipal.of(username, userPassword, authority, email, nickname, memo, Map.of());
    }

    public static TutorPrincipal of(String username, String userPassword, String authority, String email, String nickname, String memo, Map<String, Object> oAuth2Attributes) {
        // 지금은 인증만 하고 권한을 다루고 있지 않아서 임의로 세팅한다.
        Set<String> authorityTypes = Set.of(AuthorityType.USER.getValue(), AuthorityType.ADMIN.getValue());

        return new TutorPrincipal(
                username,
                userPassword,
                authority,
                authorityTypes.stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toUnmodifiableSet()),
                email,
                nickname,
                memo,
                oAuth2Attributes
        );
    }

    public static TutorPrincipal from(UserAccountDto dto) {
        return TutorPrincipal.of(
                dto.userId(),
                dto.userPassword(),
                dto.authority(),
                dto.email(),
                dto.nickname(),
                dto.memo()
        );
    }

    public UserAccountDto toDto() {
        return UserAccountDto.of(
                username,
                userPassword,
                authority,
                email,
                nickname,
                memo
        );
    }


    @Override public String getUsername() { return username; }
    @Override public String getPassword() { return userPassword; }
    @Override public Collection<? extends GrantedAuthority> getAuthorities() { return authorities; }

    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }

    @Override public Map<String, Object> getAttributes() { return oAuth2Attributes; }
    @Override public String getName() { return username; }



}