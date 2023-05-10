package com.example.testcreator.web.interceptor;

import com.example.testcreator.cache.SessionIdCacheService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RegisterInterceptor extends HttpFilter {
  private final SessionIdCacheService sessionIdCacheService;

  @Override
  protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
    if (Optional.ofNullable(sessionIdCacheService.get(request.getSession().getId())).isPresent()){
      super.doFilter(request, response, chain);
    } else {
      HttpServletRequestWrapper wrapper = new HttpServletRequestWrapper(request){
        @Override
        public String getRequestURI() {
          return "/login";
        }
      };
      super.doFilter(wrapper, response, chain);
    }
  }
}
