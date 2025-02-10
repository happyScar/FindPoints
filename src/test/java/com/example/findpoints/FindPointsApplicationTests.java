package com.example.findPoints;

import com.example.findPoints.info.PointInfo;
import com.example.findPoints.utils.CoordinateUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest
class FindPointsApplicationTests {
    double r = 100000;

    @Test
    void testsWithinRange() {
        System.out.println(CoordinateUtil.isWithinRange(
                10000,
                22,
                113.72,
                22, 113.82));// false
        System.out.println(CoordinateUtil.isWithinRange(
                10000,
                22,
                113.72,
                22, 113.71));// true

    }

}
