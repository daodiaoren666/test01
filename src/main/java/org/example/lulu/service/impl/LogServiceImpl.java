package org.example.lulu.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.lulu.common.PageResult;
import org.example.lulu.common.QueryPageBean;
import org.example.lulu.common.R;
import org.example.lulu.entity.Log;
import org.example.lulu.mapper.LogMapper;
import org.example.lulu.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 露露
 * @since 2023-03-22
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {
    @Autowired
    private LogMapper logMapper;
    @Override
    public PageResult pageQuery(QueryPageBean queryPageBean) {
        PageResult pageResult = new PageResult();
        IPage<Log> page = new Page<>(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        logMapper.selectPage(page,null);
        pageResult.setRows(page.getRecords());
        pageResult.setTotal(page.getTotal());
        R.success("查询成功");
        return pageResult;
    }
}
