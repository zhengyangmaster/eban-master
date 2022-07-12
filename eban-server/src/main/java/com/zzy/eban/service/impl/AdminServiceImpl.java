package com.zzy.eban.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzy.eban.config.security.JwtTokenUtils;
import com.zzy.eban.mapper.AdminMapper;
import com.zzy.eban.mapper.AdminRoleMapper;
import com.zzy.eban.mapper.RoleMapper;
import com.zzy.eban.pojo.Admin;
import com.zzy.eban.pojo.AdminRole;
import com.zzy.eban.pojo.RespBean;
import com.zzy.eban.pojo.Role;
import com.zzy.eban.service.IAdminService;
import com.zzy.eban.utils.AdminUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zzy
 * @since 2022-02-21
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    AdminMapper adminMapper;

    @Autowired
    private AdminRoleMapper adminRoleMapper;

    @Autowired
    JwtTokenUtils jwtTokenUtils;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    /**
     * @description: 登录之后返回token
     * @author: zzy
     * @data: 2022/2/22 16:49
     */
    @Override
    public RespBean login(String userName, String passWord,String code, HttpServletRequest request) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(userName);

        String captcha = (String) request.getSession().getAttribute("captcha");
        if (StringUtils.isEmpty(captcha)||!captcha.equalsIgnoreCase(code)){
           return RespBean.error("验证码输入错误，请重新输入！");

        }

        //登录
        if (null == userDetails || !passwordEncoder.matches(passWord, userDetails.getPassword())) {
            return RespBean.error("用户名密码不正确");
        }
        if (!userDetails.isEnabled()) {
            return RespBean.error("账号被禁用");
        }

        //更新security登录用户对象
        UsernamePasswordAuthenticationToken authenticationToken= new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //生成token
        String token = jwtTokenUtils.generateToken(userDetails);
        Map<String, String> map = new HashMap<>();
        map.put("token",token);
        map.put("tokenHead",tokenHead);

        return RespBean.success("登录成功",map);
    }
/**
 * @description: 根据用户id查询角色列表
 * @author: zzy
 * @data: 2022/2/25 10:55
 */
    @Override
    public List<Role> getRoles(Integer adminId) {
       return roleMapper.getRoles(adminId);

    }
/**
 * @description: 获取所有操作员
 * @author: zzy
 * @data: 2022/3/20 19:55
 */
    @Override
    public List<Admin> getAllAdmins(String keywords) {

        return adminMapper.getAllAdmins(AdminUtils.getCurrentAdmin().getId(),keywords);
    }

    @Override
    @Transactional
    public RespBean updateAdminRole(Integer adminId, Integer[] rids) {

        adminRoleMapper.delete(new QueryWrapper<AdminRole>().eq("adminId",adminId));

        Integer result = adminRoleMapper.updateAdminRole(adminId, rids);
        if(rids.length==result){
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }

    @Override
    public RespBean updateAdminPassword(String oldPass, String pass, Integer adminId) {
        Admin admin = adminMapper.selectById(adminId);
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        //判断旧密码是否一致
        if (encoder.matches(admin.getPassword(),admin.getPassword())){
        admin.setPassword(encoder.encode(pass));
            int record = adminMapper.updateById(admin);
            if (1==record){
                return RespBean.success("更新成功");
            }

        }
        return RespBean.error("更新失败");
    }

    @Override
    public RespBean updateUserFace(String url, Integer id, Authentication authentication) {
        Admin admin = adminMapper.selectById(id);
        admin.setUserFace(url);
        int result = adminMapper.updateById(admin);
        if (1==result){
            Admin principal = (Admin) authentication.getPrincipal();
            principal.setUserFace(url);
            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(admin,null,authentication.getAuthorities()));
            return RespBean.success("头像更新成功",url);
        }
       return RespBean.error("头像更新失败，请联系管理员");

    }


    @Override
    public Admin getAdminByUserName(String name) {
       return adminMapper.selectOne(new QueryWrapper<Admin>().eq("username",name).eq("enabled",true));


    }



}
