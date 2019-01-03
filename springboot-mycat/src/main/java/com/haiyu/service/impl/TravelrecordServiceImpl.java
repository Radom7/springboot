package com.haiyu.service.impl;

import com.haiyu.entity.Travelrecord;
import com.haiyu.mapper.TravelrecordMapper;
import com.haiyu.service.TravelrecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Title: TravelrecordServiceImpl
 * @Description:
 * @author: youqing
 * @version: 1.0
 * @date: 2018/8/31 15:50
 */
@Service
public class TravelrecordServiceImpl implements TravelrecordService {

    @Autowired
    private TravelrecordMapper travelrecordMapper;

    @Override
    public List<Travelrecord> getList() {
        return travelrecordMapper.selectAll();
    }

    @Override
    public int save(Travelrecord travelrecord) {
        return travelrecordMapper.insert(travelrecord);
    }

    @Override
    public int deleteById(Long id) {
        return travelrecordMapper.deleteByPrimaryKey(id);
    }
}
