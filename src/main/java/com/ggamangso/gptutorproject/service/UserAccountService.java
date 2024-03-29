package com.ggamangso.gptutorproject.service;

import com.ggamangso.gptutorproject.constant.AuthorityType;
import com.ggamangso.gptutorproject.domain.UserAccount;
import com.ggamangso.gptutorproject.domain.dto.UserAccountDto;
import com.ggamangso.gptutorproject.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public Optional<UserAccountDto> searchUser(String username) {
        return userAccountRepository.findById(username)
                .map(UserAccountDto::from);
    }

    public UserAccountDto saveUser(String username, String userPassword, String authority, String email, String nickname, String memo) {
        if(nickname.equals("")){
            return UserAccountDto.from(
                    userAccountRepository.save(UserAccount.of(username, passwordEncoder.encode(userPassword),AuthorityType.of(authority), email, username, memo))
            );
        }
        return UserAccountDto.from(
                userAccountRepository.save(UserAccount.of(username, passwordEncoder.encode(userPassword),AuthorityType.of(authority), email, nickname, memo))
        );
    }

}