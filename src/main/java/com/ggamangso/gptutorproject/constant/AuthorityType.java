package com.ggamangso.gptutorproject.constant;

import lombok.Getter;

public enum AuthorityType {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    @Getter
    private final String value;

    AuthorityType(String value){
        this.value = value;
    }
}
