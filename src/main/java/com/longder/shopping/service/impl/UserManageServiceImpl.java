package com.longder.shopping.service.impl;

import com.longder.shopping.entity.po.SysRole;
import com.longder.shopping.entity.po.SysUser;
import com.longder.shopping.entity.po.SysUserRole;
import com.longder.shopping.repository.SysUserRepository;
import com.longder.shopping.repository.SysUserRoleRepository;
import com.longder.shopping.service.UserManageService;
import com.longder.shopping.util.EncryptionUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;


@Service
public class UserManageServiceImpl implements UserManageService {
    @Resource
    private SysUserRepository sysUserRepository;
    @Resource
    private SysUserRoleRepository sysUserRoleRepository;
    /**
     * 默认密码
     */
    @Value("${system.default-password}")
    private String defaultPassword;

    /**
     * 保存一个用户，新增和修改都可用
     *
     * @param sysUser
     * @param role
     */
    @Override
    @Transactional
    public void saveOneUser(SysUser sysUser, SysRole role) {
        if (ObjectUtils.isEmpty(sysUser.getId())) {//空的 新增
            //处理下密码
            if (ObjectUtils.isEmpty(sysUser.getPassword())) {//空密码，就给个默认密码
                sysUser.setPassword(EncryptionUtil.encrypt(defaultPassword));
            } else {
                sysUser.setPassword(EncryptionUtil.encrypt(sysUser.getPassword().trim()));
            }
            sysUser.setMember(false);
            sysUser.setCreateDate(LocalDate.now());
            sysUserRepository.save(sysUser);
            SysUserRole userRole = new SysUserRole(sysUser, role);
            sysUserRoleRepository.save(userRole);
        } else {//修改
            //不修改密码和登录名和角色，只能修改姓名和手机号
            SysUser dbUser = sysUserRepository.getOne(sysUser.getId());
            dbUser.setName(sysUser.getName());
            dbUser.setPhone(sysUser.getPhone());
            sysUserRepository.save(dbUser);
        }
    }

    /**
     * 检查登录名
     *
     * @param loginName
     * @return true:可以注册  false：不能注册
     */
    @Override
    public Boolean checkLoginName(String loginName) {
        SysUser sysUser = sysUserRepository.getByLoginName(loginName);
        return ObjectUtils.isEmpty(sysUser);
    }

    /**
     * 用户列表数据
     *
     * @return
     */
    @Override
    public List<SysUser> listUsers() {
        List<SysUser> userList = sysUserRepository.listCommonUser();
        //把单个角色塞进去
        userList.forEach(sysUser -> sysUser.setRole(sysUser.getRoles().get(0).getRole()));
        return userList;
    }

    /**
     * 查询获取一个用户
     *
     * @param userId
     * @return
     */
    @Override
    public SysUser getOneUser(Long userId) {
        return sysUserRepository.getOne(userId);
    }

    /**
     * 删除一个用户
     *
     * @param userId
     */
    @Override
    @Transactional
    public void deleteOneUser(Long userId) {
        sysUserRepository.deleteById(userId);
    }

    /**
     * 检查某用户是否可以申请会员
     * 注册时间大于2年才可以
     * @param sysUser
     * @return true:可以  false:不可以
     */
    @Override
    public boolean canApplyMember(SysUser sysUser) {
        LocalDate createDate = sysUser.getCreateDate();
        LocalDate now = LocalDate.now();
        return Period.between(createDate, now).getYears() >= 2;
    }

    /**
     * 申请会员
     *
     * @param userId
     * @return
     */
    @Override
    @Transactional
    public boolean applyMember(Long userId) {
        SysUser sysUser = sysUserRepository.getOne(userId);
        if(!canApplyMember(sysUser)){
            return false;
        }else{
            sysUser.setMember(true);
            sysUserRepository.save(sysUser);
            return true;
        }
    }
}
