package org.example.lulu.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.lulu.common.PageResult;
import org.example.lulu.common.QueryPageBean;
import org.example.lulu.entity.Daili;
import org.example.lulu.mapper.DailiMapper;
import org.example.lulu.service.IDailiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2023-04-02
 */
@Service
public class DailiServiceImpl extends ServiceImpl<DailiMapper, Daili> implements IDailiService {
     @Autowired
     DailiMapper dailiMapper;
    @Override
    public PageResult getdailiAll(QueryPageBean queryPageBean) {
        PageResult pageResult = new PageResult();
        IPage<Daili> page = new Page<>(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        dailiMapper.selectPage(page,null);
        pageResult.setTotal(page.getTotal());
        pageResult.setRows(page.getRecords());
        return pageResult;
    }
}
