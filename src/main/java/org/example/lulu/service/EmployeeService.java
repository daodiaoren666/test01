package org.example.lulu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.lulu.common.R;
import org.example.lulu.entity.Management;

public interface EmployeeService extends IService<Management> {
    public R login(Management management);
    public R logout();
}
