package com.ggamangso.gptutorproject.constant;

import lombok.Getter;

public enum AuthorityType {
    USER("Role_User"),
    ADMIN("Role_Admin");

    @Getter
    private final String description;

    AuthorityType(String description){
        this.description = description;
    }
}
