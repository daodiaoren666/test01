package org.example.lulu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.lulu.entity.Menu;

import java.util.List;

public interface IMenuService extends IService<Menu> {

    List<Menu> selectRouterMenuByUserId(Long id);
}
