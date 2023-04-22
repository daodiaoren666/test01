package org.example.lulu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.lulu.entity.Management;
import org.example.lulu.mapper.EmployeeMapper;
import org.example.lulu.common.PageResult;
import org.example.lulu.common.QueryPageBean;
import org.example.lulu.common.R;
import org.example.lulu.service.toolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class toolServiceImpl  implements toolService {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Override
    public PageResult pageQuery(QueryPageBean queryPageBean) {
        PageResult pageResult = new PageResult();
        Integer CurrentPage=queryPageBean.getCurrentPage();//当前页码
        Integer pageSize = queryPageBean.getPageSize();//每页显示数
        String queryString=queryPageBean.getQueryString();//查询条件
        IPage<Management> page=new Page<>(CurrentPage,pageSize);
        LambdaQueryWrapper<Management> queryWrapper = new LambdaQueryWrapper<>();
        if(queryString!="null") {
            queryWrapper.like(Management::getManagementName, queryString);
        }
        employeeMapper.selectPage(page,queryWrapper);
        pageResult.setRows(page.getRecords());
        pageResult.setTotal(page.getTotal());
        R.success("查询成功");
        return pageResult;
    }
}
