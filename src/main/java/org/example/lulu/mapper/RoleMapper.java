package org.example.lulu.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.lulu.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2023-04-16
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    List<String> selectRoleByUserId(Long id);

    void updateMenuListById(@Param("roleList") String roleList,@Param("l") long userid);
}
