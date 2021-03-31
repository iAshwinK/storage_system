package com.persistent.storage.service;

import com.persistent.storage.vo.KeyVal;
import org.apache.commons.io.input.ReversedLinesFileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Service
public class FileStorageImpl implements SecondaryStorage {

    @Value("classpath:storage_file.txt")
    private Resource resource;

    private static final Logger LOGGER = LoggerFactory.getLogger(FileStorageImpl.class);

    private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    @Override
    public List<KeyVal> getAll() {
        readWriteLock.readLock().lock();
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
        }finally {
            LOGGER.info("Key Val list size:" + keyValList.size());
            readWriteLock.readLock().unlock();
            return keyValList;
        }
    }

    @Override
    public KeyVal getByKey(String key) {
        readWriteLock.readLock().lock();
        try {
            ReversedLinesFileReader fileReader = new ReversedLinesFileReader(resource.getFile(), null);
            String str;
            do {
                str = fileReader.readLine();
                KeyVal keyVal = getKeyVal(fileReader.readLine());
                if (keyVal.getKey().equals(key)) {
                    LOGGER.info("Key found for:"+keyVal.toString());
                    return keyVal;
                }
            } while (str != null);
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            readWriteLock.readLock().unlock();
        }
        LOGGER.info("Key not found");
        return null;
    }

    public void update(String key, Object value) {
        insert(key, value);
    }

    @Override
    public void insert(String key, Object value) {
        readWriteLock.writeLock().lock();
        LOGGER.info("inserting the key:"+key);
        //insert {key} {value} at EOF
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(resource.getFile(), true));
            String line = key + " " + value.toString();
            LOGGER.debug("line to be inserted:"+line);
            bw.newLine();
            bw.append(line);
            bw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }finally {
            readWriteLock.writeLock().unlock();
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
