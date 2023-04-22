package org.example.lulu.exception;

import com.alibaba.fastjson.JSON;
import org.example.lulu.common.R;
import org.example.lulu.util.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest Request, HttpServletResponse Response, AuthenticationException e) throws IOException, ServletException {
        R r = new R(HttpStatus.FORBIDDEN.value(), "用户认证失败");
        String json= JSON.toJSONString(r);
        WebUtils.renderString(Response,json);
    }
}
