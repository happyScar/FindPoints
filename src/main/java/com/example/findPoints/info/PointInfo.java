package com.example.findPoints.info;

import lombok.*;

/**
 * 模拟站点信息实体类
 *
 * @author: scarborough
 * @datetime: 2025/2/7 - 9:04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PointInfo {

    private Long id;
    private double xPoint;
    private double yPoint;
}
