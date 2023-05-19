package com.gft.demo.filter;

import com.gft.demo.domain.entity.ApiKey;
import com.gft.demo.repository.ApiKeyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
public class ApiKeyRequestFilter extends GenericFilterBean {

    static final Logger LOG = LoggerFactory.getLogger(ApiKeyRequestFilter.class);

    ApiKeyRepository apiKeyRepository;

    public ApiKeyRequestFilter(ApiKeyRepository apiKeyRepository){
        this.apiKeyRepository = apiKeyRepository;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String path = req.getRequestURI();

        if(path.startsWith("/actuator") == true){
            chain.doFilter(request, response);
            return;
        }

        String key = req.getHeader("Key") == null ? "" : req.getHeader("Key");
        LOG.info("Trying key: " + key);

        Optional<ApiKey> apiKeyOptional = this.apiKeyRepository.findOneByKey(key);
        if(apiKeyOptional.isPresent()){
            chain.doFilter(request, response);
        } else {
            HttpServletResponse resp = (HttpServletResponse) response;
            String error = "Invalid API KEY";

            resp.reset();
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentLength(error .length());
            response.getWriter().write(error);
        }
    }

}
