package com.moneyapi.config.token;

import org.eclipse.jdt.internal.compiler.problem.AbortCompilationUnit;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class RefreshTokenPostProcessor implements ResponseBodyAdvice<OAuth2AccessToken> {

    /***
     *
     * @param returnType
     * @param converterType
     * @return OAuth2AccessToken
     *
     * Metodo irá interceptar o retorno da resposta quando for feito um requisicao
     * para /oauth/token ai terei o objeto
     *
     * {
     *     "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9
     *                      .eyJleHAiOjE1ODg4MTQ1MzEsInVzZXJfbmFtZSI6ImFkbWluIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9S
     *                                       T0xFIl0sImp0aSI6IjI5ZTU1MzEzLWU3YTUtNDI5Ny04MzM2LTY1MzE3NDY2MjIy
     *                                       ZSIsImNsaWVudF9pZCI6ImFuZ3VsYXIiLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXX0
     *                      .e99bKNyK0LESDn7lK1lyWmiZJ9VQVGjDBk9Gs085mno",
     *
     *     "token_type": "bearer",
     *     "refresh_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9
     *     .eyJ1c2VyX25hbWUiOiJhZG1pbiIsInNjb3BlIjpbInJlYWQiLCJ3cml0ZSJdLCJhdGkiOiIyOWU1NT
     *                          MxMy1lN2E1LTQyOTctODMzNi02NTMxNzQ2NjIyMmUiLCJleHAiOjE1ODg5MDA5MTEsImF1dGhvcml
     *                          0aWVzIjpbIlJPTEVfUk9MRSJdLCJqdGkiOiI5N2IzYWFlZS03MjZkLTQxNGEtODQyMy0zNjM2ZDAx
     *                          ZWMzNjIiLCJjbGllbnRfaWQiOiJhbmd1bGFyIn0
     *     .-KRx-xPfQgttLtiCEzStby6CtF5FWeYY2N_AowJtB44",
     *     "expires_in": 19,
     *     "scope": "read write",
     *     "jti": "29e55313-e7a5-4297-8336-65317466222e"
     * }
     *
     *
     *
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return returnType.getMethod().getName().equals("postAccessToken");
    }

    @Override
    public OAuth2AccessToken beforeBodyWrite(OAuth2AccessToken body, MethodParameter returnType,
                                             MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                             ServerHttpRequest request, ServerHttpResponse response) {

        HttpServletRequest req = ((ServletServerHttpRequest) request).getServletRequest();
        HttpServletResponse resp = ((ServletServerHttpResponse) response).getServletResponse();

        DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) body;

        String refreshToken = body.getRefreshToken().getValue();
        addRefreshTokenOnCookie(refreshToken, req, resp);
        removeRefreshTokenFromBody(token);

        return body;
    }


    private void removeRefreshTokenFromBody(DefaultOAuth2AccessToken token) {
        token.setRefreshToken(null);
    }

    private void addRefreshTokenOnCookie(String refreshToken, HttpServletRequest req, HttpServletResponse resp) {
        Cookie cookie = new Cookie("refreshToken", refreshToken);
        cookie.setHttpOnly(true); // so será acessivel em http
        cookie.setSecure(false); // TODO : mudar para true em pr oducao será feito deploy em HTTPS
        cookie.setPath(req.getContextPath() + "/oauth/token");
        cookie.setMaxAge(60*60*24); // expira em 30 dias
        resp.addCookie(cookie);
    }

}
