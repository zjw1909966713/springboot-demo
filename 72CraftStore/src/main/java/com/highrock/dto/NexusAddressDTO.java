package com.highrock.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 张进文
 * @ClassName NexusAddressDTO
 * @Description TODO
 * @Date 2019/4/10 11:28
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NexusAddressDTO implements Serializable {

    private static final long serialVersionUID = -4918295997054294471L;
    private String id;
    private String country;
    private String zip;
    private String state;
    private String city;
    private String street;
}
