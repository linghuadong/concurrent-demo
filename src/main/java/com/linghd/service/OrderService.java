package com.linghd.service;

import com.linghd.entity.OrderLine;
import com.linghd.entity.User;

/**
 * Created by Ling on 2016/12/28.
 */
public interface OrderService {
    void createOrder(OrderLine[] orderLines, User user);
}
