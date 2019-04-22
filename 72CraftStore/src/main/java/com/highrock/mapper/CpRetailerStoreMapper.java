package com.highrock.mapper;

import com.highrock.model.CpRetailerStore;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author zhangjinwen
 * @create 2017-11-01 12:02
 * @desc
 **/
public interface CpRetailerStoreMapper {


        /**
         * 根据storeNo找到cp_retailer_store
         * @param storeNo
         * @return
         */
        @Select("select id,retailer_no,store_no,name,authorization,token,state_code,address,city,zip,paywork_id,paywork_key from cp_retailer_store where store_no=#{storeNo}")
        CpRetailerStore getCpRetailerStoreByStoreNo(@Param("storeNo") String storeNo);


        /**
         * 根据id更新cp_retailer_store
         * @param store
         * @return
         */
        int updateCpRetailerStoreById(CpRetailerStore store);


        /**
         * 根据token找到cp_retailer_store
         * @param token
         * @return
         */
        @Select("select id,retailer_no,store_no,name,authorization,token,state_code,address,city,zip from cp_retailer_store where token=#{token}")
        CpRetailerStore getCpRetailerStoreByToken(@Param("token") String token);

        /**
         * @description: 保存零售商
         * @author: 张进文
         * @param: [cpRetailerStore]
         * @return: int
         * @date: 2018/9/5 16:57
         * @version: 1.0
         */
        @Insert("insert into cp_retailer_store(retailer_no,store_no,name,authorization,token,state_code,address,city,zip,paywork_id,paywork_key) values(#{retailer_no},#{store_no},#{name},#{authorization},#{token},#{state_code},#{address},#{city},#{zip},#{paywork_id},#{paywork_key}) ")
        int saveCpRetailerStore(CpRetailerStore cpRetailerStore);



        /**
         * @description: TODO
         * @author: 张进文
         * @param: []
         * @return: int
         * @date: 2018/9/6 7:57
         * @version: 1.0
         */
        @Delete("delete from cp_retailer_store")
        int deleteAll();

        /**
         * @description: 通过retailerNo和storeNo 查找cp_retailer_store
         * @author: 张进文
         * @param: [retailerNo, storeNo]
         * @return: java.util.List<com.highrock.model.CpRetailerStore>
         * @date: 2018/9/6 9:24
         * @version: 1.0
         */
        @Select("select * from cp_retailer_store where  retailer_no=#{retailerNo} and store_no=#{storeNo}")
        List<CpRetailerStore> getCpRetailerStoreByRetailerNoAndStoreNo(@Param("retailerNo") String retailerNo,@Param("storeNo") String storeNo );



        /**
         * @description: 更新
         * @author: 张进文
         * @param: [cpRetailerStore]
         * @return: int
         * @date: 2018/9/6 10:40
         * @version: 1.0
         */
        int updateCpRetailerStoreByRetailerNoAndStoreNo(CpRetailerStore cpRetailerStore);


        /**
         * @description: 删除
         * @author: 张进文
         * @param: [retailerNo, storeNo]
         * @return: int
         * @date: 2018/9/6 11:28
         * @version: 1.0
         */
        @Delete("delete from cp_retailer_store where  retailer_no=#{retailerNo} and store_no=#{storeNo} ")
        int deleteCpRetailerStoreByRetailerNoAndStoreNo(@Param("retailerNo") String retailerNo,@Param("storeNo") String storeNo);




        /** *
         * @description: 查找所有店铺
         * @author: 张进文
         * @param: []
         * @return: java.util.List<com.highrock.model.CpRetailerStore>
         * @date: 2019/4/10 11:38
         * @version: 1.0
         */
        @Select("select id,retailer_no,store_no,name,authorization,token,state_code,address,city,zip,paywork_id,paywork_key from cp_retailer_store")
        List<CpRetailerStore> getCpRetailerStoreListAll();



}
