package com.highrock.controller;


import com.highrock.service.StoreService;
import com.highrock.util.BaseJsonRst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
 * @author 张进文
 * @ClassName RetailerController
 * @Description 零售商controller
 * @Date 2018/9/6 8:56
 * @Version 1.0
 */
@RestController
@RequestMapping("/retailer")
@CrossOrigin
public class RetailerController  {

    @Autowired
    private StoreService storeService;

    /**
     * @description: 保存零售商
     * @author: 张进文
     * @param: [data]
    {
    "cp_retailer": [
    {
    "id": 1,
    "retailer_no": "USA001",
    "name": "A-16"
    },
    {
    "id": 2,
    "retailer_no": "USA002",
    "name": "Midwest Mountain"
    },
    {
    "id": 3,
    "retailer_no": "USA999",
    "name": "TEST"
    }
    ],
    "cp_retailer_store": [
    {
    "id": 1,
    "retailer_no": "USA001",
    "store_no": "STR001",
    "name": "Ute Mountaineer",
    "authorization": "",
    "token": "",
    "state_code": "CO",
    "address": "210 S Galena St",
    "city": "Aspen",
    "zip": "81611"
    },
    {
    "id": 2,
    "retailer_no": "USA002",
    "store_no": "STR002",
    "name": "Midwest Mountaineering",
    "authorization": "",
    "token": "",
    "state_code": "MN",
    "address": "309 Cedar Ave S",
    "city": "Minneapolis",
    "zip": "55454"
    },
    {
    "id": 3,
    "retailer_no": "USA999",
    "store_no": "STR999",
    "name": "Test Store",
    "authorization": "",
    "token": "123456789@987654321",
    "state_code": "WI",
    "address": "634 W Main",
    "city": "Madison",
    "zip": "53703"
    }
    ]
    }

     * @return: com.highrock.util.BaseJsonRst
     * @date: 2018/9/5 16:31
     * @version: 1.0
     */
    @PostMapping("/all")
    public BaseJsonRst saveStore(@RequestBody String data){
        BaseJsonRst ret= storeService.saveStore(data);
        return ret;
    }


    /**
     * @description: 保存零售商
     * @author: 张进文
     * @param:
    {
    "retailer_no":"",
    "name":""
    }

     * @return: com.highrock.util.BaseJsonRst
     * @date: 2018/9/6 8:10
     * @version: 1.0
     */
    @PostMapping("")
    public BaseJsonRst saveRetailer(@RequestBody String data){
        BaseJsonRst ret= storeService.saveRetailer(data);
        return ret;
    }

    /**
     * @description: 更新
     * @author: 张进文
     * @param: [data]
    {
    "name":""
    }
     * @return: com.highrock.util.BaseJsonRst
     * @date: 2018/9/6 9:41
     * @version: 1.0
     */
    @PutMapping("/{retailerNo}")
    public  BaseJsonRst updateRetailer(@PathVariable String retailerNo,@RequestBody String data){
        BaseJsonRst ret= storeService.updateRetailer(retailerNo,data);
        return ret;
    }

    /**
     * @description: 删除
     * @author: 张进文
     * @param: [retailerNo]
     * @return: com.highrock.util.BaseJsonRst
     * @date: 2018/9/6 9:55
     * @version: 1.0
     */
    @DeleteMapping("/{retailerNo}")
    public BaseJsonRst deleteRetailer(@PathVariable String retailerNo){
        BaseJsonRst ret= storeService.deleteRetailer(retailerNo);
        return ret;

    }


    /**
     * @description: 更新
     * @author: 张进文
     * @param: [data]
    {
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
     * @date: 2018/9/6 10:11
     * @version: 1.0
     */
    @PutMapping("/{retailerNo}/store/{storeNo}")
    public BaseJsonRst updateRetailerStore(@PathVariable String retailerNo,@PathVariable String storeNo,@RequestBody String data){
        BaseJsonRst ret=storeService.updateRetailerStore(retailerNo,storeNo,data);
        return ret;
    }




    /**
     * @description: 删除
     * @author: 张进文
     * @param: [data]

     * @return: com.highrock.util.BaseJsonRst
     * @date: 2018/9/6 10:11
     * @version: 1.0
     */
    @DeleteMapping("/{retailerNo}/store/{storeNo}")
    public BaseJsonRst deleteRetailerStore(@PathVariable String retailerNo,@PathVariable String storeNo){
        BaseJsonRst ret=storeService.deleteRetailerStore(retailerNo,storeNo);
        return ret;
    }



}
