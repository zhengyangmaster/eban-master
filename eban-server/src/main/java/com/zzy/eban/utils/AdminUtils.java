package com.zzy.eban.utils;

import com.zzy.eban.pojo.Admin;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author ZhuZhengYang
 * @description TODO
 * @since 2022/3/20
 */
public class AdminUtils {
    public static Admin getCurrentAdmin(){
        return (Admin)(SecurityContextHolder.getContext().
                getAuthentication().getPrincipal());
    }
}
