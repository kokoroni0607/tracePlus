package com.bdth.traceplus.service;

import com.bdth.traceplus.domain.Account;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bdth.traceplus.domain.BaseResult;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author weiming.zhu
 * @since 2019-06-12
 */
public interface AccountService extends IService<Account> {

    BaseResult regist(Account account, String systemPassword);

    BaseResult login(HttpServletRequest request, Account account);

    BaseResult logout(HttpServletRequest request);

    BaseResult info(Account account);

    BaseResult updateAccount(Account account, String newPassword);
}
