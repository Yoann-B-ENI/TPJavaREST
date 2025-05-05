package fr.eni.vioyo.RestLudotheque.rest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ApiResponse<T> {

    @NonNull
    private boolean success;
    @NonNull
    private String message;
    private HashMap<String, String> metadata;
    private T data;

}
