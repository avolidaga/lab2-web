package com.example.lab_2.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AreaCheckService {

    private final List<Float> validRValues = List.of(1F, 1.5F, 2F);

    public void validate(int x, float y, float r) {
        if (x < -5 || x > 3) {
            throw new InvalidParameterException("X must be from -5 to 3");
        }
        if (y < -3 || y > 5) {
            throw new InvalidParameterException("Y must be from -3 to 5");
        }
        if (!validRValues.contains(r)) {
            throw new InvalidParameterException("Wrong R");
        }
    }


    public Boolean checkHit(int x, float y, float r) {
        if (x <= 0 && y >= 0 && x >= -r && y <= r / 2) {
            return true;                                                // 2 квадранта (прямоугольник)
        } else if (x <= 0 && y <= 0) {
            if (y >= -x - r) {
                return true;
            }
            return false;                                               // 3 квадранта (треугольник)
        } else if (x >= 0 && y <= 0) {
            if ((Math.pow(x, 2) + Math.pow(y, 2)) <= Math.pow(r, 2)) {
                return true;
            }
            return false;                                               // 4 квадранта (шар)
        } else return false;
    }

    public Map<String, Number> parseParams(String xParam, String yParam, String rParam) {
        Integer x = Integer.parseInt(xParam);
        Float y = Float.parseFloat(yParam);
        Float r = Float.parseFloat(rParam);
        return Map.of(
                "x", x,
                "y", y,
                "r", r
        );
    }
}
