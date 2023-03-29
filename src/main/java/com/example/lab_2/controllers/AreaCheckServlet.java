package com.example.lab_2.controllers;

import com.example.lab_2.models.Point;
import com.example.lab_2.services.AreaCheckService;
import com.example.lab_2.state.State;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "hit", value = "/hit")
public class AreaCheckServlet extends HttpServlet {

    AreaCheckService areaCheckService;
    State state;

    @Override
    public void init() {
        state = new State();
        areaCheckService = new AreaCheckService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int x = (int) req.getAttribute("x");
        float y = (float) req.getAttribute("y");
        float r = (float) req.getAttribute("r");
        boolean hitted = areaCheckService.checkHit(x, y, r);

        String date = new SimpleDateFormat("dd.MM.yyyy hh:mm").format(new Date());

        long startTime = (long) getServletContext().getAttribute("leadTime");
        float executionTime = (float) (System.nanoTime() - startTime) / 1_000_000;

        Point point = new Point(x, y, r, hitted, date, executionTime);
        state.add(req.getSession().getId(), point);

        req.setAttribute("state", state);
        getServletContext().getRequestDispatcher("/table.jsp").forward(req, res);
    }
}