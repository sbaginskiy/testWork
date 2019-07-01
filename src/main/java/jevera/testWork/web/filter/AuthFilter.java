package jevera.testWork.web.filter;

import org.springframework.stereotype.Component;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();
        if (!requestURI.startsWith("/api/")) {
            chain.doFilter(request, response);
            return;
        }
        if (httpRequest.getSession() != null && httpRequest.getSession().getAttribute("employee") != null) {
            chain.doFilter(request, response);
            return;
        }
        ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Not authorized");
    }
}
