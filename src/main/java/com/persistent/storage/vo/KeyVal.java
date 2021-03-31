package com.persistent.storage.vo;

import java.io.Serializable;

public class KeyVal implements Serializable {

    private String key;
    private Object value;

    public KeyVal() {
    }

    public KeyVal(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
