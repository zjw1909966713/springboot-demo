package com.highrock.mapper;

import com.highrock.model.CpStyleStoreAuthorization;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author 张进文
 * @ClassName CpStyleStoreAuthorizationMapper
 * @Description 打折
 * @Date 2018/9/11 8:21
 * @Version 1.0
 */
@Repository
public interface CpStyleStoreAuthorizationMapper {

    /**
     * @description: 保存
     * @author: 张进文
     * @param: [cpStyleStoreAuthorization]
     * @return: int
     * @date: 2018/9/11 8:25
     * @version: 1.0
     */
    @Insert("insert into cp_style_store_authorization(store_no,style_no,commission,price_policy) values(#{store_no},#{style_no},#{commission},#{price_policy})")
    int saveCpStyleStoreAuthorization(CpStyleStoreAuthorization cpStyleStoreAuthorization);



    /**
     * @description: 删除
     * @author: 张进文
     * @param: [storeNo, styleNo]
     * @return: int
     * @date: 2018/9/11 8:37
     * @version: 1.0
     */
    @Delete("delete from cp_style_store_authorization where store_no=#{storeNo} and style_no=#{styleNo}")
    int deleteCpStyleStoreAuthorizationByStoreNoAndStyleNo(@Param("storeNo") String storeNo, @Param("styleNo") String styleNo);



    /**
     * @description: 查询
     * @author: 张进文
     * @param: [storeNo, styleNo]
     * @return: com.highrock.model.CpStyleStoreAuthorization
     * @date: 2018/9/11 8:40
     * @version: 1.0
     */
    @Select("select * from cp_style_store_authorization where store_no=#{storeNo} and style_no=#{styleNo}")
    CpStyleStoreAuthorization getCpStyleStoreAuthorizationByStoreNoAndStyleNo(@Param("storeNo") String storeNo, @Param("styleNo") String styleNo);



    /**
     * @description: 更新
     * @author: 张进文
     * @param: [storeNo, styleNo]
     * @return: int
     * @date: 2018/9/11 8:41
     * @version: 1.0
     */
    int updateCpStyleStoreAuthorizationByStoreNoAndStyleNo(CpStyleStoreAuthorization cpStyleStoreAuthorization);

}
