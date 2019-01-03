package com.haiyu.entity;

import java.util.Date;
import javax.persistence.*;

public class Travelrecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private String userId;

    private Date traveldate;

    private Long fee;

    private Integer days;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return user_id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return traveldate
     */
    public Date getTraveldate() {
        return traveldate;
    }

    /**
     * @param traveldate
     */
    public void setTraveldate(Date traveldate) {
        this.traveldate = traveldate;
    }

    /**
     * @return fee
     */
    public Long getFee() {
        return fee;
    }

    /**
     * @param fee
     */
    public void setFee(Long fee) {
        this.fee = fee;
    }

    /**
     * @return days
     */
    public Integer getDays() {
        return days;
    }

    /**
     * @param days
     */
    public void setDays(Integer days) {
        this.days = days;
    }

    @Override
    public String toString() {
        return "Travelrecord{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", traveldate=" + traveldate +
                ", fee=" + fee +
                ", days=" + days +
                '}';
    }
}