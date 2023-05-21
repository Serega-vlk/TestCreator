package com.example.testcreator.cache.records;

import com.example.testcreator.web.dto.TestInput;

public record TestRegistrationRecord(TestInput testInput) implements CacheRecord{

}
