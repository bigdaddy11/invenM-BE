package com.main.invenmbe.dto;

public class LoginResponse {
    private String message;
    private String token;

    // 기본 생성자
    public LoginResponse() {}

    // 모든 필드를 포함하는 생성자
    public LoginResponse(String message, String token) {
        this.message = message;
        this.token = token;
    }

    // Getter 및 Setter
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

