package com.liviu.l3final.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Category implements Serializable {
    private Long category_id;
    private String name;

    @Override
    public String toString() {
        return name;
    }
}
