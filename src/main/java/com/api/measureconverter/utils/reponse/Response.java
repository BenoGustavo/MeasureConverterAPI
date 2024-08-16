package com.api.measureconverter.utils.reponse;

import lombok.Data;

@Data
public class Response<T> {
    private int status;
    private String result;
    private ResponseError error;
    private T data;

    private Response(Builder<T> builder) {
        this.status = builder.status;
        this.result = builder.result;
        this.error = builder.error;
        this.data = builder.data;
    }

    public static class Builder<T> {
        private int status;
        private String result;
        private ResponseError error;
        private T data;

        public Builder<T> status(int status) {
            this.status = status;
            return this;
        }

        public Builder<T> result(String result) {
            this.result = result;
            return this;
        }

        public Builder<T> error(ResponseError error) {
            this.error = error;
            return this;
        }

        public Builder<T> error(int code, String message) {
            this.error = new ResponseError(code, message);
            return this;
        }

        public Builder<T> data(T data) {
            this.data = data;
            return this;
        }

        public Response<T> build() {
            return new Response<>(this);
        }
    }
}
