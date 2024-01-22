package com.ggamangso.gptutorproject.constant;

import lombok.Getter;

public enum MessageType {
    SYSTEM("system"),
    ASSISTANT("assistant"),
    USER("user");

      @Getter
    private final String value;

    MessageType( String value) {
        this.value = value;
    }


}
