package com.linghd.service.impl;

import com.linghd.entity.Product;
import com.linghd.mapper.ProductMapper;
import com.linghd.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Ling on 2016/12/28.
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    public Product getById(long productId) {
        return productMapper.getById(productId);
    }
}
