package com.ifma.muzik.controller;

import java.util.ArrayList;
import java.util.List;

class Response<T> {

    private T data;
    private List<String> erros;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<String> getErros() {
        if (erros == null) {
            erros = new ArrayList<>();
        }
        return erros;
    }


}
