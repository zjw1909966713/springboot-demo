package com.highrock.controller;


import com.highrock.service.StoreService;
import com.highrock.util.BaseJsonRst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 */
@RestController
@RequestMapping("/store")
@CrossOrigin
public class StoreController {

    @Autowired
    private StoreService storeService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * 商店登录获取token
     *
     * @param storeNo
     * @param authorization
     * @return
     */
    @GetMapping("/auth/{storeNo}/{authorization}")
    public BaseJsonRst storeLogin(@PathVariable String storeNo, @PathVariable String authorization) {
        logger.info("============零售商登录请求获取token==========================");

        BaseJsonRst ret = storeService.storeLogin(storeNo, authorization);
        return ret;
    }


    @GetMapping("/auth/{token}")
    public BaseJsonRst getStoreNo(@PathVariable String token) {
        logger.info("==================通过token请求==========================");
        BaseJsonRst ret = storeService.getStoreNo(token);
        return ret;
    }



    /**
     * @description: 保存零售商店
     * @author: 张进文
     * @param: [data]

    {
    "retailer_no": "USA999",
    "store_no": "STR999",
    "name": "Test Store",
    "authorization": "",
    "token": "123456789@987654321",
    "state_code": "WI",
    "address": "634 W Main",
    "city": "Madison",
    "zip": "53703"
    "paywork_id":"",
    "paywork_key":""
    }

     * @return: com.highrock.util.BaseJsonRst
     * @date: 2018/9/6 9:13
     * @version: 1.0
     */
    @PostMapping("")
    public BaseJsonRst saveRetailerStore(@RequestBody String data){
        logger.info("================保存零售商店====================");
        BaseJsonRst ret=storeService.saveRetailerStore(data);
        return ret;
    }



    /**
     * @description: 添加价格公式
     * @author: 张进文
     * @param: [data]
        {
            "store_no":"",
            "style_no":"",
            "commission":"",
            "price_policy":""
        },
    {
    "store_no":"",
    "style_no":"",
    "commission":"",
    "price_policy":""
    }
     * @return: com.highrock.util.BaseJsonRst
     * @date: 2018/9/11 8:55
     * @version: 1.0
     */
    @PostMapping("/style/all")
    public BaseJsonRst saveCpStyleStoreAuthorizationAll(@RequestBody String data){
        logger.info("===========添加价格公式====================");
        BaseJsonRst ret=storeService.saveCpStyleStoreAuthorizationAll(data);
        return ret;
    }


    /**
     * @description: 添加价格公式
     * @author: 张进文
     * @param: [data]
    {
    "store_no":"",
    "style_no":"",
    "commission":"",
    "price_policy":""
    }
     * @return: com.highrock.util.BaseJsonRst
     * @date: 2018/9/11 8:55
     * @version: 1.0
     */
    @PostMapping("/style")
    public BaseJsonRst saveCpStyleStoreAuthorization(@RequestBody String data){
        logger.info("===========添加价格公式====================");
        BaseJsonRst ret=storeService.saveCpStyleStoreAuthorization(data);
        return ret;
    }

    /**
     * @description: 根据查找
     * @author: 张进文
     * @param: [storeNo, styleNo]
     * @return: com.highrock.util.BaseJsonRst
     * @date: 2018/9/11 9:07
     * @version: 1.0
     */
    @GetMapping("/{storeNo}/style/{styleNo}")
    public BaseJsonRst getCpStyleStoreAuthorization(@PathVariable String storeNo,@PathVariable String styleNo){
        BaseJsonRst ret=storeService.getCpStyleStoreAuthorizationByStoreNoAndStyleNo(storeNo,styleNo);
      return ret;
    }



    /**
     * @description: 删除
     * @author: 张进文
     * @param: [storeNo, styleNo]
     * @return: com.highrock.util.BaseJsonRst
     * @date: 2018/9/11 9:07
     * @version: 1.0
     */
    @DeleteMapping("/{storeNo}/style/{styleNo}")
    public BaseJsonRst deleteCpStyleStoreAuthorization(@PathVariable String storeNo,@PathVariable String styleNo){
        BaseJsonRst ret=storeService.deleteCpStyleStoreAuthorizationByStoreNoAndStyleNo(storeNo,styleNo);
        return ret;
    }


    /**
     * @description: 更新
     * @author: 张进文
     * @param: [storeNo, styleNo, data]
     data {
    "commission":"",
    "price_policy":""
    }
     * @return: com.highrock.util.BaseJsonRst
     * @date: 2018/9/11 9:33
     * @version: 1.0
     */
    @PutMapping("/{storeNo}/style/{styleNo}")
    public BaseJsonRst updateCpStyleStoreAuthorization(@PathVariable String storeNo,@PathVariable String styleNo,@RequestBody String data){

        BaseJsonRst ret=storeService.updateCpStyleStoreAuthorizationByStoreNoAndStyleNo(storeNo,styleNo,data);
        return ret;
    }



    /** *
     * @description: 通过store查找
     * @author: 张进文
     * @param: [storeNo]
     * @return: com.highrock.util.BaseJsonRst
     * @date: 2018/12/12 9:40
     * @version: 1.0
     */
    @GetMapping("/{storeNo}")
    public  BaseJsonRst getCpRetailerStoreByStoreNo(@PathVariable String storeNo){
        BaseJsonRst ret=storeService.getCpRetailerStoreByStoreNo(storeNo);
        return ret;
    }



    /** *
     * @description: 把store缓存到redis中
     * @author: 张进文
     * @param: []
     * @return: com.highrock.util.BaseJsonRst
     * @date: 2019/4/10 11:58
     * @version: 1.0
     */
    @GetMapping("/setStoreRedis")
    public BaseJsonRst setStoreRedis(){
        BaseJsonRst ret=storeService.setStoreRedis();
        return ret;
    }




}
