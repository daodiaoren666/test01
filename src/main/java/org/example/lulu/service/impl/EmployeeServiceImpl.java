package org.example.lulu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.lulu.common.R;
import org.example.lulu.entity.LoginUser;
import org.example.lulu.entity.Management;
import org.example.lulu.mapper.EmployeeMapper;
import org.example.lulu.service.EmployeeService;
import org.example.lulu.util.JwtUtil;
import org.example.lulu.util.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Management> implements EmployeeService {
@Autowired
private EmployeeMapper employeeMapper;
@Autowired
private RedisCache redisCache;
@Autowired
private AuthenticationManager authenticationManager;

    /**
     * 登录
     * @param management
     * @return
     */
    @Override
    public R login(Management management) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(management.getManagementName(),management.getManagementPassword());
        Authentication authentication=authenticationManager.authenticate(token);
        if(Objects.isNull(authentication)){
            throw  new RuntimeException("用户名或者密码错误");
        }
//       使用userid生成token
        LoginUser loginUser=(LoginUser) authentication.getPrincipal();
        String userid= String.valueOf(loginUser.getManagement().getManagementId());
        String jwt = JwtUtil.createJWT(userid);
        //用户信息存入redis中
        redisCache.setCacheObject("login:"+userid,loginUser);
        //将token响应给前端
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("token",jwt);
        hashMap.put("userId",(((LoginUser) authentication.getPrincipal()).getManagement().getManagementId()));
        hashMap.put("userName",((LoginUser) authentication.getPrincipal()).getUsername());
        return  R.success(200,"登录成功",hashMap);
    }
    public R logout(){
      Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userid = loginUser.getManagement().getManagementId();
        redisCache.deleteObject("login:"+userid);
          return R.success(200,"退出成功");
    }


}
