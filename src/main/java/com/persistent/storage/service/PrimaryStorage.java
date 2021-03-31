package com.persistent.storage.service;

public interface PrimaryStorage {

    Object getEntry(String key);

    boolean deleteEntry(String key);

    String insertEntry(String key, Object value);

    boolean updateEntry(String key, Object value);

}
