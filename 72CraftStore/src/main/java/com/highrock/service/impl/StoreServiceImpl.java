package com.highrock.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.highrock.dto.NexusAddressDTO;
import com.highrock.mapper.CpRetailerMapper;
import com.highrock.mapper.CpRetailerStoreMapper;
import com.highrock.mapper.CpStyleMapper;
import com.highrock.mapper.CpStyleStoreAuthorizationMapper;
import com.highrock.model.CpRetailer;
import com.highrock.model.CpRetailerStore;
import com.highrock.model.CpStyle;
import com.highrock.model.CpStyleStoreAuthorization;
import com.highrock.service.StoreService;
import com.highrock.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class StoreServiceImpl implements StoreService {


    @Autowired
    private CpRetailerStoreMapper cpRetailerStoreMapper;


    @Autowired
    private CpRetailerMapper cpRetailerMapper;



    @Autowired
    private CpStyleStoreAuthorizationMapper cpStyleStoreAuthorizationMapper;


    @Autowired
    private CpStyleMapper cpStyleMapper;


    @Autowired
    private RedisUtil redisUtil;


    @Override
    public BaseJsonRst storeLogin(String storeNo, String authorization) {
        BaseJsonRst ret = new BaseJsonRst();

        //
        if (StringUtil.isBlank(storeNo)) {
            ret.setSuccess(false);
            ret.setMessage("storeNo missing");
            return ret;
        }

        if (StringUtil.isBlank(authorization)) {
            ret.setSuccess(false);
            ret.setMessage("authorization missing");
            return ret;
        }

        CpRetailerStore store = cpRetailerStoreMapper.getCpRetailerStoreByStoreNo(storeNo);

        if (store == null || (!authorization.equals(store.getAuthorization()))) {
            //登录失败
            ret.setSuccess(false);
            ret.setMessage("storeNo or  authorization  error");
            return ret;
        }

        JSONObject jsonObject=new JSONObject();

        String storeToken=null;

        if (StringUtil.isBlank(store.getToken())) {
            //如果store token为空，则新建一个token
            String token = TokenUtil.createToken();
            //创建token
            CpRetailerStore store1 = new CpRetailerStore();
            store1.setId(store.getId());
            store1.setToken(token);
            cpRetailerStoreMapper.updateCpRetailerStoreById(store1);

            storeToken=token;
            //ret.setResult(token);

        } else {
           // ret.setResult(store.getToken());
            storeToken=store.getToken();
        }

        jsonObject.put("token",storeToken);
        jsonObject.put("payworkId",store.getPaywork_id());
        jsonObject.put("payworkKey",store.getPaywork_key());

        ret.setSuccess(true);

        ret.setResult(jsonObject);

        return ret;
    }

    @Override
    public BaseJsonRst getStoreNo(String token) {
        BaseJsonRst ret = new BaseJsonRst();

        //
        if (StringUtil.isBlank(token)) {
            ret.setSuccess(false);
            ret.setMessage("token missing");
            return ret;
        }

        CpRetailerStore store = cpRetailerStoreMapper.getCpRetailerStoreByToken(token);

        if (store == null) {
            ret.setSuccess(false);
            ret.setMessage("find the failure");
            return ret;
        }


        ret.setSuccess(true);
        ret.setResult(store.getStore_no());

        return ret;
    }

    @Override
    public BaseJsonRst saveStore(String data) {
        BaseJsonRst ret = new BaseJsonRst();
        if (StringUtil.isBlank(data)) {
            ret.setSuccess(false);
            ret.setMessage("missing requestbody");
            return ret;
        }

        Map<String,Object> dataMap= JSONHelper.convertJsonStrToMap(data);

        //
        if (!dataMap.containsKey("cp_retailer")){
            ret.setSuccess(false);
            ret.setMessage("missing cp_retailer filed");
            return ret;
        }

        if (!dataMap.containsKey("cp_retailer_store")){
            ret.setSuccess(false);
            ret.setMessage("missing cp_retailer_store filed");
            return ret;
        }


        String retailerStr=dataMap.get("cp_retailer").toString();


        List<CpRetailer> retailerList= JSONObject.parseArray(retailerStr,CpRetailer.class);


        for(CpRetailer cpRetailer:retailerList){

          CpRetailer cpRetailer1=  cpRetailerMapper.getCpRetailerByRetailerNo(cpRetailer.getRetailer_no());
          if (cpRetailer1==null){
              cpRetailerMapper.saveCpRetailer(cpRetailer);
          }else {
              cpRetailerMapper.updateCpRetailerByRetailerNo(cpRetailer);
          }
        }


        String retailerStoreStr=dataMap.get("cp_retailer_store").toString();
        List<CpRetailerStore> cpRetailerStoreList=JSONObject.parseArray(retailerStoreStr,CpRetailerStore.class);



        for (CpRetailerStore cpRetailerStore :cpRetailerStoreList ){


           List<CpRetailerStore> list= cpRetailerStoreMapper.getCpRetailerStoreByRetailerNoAndStoreNo(cpRetailerStore.getRetailer_no(),cpRetailerStore.getStore_no());
           if(list==null||list.size()==0){

               cpRetailerStoreMapper.saveCpRetailerStore(cpRetailerStore);
           }else{
               cpRetailerStoreMapper.updateCpRetailerStoreByRetailerNoAndStoreNo(cpRetailerStore);
           }
        }

        ret.setSuccess(true);
        ret.setMessage("success");
        return ret;
    }

    @Override
    public BaseJsonRst saveRetailer(String data) {
        BaseJsonRst ret = new BaseJsonRst();
        if (StringUtil.isBlank(data)) {
            ret.setSuccess(false);
            ret.setMessage("missing requestbody");
            return ret;
        }
        Map<String,Object> dataMap= JSONHelper.convertJsonStrToMap(data);

        if (!dataMap.containsKey("retailer_no")||StringUtil.isBlank(dataMap.get("retailer_no").toString())){
            ret.setSuccess(false);
            ret.setMessage("missing retailer_no field or retailer_no is blank ");
            return ret;
        }


        if (!dataMap.containsKey("name")||StringUtil.isBlank(dataMap.get("name").toString())){
            ret.setSuccess(false);
            ret.setMessage("missing name field or name is blank ");
            return ret;
        }

       String retailer_no=dataMap.get("retailer_no").toString();
       String name=dataMap.get("name").toString();

       CpRetailer existCpRetailer=cpRetailerMapper.getCpRetailerByRetailerNo(retailer_no);
       if (existCpRetailer!=null){
           ret.setSuccess(false);
           ret.setMessage("retailer_no already exists");
           return ret;
       }

       CpRetailer cpRetailer=JSONObject.parseObject(data,CpRetailer.class);
       cpRetailerMapper.saveCpRetailer(cpRetailer);
       ret.setSuccess(true);
       ret.setMessage("success");
       return ret;
    }

    @Override
    public BaseJsonRst saveRetailerStore(String data) {
        BaseJsonRst ret = new BaseJsonRst();
        if (StringUtil.isBlank(data)) {
            ret.setSuccess(false);
            ret.setMessage("missing requestbody");
            return ret;
        }
        Map<String,Object> dataMap= JSONHelper.convertJsonStrToMap(data);

        if (!dataMap.containsKey("retailer_no")||StringUtil.isBlank(dataMap.get("retailer_no").toString())){
            ret.setSuccess(false);
            ret.setMessage("missing retailer_no field or retailer_no is blank ");
            return ret;
        }

        if (!dataMap.containsKey("store_no")||StringUtil.isBlank(dataMap.get("store_no").toString())){
            ret.setSuccess(false);
            ret.setMessage("missing store_no field or store_no is blank ");
            return ret;
        }

        String retailer_no=dataMap.get("retailer_no").toString();
        String store_no=dataMap.get("store_no").toString();

        CpRetailer existCpRetailer=cpRetailerMapper.getCpRetailerByRetailerNo(retailer_no);
        if (existCpRetailer==null){
            ret.setSuccess(false);
            ret.setMessage("Retailer_no does not exist");
            return ret;
        }

        List<CpRetailerStore> existCpRetailerStore=cpRetailerStoreMapper.getCpRetailerStoreByRetailerNoAndStoreNo(retailer_no,store_no);

        if(existCpRetailerStore!=null&&existCpRetailerStore.size()!=0){
            ret.setSuccess(false);
            ret.setMessage("Retailer_no's store_no already exists");
            return ret;
        }

        CpRetailerStore cpRetailerStore=JSONObject.parseObject(data,CpRetailerStore.class);

        cpRetailerStoreMapper.saveCpRetailerStore(cpRetailerStore);
        ret.setSuccess(true);
        ret.setMessage("success");
        return ret;
    }

    @Override
    public BaseJsonRst updateRetailer(String retailerNo,String data) {
            BaseJsonRst ret = new BaseJsonRst();
            if (StringUtil.isBlank(data)) {
                ret.setSuccess(false);
                ret.setMessage("missing requestbody");
                return ret;
            }
            if (StringUtil.isBlank(retailerNo)) {
                ret.setSuccess(false);
                ret.setMessage("missing retailerNo");
                return ret;
            }
            Map<String,Object> dataMap= JSONHelper.convertJsonStrToMap(data);




            if (!dataMap.containsKey("name")||StringUtil.isBlank(dataMap.get("name").toString())){
                ret.setSuccess(false);
                ret.setMessage("missing name field or name is blank ");
                return ret;
            }

            String name=dataMap.get("name").toString();

            CpRetailer existCpRetailer=cpRetailerMapper.getCpRetailerByRetailerNo(retailerNo);
            if (existCpRetailer==null){
                ret.setSuccess(false);
                ret.setMessage("retailer_no does not exists");
                return ret;
            }


            CpRetailer cpRetailer=JSONObject.parseObject(data,CpRetailer.class);
            cpRetailer.setRetailer_no(retailerNo);

            cpRetailerMapper.updateCpRetailerByRetailerNo(cpRetailer);

            ret.setSuccess(true);
            ret.setMessage("success");
            return ret;


    }

    @Override
    public BaseJsonRst deleteRetailer(String retailerNo) {
        BaseJsonRst ret = new BaseJsonRst();
        if (StringUtil.isBlank(retailerNo)) {
            ret.setSuccess(false);
            ret.setMessage("retailerNo missing");
            return ret;
        }

        CpRetailer existCpRetailer=cpRetailerMapper.getCpRetailerByRetailerNo(retailerNo);
        if (existCpRetailer==null){
            ret.setSuccess(false);
            ret.setMessage("Retailer_no does not exist or has been deleted");
            return ret;
        }

        cpRetailerMapper.deleteCpRetailerByRetailerNo(retailerNo);

        ret.setSuccess(true);
        ret.setMessage("success");
        return ret;
    }

    @Override
    public BaseJsonRst updateRetailerStore(String retailerNo,String storeNo,String data) {
        BaseJsonRst ret = new BaseJsonRst();
        if (StringUtil.isBlank(data)) {
            ret.setSuccess(false);
            ret.setMessage("missing requestbody");
            return ret;
        }
        if (StringUtil.isBlank(retailerNo)) {
            ret.setSuccess(false);
            ret.setMessage("missing retailer_no field or retailer_no is blank ");
            return ret;
        }
        if (StringUtil.isBlank(storeNo)) {
            ret.setSuccess(false);
            ret.setMessage("missing store_no field or store_no is blank");
            return ret;
        }

        List<CpRetailerStore> existCpRetailerStore=cpRetailerStoreMapper.getCpRetailerStoreByRetailerNoAndStoreNo(retailerNo,storeNo);

        if(existCpRetailerStore==null||existCpRetailerStore.size()==0){
            ret.setSuccess(false);
            ret.setMessage("Retailer_no's store_no does not exist");
            return ret;
        }



        CpRetailerStore cpRetailerStore=JSONObject.parseObject(data,CpRetailerStore.class);

        cpRetailerStore.setRetailer_no(retailerNo);
        cpRetailerStore.setStore_no(storeNo);
        cpRetailerStoreMapper.updateCpRetailerStoreByRetailerNoAndStoreNo(cpRetailerStore);

        ret.setSuccess(true);
        ret.setMessage("success");
        return ret;
    }

    @Override
    public BaseJsonRst deleteRetailerStore(String retailerNo, String storeNo) {
        BaseJsonRst ret = new BaseJsonRst();

        if (StringUtil.isBlank(retailerNo)) {
            ret.setSuccess(false);
            ret.setMessage("missing retailer_no field or retailer_no is blank ");
            return ret;
        }
        if (StringUtil.isBlank(storeNo)) {
            ret.setSuccess(false);
            ret.setMessage("missing store_no field or store_no is blank");
            return ret;
        }




        List<CpRetailerStore> existCpRetailerStore=cpRetailerStoreMapper.getCpRetailerStoreByRetailerNoAndStoreNo(retailerNo,storeNo);

        if(existCpRetailerStore==null||existCpRetailerStore.size()==0){
            ret.setSuccess(false);
            ret.setMessage("Retailer_no's store_no does not exist");
            return ret;
        }

        cpRetailerStoreMapper.deleteCpRetailerStoreByRetailerNoAndStoreNo(retailerNo,storeNo);
        ret.setSuccess(true);
        ret.setMessage("success");
        return ret;
    }


    @Override
    public BaseJsonRst saveCpStyleStoreAuthorization(String data) {
        BaseJsonRst ret = new BaseJsonRst();
        if (StringUtil.isBlank(data)) {
            ret.setSuccess(false);
            ret.setMessage("missing requestbody");
            return ret;
        }

        Map<String,Object> dataMap= JSONHelper.convertJsonStrToMap(data);

        if (!dataMap.containsKey("store_no")||StringUtil.isBlank(dataMap.get("store_no").toString())){
            ret.setSuccess(false);
            ret.setMessage("missing store_no field or store_no is blank ");
            return ret;
        }

        if (!dataMap.containsKey("style_no")||StringUtil.isBlank(dataMap.get("style_no").toString())){
            ret.setSuccess(false);
            ret.setMessage("missing style_no field or style_no is blank ");
            return ret;
        }

        String store_no=dataMap.get("store_no").toString();
        String style_no=dataMap.get("style_no").toString();


        CpRetailerStore cpRetailerStore=cpRetailerStoreMapper.getCpRetailerStoreByStoreNo(store_no);
        if(cpRetailerStore==null){
            ret.setSuccess(false);
            ret.setMessage("store_no does not exist");
            return ret;
        }


        CpStyle cpStyle=cpStyleMapper.getCpStyleByStyleNo(style_no);

        if (cpStyle==null){
            ret.setSuccess(false);
            ret.setMessage("style_no does not exist");
            return ret;

        }


        CpStyleStoreAuthorization cpStyleStoreAuthorizationExist=cpStyleStoreAuthorizationMapper.getCpStyleStoreAuthorizationByStoreNoAndStyleNo(store_no,style_no);
        if(cpStyleStoreAuthorizationExist!=null){
            ret.setSuccess(false);
            ret.setMessage("Store_no's style_no already exists");
            return ret;
        }

        CpStyleStoreAuthorization cpStyleStoreAuthorization=JSONObject.parseObject(data,CpStyleStoreAuthorization.class);

        cpStyleStoreAuthorizationMapper.saveCpStyleStoreAuthorization(cpStyleStoreAuthorization);

        ret.setSuccess(true);
        ret.setMessage("success");


        return ret;
    }

    @Override
    public BaseJsonRst getCpStyleStoreAuthorizationByStoreNoAndStyleNo(String storeNo, String styleNo) {
        BaseJsonRst ret = new BaseJsonRst();

        if (StringUtil.isBlank(storeNo)) {
            ret.setSuccess(false);
            ret.setMessage("missing store_no field or store_no is blank ");
            return ret;
        }
        if (StringUtil.isBlank(styleNo)) {
            ret.setSuccess(false);
            ret.setMessage("missing style_no field or style_no is blank");
            return ret;
        }

        CpRetailerStore cpRetailerStore=cpRetailerStoreMapper.getCpRetailerStoreByStoreNo(storeNo);
        if(cpRetailerStore==null){
            ret.setSuccess(false);
            ret.setMessage("store_no does not exist");
            return ret;
        }


        CpStyle cpStyle=cpStyleMapper.getCpStyleByStyleNo(styleNo);

        if (cpStyle==null){
            ret.setSuccess(false);
            ret.setMessage("style_no does not exist");
            return ret;

        }


        CpStyleStoreAuthorization cpStyleStoreAuthorizationExist=cpStyleStoreAuthorizationMapper.getCpStyleStoreAuthorizationByStoreNoAndStyleNo(storeNo,styleNo);

        if (cpStyleStoreAuthorizationExist==null){
            ret.setSuccess(false);
            ret.setMessage("Store_no's style_no does not exist");
            return ret;
        }

        ret.setSuccess(true);
        ret.setMessage("success");
        ret.setResult(cpStyleStoreAuthorizationExist);


        return ret;
    }

    @Override
    public BaseJsonRst deleteCpStyleStoreAuthorizationByStoreNoAndStyleNo(String storeNo, String styleNo) {
        BaseJsonRst ret = new BaseJsonRst();

        if (StringUtil.isBlank(storeNo)) {
            ret.setSuccess(false);
            ret.setMessage("missing store_no field or store_no is blank ");
            return ret;
        }
        if (StringUtil.isBlank(styleNo)) {
            ret.setSuccess(false);
            ret.setMessage("missing style_no field or style_no is blank");
            return ret;
        }

        CpRetailerStore cpRetailerStore=cpRetailerStoreMapper.getCpRetailerStoreByStoreNo(storeNo);
        if(cpRetailerStore==null){
            ret.setSuccess(false);
            ret.setMessage("store_no does not exist");
            return ret;
        }


        CpStyle cpStyle=cpStyleMapper.getCpStyleByStyleNo(styleNo);

        if (cpStyle==null){
            ret.setSuccess(false);
            ret.setMessage("style_no does not exist");
            return ret;

        }


        int deleteSum=cpStyleStoreAuthorizationMapper.deleteCpStyleStoreAuthorizationByStoreNoAndStyleNo(storeNo,styleNo);

        if(deleteSum==0){
            ret.setSuccess(false);
            ret.setMessage("Store_no's style_no does not exist");
            return ret;
        }

        ret.setSuccess(true);
        ret.setMessage("success");
        return ret;
    }


    @Override
    public BaseJsonRst updateCpStyleStoreAuthorizationByStoreNoAndStyleNo(String storeNo, String styleNo, String data) {
        BaseJsonRst ret = new BaseJsonRst();
        if (StringUtil.isBlank(data)) {
            ret.setSuccess(false);
            ret.setMessage("missing requestbody");
            return ret;
        }
        if (StringUtil.isBlank(storeNo)) {
            ret.setSuccess(false);
            ret.setMessage("missing storeNo field or storeNo is blank ");
            return ret;
        }
        if (StringUtil.isBlank(styleNo)) {
            ret.setSuccess(false);
            ret.setMessage("missing styleNo field or styleNo is blank");
            return ret;
        }


        CpRetailerStore cpRetailerStore=cpRetailerStoreMapper.getCpRetailerStoreByStoreNo(storeNo);
        if(cpRetailerStore==null){
            ret.setSuccess(false);
            ret.setMessage("store_no does not exist");
            return ret;
        }


        CpStyle cpStyle=cpStyleMapper.getCpStyleByStyleNo(styleNo);

        if (cpStyle==null){
            ret.setSuccess(false);
            ret.setMessage("style_no does not exist");
            return ret;

        }

        CpStyleStoreAuthorization cpStyleStoreAuthorizationExist=cpStyleStoreAuthorizationMapper.getCpStyleStoreAuthorizationByStoreNoAndStyleNo(storeNo,styleNo);


        if (cpStyleStoreAuthorizationExist==null){
            ret.setSuccess(false);
            ret.setMessage("Store_no's style_no does not exist");
            return ret;
        }


        CpStyleStoreAuthorization cpStyleStoreAuthorization=JSONObject.parseObject(data,CpStyleStoreAuthorization.class);
        cpStyleStoreAuthorization.setStore_no(storeNo);
        cpStyleStoreAuthorization.setStyle_no(styleNo);
        cpStyleStoreAuthorizationMapper.updateCpStyleStoreAuthorizationByStoreNoAndStyleNo(cpStyleStoreAuthorization);
        ret.setSuccess(true);
        ret.setMessage("success");
        return ret;
    }

    @Override
    public BaseJsonRst saveCpStyleStoreAuthorizationAll(String data) {
        BaseJsonRst ret = new BaseJsonRst();
        if (StringUtil.isBlank(data)) {
            ret.setSuccess(false);
            ret.setMessage("missing requestbody");
            return ret;
        }

        List<CpStyleStoreAuthorization> cpStyleStoreAuthorizationList=JSONObject.parseArray(data,CpStyleStoreAuthorization.class);

        for (CpStyleStoreAuthorization cpStyleStoreAuthorization:cpStyleStoreAuthorizationList){
            CpStyleStoreAuthorization cpStyleStoreAuthorization1=cpStyleStoreAuthorizationMapper.getCpStyleStoreAuthorizationByStoreNoAndStyleNo(cpStyleStoreAuthorization.getStore_no(),cpStyleStoreAuthorization.getStyle_no());
            if (cpStyleStoreAuthorization1==null){
                CpRetailerStore cpRetailerStore=cpRetailerStoreMapper.getCpRetailerStoreByStoreNo(cpStyleStoreAuthorization.getStore_no());
                if(cpRetailerStore==null){
                    ret.setSuccess(false);
                    ret.setMessage("store_no does not exist");
                    return ret;
                }


                CpStyle cpStyle=cpStyleMapper.getCpStyleByStyleNo(cpStyleStoreAuthorization.getStyle_no());

                if (cpStyle==null){
                    ret.setSuccess(false);
                    ret.setMessage("style_no does not exist");
                    return ret;

                }


                cpStyleStoreAuthorizationMapper.saveCpStyleStoreAuthorization(cpStyleStoreAuthorization);
            }else{
                cpStyleStoreAuthorizationMapper.updateCpStyleStoreAuthorizationByStoreNoAndStyleNo(cpStyleStoreAuthorization);
            }
        }


        ret.setSuccess(true);
        ret.setMessage("success");
        return ret;
    }

    @Override
    public BaseJsonRst getCpRetailerStoreByStoreNo(String storeNo) {
        BaseJsonRst ret = new BaseJsonRst();
        if (StringUtil.isBlank(storeNo)) {
            ret.setSuccess(false);
            ret.setMessage("missing storeNo");
            return ret;
        }

        CpRetailerStore store = cpRetailerStoreMapper.getCpRetailerStoreByStoreNo(storeNo);
        if(store==null){
            ret.setSuccess(false);
            ret.setMessage("This storeNo does not exist");
            return ret;
        }

        ret.setSuccess(true);
        store.setToken(null);
        store.setAuthorization(null);
        ret.setMessage("success");
        ret.setResult(store);


        return ret;
    }

    @Override
    public BaseJsonRst setStoreRedis() {
        List<CpRetailerStore> retailerStoreListAll = cpRetailerStoreMapper.getCpRetailerStoreListAll();

         List<NexusAddressDTO>  addressDTOList=  retailerStoreListAll.stream().map(e->new NexusAddressDTO(e.getStore_no(),"US",e.getZip(),e.getState_code(),e.getCity(),e.getAddress())).collect(Collectors.toList());

         redisUtil.set("store_product", JSON.toJSONString(addressDTOList));


        return BaseJsonRst.success();
    }
}
