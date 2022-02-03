package dev.facturador.config.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CheckLogIn implements HandlerInterceptor {

    @Override
    public boolean preHandle
            (HttpServletRequest request, HttpServletResponse response, Object handleHr)
                throws Exception{
        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");
        if(user==null){
            response.sendRedirect("http://localhost:3000");
            return false;
        } else {
            return true;
        }
    }
}
