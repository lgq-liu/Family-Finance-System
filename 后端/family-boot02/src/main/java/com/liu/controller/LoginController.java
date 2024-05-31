package com.liu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liu.entity.Users;
import com.liu.service.UsersService;
import com.liu.tools.JwtHelper;

import com.liu.tools.MD5;
import com.liu.tools.Result;
import com.liu.vo.LoginVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class LoginController {
    @Resource
    private UsersService usersService;

    //通过
    @GetMapping("info")
    public Result info(HttpServletRequest request) {
        String token = (String) request.getAttribute("token");
        Long uid = JwtHelper.getUserId(token);
        String username = JwtHelper.getUsername(token);
        String[] stringList = {"admin", "update"};
        Map<String, Object> map = new HashMap<>();
        map.put("uid", uid);
        map.put("name", username);
        map.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        map.put("roles", stringList);

        return Result.ok(map);
    }

    //登录方法
    @PostMapping("/login")
    public Result login(@RequestBody LoginVo loginVo) {
        String username = loginVo.getUsername();
        String pwd = MD5.encrypt(loginVo.getPassword());
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        queryWrapper.eq("password", pwd);
        Users users = usersService.getOne(queryWrapper);
        if (users == null) {
            return Result.fail().message("用户名密码不对");
        }
        Map<String, Object> map = new HashMap<>();
        String token = JwtHelper.createToken(users.getUid(), users.getUsername());
        map.put("token", token);
        return Result.ok(map);
    }

    @PostMapping("logout")
    public Result logout() {
        return Result.ok();
    }
}
