package com.example.testcreator.cache;

import com.example.testcreator.cache.records.TestRegistrationRecord;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class TestRegistrationCacheService implements CacheService<TestRegistrationRecord, String>{
  @Override
  @Cacheable(cacheNames = "regCache", key = "#id")
  public TestRegistrationRecord get(String id) {
    return null;
  }

  @Override
  @CachePut(cacheNames = "regCache", key = "#id")
  public TestRegistrationRecord createOrUpdate(String id, TestRegistrationRecord value) {
    return value;
  }

  @Override
  @CacheEvict(cacheNames = "regCache", key = "#id")
  public void delete(String id) {

  }
}
