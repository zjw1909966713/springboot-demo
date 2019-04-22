package com.highrock.mapper;

import com.highrock.model.CpStyle;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface CpStyleMapper {

    @Select("select * from cp_style where style_no=#{styleNo}")
    CpStyle getCpStyleByStyleNo(@Param("styleNo") String styleNo);
}
