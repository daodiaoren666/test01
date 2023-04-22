package org.example.lulu.util;

import org.example.lulu.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class  AutionUtis {
    @Autowired
            private  MenuMapper menuMapper;
     List<String> list=new ArrayList<>();
    //根据用户查询权限信息
     String[] menuid;
    public  List<String> getMenuList(Long managementId) {
        String str = menuMapper.selectRoleMenuList(managementId);
        menuid = str.split(",");
        for (int i = 0; i < menuid.length; i++) {
            String gg = menuMapper.selectPermsByUserId(Long.valueOf(menuid[i])).toString();
            gg = gg.substring(1, gg.length() - 1);
            list.add(gg);
        }
        return list;
    }
}
