package com.persistent.storage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MapServiceImpl implements PrimaryStorage {

    @Autowired
    private PrimaryMap primaryMap;

    @Autowired
    private SecondaryStorage secondaryStorage;

    //create, read, update and delete a key-value pair
    //get delete insert update

    public Object getEntry(String key) {
        Object result = primaryMap.keyValueMap.get(key);
        return result;
    }

    public boolean deleteEntry(String key) {
        if(primaryMap.keyValueMap.containsKey(key)) {
            primaryMap.keyValueMap.remove(key);
            secondaryStorage.deleteByKey(key);
            return true;
        }
        return false;
    }

    public String insertEntry(String key, Object value) {
        primaryMap.keyValueMap.put(key, value);
        secondaryStorage.insert(key, value);
        return key;
    }

    public boolean updateEntry(String key,Object value){
        if(primaryMap.keyValueMap.containsKey(key)){
            primaryMap.keyValueMap.put(key, value);
            secondaryStorage.update(key, value);
            return true;
        }
        return false;
    }

}
