package com.cjj.myapplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private int order_id;
    private int user_id;
    private int film_scene_id;
    private BigDecimal amount;
    private LocalDateTime orderdate;
    private String order_number;

}
