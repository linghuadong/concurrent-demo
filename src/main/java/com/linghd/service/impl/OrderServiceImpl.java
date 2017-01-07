package com.linghd.service.impl;

import com.linghd.common.IdWorker;
import com.linghd.entity.Order;
import com.linghd.entity.OrderLine;
import com.linghd.entity.User;
import com.linghd.entity.enums.OrderState;
import com.linghd.mapper.ProductMapper;
import com.linghd.service.OrderService;
import com.linghd.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

import java.math.BigDecimal;
import java.util.List;

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
    private RedisTemplate redisTemplate;

    @Autowired
    private JedisPool jedisPool;

    public void createOrder(OrderLine[] orderLines, User user) {
        Jedis jedis = jedisPool.getResource();
        for (OrderLine orderLine : orderLines) {
            String key = "product:" + orderLine.getProductId();
            if(!"OK".equals(jedis.watch(key))) throw new ServiceException("未知错误！");
            String stockQuantityStr = jedis.get(key);
            Long stockQuantity;
            if (stockQuantityStr == null) {
                stockQuantity = productMapper.getStockQuantity(orderLine.getProductId());
            }else {
                stockQuantity = Long.parseLong(stockQuantityStr);
            }
            if (stockQuantity < orderLine.getNum()) throw new ServiceException("库存不足！");
            Transaction t = jedis.multi();
            t.set(key, String.valueOf(stockQuantity - orderLine.getNum()));
            List<Object> rs = t.exec();
            if (rs == null || rs.isEmpty()) throw new ServiceException("没抢到！换个姿势再来！");
        }

//        try {
//            for (OrderLine orderLine : orderLines) {
//                String key = "product:" + orderLine.getProductId();
//                redisTemplate.watch(key);
//                Long stockQuantity = (Long) redisTemplate.opsForValue().get(key);
//                if (stockQuantity == null) {
//                    stockQuantity = productMapper.getStockQuantity(orderLine.getProductId());
//                }
//                if (stockQuantity < orderLine.getNum()) throw new ServiceException("库存不足！");
//                redisTemplate.multi();
//                redisTemplate.opsForValue().set(key, stockQuantity - orderLine.getNum());
//                redisTemplate.exec();
//            }
//        } catch (Exception e) {
//            throw new ServiceException("没抢到！换个姿势再来！");
//        }
        Order order = new Order(idWorker.nextId(), user.getId(), OrderState.NEW, getOrdertotalMoney(orderLines));
        order.setOrderLines(orderLines);
        redisTemplate.convertAndSend("order:insert", order);
    }

    private BigDecimal getOrdertotalMoney(OrderLine[] orderLines) {
        BigDecimal total = new BigDecimal(0);
        for (OrderLine orderLine : orderLines) {
            total.add(orderLine.getProductPrice().multiply(new BigDecimal(orderLine.getNum())));
        }
        return total;
    }

}
