package com.example.testcreator.service;

import com.example.testcreator.web.dto.Marks;
import com.example.testcreator.web.dto.Test;

import java.util.List;

public interface TestService {
  List<Test> getAllTests(String id);

  void deleteTest(String id, String test);

  Marks getMarksByTest(String id, String test);
}
