package com.ggamangso.gptutorproject.constant;

import lombok.Getter;

@Getter
public enum AuthorityType {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private final String value;

    AuthorityType(String value){
        this.value = value;
    }
}
