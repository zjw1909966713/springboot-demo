package com.highrock.service;

import com.highrock.util.BaseJsonRst;

public interface StoreService {

    /**
     * 零售商登录获取token
     * @param storeNo
     * @param authorization
     * @return
     */
    BaseJsonRst storeLogin(String storeNo, String authorization );


    /**
     * 根据token
     * @param token
     * @return
     */
    BaseJsonRst getStoreNo(String token);

    /**
     * @description: 保存零售商和零售商店
     * @author: 张进文
     * @param: [data]
     * @return: com.highrock.util.BaseJsonRst
     * @date: 2018/9/5 16:39
     * @version: 1.0
     */
    BaseJsonRst saveStore(String data);

    /**
     * @description: 保存零售商
     * @author: 张进文
     * @param: [data]
     * @return: com.highrock.util.BaseJsonRst
     * @date: 2018/9/6 8:25
     * @version: 1.0
     */
    BaseJsonRst saveRetailer(String data);

    /**
     * @description: 保存零售商店
     * @author: 张进文
     * @param: [data]
     * @return: com.highrock.util.BaseJsonRst
     * @date: 2018/9/6 9:18
     * @version: 1.0
     */
    BaseJsonRst saveRetailerStore(String data);

    /**
     * @description: 更新零售商
     * @author: 张进文
     * @param: [data]
     * @return: com.highrock.util.BaseJsonRst
     * @date: 2018/9/6 9:41
     * @version: 1.0
     */
    BaseJsonRst updateRetailer(String retailerNo,String data);

    /**
     * @description: 根据retailerNo删除
     * @author: 张进文
     * @param: [retailerNo]
     * @return: com.highrock.util.BaseJsonRst
     * @date: 2018/9/6 9:57
     * @version: 1.0
     */
    BaseJsonRst deleteRetailer(String retailerNo);

    /**
     * @description: 更新
     * @author: 张进文
     * @param: [data]
     * @return: com.highrock.util.BaseJsonRst
     * @date: 2018/9/6 10:13
     * @version: 1.0
     */
    BaseJsonRst updateRetailerStore(String retailerNo,String storeNo,String data);


    /**
     * @description: 删除
     * @author: 张进文
     * @param: [retailerNo, storeNo, data]
     * @return: com.highrock.util.BaseJsonRst
     * @date: 2018/9/6 11:24
     * @version: 1.0
     */
    BaseJsonRst deleteRetailerStore(String retailerNo, String storeNo);

    /**
     * @description: 保存价格公式
     * @author: 张进文
     * @param: [data]
     * @return: com.highrock.util.BaseJsonRst
     * @date: 2018/9/11 8:50
     * @version: 1.0
     */
    BaseJsonRst saveCpStyleStoreAuthorization(String data);

    /**
     * @description: 根据 查找
     * @author: 张进文
     * @param: [storeNo, styleNo]
     * @return: com.highrock.util.BaseJsonRst
     * @date: 2018/9/11 9:20
     * @version: 1.0
     */
    BaseJsonRst getCpStyleStoreAuthorizationByStoreNoAndStyleNo(String storeNo, String styleNo);

    /**
     * @description: 删除
     * @author: 张进文
     * @param: [storeNo, styleNo]
     * @return: com.highrock.util.BaseJsonRst
     * @date: 2018/9/11 9:26
     * @version: 1.0
     */
    BaseJsonRst deleteCpStyleStoreAuthorizationByStoreNoAndStyleNo(String storeNo, String styleNo);

    /**
     * @description: 更新
     * @author: 张进文
     * @param: [storeNo, styleNo, data]
     * @return: com.highrock.util.BaseJsonRst
     * @date: 2018/9/11 9:33
     * @version: 1.0
     */
    BaseJsonRst updateCpStyleStoreAuthorizationByStoreNoAndStyleNo(String storeNo, String styleNo, String data);

    /**
     * @description: 保存
     * @author: 张进文
     * @param: [data]
     * @return: com.highrock.util.BaseJsonRst
     * @date: 2018/9/11 10:29
     * @version: 1.0
     */
    BaseJsonRst saveCpStyleStoreAuthorizationAll(String data);

    /** *
     * @description: 根据storeNo查找store
     * @author: 张进文
     * @param: [storeNo]
     * @return: com.highrock.util.BaseJsonRst
     * @date: 2018/12/12 9:34
     * @version: 1.0
     */
    BaseJsonRst getCpRetailerStoreByStoreNo(String storeNo);


    /**
     * @description: 把店铺加到redis中
     * @author: 张进文
     * @param: []
     * @return: com.highrock.util.BaseJsonRst
     * @date: 2019/4/10 11:33
     * @version: 1.0
     */
    BaseJsonRst setStoreRedis();

}
