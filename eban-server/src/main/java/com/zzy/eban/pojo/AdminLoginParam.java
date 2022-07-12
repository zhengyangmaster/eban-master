package com.zzy.eban.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author ZhuZhengYang
 * @description 用户登录实体类
 * @since 2022/2/22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "admin登录对象")
public class AdminLoginParam {

    @ApiModelProperty(value = "用户名",required = true)
    private String userName;
    @ApiModelProperty(value = "密码",required = true)
    private String passWord;

    @ApiModelProperty(value = "验证码",required = true)
    private String code;
}
