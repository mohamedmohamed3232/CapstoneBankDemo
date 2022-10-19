package com.mohamed.capstonebankdemo.interceptors;

import com.mohamed.capstonebankdemo.models.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class AppInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler ) throws Exception {
        System.out.println("In the Handle Interceptor Method");
        //Checking to see if the request starts with /app to begin interception
        if(request.getRequestURI().startsWith("/app")){
            // Getting the http session
            HttpSession session = request.getSession();
            //Getting the User for the session
            User user = (User) session.getAttribute("user");
            // Check if the User is authenticated(logged in)
            //boolean isAuthenticated = (boolean) session.getAttribute("authenticated");
            //Validating all the sessions
            if(user == null ) {
                //redirectAttributes.addFlashAttribute("error", "You mus login in to get to that page");
                response.sendRedirect("/login");
                return false;
            }

        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("In Post Handle Interceptor Method");
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("In After Completion Interceptor Method");
    }
}