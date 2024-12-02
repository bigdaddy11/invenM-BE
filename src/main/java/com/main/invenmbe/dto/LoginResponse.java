package com.main.invenmbe.dto;

public class LoginResponse {
    private String message;
    private String token;
    private Long id;

    // 기본 생성자
    public LoginResponse() {}

    // 모든 필드를 포함하는 생성자
    public LoginResponse(String message, String token, Long id) {
        this.message = message;
        this.token = token;
        this.id = id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

