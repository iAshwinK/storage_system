package com.persistent.storage.service;

import com.persistent.storage.vo.KeyVal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileStorageImpl implements SecondaryStorage {
    @Override
    public List<KeyVal> getAll() {
        //retrieve all data from as in given order
        return null;
    }

    @Override
    public KeyVal getByKey(String key) {
        //get first matching record from the bottom of file
        return null;
    }

    public void update(String key,Object value){
        insert(key, value);
    }

    @Override
    public void insert(String key, Object value) {
        //insert {key} {value} at EOF
    }

    @Override
    public void deleteByKey(String key) {
        insert(key,"NULL");
    }
}
