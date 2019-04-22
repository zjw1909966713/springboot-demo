package com.highrock.mapper;

import com.highrock.model.CpRetailer;
import org.apache.ibatis.annotations.*;

/**
 * @description: TODO
 * @author: 张进文
 * @date: 2018/9/5 16:33
 * @version: 1.0
 */
public interface CpRetailerMapper {


    /**
     * @description: 保存零售商
     * @author: 张进文
     * @param: [cpRetailer]
     * @return: int
     * @date: 2018/9/5 16:35
     * @version: 1.0
     */
    @Insert("insert into cp_retailer(retailer_no,name) values(#{retailer_no},#{name})")
    int saveCpRetailer(CpRetailer cpRetailer);



    /**
     * @description: 清除所有数据
     * @author: 张进文
     * @param: []
     * @return: int
     * @date: 2018/9/6 7:47
     * @version: 1.0
     */
    @Delete("delete from cp_retailer ")
    int deleteAll();


    /**
     * @description: 查找零售
     * @author: 张进文
     * @param: [retailerNo]
     * @return: com.highrock.model.CpRetailer
     * @date: 2018/9/6 8:37
     * @version: 1.0
     */
    @Select("select * from cp_retailer where retailer_no=#{retailerNo}")
    CpRetailer getCpRetailerByRetailerNo(@Param("retailerNo") String retailerNo);


    /**
     * @description: 根据retailerNo更新
     * @author: 张进文
     * @param: [retailerNo]
     * @return: int
     * @date: 2018/9/6 9:45
     * @version: 1.0
     */
    int updateCpRetailerByRetailerNo(CpRetailer cpRetailer);



    /**
     * @description: 根据id更新
     * @author: 张进文
     * @param: [id]
     * @return: int
     * @date: 2018/9/6 9:49
     * @version: 1.0
     */
    int updateCpRetailerById(CpRetailer cpRetailer);




    /**
     * @description: 根据retailerNo删除
     * @author: 张进文
     * @param: [retailerNo]
     * @return: int
     * @date: 2018/9/6 10:00
     * @version: 1.0
     */
    @Delete("delete from cp_retailer where retailer_no=#{retailerNo}")
    int deleteCpRetailerByRetailerNo(String retailerNo);


}
