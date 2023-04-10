package com.cjj.myapplication.api.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class GetOrederModel {
    private int film_scene_id;
    private int user_id;
    private BigDecimal amount;
    private int seat_number;
    private LocalDateTime orderdate;
    private List<List> msg;
}
