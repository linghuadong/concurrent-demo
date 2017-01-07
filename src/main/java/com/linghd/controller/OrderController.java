package com.linghd.controller;

import com.linghd.dto.OrderRequest;
import com.linghd.entity.OrderLine;
import com.linghd.entity.User;
import com.linghd.service.OrderService;
import com.linghd.service.ProductService;
import com.linghd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Ling on 2016/12/28.
 */
@RestController
@RequestMapping(value = "/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @RequestMapping(method = RequestMethod.POST)
    public void createOrder(@RequestBody OrderRequest[] orderRequests){
        User user = userService.getByUsername("test");
        OrderLine[] orderLines = new OrderLine[orderRequests.length];
        for (int i = 0; i < orderRequests.length; i++) {
            orderLines[i] = new OrderLine(productService.getById(orderRequests[i].getProductId()), orderRequests[i].getNumber());
        }
        orderService.createOrder(orderLines,user);
    }

}
