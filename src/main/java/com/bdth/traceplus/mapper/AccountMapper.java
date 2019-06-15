package com.bdth.traceplus.mapper;

import com.bdth.traceplus.domain.Account;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author weiming.zhu
 * @since 2019-06-12
 */
@Mapper
@Repository
public interface AccountMapper extends BaseMapper<Account> {

}
