package com.linghd.dto;

/**
 * Created by Ling on 2016/12/28.
 */
public class OrderRequest {
    private long productId;
    private int number;

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
