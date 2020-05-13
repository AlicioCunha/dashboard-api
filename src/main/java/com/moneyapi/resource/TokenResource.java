package com.moneyapi.resource;

import com.moneyapi.config.property.ApiProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/tokens")
public class TokenResource {

    @Autowired
    private ApiProperty property;

    @DeleteMapping("/revoke")
    public void revoke(HttpServletRequest servlet, HttpServletResponse response){
        Cookie cookie = new Cookie("refreshToken", null);
        cookie.setHttpOnly(true); // so será acessivel em http
        cookie.setSecure(property.getSecurity().isEnableHttps());
        cookie.setPath(servlet.getContextPath() + "/oauth/token");
        cookie.setMaxAge(0); // expira em 30 dias
        response.addCookie(cookie);
        response.setStatus(HttpStatus.NO_CONTENT.value());
    }
}
