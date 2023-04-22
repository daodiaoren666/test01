package org.example.lulu;

import org.example.lulu.mapper.MenuMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestErr {
    @Autowired
    private MenuMapper menuMapper;
    public String[] menuid;
   @Test
    public void testById(){
        System.out.println(menuMapper.selectPermsByUserId(7L));
    }

 @Test
    public void tt(){
     String str= menuMapper.selectRoleMenuList(1L);
     menuid=str.split(",");
     for (int i = 0; i <menuid.length ; i++) {
         System.out.println(menuid[i]);
     }
 }

}