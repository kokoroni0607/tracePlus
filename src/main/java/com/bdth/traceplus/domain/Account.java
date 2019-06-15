package com.bdth.traceplus.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author weiming.zhu
 * @since 2019-06-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("account")
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "account_id", type = IdType.AUTO)
    private Integer accountId;

    /**
     * 用户姓名
     */
    private String accountName;

    /**
     * 用户手机号
     */
    private String accountPhone;

    /**
     * 用户密码
     */
    private String accountPassword;

    /**
     * 用户角色 1商户管理员 2普通用户 3打包员
     */
    private String accountRole;

    /**
     * 用户公司id
     */
    private Integer accountManufId;

    /**
     * 用户真实姓名
     */
    @TableField("account_realName")
    private String accountRealname;

    /**
     * 身份证号
     */
    @TableField("account_idNumber")
    private String accountIdnumber;

    /**
     * 身份证图像
     */
    private String accountImg;

    /**
     * 用户备注
     */
    private String accountRemark;

    private Integer createBy;

    private LocalDateTime createDate;

    /**
     * 0未提交审核 1已提交审核 2审核通过 3审核不通过
     */
    private String status;


}
