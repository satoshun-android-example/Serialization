package com.github.satoshun.example;

import kotlinx.serialization.Serializable;

@Serializable
public class ResponseJava<T> {
    public T data;
}
