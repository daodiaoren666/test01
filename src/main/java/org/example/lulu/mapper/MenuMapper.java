package org.example.lulu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.lulu.entity.Menu;

import java.util.List;
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
     List<String> selectPermsByUserId(Long id);

     List<Menu> selectRouterMenuByUserId(Long id);

    List<Integer> selectRoutersAllId(Long id);

    String selectRoleMenuList(long managementId);
}
