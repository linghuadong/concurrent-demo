package com.linghd.mapper;

import com.linghd.entity.Product;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Ling on 2016/12/28.
 */
public interface ProductMapper {

    Product getById(long id);

    int updateStockQuantity(@Param("number") int number, @Param("id") long id);

    long getStockQuantity(long id);
}
