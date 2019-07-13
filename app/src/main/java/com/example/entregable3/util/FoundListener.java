package com.example.entregable3.util;

public interface FoundListener<T> {
    void onFound(T result);
    void onNotFound();
    void onCancelled();
}
