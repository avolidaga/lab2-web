package com.example.lab_2.filters;

import com.example.lab_2.services.AreaCheckService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;

//@WebFilter(servletNames = {"hit"})
//@WebFilter(urlPatterns = "/")
@WebFilter(filterName = "Parse")
public class ParseFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        if (Objects.equals(req.getMethod(), "POST")) {
            try {
                int x = Integer.parseInt(req.getParameter("x"));
                float y = Float.parseFloat(req.getParameter("y"));
                float r = Float.parseFloat(req.getParameter("r"));
                req.setAttribute("x", x);
                req.setAttribute("y", y);
                req.setAttribute("r", r);
                chain.doFilter(req, res);
            } catch (NumberFormatException e) {
                res.setStatus(400);
                req.setAttribute("error", "Wrong number format");
                getServletContext().getRequestDispatcher("/error.jsp").forward(req, res);
            }
        } else {
            chain.doFilter(req, res);
        }

    }
}
