package com.example.testcreator.web.controller;

import com.example.testcreator.cache.SessionIdCacheService;
import com.example.testcreator.cache.records.HttpSessionRecord;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
@RequestMapping("/login")
public class LoginController {
  private final SessionIdCacheService sessionIdCacheService;

  @GetMapping
  public String login(){
    return "login";
  }

  @PostMapping
  public String loginSet(HttpServletRequest request,
                         @RequestParam(name = "code") String code){
    sessionIdCacheService.createOrUpdate(request.getSession().getId(), new HttpSessionRecord(code));
    return "redirect:/test/all";
  }
}
