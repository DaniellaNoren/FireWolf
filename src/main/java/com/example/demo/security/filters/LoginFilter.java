package com.example.demo.security.filters;

import com.example.demo.models.user.UserRegisterModel;
import com.google.gson.Gson;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Collectors;

public class LoginFilter extends AbstractAuthenticationProcessingFilter {

    public LoginFilter(RequestMatcher requiresAuthenticationRequestMatcher, AuthenticationManager authenticationManager,
                       AuthenticationSuccessHandler handler) {
        super(requiresAuthenticationRequestMatcher);
        this.setAuthenticationManager(authenticationManager);
        this.setAuthenticationSuccessHandler(handler);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        String requestData = getRequestBody(request.getReader());
        System.out.println(requestData);
        UserRegisterModel user = getPOJOFromJson(requestData, UserRegisterModel.class);
        UsernamePasswordAuthenticationToken token;
        if(checkIfNull(user)) {
            throw new BadCredentialsException("Missing request body with user name and password.");
        }

        token = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        return this.getAuthenticationManager().authenticate(token);
    }

    public String getRequestBody(BufferedReader requestReader){
        return requestReader.lines().collect(Collectors.joining());
    }

    public <T>T getPOJOFromJson(String JSON, Class<T> aClass){
        Gson gson = new Gson();
        Object obj = (T) gson.fromJson(JSON, aClass);
        return (T) obj;
    }

    public boolean checkIfNull(UserRegisterModel userRegisterModel){
        return userRegisterModel == null ||
                userRegisterModel.getUserName() == null ||
                userRegisterModel.getPassword() == null;
    }

}
