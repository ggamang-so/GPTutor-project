package com.ggamangso.gptutorproject.constant;

import lombok.Getter;

public enum MessageType {
    SYSTEM("system"),
    ASSISTANT("assistant"),
    USER("user");

    @Getter
    private final String description;

    MessageType(String description){
        this.description = description;
    }
}
