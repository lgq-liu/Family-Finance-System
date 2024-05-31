package com.liu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liu.entity.Account;
import com.liu.entity.Education;
import com.liu.entity.Users;
import com.liu.service.AccountService;
import com.liu.service.UsersService;
import com.liu.tools.Result;
import com.liu.vo.AccountVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author 刘国强
 */
@RestController
@RequestMapping("/account")
@CrossOrigin
public class AccountController {

    @Resource
    private AccountService accountService;
    @Resource
    private UsersService usersService;
    //按照账户ID查询账户和用户
    @GetMapping("/accountById/{aid}")
    public Result getAccountById(@PathVariable Long aid){
        Account account = accountService.getAccountById(aid);
        if(account==null){
            return Result.fail().message("未查到数据");
        }
        return Result.ok(account);
    }
    @GetMapping("/listWhere")
    public Result listWhere(){
        List<Account> accountList =  accountService.listWhere();
        if(accountList==null){
            return Result.fail().message("未查到数据");
        }
        return Result.ok(accountList);
    }
    /**
     * 条件查询带分页
     *
     * @param pageIndex 当前第几页
     * @param pageSize 每页查询多少条
     * @param accountVo 封装查询对象
     * @return
     */
    @PostMapping("/list/{pageIndex}/{pageSize}")
    public Result list(
            @PathVariable Long pageIndex,
            @PathVariable Long pageSize,
            @RequestBody(required = false) AccountVo accountVo
    ) {
        Page<Account> page = new Page<>(pageIndex, pageSize);

        QueryWrapper queryWrapper = new QueryWrapper<>();
        if (StringUtils.checkValNotNull(accountVo.getAccountDes())) {
            queryWrapper.like("account_des", accountVo.getAccountDes());
        }
        queryWrapper.orderByDesc("aid");
        Page pageList = accountService.page(page, queryWrapper);

        if (pageList == null) {
            return Result.fail().message("查询错误");
        }
        return Result.ok(pageList);
    }

    @PostMapping("/add")
    public Result add(@RequestBody Account account){
        account.setCreateTime(new Date());
        account.setDeleted(0);
        boolean save = accountService.save(account);
        if(save){
            Users users = new Users();
            users.setUid(account.getUid());
            users.setIsType(1);
            boolean update=usersService.updateById(users);
            if(update){
                return Result.ok().message("创建账户成功");
            }
        }
        return Result.fail().message("开通失败");
    }

    @PutMapping("/update")
    public Result update(@RequestBody Account account){
        account.setUpdateTime(new Date());
        account.setDeleted(0);
        boolean save = accountService.updateById(account);
        if(save){
            return Result.ok().message("修改成功");
        }
        return Result.fail().message("修改失败");
    }

    @DeleteMapping("/del/{id}")
    public Result del(@PathVariable Long id){
        boolean remove = accountService.removeById(id);
        if(remove){
            return Result.ok().message("删除成功");
        }
        return Result.fail().message("删除失败");
    }
}
