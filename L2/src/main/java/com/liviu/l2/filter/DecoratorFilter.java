package com.liviu.l2.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(
        filterName = "DecoratorFilter",
        urlPatterns = {"/parse"}
)
public class DecoratorFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // Wrap the response to capture the output
        HttpServletResponseWrapper responseWrapper = new HttpServletResponseWrapper ((HttpServletResponse) servletResponse);

        // Continue the filter chain
        filterChain.doFilter(servletRequest, responseWrapper);

        // Get the captured response content
        String originalContent = responseWrapper.toString();

        // Decorate the response with the prelude and coda
        // String decoratedContent = addPrelude() + originalContent + addCoda();
        String decoratedContent = addPrelude() + originalContent + addCoda();
        // Write the decorated response to the actual response
        PrintWriter out = servletResponse.getWriter();
        out.write(decoratedContent);
    }

    private String addPrelude() {
        // Add your specific prelude here
        return "<p>hi</p>";
    }

    private String addCoda() {
        // Add your specific coda here
        return "<!-- Coda -->";
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
