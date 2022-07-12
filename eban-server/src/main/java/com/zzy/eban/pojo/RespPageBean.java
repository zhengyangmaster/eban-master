package com.zzy.eban.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author ZhuZhengYang
 * @description TODO
 * @since 2022/3/22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespPageBean {

    private Long total;

    private List<?> data;
}
