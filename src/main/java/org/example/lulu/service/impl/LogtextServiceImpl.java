package org.example.lulu.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.lulu.common.PageResult;
import org.example.lulu.common.QueryPageBean;
import org.example.lulu.common.R;
import org.example.lulu.entity.Log;
import org.example.lulu.entity.Logtext;
import org.example.lulu.mapper.LogMapper;
import org.example.lulu.mapper.LogtextMapper;
import org.example.lulu.service.ILogtextService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2023-04-05
 */
@Service
public class LogtextServiceImpl extends ServiceImpl<LogtextMapper, Logtext> implements ILogtextService {
    @Autowired
    private LogtextMapper logtextMapper;
    @Override
    public PageResult pageQuery(QueryPageBean queryPageBean) {
        PageResult pageResult = new PageResult();
        IPage<Logtext> page = new Page<>(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        logtextMapper.selectPage(page,null);
        pageResult.setRows(page.getRecords());
        pageResult.setTotal(page.getTotal());
        R.success("查询成功");
        return pageResult;

    }

}
