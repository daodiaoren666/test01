package org.example.lulu.exception;

import com.alibaba.fastjson.JSON;
import org.example.lulu.common.R;
import org.example.lulu.util.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        R r = new R(HttpStatus.UNAUTHORIZED.value(), "用户权限不足");
        String json= JSON.toJSONString(r);
        WebUtils.renderString(httpServletResponse,json);
    }
}
