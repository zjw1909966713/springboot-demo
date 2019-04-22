package com.highrock.model;

import java.io.Serializable;

/**
 * @author 张进文
 * @ClassName CpStyleStoreAuthorization
 * @Description TODO
 * @Date 2018/9/11 8:21
 * @Version 1.0
 */
public class CpStyleStoreAuthorization implements Serializable {

    private Integer id;
    private String store_no;
    private String style_no;
    private String commission;

    //价格策略公式
    private String price_policy;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStore_no() {
        return store_no;
    }

    public void setStore_no(String store_no) {
        this.store_no = store_no;
    }

    public String getStyle_no() {
        return style_no;
    }

    public void setStyle_no(String style_no) {
        this.style_no = style_no;
    }

    public String getCommission() {
        return commission;
    }

    public void setCommission(String commission) {
        this.commission = commission;
    }

    public String getPrice_policy() {
        return price_policy;
    }

    public void setPrice_policy(String price_policy) {
        this.price_policy = price_policy;
    }
}
