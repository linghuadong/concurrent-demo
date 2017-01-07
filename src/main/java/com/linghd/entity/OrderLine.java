package com.linghd.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Ling on 2016/12/28.
 */
public class OrderLine implements Serializable {

    private static final long serialVersionUID = -1480992969640583533L;
    private long id;
    private long orderNumber;
    private long productId;
    private String productName;
    private String productPicture;
    private BigDecimal productPrice;
    private int num;

    public OrderLine(long orderNumber, Product product, int num) {
        this.orderNumber = orderNumber;
        this.productId = product.getId();
        this.productName = product.getName();
        this.productPicture = product.getPicture();
        this.productPrice = product.getPrice();
        this.num = num;
    }

    public OrderLine(Product product, int num) {
        this(0L,product, num);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPicture() {
        return productPicture;
    }

    public void setProductPicture(String productPicture) {
        this.productPicture = productPicture;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
