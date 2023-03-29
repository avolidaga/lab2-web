package com.example.lab_2.filters;

import com.example.lab_2.services.AreaCheckService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.Objects;

//@WebFilter(servletNames = {"hit"})
//@WebFilter(urlPatterns = "/")
@WebFilter(filterName = "Validate")
public class ValidateFilter extends HttpFilter {

    AreaCheckService areaCheckService;

    @Override
    public void init() throws ServletException {
        areaCheckService = new AreaCheckService();
    }

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        if (Objects.equals(req.getMethod(), "POST")) {
            try {
                int x = (int) req.getAttribute("x");
                float y = (float) req.getAttribute("y");
                float r = (float) req.getAttribute("r");
                areaCheckService.validate(x, y, r);
                req.setAttribute("x",x);
                req.setAttribute("y",y);
                req.setAttribute("r",r);
                chain.doFilter(req, res);
            } catch (InvalidParameterException e) {
                res.setStatus(400);
                req.setAttribute("error", e.getMessage());
                getServletContext().getRequestDispatcher("/error.jsp").forward(req, res);
            }
        } else {
            chain.doFilter(req, res);
        }
    }
}
