package com.liu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liu.entity.Education;
import com.liu.service.EducationService;
import com.liu.tools.Result;
import com.liu.vo.EducationVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


/**
 * @author 刘国强
 */
@RestController
@RequestMapping("/education")
@CrossOrigin
public class EducationController {
    @Resource
    private EducationService educationService;

    @GetMapping("/list")
    public Result list() {
        List<Education> educationList = educationService.list(null);
        if (educationList == null) {
            return Result.fail().message("未查到数据");
        }
        return Result.ok(educationList);
    }

    @PostMapping("/listWhere/{pageIndex}/{pageSize}")
    public Result listWhere(
            @PathVariable Long pageIndex,
            @PathVariable Long pageSize,
            @RequestBody(required = false) EducationVo educationVo
    ) {
        Page<Education> page = new Page<>(pageIndex, pageSize);

        QueryWrapper<Education> queryWrapper = new QueryWrapper<>();

        if (StringUtils.checkValNotNull(educationVo.getEducationCode())) {
            queryWrapper.like("education_code", educationVo.getEducationCode());
        }
        if (StringUtils.checkValNotNull(educationVo.getEducationName())) {
            queryWrapper.like("education_name", educationVo.getEducationName());
        }
        queryWrapper.orderByDesc("eid");
        Page<Education> educationPage = educationService.page(page, queryWrapper);

        if (educationPage == null) {
            return Result.fail().message("未查到数据");
        }

        return Result.ok(educationPage);

    }

    /***
     * 增加方法
     */
    @PostMapping("/add")
    public Result add(@RequestBody Education education) {
        education.setCreateTime(new Date());
        education.setDeleted(0);
        boolean save = educationService.save(education);
        if (save) {
            return Result.ok().message("添加成功");
        }
        return Result.fail().message("添加失败");
    }

    /**
     * 修改方法
     *
     * @param education
     * @return
     */
    @PutMapping("/update")
    public Result update(@RequestBody Education education) {
        education.setUpdateTime(new Date());
        education.setDeleted(0);
        boolean save = educationService.updateById(education);
        if (save) {
            return Result.ok().message("修改成功");
        }
        return Result.fail().message("修改失败");
    }

    @DeleteMapping("/del/{id}")
    public Result del(@PathVariable Long id) {
        boolean remove = educationService.removeById(id);
        if (remove) {
            return Result.ok().message("删除成功");
        }
        return Result.fail().message("删除失败");
    }
}
