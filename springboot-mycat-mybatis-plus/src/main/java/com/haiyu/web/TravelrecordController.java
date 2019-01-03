package com.haiyu.web;


import com.haiyu.entity.Travelrecord;
import com.haiyu.service.TravelrecordService;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liuxing
 * @since 2018-09-01
 */
@RestController
@MapperScan("com.haiyu.mapper")
public class TravelrecordController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TravelrecordService travelrecordService;

    @RequestMapping("getList")
    public List<Travelrecord> getList(){
        logger.info("获取所有列表");
        return travelrecordService.selectList(null);
    }

    @RequestMapping("add")
    public String add(@RequestBody Travelrecord travelrecord){
        logger.info("添加数据");
        Boolean result = travelrecordService.insert(travelrecord);
        if(result == true){
            logger.info("添加成功");
            return "添加成功";
        }
        logger.error("添加失败");
        return "添加失败";
    }

    @DeleteMapping("delete/{id}")
    public String delete(@PathVariable String id){
        logger.info("删除数据："+id);
        Long i = Long.parseLong(id);
        Boolean result = travelrecordService.deleteById(i);
        if(result == true){
            logger.info("删除数据成功");
            return "删除数据成功";
        }
        logger.error("删除数据失败");
        return "删除数据失败";
    }

}
