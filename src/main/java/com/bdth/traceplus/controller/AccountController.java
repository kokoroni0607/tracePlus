package com.bdth.traceplus.controller;


import com.bdth.traceplus.annotation.CheckParams;
import com.bdth.traceplus.domain.Account;
import com.bdth.traceplus.domain.BaseResult;
import com.bdth.traceplus.service.impl.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author weiming.zhu
 * @since 2019-06-12
 */
@RestController
@RequestMapping("/account")
@ResponseBody
public class AccountController {

    @Autowired
    private AccountServiceImpl accountService;

    @GetMapping("/test")
    public BaseResult test() {
        int count = accountService.count();
        return BaseResult.success(count);
    }

    // 系统管理员权限注册
    @PostMapping("/regist")
    public BaseResult regist(Account account, String systemPassword) {
        return accountService.regist(account, systemPassword);
    }

    // 登录
    @PostMapping("/login")
    public BaseResult login(HttpServletRequest request, Account account) {
        return accountService.login(request, account);
    }

    // 退出
    @PostMapping("/logout")
    public BaseResult logout(HttpServletRequest request) {
        return accountService.logout(request);
    }

    // 用户信息
    @CheckParams(type = "account")
    @PostMapping("/info")
    public BaseResult info(Account account) {
        return accountService.info(account);
    }

    // 修改密码
    @PostMapping("/update")
    public BaseResult updateAccount(Account account, String newPassword) {
        return accountService.updateAccount(account,newPassword);
    }
}
