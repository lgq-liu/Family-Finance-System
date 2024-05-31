package com.liu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liu.entity.Account;
import com.liu.entity.Type;
import com.liu.entity.TypeAccount;
import com.liu.entity.Users;
import com.liu.service.AccountService;
import com.liu.service.TypeAccountService;
import com.liu.service.TypeService;
import com.liu.service.UsersService;
import com.liu.tools.Result;
import com.liu.vo.AccountVo;
import com.liu.vo.TypeAccountVo;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 刘国强
 */
@RestController
@RequestMapping("/typeAccount")
@CrossOrigin
public class TypeAccountController {

    @Resource
    private TypeAccountService typeAccountService;
//    @Resource
//    private AccountService accountService;
//
//    /**
//     * 条件查询带分页
//     *
//     * @param pageIndex     当前第几页
//     * @param pageSize      每页查询多少条
//     * @param typeAccountVo 封装查询对象
//     * @return
//     */
//    @PostMapping("/list/{pageIndex}/{pageSize}")
//    public Result list(
//            @PathVariable Long pageIndex,
//            @PathVariable Long pageSize,
//            @RequestBody(required = false) TypeAccountVo typeAccountVo
//    ) {
//        Page<TypeAccount> page = new Page<>(pageIndex, pageSize);
//
//        QueryWrapper queryWrapper = new QueryWrapper<>();
//        if (StringUtils.checkValNotNull(typeAccountVo.getName())) {
//            queryWrapper.like("name", typeAccountVo.getName());
//        }
//        if (StringUtils.checkValNotNull(typeAccountVo.getAccountCode())) {
//            queryWrapper.like("account_code", typeAccountVo.getAccountCode());
//        }
//        if (StringUtils.checkValNotNull(typeAccountVo.getAccountName())) {
//            queryWrapper.like("account_name", typeAccountVo.getAccountName());
//        }
//        queryWrapper.orderByDesc("ttid");
//        Page pageList = typeAccountService.page(page, queryWrapper);
//
//        if (pageList == null) {
//            return Result.fail().message("查询错误");
//        }
//        return Result.ok(pageList);
//    }
//
//    @PostMapping("/add")
//    public Result add(@RequestBody TypeAccount typeAccount) {
//        //判断收入/支出
//        //按照aid多表查询
//        //处理账户变化
//        if (typeAccount.getType().intValue() == 1) {
//            //获得发生额
//            double accrual = typeAccount.getAccrualMoney().doubleValue();
//            TypeAccount account = typeAccountService.getById(typeAccount.getTtid());
//            double balanceMoney = account.getBalanceMoney().doubleValue()+accrual;
//            //填充数据
//            BigDecimal bigDecimal = new BigDecimal(balanceMoney);
//            typeAccount.setBalanceMoney(bigDecimal);
//            Account accUser = accountService.getAccountById(typeAccount.getTtid().longValue());
//            typeAccount.setDivertName(accUser.getUsers().getName());
//            typeAccount.setDvtId(accUser.getUsers().getUid());
//            typeAccount.setDivertTime(new Date());
//            typeAccount.setCreateTime(new Date());
//            typeAccount.setDeleted(0);
//            typeAccount.setTtid(0L);
//            boolean save = typeAccountService.save(typeAccount);
//            if(save){
//                //总账户增加
//                Account account1 = accountService.getById(typeAccount.getAid().longValue());
//                double totalMoney = account1.getAccountMoney().doubleValue()+accrual;
//                BigDecimal bigDecimal1 = new BigDecimal(totalMoney);
//                account1.setAccountMoney(bigDecimal1);
//                boolean update = accountService.updateById(account1);
//                if(update){
//                    return Result.ok().message("账户的收入更新成功");
//                }
//            }
//            //TODO
//        }
//        if(typeAccount.getType()==0){}
//        return Result.fail().message("账户添加错误");
//    }
@Resource
private TypeService typeService;

    @PostMapping("/save")
    public Result save(@RequestBody TypeAccount typeAccount){
        Type type = typeService.getById(typeAccount.getTid());
        typeAccount.setAccountCode(type.getTypeCode());
        typeAccount.setAccountName(type.getTypeName());
        typeAccount.setDeleted(0);
        typeAccount.setCreateTime(new Date());
        boolean save = typeAccountService.save(typeAccount);
        return Result.ok(save);
    }

    @PostMapping("add")
    @Transactional
    public Result add(@RequestBody TypeAccount typeAccount){
        //(1)判断收入支出  type = 1 余额 + 发生额
        //（2）按照aid 多表查询 用户
        //(3)处理账户变化

        if(typeAccount.getType().intValue() == 1){
            //调用处理收入
            ;
            return typeAccountService.accountSouRu(typeAccount);
        }
        if(typeAccount.getType().intValue() == 0){
            return typeAccountService.accountZhiChu(typeAccount);
        }
        return Result.fail().message("账户添加错误");
    }

    @PostMapping("/list/{pageIndex}/{pageSize}")
    public Result list(
            @PathVariable Long pageIndex,
            @PathVariable  Long pageSize,
            @RequestBody (required = false)  TypeAccountVo typeAccountVo
    ){
        Page<TypeAccount> page = new Page<>(pageIndex,pageSize);

        QueryWrapper queryWrapper = new QueryWrapper<>();

        if(StringUtils.checkValNotNull(typeAccountVo.getName())){
            queryWrapper.like("name",typeAccountVo.getName());
        }

        if(StringUtils.checkValNotNull(typeAccountVo.getAccountCode())){
            queryWrapper.like("account_code",typeAccountVo.getAccountCode());
        }

        if(StringUtils.checkValNotNull(typeAccountVo.getAccountName())){
            queryWrapper.like("account_name",typeAccountVo.getAccountName());
        }
        queryWrapper.orderByDesc("ttid");
        Page pageList = typeAccountService.page(page, queryWrapper);

        if(pageList == null){
            return Result.fail().message("查询错误");
        }
        return Result.ok(pageList);
    }
    @DeleteMapping("/del/{id}")
    public Result del(@PathVariable Long id){
        boolean remove = typeAccountService.removeById(id);
        if(remove){
            return Result.ok().message("删除成功");
        }
        return Result.fail().message("删除失败");
    }
}
