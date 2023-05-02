package com.example.testcreator.service;

import com.example.testcreator.web.dto.Test;

import java.util.List;

public interface TestService {
  List<Test> getAllTests(String id);
}
