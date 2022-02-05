package com.todo.filter;

import com.todo.constant.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
public class LoginCheckFilter implements Filter {

    private static final String[] whiteList = {"/", "/login", "/logout", "/css/*"};

    @Override
    public void init (FilterConfig filterConfig) throws ServletException {
        log.info("loginCheckFilter init...");
    }

    @Override
    public void destroy () {
        log.info("loginCheckFilter destroy...");
    }

    @Override
    public void doFilter (ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        log.info("loginCheckFilter doFilter...");

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String requestURI = httpRequest.getRequestURI();

        try {
            log.info("인증 체크 필터 시작 {}", requestURI);

            // 필터링 대상 url
            if(isLoginCheckPath(requestURI)) {
                log.info("인증 체크 로직 실행 {}", requestURI);

                HttpSession session = httpRequest.getSession(false);
                if(session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {
                    log.info("미인증 사용자 요청 {}", requestURI);

                    //로그인 페이지로 리다이렉트
                    httpResponse.sendRedirect("/loginPage");
                    return;
                }
            }

            chain.doFilter(request, response);
        }
        catch (Exception e) {
            throw e;
        }
        finally {
            log.info("인증 체크 필터 종료 {}", requestURI);
        }
    }

    private boolean isLoginCheckPath(String requestURI) {
        return !PatternMatchUtils.simpleMatch(whiteList, requestURI);
    }
}