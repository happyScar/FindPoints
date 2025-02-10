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
    void testIsWithinRange() {
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
    void testIsWithinRanges() {
        List<PointInfo> list = new ArrayList<>();
        for (int i = 0; i < 5; ++i) {
            list.add(new PointInfo(
                    i,
                    (i + 1) * 2,
                    (i + 1) * 3));
        }
        Map<Integer, Double> map1 = CoordinateUtil.isWithinRange(
                10000,
                2.1,
                3,
                list);
        System.out.println(map1.isEmpty() ? "No Points" : map1.toString());// No Points
        Map<Integer, Double> map2 = CoordinateUtil.isWithinRange(
                10000,
                2.1,
                3,
                list);
        System.out.println(map2.isEmpty() ? "No Points" : map2.toString());// {0=11119.492664455212}

    }

}
