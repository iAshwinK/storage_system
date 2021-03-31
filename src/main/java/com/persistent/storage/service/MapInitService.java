package com.persistent.storage.service;

import com.persistent.storage.vo.KeyVal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class MapInitService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MapInitService.class);
    @Autowired
    private PrimaryMap primaryMap;
    @Autowired
    private SecondaryStorage secondaryStorage;


    @PostConstruct
    private void initPrimaryMap(){
        List<KeyVal> persistentList = secondaryStorage.getAll();
        persistentList.forEach(item-> primaryMap.keyValueMap.put(item.getKey(),item.getValue()));
        LOGGER.info("primary map size:"+primaryMap.keyValueMap.size());
    }


}
