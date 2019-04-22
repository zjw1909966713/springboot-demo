package com.highrock.model;

import java.io.Serializable;

/**
 * @author 张进文
 * @ClassName CpRetailer
 * @Description 零售商
 * @Date 2018/9/5 16:27
 * @Version 1.0
 */
public class CpRetailer implements Serializable{


    /**
     *
     */
    private Integer id;

    /**
     *零售商号
     */
    private  String retailer_no;

    /**
     *零售商名字
     */
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRetailer_no() {
        return retailer_no;
    }

    public void setRetailer_no(String retailer_no) {
        this.retailer_no = retailer_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
