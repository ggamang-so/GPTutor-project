package com.ggamangso.gptutorproject.constant;

import lombok.Getter;

public enum UserType {
    USER("Role_User"),
    ADMIN("Role_Admin");

    @Getter
    private final String description;

    UserType(String description){
        this.description = description;
    }
}
