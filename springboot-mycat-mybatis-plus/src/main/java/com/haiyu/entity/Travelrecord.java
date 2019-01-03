package com.haiyu.entity;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author liuxing
 * @since 2018-09-01
 */
public class Travelrecord extends Model<Travelrecord> {

    private static final long serialVersionUID = 1L;

    private Long id;

    @TableField("user_id")
    private String userId;

    private Date traveldate;

    private BigDecimal fee;

    private Integer days;

    public Long getId() {
        return id;
    }

    public Travelrecord setId(Long id) {
        this.id = id;
        return this;
    }
    public String getUserId() {
        return userId;
    }

    public Travelrecord setUserId(String userId) {
        this.userId = userId;
        return this;
    }
    public Date getTraveldate() {
        return traveldate;
    }

    public Travelrecord setTraveldate(Date traveldate) {
        this.traveldate = traveldate;
        return this;
    }
    public BigDecimal getFee() {
        return fee;
    }

    public Travelrecord setFee(BigDecimal fee) {
        this.fee = fee;
        return this;
    }
    public Integer getDays() {
        return days;
    }

    public Travelrecord setDays(Integer days) {
        this.days = days;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Travelrecord{" +
        "id=" + id +
        ", userId=" + userId +
        ", traveldate=" + traveldate +
        ", fee=" + fee +
        ", days=" + days +
        "}";
    }
}
