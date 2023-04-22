package org.example.lulu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.lulu.entity.Menu;
import org.example.lulu.mapper.MenuMapper;
import org.example.lulu.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Autowired
    private MenuMapper menuMapper;
    @Override
    public List<Menu> selectRouterMenuByUserId(Long id) {

             List<Menu> menus=menuMapper.selectRouterMenuByUserId(id);
        //构建tree
        //先找出第一层的菜单  然后去找他们的子菜单设置到children属性中
        List<Menu> menuTree = builderMenuTree(menus,0L);
        return menuTree;
    }
    private List<Menu> builderMenuTree(List<Menu> menus,Long parenId){
       List<Menu> menuTree=menus.stream().
               filter(menu->menu.getParentId().equals(parenId))
               .map(menu ->menu.setChildren(gettChildren(menu,menus)))
               .collect(Collectors.toList());
        return menuTree;
        }

    private List<Menu> gettChildren(Menu menu, List<Menu> menus) {
        List<Menu> ChildrenList =menus.stream().
                filter(m->m.getParentId().equals(menu.getId()))
                .map(m ->m.setChildren(gettChildren(m,menus)))
                .collect(Collectors.toList());
        return ChildrenList;
    }
}
