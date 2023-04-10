package com.cjj.myapplication.service.serviceImpl;

import com.cjj.myapplication.api.dto.FilmDTO;
import com.cjj.myapplication.api.dto.GetOrederModel;
import com.cjj.myapplication.api.dto.OrderDTO;
import com.cjj.myapplication.common.PageUtils.PageRequest;
import com.cjj.myapplication.common.PageUtils.PageResult;
import com.cjj.myapplication.common.PageUtils.PageUtils;
import com.cjj.myapplication.mapper.FilmSceneMapper;
import com.cjj.myapplication.model.FilmScene;
import com.cjj.myapplication.model.Order;
import com.cjj.myapplication.model.Seat;
import com.cjj.myapplication.service.FilmSceneService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class FilmSceneServiceImpl implements FilmSceneService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private FilmSceneMapper filmSceneMapper;
    @Override
    public void addFilmScene(FilmScene film) {
            filmSceneMapper.addFilmScene(film);
    }

    @Override
    public Map selectFilmScene(FilmScene film) {
        Map filmScene=filmSceneMapper.selectFilmScene(film);
        return filmScene;
    }

    @Override
    public Map<String, Object> getFilmInfoByFSID(Integer filmSceneId) {
        Map<String,Object> map=filmSceneMapper.getFilmInfoByFSID(filmSceneId);
        return map;
    }

    @Transactional
    @Override
    public boolean setOrder(GetOrederModel getOrederModel) {
        try {
        //order构建
        Order order=new Order();
        order.setUser_id(getOrederModel.getUser_id());
        order.setFilm_scene_id(getOrederModel.getFilm_scene_id());
        order.setAmount(getOrederModel.getAmount());
        order.setOrderdate(getOrederModel.getOrderdate());
        //生成ord_number
        String s="CM";
        s+="12138"+getOrederModel.getOrderdate().toString();
        order.setOrder_number(s);
        //
            filmSceneMapper.setOrder(order);
            int ordig=filmSceneMapper.getOrderID(s);
            for (List list:getOrederModel.getMsg()){
                Seat seat=new Seat();
                seat.setRow(list.get(0).toString());
                seat.setColumn(list.get(1).toString());
                seat.setFilm_s_id(getOrederModel.getFilm_scene_id());
                seat.setOrd_id(ordig);
                filmSceneMapper.addSeat(seat);
            }
            filmSceneMapper.updateFilmScene(getOrederModel.getFilm_scene_id(),getOrederModel.getSeat_number());
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public PageResult findOrderPage(PageRequest pageRequest) {
        return PageUtils.getPageResult(pageRequest, getPageInfo(pageRequest));
    }
    private PageInfo getPageInfo(PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        String  search=pageRequest.getSearch();
        List<OrderDTO> orderList=filmSceneMapper.selectOrderPage(pageRequest.getId(),search);
        for(OrderDTO orderDTO : orderList){
            String s=orderDTO.getImageSrc();
            s="https://images.weserv.nl/?url="+s;
            orderDTO.setImageSrc(s);
        }
        return new PageInfo<OrderDTO>(orderList);
    }
}
