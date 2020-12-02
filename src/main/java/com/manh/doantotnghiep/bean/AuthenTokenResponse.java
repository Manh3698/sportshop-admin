package com.manh.doantotnghiep.bean;

public class AuthenTokenResponse {

    private String accessToken;

    private String tokenType;

    public AuthenTokenResponse() {
        super();
    }

    public AuthenTokenResponse(final String accessToken) {
        this.accessToken = accessToken;
        this.tokenType = "Bearer";
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    
}
