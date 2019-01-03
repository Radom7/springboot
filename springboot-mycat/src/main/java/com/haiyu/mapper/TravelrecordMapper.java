package com.haiyu.mapper;

import com.haiyu.entity.Travelrecord;
import com.haiyu.utils.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelrecordMapper extends MyMapper<Travelrecord> {
}