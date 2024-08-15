package com.api.measureconverter.utils.dto;

import java.util.Date;

import lombok.Data;

@Data
public class TokenDto {
    private String token;
    private Date expiresIn;

    private TokenDto(Builder builder) {
        this.token = builder.token;
        this.expiresIn = builder.expiresIn;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String token;
        private Date expiresIn;

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public Builder expiresIn(Date expiresIn) {
            this.expiresIn = expiresIn;
            return this;
        }

        public TokenDto build() {
            return new TokenDto(this);
        }
    }
}
