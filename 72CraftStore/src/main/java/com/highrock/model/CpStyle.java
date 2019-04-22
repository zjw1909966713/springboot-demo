package com.highrock.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author 张进文
 * @ClassName CpStyle
 * @Description TODO
 * @Date 2018/9/11 9:44
 * @Version 1.0
 */
public class CpStyle implements Serializable {

    private Integer id;
    private String style_no;
    private String name;
    private BigDecimal price;
    private Integer brand_id;
    private Integer product_category;
    private String img_path;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStyle_no() {
        return style_no;
    }

    public void setStyle_no(String style_no) {
        this.style_no = style_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(Integer brand_id) {
        this.brand_id = brand_id;
    }

    public Integer getProduct_category() {
        return product_category;
    }

    public void setProduct_category(Integer product_category) {
        this.product_category = product_category;
    }

    public String getImg_path() {
        return img_path;
    }

    public void setImg_path(String img_path) {
        this.img_path = img_path;
    }
}
