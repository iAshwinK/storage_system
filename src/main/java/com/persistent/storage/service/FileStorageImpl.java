package com.persistent.storage.service;

import com.persistent.storage.vo.KeyVal;
import org.apache.commons.io.input.ReversedLinesFileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileStorageImpl implements SecondaryStorage {

    @Value("classpath:storage_file.txt")
    private Resource resource;

    private static final Logger LOGGER = LoggerFactory.getLogger(FileStorageImpl.class);

    @Override
    public List<KeyVal> getAll() {
        List<KeyVal> keyValList = new ArrayList<>();
        LOGGER.info("Starting to read all key-values from file");
        try {
            File file = resource.getFile();
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                keyValList.add(getKeyVal(line));
            }
            fr.close();
        } catch (IOException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        LOGGER.info("Key Val list size:" + keyValList.size());
        return keyValList;
    }

    @Override
    public KeyVal getByKey(String key) {
        try {
            ReversedLinesFileReader fileReader = new ReversedLinesFileReader(resource.getFile(), null);
            String str;
            do {
                str = fileReader.readLine();
                KeyVal keyVal = getKeyVal(fileReader.readLine());
                if (keyVal.getKey().equals(key)) {
                    return keyVal;
                }
            } while (str != null);
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(String key, Object value) {
        insert(key, value);
    }

    @Override
    public void insert(String key, Object value) {
        //insert {key} {value} at EOF
        try {
            File f = resource.getFile();
            BufferedWriter bw = new BufferedWriter(new FileWriter(f, true));
            bw.append(key + " " + value.toString());
            bw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void deleteByKey(String key) {
        insert(key, "NULL");
    }

    private KeyVal getKeyVal(String line) {
        String[] arr = line.split("\\s+");
        return new KeyVal(arr[0], arr[1]);
    }
}
