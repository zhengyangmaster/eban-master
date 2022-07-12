package com.zzy.eban.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ZhuZhengYang
 * @description 公共返回对象
 * @since 2022/2/22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespBean {
    private long code;
    private String message;
    private Object object;
/**
 * @description: 成功返回
 * @author: zzy
 * @data: 2022/2/22 16:18
 */
    public static RespBean success(String message){
        return new RespBean(200,message,null);
    }
/**
 * @description: 成功返回结果
 * @author: zzy
 * @data: 2022/2/22 16:19
 */
    public static RespBean success(String message,Object object){
        return new RespBean(200,message,object);
    }

    /**
     * @description: 失败返回请求
     * @author: zzy
     * @data: 2022/2/22 16:21
     */
    public static RespBean error(String message){
        return new RespBean(500,message,null);
    }

    /**
     * @description: 失败返回请求
     * @author: zzy
     * @data: 2022/2/22 16:21
     */
    public static RespBean error(String message,Object object){
        return new RespBean(500,message,object);
    }




}
