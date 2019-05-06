package org.labiak.java.filters;

import javax.servlet.*;
import java.io.IOException;

public class ServletFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(request, response);
    }
}
