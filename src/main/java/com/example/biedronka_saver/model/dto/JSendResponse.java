package com.example.biedronka_saver.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class JSendResponse<T> {
    private String status;
    private String message;
    private T data;

    public static <T> JSendResponse<T> success(String message, T data) {
        return new JSendResponse<>("success", message, data);
    }

    public static <T> JSendResponse<T> fail(String message, T data) {
        return new JSendResponse<>("fail", message, data);
    }

    public static <T> JSendResponse<T> error(String message, T data) {
        return new JSendResponse<>("error", message, data);
    }
}
