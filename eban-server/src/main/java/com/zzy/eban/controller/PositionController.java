package com.zzy.eban.controller;


import com.zzy.eban.pojo.Position;
import com.zzy.eban.pojo.RespBean;
import com.zzy.eban.service.IPositionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zzy
 * @since 2022-02-21
 */
@RestController
@RequestMapping("/system/basic/pos")
public class PositionController {

    @Autowired
    private IPositionService positionService;

    @ApiOperation(value = "获取所有的职位信息")
    @GetMapping("/")
    public List<Position> getAllPosition() {

        return positionService.list();
    }

    @ApiOperation(value = "添加职位信息")
    @PostMapping("/addPosition")
    public RespBean addPosition(@RequestBody Position position) {
        position.setCreateDate(LocalDateTime.now());
        if (positionService.save(position)) {
            return RespBean.success("添加职位成功");
        }
        return RespBean.error("添加职位失败");
    }

    @ApiOperation(value = "更新职位信息")
    @PostMapping("/updatePosition")
    public RespBean updatePosition(@RequestBody Position position) {
        if (positionService.updateById(position)) {
            return RespBean.success("更新职位成功");

        }
        return RespBean.error("更新职位失败");
    }

    @ApiOperation(value = "删除职位信息")
    @DeleteMapping("/deletePosition/{id}")
    public RespBean deletePosition(@PathVariable Integer id){
        if (positionService.removeById(id)){
            return RespBean.success("删除职位成功");
        }
        return RespBean.error("删除职位失败");
    }

    @ApiOperation(value = "批量删除职位信息")
    @DeleteMapping("/")
    public RespBean deletePositions(Integer [] ids){
        if (positionService.removeByIds(Arrays.asList(ids))){
            return RespBean.success("批量删除成功");
        }
        return RespBean.error("批量删除失败");
    }


}
