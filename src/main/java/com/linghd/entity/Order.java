package com.linghd.entity;

import com.linghd.entity.enums.OrderState;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by Ling on 2016/12/28.
 */
public class Order implements Serializable {
    private static final long serialVersionUID = -7118531620342987148L;
    private long number;
    private long userId;
    private OrderState state;
    private BigDecimal totalPrice;
    private Date createDate;

    private OrderLine[] orderLines;

    public Order(long number, long userId, OrderState state, BigDecimal totalPrice) {
        this.number = number;
        this.userId = userId;
        this.state = state;
        this.totalPrice = totalPrice;
        this.createDate = new Date();
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public OrderLine[] getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(OrderLine[] orderLines) {
        this.orderLines = orderLines;
    }

    @Override
    public String toString() {
        return "Order{" +
                "number=" + number +
                ", userId=" + userId +
                ", state=" + state +
                ", totalPrice=" + totalPrice +
                ", createDate=" + createDate +
                ", orderLines=" + Arrays.toString(orderLines) +
                '}';
    }
}
