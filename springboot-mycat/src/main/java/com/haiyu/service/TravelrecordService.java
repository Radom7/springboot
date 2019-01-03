package com.haiyu.service;

import com.haiyu.entity.Travelrecord;

import java.util.List;

/**
 * @Title: TravelrecordService
 * @Description:
 * @author: youqing
 * @version: 1.0
 * @date: 2018/8/31 15:49
 */
public interface TravelrecordService {

    List<Travelrecord> getList();

    int save(Travelrecord travelrecord);

    int deleteById(Long id);
}
