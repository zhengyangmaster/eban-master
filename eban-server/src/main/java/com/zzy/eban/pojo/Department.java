package com.zzy.eban.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * <p>
 *
 * </p>
 *
 * @author zzy
 * @since 2022-02-21
 */
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false,of = "name")
@Accessors(chain = true)
@TableName("t_department")
@ApiModel(value="Department对象", description="")
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "部门名称")
    @Excel(name = "部门名称")
    @NonNull
    private String name;

    @ApiModelProperty(value = "父id")
    private Integer parentId;

    @ApiModelProperty(value = "路径")
    private String depPath;

    @ApiModelProperty(value = "是否启用")
    private Boolean enabled;

    @ApiModelProperty(value = "是否上级")
    private Boolean isParent;

    @ApiModelProperty(value = "子部门列表")
    @TableField(exist = false)
    private List<Department> children;

    @ApiModelProperty(value = "存储过程使用的返回结果")
    @TableField(exist = false)
    private Integer result;




}
