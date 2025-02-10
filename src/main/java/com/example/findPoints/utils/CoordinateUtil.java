package com.example.findPoints.utils;

import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GeodeticCurve;
import org.gavaghan.geodesy.GlobalCoordinates;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import com.example.findPoints.info.PointInfo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * 坐标工具类
 *
 * @author: scarborough
 * @datetime: 2025/2/6 - 10:23
 */
@Component
public class CoordinateUtil {

    /**
     * 获取两点距离（目标点为单个点）
     *
     * @param centerLatitude  中心点纬度
     * @param centerLongitude 中心点经度
     * @param targetLatitude  目标点纬度
     * @param targetLongitude 目标点经度
     * @return 返回两点之间距离
     */
    public static Double getDistance(double centerLatitude, double centerLongitude,
                                              double targetLatitude, double targetLongitude) {
        GeodeticCurve geoCurve = new GeodeticCalculator().calculateGeodeticCurve(
                Ellipsoid.Sphere,
                new GlobalCoordinates(centerLatitude, centerLongitude),
                new GlobalCoordinates(targetLatitude, targetLongitude)
        );
        return geoCurve.getEllipsoidalDistance();
    }

    /**
     * 获取两点距离（目标点为多个点）
     *
     * @param centerLatitude  中心点纬度
     * @param centerLongitude 中心点经度
     * @param list 目标点集（元素类型为PointInfo）
     * @return 返回两点之间距离，使用 map<id,distance> 存储
     */
    public static Map<Integer, Double> getDistance(double centerLatitude, double centerLongitude, List<PointInfo> list) {
        Map<Integer, Double> map = new HashMap<>();
        GlobalCoordinates centreGlobalCoordinates = new GlobalCoordinates(centerLatitude, centerLongitude);
        for (PointInfo point :
                list) {
            GeodeticCurve geoCurve = new GeodeticCalculator().calculateGeodeticCurve(
                    Ellipsoid.Sphere,
                    centreGlobalCoordinates,
                    new GlobalCoordinates(point.getXPoint(), point.getYPoint())
                    );
            map.put(point.getId(),geoCurve.getEllipsoidalDistance());
        }
        return map;
    }

    /**
     * 判断目标点是否在区域范围内（目标点为单个点）
     *
     * @param r               区域半径
     * @param centerLatitude  中心点纬度
     * @param centerLongitude 中心点经度
     * @param targetLatitude  目标点纬度
     * @param targetLongitude 目标点经度
     * @return 若目标点落在区域范围内，则返回 true，否则返回 false。（边界情况视为不在范围内）
     */
    public static boolean isWithinRange(double r, double centerLatitude, double centerLongitude,
                                        double targetLatitude, double targetLongitude) {
        double distance = getDistance(centerLatitude, centerLongitude, targetLatitude, targetLongitude);
        return distance < r;
    }

    /**
     * 判断目标点是否在区域范围内（目标点为多个点）
     *
     * @param r               区域半径
     * @param centerLatitude  中心点纬度
     * @param centerLongitude 中心点经度
     * @param list 目标点集（元素类型为PointInfo）
     * @return 将不在区域范围内的点从 map中删去，返回在区域范围内的点的map集合
     */
    public static Map<Integer, Double> isWithinRange(double r, double centerLatitude, double centerLongitude,List<PointInfo> list) {
        Map<Integer,Double> distanceMap = getDistance(centerLatitude, centerLongitude, list);
        Iterator <Map.Entry<Integer,Double>> iterator = distanceMap.entrySet().iterator();
        while ((iterator.hasNext())){
            Map.Entry<Integer,Double> entry = iterator.next();
            if (entry.getValue() >=r){
                iterator.remove();
            }else {
                System.out.println();
            }
        }
        return distanceMap;
    }


}
