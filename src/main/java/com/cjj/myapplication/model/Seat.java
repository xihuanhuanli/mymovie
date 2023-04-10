package com.cjj.myapplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Seat {
    private int seat_id;
    private String row;
    private String column;
    private int status;
    private int film_s_id;
    private int ord_id;
}
