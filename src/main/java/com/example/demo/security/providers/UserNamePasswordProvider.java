package com.example.demo.security.providers;

import com.example.demo.security.JPAUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserNamePasswordProvider implements AuthenticationProvider {

    @Autowired
    JPAUserDetailsService userDetailsService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserDetails userDetails;
        try{
            userDetails = userDetailsService.loadUserByUsername(authentication.getPrincipal().toString());
        }catch(UsernameNotFoundException e){
            throw new UsernameNotFoundException(e.getMessage());
        }

        if(passwordEncoder.matches(authentication.getCredentials().toString(), userDetails.getPassword())){
            authentication = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
        }else{
            throw new BadCredentialsException("Something went wrong");
        }
        return authentication;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
    }


}