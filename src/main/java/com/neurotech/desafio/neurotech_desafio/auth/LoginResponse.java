package com.neurotech.desafio.neurotech_desafio.auth;

public class LoginResponse {
  private String tokenType = "Bearer";
  private String accessToken;
  public LoginResponse(String accessToken){ this.accessToken = accessToken; }

    public String getTokenType() {
        return tokenType;
    }

    public String getAccessToken() {
        return accessToken;
    }
  
}