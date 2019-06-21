package com.longder.shopping.service;

import com.longder.shopping.entity.po.SysRole;
import com.longder.shopping.entity.po.SysUser;

import java.util.List;

/**
 * 用户管理业务
 */
public interface UserManageService {
    /**
     * 保存一个用户，新增和修改都可用
     *
     * @param sysUser
     * @param role
     */
    void saveOneUser(SysUser sysUser, SysRole role);


    /**
     * 检查登录名
     * @param loginName
     * @return true:可以注册  false：不能注册
     */
    Boolean checkLoginName(String loginName);

    /**
     * 用户列表数据
     * @return
     */
    List<SysUser> listUsers();

    /**
     * 查询获取一个用户
     * @param userId
     * @return
     */
    SysUser getOneUser(Long userId);

    /**
     * 删除一个用户
     * @param userId
     */
    void deleteOneUser(Long userId);


    /**
     * 检查某用户是否可以申请会员
     * @param sysUser
     * @return true:可以  false:不可以
     */
    boolean canApplyMember(SysUser sysUser);

    /**
     * 申请会员
     * @param userId
     * @return
     */
    boolean applyMember(Long userId);
}
