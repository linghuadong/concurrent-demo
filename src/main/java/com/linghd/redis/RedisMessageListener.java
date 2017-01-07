package com.linghd.redis;

import com.linghd.entity.Order;
import com.linghd.entity.OrderLine;
import com.linghd.entity.User;
import com.linghd.mapper.OrderLineMapper;
import com.linghd.mapper.OrderMapper;
import com.linghd.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Ling on 2017/1/5.
 */
public class RedisMessageListener implements MessageListener {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderLineMapper orderLineMapper;

    @Transactional
    public void onMessage(Message message, byte[] bytes) {
        Order order = (Order) redisTemplate.getValueSerializer().deserialize(message.getBody());
        try {
            orderMapper.insert(order);
            for (OrderLine orderLine : order.getOrderLines()) {
                productMapper.updateStockQuantity(orderLine.getNum(), orderLine.getProductId());
                orderLine.setOrderNumber(order.getNumber());
                orderLineMapper.insert(orderLine);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
