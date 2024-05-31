package com.liu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liu.entity.TypeAccount;
import com.liu.tools.Result;

public interface TypeAccountService extends IService<TypeAccount> {
    Result accountSouRu(TypeAccount typeAccount);
    Result accountZhiChu(TypeAccount typeAccount);
}
