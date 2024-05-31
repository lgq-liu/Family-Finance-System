package com.liu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liu.entity.Family;
import com.liu.entity.Users;
import com.liu.service.UsersService;
import com.liu.tools.MD5;
import com.liu.tools.Result;
import com.liu.vo.UsersVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;


/**
 * @author 刘国强
 */
@CrossOrigin
@RestController
@RequestMapping("/users")
public class UsersController {
    @Resource
    private UsersService usersService;

    @GetMapping("/familyEdu/{uid}")
    public Result familyEduById(@PathVariable Long uid){
        Users users = usersService.familyEduById(uid);
        if(users==null){
            return Result.fail().message("未查到数据");
        }
        return Result.ok(users);
    }
    /**
     * 条件查询带分页
     * @param pageIndex 当前第几个
     * @param pageSize  每页显示多少条
     * @param usersVo   页面传值
     * @return
     */
    @PostMapping("/list/{pageIndex}/{pageSize}")
    public Result list(
            @PathVariable Long pageIndex,
            @PathVariable Long pageSize,
            @RequestBody (required = false) UsersVo usersVo
    ){
        Page<Users> page = new Page<>(pageIndex,pageSize);
        if(usersVo.getUsername()!=null||usersVo.getUsername()!=""){
            String username="%"+usersVo.getUsername()+"%";
            usersVo.setUsername(username);
        }
        if(usersVo.getName()!=null||usersVo.getName()!=""){
            String name="%"+usersVo.getName()+"%";
            usersVo.setName(name);
        }
        IPage<Users> pageUsers = usersService.listUsers(page, usersVo);
        if(pageUsers == null){
            return Result.fail().message("未查到数据");
        }
        return Result.ok(pageUsers);
    }
//    @PostMapping("/list/{pageIndex}/{pageSize}")
//    public Result list(
//            @PathVariable Long pageIndex,
//            @PathVariable Long pageSize,
//            @RequestBody (required = false) UsersVo usersVo
//    ){
//        Page<Users> page = new Page<>(pageIndex,pageSize);
//
//        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
//
//        if(StringUtils.checkValNotNull(usersVo.getUsername())){
//            queryWrapper.like("username",usersVo.getUsername());
//        }
//        if(StringUtils.checkValNotNull(usersVo.getName())){
//            queryWrapper.like("name",usersVo.getName());
//        }
//        queryWrapper.orderByDesc("uid");
//        Page<Users> usersPage = usersService.page(page, queryWrapper);
//        if(usersPage == null){
//            return Result.fail().message("未查到数据");
//        }
//        return Result.ok(usersPage);
//    }
    // 按id删除
    @DeleteMapping("/del/{id}")
    public Result del(@PathVariable Long id){
        boolean remove = usersService.removeById(id);
        if(remove){
            return Result.ok().message("删除成功");
        }
        return Result.fail().message("删除失败");
    }

    @PostMapping("/add")
    public Result add(@RequestBody Users users){
        //MD5加密
        String names= MD5.encrypt(users.getPassword());
        users.setPassword(names);
        users.setDeleted(0);
        users.setCreateTime(new Date());
        boolean save=usersService.save(users);
        if(save){
            return Result.ok().message("添加成功");
        }
        return Result.fail().message("添加失败");
    }

    @PutMapping("/update")
    public Result update(@RequestBody Users users){
        users.setUpdateTime(new Date());
        users.setDeleted(0);
        boolean save = usersService.updateById(users);
        if(save){
            return Result.ok().message("修改成功");
        }
        return Result.fail().message("修改失败");
    }
}
