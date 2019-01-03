package com.haiyu.entity;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.command.BaseModel;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.Data;

import java.util.Date;

/**
 * @Title: Test
 * @Description:
 * @author: youqing
 * @version: 1.0
 * @date: 2018/9/5 16:20
 */
@Table(name = "test")
@Data
public class Test extends BaseModel {

    private static final long serialVersionUID = 5199200306752426433L;

    @Column(name = "id",type = MySqlTypeConstant.INT,length = 11,isKey = true,isAutoIncrement = true)
    private Integer id;

    @Column(name = "name",type = MySqlTypeConstant.VARCHAR,length = 111)
    private String  name;

    @Column(name = "description",type = MySqlTypeConstant.TEXT)
    private String  description;

    @Column(name = "create_time",type = MySqlTypeConstant.DATETIME)
    private Date    create_time;

    @Column(name = "update_time",type = MySqlTypeConstant.DATETIME)
    private Date    update_time;

    @Column(name = "number",type = MySqlTypeConstant.BIGINT,length = 5)
    private Long    number;

    @Column(name = "lifecycle",type = MySqlTypeConstant.CHAR,length = 1)
    private String  lifecycle;

    @Column(name = "dekes",type = MySqlTypeConstant.DOUBLE,length = 5,decimalLength = 2)
    private Double  dekes;

}

