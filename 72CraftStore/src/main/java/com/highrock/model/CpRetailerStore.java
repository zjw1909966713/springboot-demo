package com.highrock.model;

import java.io.Serializable;

/**
 * 零售商店
 *
 * @author zhangjinwen
 * @create 2017-11-01 11:58
 * @desc
 **/
public class CpRetailerStore implements Serializable {


    private static final long serialVersionUID = -769945113704712451L;
    private Integer id;//
    private String retailer_no;//
    private String store_no;//
    private String name;//
    private String authorization;//
    private String token;//
    private String state_code;//
    private String address;//
    private String city;//
    private String zip;//

    private String paywork_id;//payworks 商家id
    private String paywork_key;//payworks 商家key

    public String getPaywork_id() {
        return paywork_id;
    }

    public void setPaywork_id(String paywork_id) {
        this.paywork_id = paywork_id;
    }

    public String getPaywork_key() {
        return paywork_key;
    }

    public void setPaywork_key(String paywork_key) {
        this.paywork_key = paywork_key;
    }

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

    public String getStore_no() {
        return store_no;
    }

    public void setStore_no(String store_no) {
        this.store_no = store_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getState_code() {
        return state_code;
    }

    public void setState_code(String state_code) {
        this.state_code = state_code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
