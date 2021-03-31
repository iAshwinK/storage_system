package com.persistent.storage.controller;

import com.persistent.storage.service.PrimaryMap;
import com.persistent.storage.service.PrimaryStorage;
import com.persistent.storage.vo.KeyVal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/storage")
public class StorageController {

    @Autowired
    private PrimaryStorage primaryStorage;
    //create, read, update and delete a key-value pair

    @GetMapping("/{key}")
    public ResponseEntity<Object> getValue(@PathVariable(value = "key") String key){
        Object result =  primaryStorage.getEntry(key);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{key}")
    public ResponseEntity<Void> deleteValue(@PathVariable(value = "key") String key){
        boolean result = primaryStorage.deleteEntry(key);
        if(result) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{key}")
    public ResponseEntity<String> insertValue(@PathVariable(value = "key") String key,@RequestBody KeyVal keyVal){
        String keyInserted = primaryStorage.insertEntry(key,keyVal.getValue());
        return new ResponseEntity<>(keyInserted, HttpStatus.OK);
    }

    @PostMapping("/update/{key}")
    public ResponseEntity<Object> updateValue(@PathVariable("key") String key,@RequestBody KeyVal keyVal){
        boolean result = primaryStorage.updateEntry(key, keyVal.getValue());
        if(result) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
