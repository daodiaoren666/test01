package org.example.lulu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.example.lulu.entity.LoginUser;
import org.example.lulu.entity.Management;
import org.example.lulu.mapper.EmployeeMapper;
import org.example.lulu.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 从数据库中查询user
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    public String[] menuid;
    public List<String> list=new ArrayList<>();
    @Autowired
    private EmployeeMapper employeeMapper;
@Autowired
private MenuMapper menuMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<Management> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Management::getManagementName,username);
              Management management = employeeMapper.selectOne(wrapper);
              if(Objects.isNull(management)){
                  throw new RuntimeException("用户名或者密码错误");
              }
              //根据用户查询权限信息
           String str=menuMapper.selectRoleMenuList(management.getManagementId());
           menuid=str.split(",");
        for (int i = 0; i <menuid.length ; i++) {
            String gg=menuMapper.selectPermsByUserId(Long.valueOf(menuid[i])).toString();
            gg=gg.substring(1,gg.length()-1);
            list.add(gg);
        }
              return new LoginUser(management,list);
    }
}
