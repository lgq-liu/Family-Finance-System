package com.liu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liu.entity.Account;
import com.liu.entity.Family;
import com.liu.entity.Type;
import com.liu.entity.Users;
import com.liu.service.TypeService;
import com.liu.tools.Result;
import com.liu.vo.TypeVo;
import com.liu.vo.UsersVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author 刘国强
 */
@CrossOrigin
@RestController
@RequestMapping("/type")
public class TypeController {
    @Resource
    private TypeService typeService;
    @GetMapping("/getById/{tid}")
    public Result getById(@PathVariable Long tid){
        Type type = typeService.getById(tid);
        return Result.ok(type);
    }

    @GetMapping("/listQuery")
    public Result listQuery(){
        List<Type> list = typeService.list(null);
        return Result.ok(list);
    }
    @PostMapping("/add")
    public Result add(@RequestBody Type type){ //添加方法
        type.setCreateTime(new Date());
        type.setDeleted(0);
        boolean save = typeService.save(type);
        if(save){
            return Result.ok().message("添加成功");
        }
        return Result.fail().message("添加失败");
    }

    /**
     * 条件查询带分页
     * @param pageIndex
     * @param pageSize
     * @param typeVo 查询条件
     * @return
     */
    @PostMapping("/list/{pageIndex}/{pageSize}")
    public Result list(
            @PathVariable Long pageIndex,
            @PathVariable Long pageSize,
            @RequestBody (required = false) TypeVo typeVo
    ){
        Page<Type> page = new Page<>(pageIndex, pageSize);

        QueryWrapper queryWrapper = new QueryWrapper<>();

        if(StringUtils.checkValNotNull(typeVo.getTypeCode())){
            queryWrapper.like("type_code",typeVo.getTypeCode());
        }
        if(StringUtils.checkValNotNull(typeVo.getTypeName())){
            queryWrapper.like("type_name",typeVo.getTypeName());
        }
        queryWrapper.orderByDesc("tid");
        Page pageList = typeService.page(page, queryWrapper);
        if(pageList == null){
            return Result.fail().message("查询失败");
        }
        return Result.ok(pageList);
    }
    /**
     * 修改方法
     * @param type
     * @return
     */
    @PutMapping("/update")
    public Result update(@RequestBody Type type){
        type.setUpdateTime(new Date());
        type.setDeleted(0);
        boolean save = typeService.updateById(type);
        if(save){
            return Result.ok().message("修改成功");
        }
        return Result.fail().message("修改失败");
    }

    @DeleteMapping("/del/{id}")
    public Result del(@PathVariable Long id){
        boolean remove = typeService.removeById(id);
        if(remove){
            return Result.ok().message("删除成功");
        }
        return Result.fail().message("删除失败");
    }
}
