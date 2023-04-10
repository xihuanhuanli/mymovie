package com.cjj.myapplication.mapper;

import com.cjj.myapplication.model.FilmScene;
import com.cjj.myapplication.model.Order;
import com.cjj.myapplication.model.Seat;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface FilmSceneMapper {
    void addFilmScene(@Param("filmScene")FilmScene filmScene);

    @MapKey("film_id")
    Map selectFilmScene(@Param("filmScene")FilmScene film);

    @MapKey("film_scene_id")
    Map<String, Object> getFilmInfoByFSID(@Param("filmSceneId")Integer filmSceneId);

    void updateFilmScene(@Param("filmSceneId")int filmSceneId,@Param("seatNumber") int seatNumber);

    void setOrder(@Param("order")Order order);

    int getOrderID(@Param("orderNum")String  orderNum);

    void addSeat(@Param("seat")Seat seat);
}
