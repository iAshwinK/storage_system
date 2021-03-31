package com.persistent.storage.service;

import com.persistent.storage.vo.KeyVal;

import java.util.List;

public interface SecondaryStorage {

    List<KeyVal> getAll();
    KeyVal getByKey(String key);
    void insert(String key,Object value);
    void deleteByKey(String key);
    void update(String key,Object value);

}
