package bac.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {

    private static Logger LOG = LoggerFactory.getLogger(CorsFilter.class);
    private static final String OPTIONS = "OPTIONS";

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE, HEAD, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers, X-Auth-Token");

        LOG.trace("Request-Method: " + request.getMethod());

        /**
         * The browser (following CORS specs) adds an extra step to the request:
         * It first sends a particular request with method "OPTIONS" to the URL, if the server respond approving the actual request, then the real request will start.
         * Without the check:
         * spring returns 401 (unauthorized) to the OPTIONS request because X-Auth-Token is not present in the request,
         * consequently the real request will never start
         */
        if (request.getMethod().equals(OPTIONS)) {
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }
        // Forward real request
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {

    }
}