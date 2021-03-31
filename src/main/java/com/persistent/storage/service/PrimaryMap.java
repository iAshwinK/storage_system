package com.persistent.storage.service;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class PrimaryMap {

    public ConcurrentHashMap<String,Object> keyValueMap = new ConcurrentHashMap<>();

    //helper methods will go here if needed

}
