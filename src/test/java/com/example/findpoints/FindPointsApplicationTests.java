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

    @Test
    void testsWithinRanges() {
        List<PointInfo> pointInfos = new ArrayList<>();
        for (int i = 0; i < 5; ++i) {
            pointInfos.add(new PointInfo(i, (i + 1) * 2, (i + 1) * 3));
        }
        Map<Integer, Double> pointsMap1 = CoordinateUtil.isWithinRange(
                10000,
                2.1,
                3.1,
                pointInfos);
        System.out.println(pointsMap1.isEmpty() ? "No Points" : pointsMap1.toString());// No Points
        Map<Integer, Double> pointsMap2 = CoordinateUtil.isWithinRange(
                100000,
                2.1,
                3.1,
                pointInfos);
        System.out.println(pointsMap2.isEmpty() ? "No Points" : pointsMap2.toString()); // {0=15720.304956802594}

    }

}
