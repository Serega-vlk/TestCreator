package com.example.testcreator.cache;

import com.example.testcreator.cache.records.HttpSessionRecord;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class SessionIdCacheService implements CacheService<HttpSessionRecord, String>{
  @Override
  @Cacheable(cacheNames = "sessionCache", key = "#sessionId")
  public HttpSessionRecord get(String sessionId) {
    return null;
  }

  @Override
  @CachePut(cacheNames = "sessionCache", key = "#sessionId")
  public HttpSessionRecord createOrUpdate(String sessionId, HttpSessionRecord value) {
    return value;
  }

  @Override
  @CacheEvict(cacheNames = "sessionCache", key = "#sessionId")
  public void delete(String sessionId) {

  }
}
