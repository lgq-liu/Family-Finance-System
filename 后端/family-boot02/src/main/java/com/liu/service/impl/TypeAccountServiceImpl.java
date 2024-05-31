package com.liu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liu.entity.Account;
import com.liu.entity.TypeAccount;
import com.liu.mapper.AccountMapper;
import com.liu.mapper.TypeAccountMapper;
import com.liu.service.TypeAccountService;
import com.liu.tools.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

@Service
@Transactional
public class TypeAccountServiceImpl extends ServiceImpl<TypeAccountMapper, TypeAccount> implements TypeAccountService {
    @Resource
    private AccountMapper accountMapper;

    @Override
    @Transactional
    public Result accountSouRu(TypeAccount typeAccount) {

        //根据ID查询数据
        Long id = typeAccount.getTtid();
        //先更新
        typeAccount.setClassType(0);
        if (typeAccount.getAccrualMoney().doubleValue() > 99999999) {
            return Result.fail().message("收入业务处理失败");
        }
        int i = baseMapper.updateById(typeAccount);
        if (i > 0) {
            //接收到查询的数据
            TypeAccount accountType = baseMapper.selectById(id);
            //定义余额
            double balanceMoney = accountType.getBalanceMoney().doubleValue();
            double accrualMoney = typeAccount.getAccrualMoney().doubleValue();
            //余额+ 发生额
            if (typeAccount.getAccrualMoney().doubleValue() >= 0) {
                balanceMoney = balanceMoney + accrualMoney;
            } else {
                return Result.fail().message("收入业务处理失败");
            }
            //类型转化
            BigDecimal bigDecimal = new BigDecimal(balanceMoney);
            //将计算后余额赋值余额
            typeAccount.setBalanceMoney(bigDecimal);
            Account account = accountMapper.getAccountById(typeAccount.getAid());
            typeAccount.setName(account.getUsers().getName());
            typeAccount.setDivertName(account.getUsers().getName());
            typeAccount.setDvtId(account.getUsers().getUid());
            typeAccount.setDivertTime(new Date());
            typeAccount.setTid(accountType.getTid());

//            typeAccount
            typeAccount.setCreateTime(new Date());
            typeAccount.setDeleted(0);
            typeAccount.setTtid(0L);
            typeAccount.setClassType(1);
            int insert = baseMapper.insert(typeAccount);

            if (insert > 0) {
                Account account1 = accountMapper.selectById(typeAccount.getAid());
                double sum = typeAccount.getAccrualMoney().doubleValue() + account1.getAccountMoney().doubleValue();
                BigDecimal bigs = new BigDecimal(sum);
                account1.setAccountMoney(bigs);
                int update = accountMapper.updateById(account1);

                if (update > 0) {
                    return Result.ok().message("收入业务处理成功");
                }

            }


        }

        //typeAccount.setTtid();

        return Result.fail().message("收入业务处理失败");
    }

    @Override
    public Result accountZhiChu(TypeAccount typeAccount) {
        //需要更新四次数据  ① 转账子账户减少金额  ②转账总账户减少金额 ③收到转账子账户金额增加 ④收到转总账户金额增加

        Long id = typeAccount.getTtid();// 子账户转账ID
        TypeAccount accountType = baseMapper.selectById(id);//减少子账户查询出来
        Long aaid = accountType.getAid();// 查询出子账户总总账户ID 减少用
        Long aid = typeAccount.getAid().longValue();//用户选择的总账户ID 增加用
        String name = typeAccount.getName();
        Date dateTime = new Date();
        double fa = typeAccount.getAccrualMoney().doubleValue();//发生额
        //处理 ① 转账子账户减少金额
        double zong = accountType.getBalanceMoney().doubleValue();//子账户总额
        double num = zong - fa; //子账户减少
        if (num >= 0) {
            BigDecimal bigDecimal = new BigDecimal(num);//类型转化
            typeAccount.setBalanceMoney(bigDecimal);//减少额度赋值
        } else {
            return Result.fail().code(208).message("处理支出失败");
        }
        Account account = accountMapper.getAccountById(aaid);//查询减少的总账户数据
        Account acco = accountMapper.getAccountById(aid);
        double accNum = account.getAccountMoney().doubleValue();//减少的总账户金额
        typeAccount.setDvtId(acco.getUsers().getUid());//转账对象ID
        typeAccount.setDivertName(acco.getUsers().getName());//转账对象姓名
        typeAccount.setDivertTime(dateTime);//转账对象时间
        typeAccount.setClassType(1);//控制按钮显示
        typeAccount.setCreateTime(dateTime);//创建时间
        typeAccount.setDeleted(0);//数据不删除
        typeAccount.setTtid(0L);
        int insert = baseMapper.insert(typeAccount);
        System.out.println("插入转账记录：" + insert);
        if (insert > 0) {
            accountType.setClassType(0);//更新不显示 前台按钮
            accountType.setUpdateTime(dateTime);//更新时间
            int i = baseMapper.updateById(accountType);
            if (i > 0) {
                // ②转账总账户减少金额
                double sunde = accNum - fa;
                BigDecimal bis = new BigDecimal(sunde);
                account.setAccountMoney(bis);
                account.setUpdateTime(new Date());
                int update = accountMapper.updateById(account);
                System.out.println("更新转账总账户的返回值：" + update);
                if (update > 0) {
                    // ③收到转账子账户金额增加
                    //判断收到账户 aid 及 classType 条件查询
                    QueryWrapper queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq("aid", aid);
                    queryWrapper.eq("dvt_id", typeAccount.getDvtId());
                    queryWrapper.eq("name", account.getUsers().getName());
                    queryWrapper.eq("class_type", 1);
                    TypeAccount selectOne = baseMapper.selectOne(queryWrapper);//查询需要增加的子账户
                    TypeAccount ta = new TypeAccount();
                    ta.setTtid(selectOne.getTtid());
                    double zen = selectOne.getBalanceMoney().doubleValue() + fa;//计算增加
                    BigDecimal bise = new BigDecimal(zen);
                    ta.setBalanceMoney(bise);
                    ta.setDivertName(name);
                    ta.setDivertTime(dateTime);
                    ta.setClassType(1);
                    BigDecimal fes = new BigDecimal(fa);
                    ta.setAccrualMoney(fes);

                    int updateById = baseMapper.updateById(ta);
                    if (updateById > 0) {
                        // ④收到转总账户金额增加
                        Account accountId = accountMapper.getAccountById(aid);
                        Account account1 = new Account();
                        double add = accountId.getAccountMoney().doubleValue() + fa;
                        System.out.println("\n" + add + "\n");
                        account1.setAid(accountId.getAid());
                        BigDecimal biesfe = new BigDecimal(add);
                        account1.setAccountMoney(biesfe);
                        int byId = accountMapper.updateById(account1);
                        if (byId > 0) {
                            return Result.ok().message("处理支出成功");
                        }

                    }


                }
            }
        }
        return Result.fail().code(208).message("处理支出失败");
    }

}
