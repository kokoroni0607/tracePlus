package com.bdth.traceplus.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bdth.traceplus.domain.Account;
import com.bdth.traceplus.domain.BaseResult;
import com.bdth.traceplus.excpetion.ExceptionEnum;
import com.bdth.traceplus.excpetion.TraceException;
import com.bdth.traceplus.mapper.AccountMapper;
import com.bdth.traceplus.service.AccountService;
import com.bdth.traceplus.util.BCSha3Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author weiming.zhu
 * @since 2019-06-12
 */
@Service
@Slf4j
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {
    @Autowired
    private AccountMapper accountMapper;

    /**
     * 使用系统管理员权限注册用户
     *
     * @param account
     * @param systemPassword
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public BaseResult regist(Account account, String systemPassword) {
        if (StringUtils.isEmpty(systemPassword) || !"bdthSY@2019".equals(systemPassword)) {
            return BaseResult.failed("系统管理密码不正确");
        }
        try {
            account.setAccountPassword(BCSha3Utils.encrypt("shake-128", account.getAccountPassword()))
                    .setAccountRole("1");
            int insert = accountMapper.insert(account);
            return insert > 0 ? BaseResult.success(true) : BaseResult.failed(false);
        } catch (Exception e) {
            log.warn("insert account failed");
            throw new TraceException(ExceptionEnum.UNKNOWN_ERROR);
        }
    }

    /**
     * 登录
     *
     * @param request
     * @param account
     * @return
     */
    @Override
    public BaseResult login(HttpServletRequest request, Account account) {
        if (account == null || account.getAccountPassword() == null) {
            return BaseResult.failed("用户信息不正确,需要填写密码");
        }
        Account exists;
        if (!StringUtils.isEmpty(account.getAccountName())) {
            if (Pattern.compile("^1[0-9]+").matcher(account.getAccountName()).find()) {
                exists = accountMapper.selectOne(Wrappers.<Account>lambdaQuery()
                        .eq(Account::getAccountPhone, account.getAccountName()));
            } else {
                exists = accountMapper.selectOne(Wrappers.<Account>lambdaQuery()
                        .eq(Account::getAccountName, account.getAccountName()));
            }
        } else {
            return BaseResult.failed("用户信息不正确，需要填写用户名或手机号");
        }
        if (exists == null || exists.getAccountId() == null || exists.getAccountId() < 1) {
            throw new TraceException(ExceptionEnum.ACCOUNT_ERROR);
        }
        if (BCSha3Utils.encrypt("shake-128", account.getAccountPassword()).equals(exists.getAccountPassword())) {
            exists.setAccountPassword("");
            request.getSession().setAttribute("account", exists);
            return BaseResult.success(exists);
        } else {
            return BaseResult.detail("密码不正确", 201, false);
        }
    }

    /**
     * 退出
     *
     * @param request
     * @return
     */
    @Override
    public BaseResult logout(HttpServletRequest request) {
        Account login = (Account) request.getSession().getAttribute("account");
        if (login != null) {
            request.getSession().removeAttribute("account");
            return BaseResult.success(true);
        } else {
            return BaseResult.failed(false);
        }
    }

    /**
     * 用户信息
     *
     * @param account
     * @return
     */
    @Override
    public BaseResult info(Account account) {
        Account login = accountMapper.selectById(account.getAccountId());
        // 该接口不输出密码
        if (login != null) {
            login.setAccountPassword("");
            return BaseResult.success(login);
        } else {
            return BaseResult.failed(false);
        }
    }

    @Override
    public BaseResult updateAccount(Account account, String newPassword) {
        if (account == null || account.getAccountId() == null || account.getAccountId() < 1) {
            return BaseResult.failed("用户信息不正确");
        }
        Account login = accountMapper.selectById(account.getAccountId());
        if (login != null && !login.getAccountPassword().equals(account.getAccountPassword())) {
            login.setAccountPassword(BCSha3Utils.encrypt("shake-128", newPassword));
            int update = accountMapper.updateById(login);
            return update > 0 ? BaseResult.success(true) :
                    BaseResult.detail("新密码不能与旧密码一致", 201, false);
        } else {
            return BaseResult.detail("原密码错误", 201, false);
        }
    }
}
