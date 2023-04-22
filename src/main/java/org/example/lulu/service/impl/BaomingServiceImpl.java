package org.example.lulu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.lulu.common.PageResult;
import org.example.lulu.common.QueryPageBean;
import org.example.lulu.common.R;
import org.example.lulu.entity.Baoming;
import org.example.lulu.mapper.BaomingMapper;
import org.example.lulu.service.IBaomingService;
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
public class BaomingServiceImpl extends ServiceImpl<BaomingMapper, Baoming> implements IBaomingService {

    @Autowired
    private  BaomingMapper baomingMapper;
    @Override
    public PageResult pageQuery(QueryPageBean queryPageBean) {
        IPage<Baoming> Page = new Page<>(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        PageResult pageResult = new PageResult();
        LambdaQueryWrapper<Baoming> wrapper = new LambdaQueryWrapper<>();
        wrapper.ne(Baoming::getSignupState,1);
        baomingMapper.selectPage(Page,wrapper);
        pageResult.setRows(Page.getRecords());
        pageResult.setTotal(Page.getTotal());
        R.success("查询成功");
        return pageResult;
    }

    @Override
    public Baoming getBaomingById(Integer index) {
        Baoming baoming = new Baoming();
        baoming=baomingMapper.selectById(index);
        return baoming;
    }
    /**'
     * 修改报名信息
     * @param baoming
     * @return
     */

    @Override
    public R<String> updateApply(Baoming baoming) {
        baomingMapper.updateById(baoming);
        return R.success("修改成功");
    }




    }


