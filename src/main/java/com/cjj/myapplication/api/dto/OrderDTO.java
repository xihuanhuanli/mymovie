package com.cjj.myapplication.api.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OrderDTO {
    private Integer orderId;
    private Integer ordSeatNum;
    private String filmName;
    private String cinema;
    private String cinemaType;
    private String seatInfo;

    private BigDecimal amount;
    private LocalDateTime orderDate;
    private LocalDateTime beginTime;
    private String orderNumber;
    private String imageSrc;
    private String time;
}
