package org.example.lulu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.lulu.common.PageResult;
import org.example.lulu.common.QueryPageBean;
import org.example.lulu.common.R;
import org.example.lulu.entity.Class;
import org.example.lulu.mapper.ClassMapper;
import org.example.lulu.service.IClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2023-03-28
 */
@Service
public class ClassServiceImpl extends ServiceImpl<ClassMapper, Class> implements IClassService {
    @Autowired
    private ClassMapper classMapper;

    @Override
    public PageResult pageQuery(QueryPageBean queryPageBean) {

        IPage<Class> classPage = new Page<>(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        PageResult pageResult = new PageResult();
        LambdaQueryWrapper<Class> classLambdaQueryWrapper = new LambdaQueryWrapper<>();
        if(queryPageBean.getQueryString()!=null) {
            classLambdaQueryWrapper.like(Class::getClassName, queryPageBean.getQueryString());
        }
        classMapper.selectPage(classPage,classLambdaQueryWrapper);
        pageResult.setRows(classPage.getRecords());
        pageResult.setTotal(classPage.getTotal());
        R.success("查询成功");
     return pageResult;
    }

}
