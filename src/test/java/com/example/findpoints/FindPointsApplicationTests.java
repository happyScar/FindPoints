package com.example.findPoints;

import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GeodeticCurve;
import org.gavaghan.geodesy.GlobalCoordinates;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FindPointsApplicationTests {
    double centerLatitude = 22;
    double centerLongitude = 113.72;
    double targetLatitude = 22;
    double targetLongitude = 113.82;
    double r = 100000;

    @Test
    void testsWithinRange() {
        GeodeticCurve geoCurve = new GeodeticCalculator().calculateGeodeticCurve(
                Ellipsoid.Sphere,
                new GlobalCoordinates(centerLatitude, centerLongitude),
                new GlobalCoordinates(targetLatitude, targetLongitude)
        );
        double distance = geoCurve.getEllipsoidalDistance();
        System.out.println("distance:" + distance);
        System.out.println(distance < r);
    }

}
