package com.liu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liu.entity.Family;
import com.liu.service.FamilyService;
import com.liu.tools.Result;
import com.liu.vo.FamilyVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author 刘国强
 */
@RestController
@RequestMapping("/family")
@CrossOrigin
public class FamilyController {
    @Resource
    private FamilyService familyService;

    @PostMapping("/listWhere/{pageIndex}/{pageSize}")
    public Result listWhere(
           @PathVariable Long pageIndex,
           @PathVariable Long pageSize,
           @RequestBody (required = false) FamilyVo familyVo
    ){
        Page<Family> page = new Page<>(pageIndex,pageSize);

        QueryWrapper<Family> queryWrapper = new QueryWrapper<>();

        if(StringUtils.checkValNotNull(familyVo.getFamilyCode())){
            queryWrapper.like("family_code",familyVo.getFamilyCode());
        }
        if(StringUtils.checkValNotNull(familyVo.getFamilyName())){
            queryWrapper.like("family_name",familyVo.getFamilyName());
        }
        queryWrapper.orderByDesc("fid");
        Page<Family> familyPage = familyService.page(page, queryWrapper);

        if(familyPage != null && !familyPage.getRecords().isEmpty()) {
            return Result.ok(familyPage);
        }
        return Result.fail().message("未查到数据");
    }

    @GetMapping("/list")
    public Result list(){
        List<Family> list = familyService.list(null);
        if(list == null){
            return Result.fail().message("未查到数据");
        }

        return Result.ok(list);

    }

    /***
     * 增加方法
     */
    @PostMapping("/add")
    public Result add(@RequestBody Family family){
        family.setCreateTime(new Date());
        family.setDeleted(0);
        boolean save = familyService.save(family);
        if(save){
            return Result.ok().message("添加成功");
        }
        return Result.fail().message("添加失败");
    }

    /**
     * 修改方法
     * @param family
     * @return
     */
    @PutMapping("/update")
    public Result update(@RequestBody Family family){
        family.setUpdateTime(new Date());
        family.setDeleted(0);
        boolean save = familyService.updateById(family);
        if(save){
            return Result.ok().message("修改成功");
        }
        return Result.fail().message("修改失败");
    }

    @DeleteMapping("/del/{id}")
    public Result del(@PathVariable Long id){
        boolean remove = familyService.removeById(id);
        if(remove){
            return Result.ok().message("删除成功");
        }
        return Result.fail().message("删除失败");
    }
}
