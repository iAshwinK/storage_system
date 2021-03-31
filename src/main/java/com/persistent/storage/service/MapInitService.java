package com.persistent.storage.service;

import com.persistent.storage.vo.KeyVal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class MapInitService {

    @Autowired
    private PrimaryMap primaryMap;
    @Autowired
    private SecondaryStorage secondaryStorage;


    @PostConstruct
    private void initPrimaryMap(){
        List<KeyVal> persistentList = secondaryStorage.getAll();
        persistentList.forEach(item-> primaryMap.keyValueMap.put(item.getKey(),item.getValue()));
    }


}
