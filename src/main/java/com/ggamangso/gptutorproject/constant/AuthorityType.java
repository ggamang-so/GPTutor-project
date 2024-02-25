package com.ggamangso.gptutorproject.constant;

import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

@Getter
public enum AuthorityType {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private final String value;

    AuthorityType(String value){
        this.value = value;
    }

    public static AuthorityType of(String value){
        return Arrays.stream(values())
                .filter(val -> Objects.equals(value, val.value))
                .findFirst()
                .orElse(null);
    }
}
