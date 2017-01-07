package com.linghd.service.impl;

import com.linghd.common.IdWorker;
import com.linghd.redis.JedisUtil;
import com.linghd.entity.Order;
import com.linghd.entity.OrderLine;
import com.linghd.entity.User;
import com.linghd.entity.enums.OrderState;
import com.linghd.mapper.OrderLineMapper;
import com.linghd.mapper.OrderMapper;
import com.linghd.mapper.ProductMapper;
import com.linghd.service.OrderService;
import com.linghd.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * Created by Ling on 2016/12/28.
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private JedisUtil jedisUtil;

    @Transactional
    public void createOrder(OrderLine[] orderLines, User user) {
        for (OrderLine orderLine : orderLines) {
            long stockQuantity = 0;
            String key = "pid:" + orderLine.getProductId();
            synchronized (this) {
                if (jedisUtil.exists(key)) {
                    stockQuantity = jedisUtil.increment(key, -orderLine.getNum());
                } else {
                    stockQuantity = productMapper.getStockQuantity(orderLine.getProductId()) - orderLine.getNum();
                    jedisUtil.increment(key, stockQuantity);
                }
            }
            if (stockQuantity < 0) throw new ServiceException("库存不足！");
        }
        Order order = new Order(idWorker.nextId(), user.getId(), OrderState.NEW, getOrdertotalMoney(orderLines));
        order.setOrderLines(orderLines);
        jedisUtil.pub("order:insert", order);
    }

    private BigDecimal getOrdertotalMoney(OrderLine[] orderLines) {
        BigDecimal total = new BigDecimal(0);
        for (OrderLine orderLine : orderLines) {
            total.add(orderLine.getProductPrice().multiply(new BigDecimal(orderLine.getNum())));
        }
        return total;
    }

}
